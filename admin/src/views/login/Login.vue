<template>
  <div class="food-img">
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="80px" class="login-box">
      <div class="title">管 理 员 登 录</div>
    <el-form-item label="用户名" prop="pass" class="form-item">
      <el-input type="text"  prefix-icon="el-icon-user" placeholder="请输入用户名" clearable v-model="ruleForm.username" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="checkPass" class="form-item">
      <el-input type="password"  prefix-icon="el-icon-lock" placeholder="请输入密码" clearable v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>
      <el-button type="warning" class="login-btn" @click="submitForm('ruleForm')">登 录</el-button>
      <el-button class="reset-btn" @click="resetForm('ruleForm')">重 置</el-button>
  </el-form>

    <el-dialog :visible.sync="dialogVisible" width="30%" >
      <span>请输入用户名和密码</span>
      <span slot="footer" class="dialog-footer">
          <el-button type="warning" size="small" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<script>
  import jwtDecode from 'jwt-decode'
  import {mapMutations} from 'vuex'
  import {Dialog, Notification, MessageBox} from "element-ui"

  export default {
    name: "Login",
    data() {
      return {
        ruleForm: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            {required: true, message: '用户名不可为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不可为空', trigger: 'blur'}
          ]
        },
        // 对话框显示和隐藏
        dialogVisible: false
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$syspost('/admin/login', {
              username: this.ruleForm.username,
              password: this.ruleForm.password
            }).then((r) => {
              // console.log(r.data)
              // 保存用户信息
              this.saveLoginData(r.data)
              // 前往首页
              this.$router.push('/index')
            }).catch((err) => {
              // console.log(err)
              // 报错
              Notification.error({
                title: '系统提示',
                message: '账号或密码错误'
              })
              // 清空用户信息
              this.$db.clear()
            })
          } else {
            this.dialogVisible = true;
            return false;
          }
        });
      },
      ...mapMutations({
        setToken: 'account/setToken',
        setExpireTime: 'account/setExpireTime',
        setPermissions: 'account/setPermissions',
        setRoles: 'account/setRoles',
        setUser: 'account/setUser',
        setId: 'account/setId',
        setInfo: 'account/setInfo'
      }),
      saveLoginData (data) {
        const decode = jwtDecode(data.data);
        // console.log(decode);

        this.setToken(data.data)
        this.setExpireTime(decode.expire_time)
        this.setUser(decode.username)
        this.setPermissions(decode.permission)
        this.setRoles(decode.roles)
        this.setId(decode.id)
        this.setInfo(data.info)
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    }
  }
</script>

<style scoped>
  .food-img{
    background-image: url("../images/orange.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    background-attachment: fixed;
    height:100%;
  }

  .login-box {
    border: 1px solid #DCDFE6;
    width: 25%;
    background: rgba(255,255,255,0.7);
    margin: auto auto 180px 35%;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
  }
.title{
  text-align: center;
  margin: 0 auto 30px auto;
  color: #E7B563;
  font-weight: bold;
}

  .form-item{
    margin:10px 0 20px 0;
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
  .t{
    color:rgba(0,0,0,0);
  }
</style>
