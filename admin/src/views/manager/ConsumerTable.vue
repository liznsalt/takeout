<template>
  <div>
<!--    顶部工具-->
    <el-col :span="4">
      <el-input v-model="search" placeholder="按用户名字搜索"></el-input>
    </el-col>
    <el-col :span="4" :offset="16">
      <export-excel v-if="consumerList != null" :list="consumerList"
        :t-header="['#ID', '名字', '性别', '年龄', '电话', '邮箱', '地址']"
        :t-value="['cId', 'cName', 'cSex', 'cAge', 'cTel', 'cEmail', 'cAddress']"></export-excel>
    </el-col>

<!--    table main-->
    <el-table
      :data="dataInTable()"
      style="width: 100%">
      <el-table-column
        sortable
        fixed="left"
        prop="cId"
        label="#ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="cName"
        label="名字"
        width="180">
      </el-table-column>
      <el-table-column
        prop="cSex"
        label="性别"
        width="180"
        :filters="[{ text: '男', value: '男' }, { text: '女', value: '女' }, {text: '保密', value: null}]"
        :filter-method="filterSex"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
            :type="tagType(scope.row)"
            disable-transitions>{{scope.row.cSex === null ? '保密' : scope.row.cSex}}</el-tag>
        </template>>
      </el-table-column>
      <el-table-column
        sortable
        prop="cAge"
        label="年龄"
        width="180">
      </el-table-column>
      <el-table-column
        prop="cTel"
        label="电话"
        width="180">
      </el-table-column>
      <el-table-column
        prop="cEmail"
        label="邮箱"
        width="180">
      </el-table-column>
      <el-table-column
        prop="cAddress"
        label="地址"
        width="180">
      </el-table-column>
<!--      <el-table-column-->
<!--        sortable-->
<!--        prop="cBalance"-->
<!--        label="余额"-->
<!--        width="180">-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--        prop="cPassword"-->
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
      title="用户信息编辑"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form :model="dialogForm">
        <el-form-item label="用户名称" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.cName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户性别" :label-width="formLabelWidth">
          <el-select v-model="dialogForm.cSex" placeholder="请选择性别">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="用户年龄" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.cAge" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户电话" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.cTel" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户邮箱" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.cEmail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户地址" :label-width="formLabelWidth">
          <el-input v-model="dialogForm.cAddress" autocomplete="off"></el-input>
        </el-form-item>
<!--        <el-form-item label="用户余额" :label-width="formLabelWidth">-->
<!--          <el-input v-model="dialogForm.cBalance" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="用户密码" :label-width="formLabelWidth">-->
<!--          <el-input v-model="dialogForm.cPassword" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button class="reset-btn" @click="dialogVisible = false">取 消</el-button>
        <el-button type="danger" @click="handleDelete()">删 除</el-button>
        <el-button type="warning" class="login-btn" @click="submitConsumerForm()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import ExportExcel from "../../components/ExportExcel"
  export default {
    name: "ConsumerTable",
    components: {ExportExcel},
    data() {
      return {
        consumerList: [],
        search: '',

        dialogVisible: false,
        editIndex: Number,
        editRow: Object,
        dialogForm: {
          cId: '',
          cName: '',
          cSex: '',
          cAge: '',
          cTel: '',
          cEmail: '',
          cAddress: '',
          cBalance: '',
          cPassword: ''
        },
        formLabelWidth: '120px'
      }
    },
    created() {
      this.$get('/admin/queryAllConsumers').then(res => {
        this.consumerList = res.data.data
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
          this.$get('/admin/deleteConsumerByID', {
            CID: row.cId
          }).then(res => {
            this.$message({
              type: 'success',
              message: '删除成功',
              center: true
            })
            // 更新视图
            this.dialogVisible = false
            this.consumerList = this.consumerList.filter((x) => x.cId !== row.cId)
          }).catch(err => console.log(err))
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      // table数据
      dataInTable() {
        return this.consumerList.filter(data =>
            !this.search || data.cName.toLowerCase().includes(this.search.toLowerCase()))
      },
      // filter性别的函数
      filterSex(value, row) {
        return row.cSex === value;
      },
      tagType(row) {
        if (row.cSex === '男') {
          return 'primary'
        } else if (row.cSex === '女') {
          return 'success'
        } else {
          return 'warning'
        }
      },
      // 对话框
      handleEdit(index, row) {
        this.editIndex = index
        this.editRow = row
        this.dialogVisible = true
        this.dialogForm = {
          cId: row.cId,
          cName: row.cName,
          cSex: row.cSex,
          cAge: row.cAge,
          cTel: row.cTel,
          cEmail: row.cEmail,
          cAddress: row.cAddress,
          cBalance: row.cBalance,
          cPassword: row.cPassword
        }
      },
      handleClose(done) {
        this.$confirm('确认关闭，修改信息将丢失？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      submitConsumerForm() {
        this.$confirm('是否确认修改此项?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$get("/admin/updateConsumerByCidWithAllParams", {
            CID: this.dialogForm.cId,
            name: this.dialogForm.cName,
            sex: this.dialogForm.cSex,
            age: this.dialogForm.cAge,
            telephone: this.dialogForm.cTel,
            email: this.dialogForm.cEmail,
            address: this.dialogForm.cAddress,
            password: this.dialogForm.cPassword,
            balance: this.dialogForm.cBalance
          }).then(res => {
            this.$message({
              type: 'success',
              message: '修改成功',
              center: true
            })
            // 更新视图
            this.$set(this.consumerList, this.editIndex, this.dialogForm)
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
