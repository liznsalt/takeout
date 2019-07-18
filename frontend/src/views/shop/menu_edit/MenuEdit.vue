<template>
  <el-row>
    <el-col :span="4" :offset="4">
      <el-button class="button-new-tag" size="small" @click="addNewMenu">新增菜单</el-button>
    </el-col>

<!--    main-->
    <el-col :span="14" :offset="5">
      <el-table
        :data="goodsList.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="商品 ID"><span>{{ props.row.id }}</span></el-form-item>
              <el-form-item label="商品评价"><span>{{ props.row.praise === 0 ? '暂无评价' : props.row.praise }}</span></el-form-item>
              <el-form-item label="商品名称"><span>{{ props.row.name }}</span></el-form-item>
              <el-form-item label="商品价格"><span>{{ props.row.price }}</span></el-form-item>
              <el-form-item label="商品库存"><span>{{ props.row.stock }}</span></el-form-item>
              <el-form-item label="商品类型"><span>{{ props.row.type === null ? '无类型' : props.row.type }}</span></el-form-item>
              <el-form-item label="商品描述"><span>{{ props.row.desc }}</span></el-form-item>
              <!-- 图片 -->
              <el-form-item label="商品图片">
                <div class="block">
                  <!-- 默认 -->
                  <el-image v-if="props.row.pictureUrl === '' || props.row.pictureUrl === null"
                    style="width: 100px; height: 100px" :src="require('@/assets/img/default.jpg')" fit="fill">
                  </el-image>
                  <!-- 商家添加的 -->
                  <el-image v-else style="width: 100px; height: 100px" :src="props.row.pictureUrl" fit="fill"></el-image>
                </div>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="名字" prop="name"></el-table-column>
        <el-table-column label="价格" prop="price" sortable></el-table-column>
        <el-table-column label="库存" prop="stock" sortable></el-table-column>
        <el-table-column label="类型" prop="type" :filter-method="filterHandler"
                         :filters="[{text: '小吃', value: '小吃'}, {text: '饮料', value: '饮料'}, {text: '主食', value: '主食'}]">
        </el-table-column>

<!--        搜索和编辑-->
        <el-table-column align="right">
          <template slot="header" slot-scope="scope">
            <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
          </template>
          <template slot-scope="scope">
            <el-button size="mini" class="button-new-tag" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>

<!--      新增和编辑 dialog-->
      <el-dialog title="菜单编辑" :visible.sync="dialogFormVisible" :before-close="handleClose" center>
        <el-form :model="form">
          <el-form-item label="商品名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="商品价格" :label-width="formLabelWidth">
            <el-input v-model="form.price" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="商品库存" :label-width="formLabelWidth">
            <el-input v-model="form.stock" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="商品类型" :label-width="formLabelWidth">
            <el-select v-model="form.type" placeholder="请选择商品类型">
              <el-option label="小吃" value="小吃"></el-option>
              <el-option label="饮料" value="饮料"></el-option>
              <el-option label="主食" value="主食"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商品描述" :label-width="formLabelWidth">
            <el-input v-model="form.desc" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="商品图片" :label-width="formLabelWidth">
            <!-- TODO: 上传图片 -->
            <UploadPicture
              v-if="dialogFormVisible"
              :upUrl="form.url"
              @getUrl="setFormUrl">
            </UploadPicture>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" class="reset-btn">取 消</el-button>
          <el-button type="warning" @click="submitChange" class="login-btn">确 定</el-button>
        </div>
      </el-dialog>
    </el-col>
  </el-row>
</template>

<script>
  import UploadPicture from '@/components/UploadPicture'
  import pictureUrl from '@/utils/pirctureUrl'

  export default {
    name: "MenuEdit",
    components: {
      UploadPicture
    },
    data() {
      return {
        goodsList: [],
        search: '',
        dialogFormVisible: false,
        form: {
          name: '',
          price: '',
          stock: '',
          type: '',
          desc: '',
          url: ''
        },
        formLabelWidth: '70px',

        addOpt: true,

        nowEditGid: Number,
        nowEditIndex: Number
      }
    },
    created() {
      this.$get('/shop/getAllGoodsBySid', {
        SID: this.$db.get('USER_ID')
      }).then(res => {
        this.goodsList = res.data.data
      }).catch(err => {
        console.log(err)
      })
    },
    methods: {
      handleEdit(index, row) {
        // 设置当前编辑的GID
        this.nowEditIndex = index
        this.nowEditGid = row.id
        //
        this.addOpt = false
        this.form = {
          name: row.name,
          price: row.price,
          stock: row.stock,
          type: row.type,
          desc: row.desc,
          url: row.pictureUrl
        }
        this.dialogFormVisible = true
      },
      /**
       * 删除菜单
       * @param index
       * @param row
       */
      handleDelete(index, row) {
        // console.log(row)
        this.$confirm('是否确认删除此菜单?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$post('/shop/deleteGoodsByGid', {
            GID: row.id
          }).then(() => {
            this.$message({
              type: 'info',
              message: '删除成功'
            })
            // 更新goodsList
            this.goodsList = this.goodsList.filter((x) => x.id !== row.id)
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      handleClose(done) {
        this.$confirm('你修改的信息将丢失，确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },

      /**
       * 提交表单
       * addOpt = true => 添加
       * addOpt = false => 修改
       */
      submitChange() {
        this.dialogFormVisible = false
        // TODO: 向后端提交表单
        if (this.addOpt) { // 添加商品
          console.log(this.form)
          this.$post('/shop/addGoods', {
            SID: Number(this.$db.get('USER_ID')),
            name: String(this.form.name),
            price: Number(this.form.price),
            stock: Number(this.form.stock),
            type: String(this.form.type),
            desc: this.form.desc,
            url: this.form.url
          }).then(res => {
            this.goodsList.push(res.data.data)
            this.$message({
              type: 'info',
              message: '已添加商品'
            })
          }).catch(err => {
            this.$message({
              type: 'error',
              message: '添加失败，请联系管理员'
            })
          })
        } else { // 修改商品
          this.$post('/shop/updateGoods', {
            GID: this.nowEditGid,
            name: this.form.name,
            price: this.form.price,
            stock: this.form.stock,
            type: this.form.type,
            desc: this.form.desc,
            url: this.form.url
          }).then(res => {
            this.$set(this.goodsList, this.nowEditIndex, res.data.data)
            this.$message({
              type: 'info',
              message: '修改成功'
            })
          }).catch(err => {
            this.$message({
              type: 'error',
              message: '修改失败，请联系管理员'
            })
          })
        }
      },

      addNewMenu() {
        // 此时处于添加操作状态
        this.addOpt = true
        this.form = {
          name: '',
          price: '',
          stock: '',
          type: '主食',
          desc: '',
          url: ''
        }

        this.dialogFormVisible = true
      },

      /**
       * 得到上传图片的url
       * @param u
       */
      setFormUrl(u) {
        this.form.url = u
        // console.log(this.form)
      }
    }
  }
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .button-new-tag:hover{
    color:#FFCD56;
    border-color: #FFCD56;
    background-color: rgba(255,205,86,0.1);
  }
  .button-new-tag:focus{
    color:#FFCD56;
    border-color: #FFCD56;
    background-color: rgba(255,205,86,0.1);
  }
  .login-btn {
    margin: 10px 30px 10px 30px;
  }
  .login-btn:hover{
    background: rgba(230,162,60,0.7);
  }
  .reset-btn{
    margin:10px 30px 10px 30px;
  }
  .reset-btn:hover{
    background: rgba(255,255,255,0.5);
    color:#000;
  }
</style>
