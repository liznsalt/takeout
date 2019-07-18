<template>
  <el-row style="margin-top: 50px">
    <el-col :span="10" :offset="6">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="店铺名称" prop="name">
          <el-input v-model="ruleForm.name" placeholder="请输入店铺名称"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="telephone">
          <el-input v-model="ruleForm.telephone" placeholder="请输入联系方式"></el-input>
        </el-form-item>
        <el-form-item label="店铺地址" prop="address">
          <el-input v-model="ruleForm.address" placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item label="店主姓名" prop="managerName">
          <el-input v-model="ruleForm.managerName" placeholder="请输入店主姓名"></el-input>
        </el-form-item>
        <el-form-item label="店主邮箱" prop="manageEmail">
          <el-input v-model="ruleForm.managerEmail" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="warning" @click="submitForm('ruleForm')" class="login-btn">修改</el-button>
          <el-button @click="resetForm('ruleForm')" class="reset-btn">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
  import {Notification} from "element-ui"

  export default {
    name: "SSetting",
    data() {
      return {
        oldData: {},
        ruleForm: {
          name: String,
          telephone: String,
          address: String,
          managerName: String,
          managerEmail: String
        },
        rules: {
          name: [
            { required: true, message: '请输入名字', trigger: 'blur' },
            { min: 1, max: 40, message: '长度在 1 到 40 个字符', trigger: 'blur' }
          ],
          telephone: [
            { required: true, message: '请输入手机号码', trigger: 'blur' },
            { min: 11, max: 11, message: '长度在必须是 11 个字符', trigger: 'blur' }
          ],
        }
      };
    },
    created() {
      this.oldData = JSON.parse(this.$db.get('USER_INFO'))
      this.dataReset()
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 先询问是否修改
            this.$confirm('是否确认修改个人信息?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.$get('/shop/editInfo', {
                SID: this.$db.get('USER_ID'),
                name: this.ruleForm.name,
                telephone: this.ruleForm.telephone,
                address: this.ruleForm.address,
                managerName: this.ruleForm.managerName,
                managerEmail: this.ruleForm.managerEmail,
              }).then(res => {
                this.$db.save('USER_INFO', res.data.data)
                this.oldData = JSON.parse(this.$db.get('USER_INFO'))
                this.dataReset()
                // 修改成功
                this.$message({
                  type: 'success',
                  message: '修改成功!'
                });
              }).catch(err => {
                console.log(err)
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消更改'
              });
              this.dataReset()
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.dataReset()
      },
      dataReset() {
        this.ruleForm = {
          name: this.oldData.username,
          telephone: this.oldData.telephone,
          address: this.oldData.address,
          managerName: this.oldData.managerName,
          managerEmail: this.oldData.managerEmail
        }
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
