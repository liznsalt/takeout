<template>
  <el-row style="margin-top: 50px">
    <el-col :span="10" :offset="6">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="姓名" prop="name" placeholder="请输入姓名">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="ruleForm.sex">
            <el-radio label="男" value="男" style="margin-right: 100px;"></el-radio>
            <el-radio label="女" value="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="ruleForm.age"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="telephone">
          <el-input v-model="ruleForm.telephone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="ruleForm.address"></el-input>
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
    name: "Setting",
    data() {
      return {
        oldData: {},
        ruleForm: {
          name: '',
          options: [{
            value: '1',
            label: '男'
          }, {
            value: '2',
            label: '女'
          }],
          age: '',
          telephone: '',
          email: '',
          address: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入名字', trigger: 'blur' },
            { min: 1, max: 25, message: '长度在 1 到 25 个字符', trigger: 'blur' }
          ],
          telephone: [
            { min: 11, max: 11, message: '长度在必须为 11 位', trigger: 'blur' }
          ]
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
              this.$get('/consumer/editInfo', {
                CID: this.$db.get('USER_ID'),
                name: this.ruleForm.name,
                sex: this.ruleForm.sex,
                age: this.ruleForm.age,
                telephone: this.ruleForm.telephone,
                email: this.ruleForm.email,
                address: this.ruleForm.address
              }).then(res => {
                this.$db.save('USER_INFO', res.data.data)
                this.oldData = JSON.parse(this.$db.get('USER_INFO'))
                this.dataReset()
                // 修改成功
                this.$message({
                  type: 'success',
                  message: '修改成功!',
                  center: true
                });
              }).catch(err => {
                console.log(err)
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消更改',
                center: true
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
          sex: this.oldData.sex,
          age: this.oldData.age,
          telephone: this.oldData.telephone,
          email: this.oldData.email,
          address: this.oldData.address
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
