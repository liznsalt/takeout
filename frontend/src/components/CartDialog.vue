<template>
  <div>
    <el-table
      ref="multipleTable"
      :data="cartList"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      max-height="250">
<!--      <el-table-column-->
<!--        type="selection"-->
<!--        width="55"></el-table-column>-->
      <el-table-column prop="goodsName" label="商品" align="center">
        <template slot-scope="scope">
          <el-popover
            placement="top-start"
            width="100"
            trigger="hover">
            <el-image :src="scope.row.picture"></el-image>
            <el-button slot="reference" style="border: 0px">{{scope.row.goodsName}}</el-button>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="num" label="数量" align="center" width="160">
        <template scope="scope">
          <el-input-number v-model="scope.row.num" size="mini" :min="0" @change="handleChange(scope.row)"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="total" label="价格(￥)" align="center"></el-table-column>
    </el-table>
    <div style="margin-top: 20px">
      <el-popover placement="top" width="160"
                  v-model="visible">
        <p>确认购买吗？</p>
        <div style="text-align: right; margin: 0">
          <el-button size="mini" type="text" @click.native="visible = false">取消</el-button>
          <el-button type="primary" size="mini" @click.native="Buy">确定</el-button>
        </div>
        <el-button slot="reference" icon="el-icon-check" type="primary">购买</el-button>
      </el-popover>
      <el-button @click.native="Clear" type="warning" icon="el-icon-delete">清空</el-button>
<!--      <el-button @click.native="Clear" type="danger" style="text-align: right">清空购物</el-button>-->
    </div>
  </div>
</template>

<script>
  export default {
    name: "CartDialog",
    props: ['sid'],
    data() {
      return {
        cartList: [{
          cid: 1,
          gid: 3,
          goodsName: "炒面",
          num: 1,
          picture: "http://localhost:8080/takeout/upload/201907121000433.jpg",
          price: 200,
          shopName: "mai",
          sid: 1,
          total: 200
        }],
        multipleSelection: [],

        visible: false,

        num: 1
      }
    },
    created() {
      this.refreshData()
    },

    methods: {
      getCnt(gid) {
        for (let i = 0, len = this.cartList.length; i < len; i++) {
          if (this.cartList[i].gid === gid) {
            return this.cartList[i].num
          }
        }
        return 0
      },

      refreshData() {
        this.cartList = []
        this.$get('/consumer/getShoppingCartByCidSid', {
          CID: this.$db.get('USER_ID'),
          SID: this.sid
        }).then(res => {
          this.cartList = res.data.data
        }).catch(err => console.log(err))
      },

      // 多选
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },

      handleSelectionChange(val) {
        this.multipleSelection = val;
      },

      // 通信
      Add(GID) {

      },
      Sub(GID) {

      },

      async Buy() {
        if (this.cartList.length === 0) {
          this.$message({
            type: 'info',
            message: '购物车为空'
          })
          return
        }
        let date = new Date().Format('yyyy-MM-dd hh:mm:ss')
        for (let i = 0, len = this.cartList.length; i < len; i++) {
          let item = this.cartList[i]
          await this.$get('/ShoppingCart/confirmItemWithDate', {
            CID: this.$db.get('USER_ID'),
            GID: item.gid,
            now: date
          }).then(res => {

          }).catch(err => console.log(err))
        }
        this.$message({
          type: 'success',
          message: '购买成功'
        })
        this.$emit('Buy', this.cartList)
      },
      Delete() {
        // for (let cartItem of this.multipleSelection) {
        //   this.$get('/ShoppingCart/deleteItem', {
        //     CID: this.$db.get('USER_ID'),
        //     GID: cartItem.gid
        //   })
        // }
        // this.$emit('Delete', this.multipleSelection)

        // 更新视图

      },
      async Clear() {
        for (let i = 0, len = this.cartList.length; i < len; i++) {
          let item = this.cartList[i]
          await this.$get('/ShoppingCart/deleteItem', {
            CID: this.$db.get('USER_ID'),
            GID: item.gid
          })
        }
        this.$emit('Delete', this.cartList)
        this.cartList = []
      },

      handleChange(row) {
        // console.log(row)
        this.$get('/ShoppingCart/numChoose', {
          CID: this.$db.get('USER_ID'),
          GID: row.gid,
          num: row.num
        }).then(async res => {
          // 此购物没啦
          if (row.num === 0) {
            for (let i = 0, len = this.cartList.length; i < len; i++) {
              if (this.cartList[i].gid === row.gid) {
                await this.$get('/ShoppingCart/deleteItem', {
                  CID: this.$db.get('USER_ID'),
                  GID: row.gid
                }).then(res => {
                  this.cartList.splice(i, 1)
                  this.$emit('Delete', [row])
                }).catch(err => console.log(err))
                return
              }
            }
          }

          row.total = res.data.data.total
          // 更新父组件
          this.$emit('Change', row)
        }).catch(err => {
          console.log("来到这")
          // 还原数据
          this.$get('/ShoppingCart/queryById', {
            CID: this.$db.get('USER_ID'),
            GID: row.gid
          }).then(res => {
            row.num = res.data.data.num
          }).catch(err => console.log(err))
        })
      }
    }
  }
</script>

<style scoped>

</style>
