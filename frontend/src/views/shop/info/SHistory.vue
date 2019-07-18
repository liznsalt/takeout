<template>
  <el-table
    :data="dataInTable()"
    style="width: 100%"
    height="500"
    :row-class-name="tableRowClassName"
    :span-method="objectSpanMethod">
    <el-table-column
      sortable
      prop="time"
      label="购买时间"
      width="180">
    </el-table-column>
    <el-table-column
      prop="goodsName"
      label="商品名字"
      width="180">
      <template slot-scope="scope">
        <el-popover
          placement="top-start"
          width="200"
          trigger="hover">
          <el-image :src="scope.row.picture"></el-image>
          <el-button slot="reference" style="border: 0px">{{scope.row.goodsName}}</el-button>
        </el-popover>
      </template>
    </el-table-column>
    <el-table-column
      sortable
      prop="num"
      label="购买数量">
    </el-table-column>
    <el-table-column
      prop="consumerName"
      label="顾客名字">
    </el-table-column>
    <el-table-column
      sortable
      prop="total"
      label="总价">
    </el-table-column>
    <el-table-column
      prop="state"
      label="状态"
      :filters="[{ text: '已完成', value: '已完成' }, { text: '已取消', value: '已取消' },{ text: '未完成', value: '未完成' }, { text: '待评价', value: '待评价' }]"
      :filter-method="filterTag"
      filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag
          :type="tagType(scope.row)"
          disable-transitions>{{scope.row.state}}</el-tag>
      </template>
    </el-table-column>

    <!--        搜索和编辑-->
    <el-table-column
      align="right">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索"/>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    name: "SHistory",
    data() {
      return {
        history_list: [

      //     cid: 1
      // consumerName: "Zhang San"
      // gid: 3
      // goodsName: "炒面"
      // id: 4
      // num: 0
      // picture: null
      // price: 200
      // shopName: "mai"
      // sid: 1
      // state: "已完成"
      // time: "02:03:21"
      // total: 100
        ],

        search: '',

        spanArr: [],
        pos: 1
      }
    },
    created() {
      this.$get('/shop/getOrderHistory', {
        SID: this.$db.get('USER_ID')
      }).then(res => {
        this.history_list = res.data.data
        // 获取合并数据
        this.getSpanArr(res.data.data)
      }).catch(err => console.log(err))
    },
    methods: {
      filterTag(value, row) {
        return row.state === value;
      },

      dataInTable() {
        return this.history_list.filter(data => !this.search ||
          data.goodsName.toLowerCase().includes(this.search.toLowerCase()) ||
          data.consumerName.toLowerCase().includes(this.search.toLowerCase()))
      },
      tagType(row) {
        switch (row.state) {
          case '已完成' : return 'primary'
          case '未完成' : return 'danger'
          case '待评价' : return 'warning'
          case '已取消' : return 'info'
        }
      },
      tableRowClassName({row, rowIndex}) {
        // let set = this.dateSet
        // if (set.indexOf(row.time) % 2 === 0) {
        //   return 'warning-row';
        // } else {
        //   return 'success-row';
        // }
      },

      // span test 合并测试
      // 因为要合并的行数是不固定的，此函数是实现合并随意行数的功能
      getSpanArr (data) {
        console.log(data)//从后台获取的数据
        this.spanArr = []
        for (let i = 0; i < data.length; i++) {
          if (i === 0) {
            // 如果是第一条记录（即索引是0的时候），向数组中加入１
            this.spanArr.push(1)
            this.pos = 0
          } else {
            if (data[i].time === data[i - 1].time && data[i].consumerName === data[i - 1].consumerName) {
              // 如果time相等就累加，并且push 0
              this.spanArr[this.pos] += 1
              this.spanArr.push(0)
            } else {
              // 不相等push 1
              this.spanArr.push(1)
              this.pos = i
            }
          }
        }
        console.log(this.spanArr)
      },
      // 合并行数
      objectSpanMethod ({ row, column, rowIndex, columnIndex }) {
        // columnIndex === 0 找到第一列，实现合并随机出现的行数
        if (columnIndex === 0 || columnIndex === 3) {
          const _row = this.spanArr[rowIndex]
          const _col = _row > 0 ? 1 : 0
          return {
            rowspan: _row,
            colspan: _col
          }
        }
      }
    },
    computed: {
      dateSet() {
        let res = new Set();
        let list = this.dataInTable()
        for (let i = 0, len = list.length; i < len; i++) {
          res.add(list[i].time)
        }
        return Array.from(res)
      }
    }
  }
</script>

<style scoped>

</style>
