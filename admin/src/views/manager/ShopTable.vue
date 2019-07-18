<template>
  <div>
    <!--    顶部工具-->
    <el-col :span="4">
      <el-input v-model="search" placeholder="按店铺名字搜索"></el-input>
    </el-col>
    <el-col :span="4" :offset="16">
      <export-excel v-if="shopList != null" :list="shopList"
        :t-header="['#ID', '店铺名字', '店铺地址', '电话', '店主', '店主邮箱', '图片URL', '评价']"
        :t-value="['sId', 'sName', 'sAddress', 'sTel', 'managerName', 'managerEmail', 'sPictureUrl', 'score']"></export-excel>
    </el-col>

    <!--    table main-->
    <el-table
      :data="dataInTable()"
      style="width: 100%">
      <el-table-column
        sortable
        fixed="left"
        prop="sId"
        label="#ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="sName"
        label="店铺名字"
        width="180">
      </el-table-column>
      <el-table-column
        prop="sAddress"
        label="店铺地址"
        width="180">
      </el-table-column>
      <el-table-column
        prop="sTel"
        label="电话"
        width="180">
      </el-table-column>
      <el-table-column
        prop="managerName"
        label="店主"
        width="180">
      </el-table-column>
      <el-table-column
        prop="managerEmail"
        label="店主邮箱"
        width="180">
      </el-table-column>
      <el-table-column
        prop="sPictureUrl"
        label="图片URL"
        width="180">
      </el-table-column>
      <el-table-column
        sortable
        prop="score"
        label="评价"
        width="180">
      </el-table-column>
<!--      <el-table-column-->
<!--        prop="sPassword"-->
<!--        label="密码"-->
<!--        width="180">-->
<!--      </el-table-column>-->
      <el-table-column
        fixed="right"
        label="操作">
        <template slot-scope="scope">
          <el-button
            type="warning" icon="el-icon-edit" circle
            @click="handleEdit(scope.$index, scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>


    <!--    编辑信息的对话框-->
    <el-dialog
      title="商家信息编辑"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form :model="dialogForm">
        <el-form-item label="商家名称" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.sName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商家地址" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.sAddress" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商家电话" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.sTel" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="店主名字" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.managerName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="店主邮箱" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.managerEmail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商家评价" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.score" autocomplete="off"></el-input>
        </el-form-item>
<!--        <el-form-item label="商家密码" :label-width="formLabelWidth">-->
<!--          <el-input v-model="dialogForm.sPassword" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button class="reset-btn" @click="dialogVisible = false">取 消</el-button>
        <el-button type="danger" @click="handleDelete()">删 除</el-button>
        <el-button type="warning" class="login-btn" @click="submitShopForm()">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import ExportExcel from "../../components/ExportExcel"
  export default {
    name: "ShopTable",
    components: {ExportExcel},
    data() {
      return {
        shopList: [],
        search: '',

        dialogVisible: false,
        editIndex: Number,
        editRow: Object,
        dialogForm: {
          sId: '',
          sName: '',
          sAddress: '',
          sTel: '',
          managerName: '',
          managerEmail: '',
          sPictureUrl: '',
          score: '',
          sPassword: ''
        },
        formLabelWidth: '120px'
      }
    },
    created() {
      this.$get('/admin/queryAllShops').then(res => {
        this.shopList = res.data.data
      }).catch(err => console.log(err))
    },
    methods: {
      handleDelete() {
        let row = this.editRow
        this.$confirm('是否确认删除此项?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$get('/admin/deleteShopByID', {
            SID: row.sId
          }).then(res => {
            this.$message({
              type: 'success',
              message: '删除成功',
              center: true
            })
            // 更新视图
            this.shopList = this.shopList.filter((x) => x.sId !== row.sId)
            this.dialogVisible = false
          }).catch(err => console.log(err))
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      dataInTable() {
        return this.shopList.filter(data =>
          !this.search || data.sName.toLowerCase().includes(this.search.toLowerCase()))
      },

      // 对话框
      handleEdit(index, row) {
        this.editIndex = index
        this.editRow = row
        this.dialogVisible = true
        this.dialogForm = {
          sId: row.sId,
          sName: row.sName,
          sAddress: row.sAddress,
          sTel: row.sTel,
          managerName: row.managerName,
          managerEmail: row.managerEmail,
          sPictureUrl: row.sPictureUrl,
          score: row.score,
          sPassword: row.sPassword
        }
      },
      handleClose(done) {
        this.$confirm('确认关闭，修改信息将丢失？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      submitShopForm() {
        this.$confirm('是否确认修改此项?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$get("/admin/updateShopBySidWithAllParams", {
            SID: this.dialogForm.sId,
            name: this.dialogForm.sName,
            password: this.dialogForm.sPassword,
            telephone: this.dialogForm.sTel,
            address: this.dialogForm.sAddress,
            managerName: this.dialogForm.managerName,
            managerEmail: this.dialogForm.managerEmail,
            pictureUrl: this.dialogForm.sPictureUrl,
            score: this.dialogForm.score
          }).then(res => {
            this.$message({
              type: 'success',
              message: '修改成功',
              center: true
            })
            // 更新视图
            this.$set(this.shopList, this.editIndex, this.dialogForm)
            this.dialogVisible = false
          }).catch(err => console.log(err))
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          })
        })
      }
    }
  }
</script>

<style scoped>
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
