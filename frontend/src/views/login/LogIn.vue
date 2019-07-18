<template>
  <div class="food-img">
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <el-radio-group v-model="radio" class="text">
        <el-radio :label="1">用户</el-radio>
        <el-radio :label="2">商家</el-radio>
      </el-radio-group>

      <el-form-item label="用户名" prop="username"  class="form-item">
        <el-input type="text" prefix-icon="el-icon-user" placeholder="请输入用户名" clearable v-model="form.username"/>
      </el-form-item>
      <el-form-item label="密码" prop="password" class="form-item">
        <el-input type="password" prefix-icon="el-icon-lock" placeholder="请输入密码" clearable v-model="form.password"/>
      </el-form-item>

      <el-row>
        <div class="code" @click="$router.push('/verify')">>>>手机验证码登录>>></div>
      </el-row>

      <el-row>
        <el-button type="warning" class="login-btn" @click="onSubmit('loginForm')">登 录</el-button>
        <el-button class="sign-btn" @click="$router.push('/signin')">注 册</el-button>
      </el-row>

      <el-row>
        <div class="code" style="text-align: right;color: #8c939d" @click="resetPassword">Forget password?</div>
      </el-row>

      <el-dialog :visible.sync="dialogVisible" width="30%" >
        <span>请输入用户名和密码</span>
        <span slot="footer" class="dialog-footer">
          <el-button type="warning" size="small" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>

    </el-form>
  </div>

</template>

<script>
  import jwtDecode from 'jwt-decode'
  import {mapMutations} from 'vuex'
  import {Dialog, Notification, MessageBox} from "element-ui"

  export default {
    name: "LogIn",
    data() {
      return {
        radio: 1,
        form: {
          username: '',
          password: ''
        },
        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
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
      }
    },
    methods: {
      onSubmit(formName) {
        // 为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            // this.$router.push("/index");
            let url = ''
            if (this.radio === 1) {
              url = 'consumer/login'
            } else if (this.radio === 2) {
              url = 'shop/login'
            }

            this.$syspost(url, {
              username: this.form.username,
              password: this.form.password
            }).then((r) => {
              // console.log(r.data)
              // 保存用户信息
              this.saveLoginData(r.data)
              // TODO: 返回前一页 ?
              // this.$router.go(-1)
              this.$router.push('/index')
              location.reload()
            }).catch((err) => {
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

        // console.log(this.$db.get('USER_TOKEN'))
        // console.log(this.$db.get('EXPIRE_TIME'))
        // console.log(this.$db.get('USER'))
        // console.log(this.$db.get('PERMISSIONS'))
        // console.log(this.$db.get('ROLES'))
      },
      /*
      resetForm(loginForm){
        this.dialogVisible = false;
        this.$refs[loginForm].resetFields();
        },
       */
      resetPassword() {
         if (this.radio === 1) {
           this.$router.push('/password_reset/consumer')
         } else {
           this.$router.push('/password_reset/shop')
         }
      }
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
    margin: auto auto 140px 35%;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
  }
  .text {
    text-align: center;
    margin: 0 auto 20px auto;
    color: #E7B563;
  }
  .code{
    color:rgb(64,158,255);
    text-align: center;
    cursor:pointer;
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
  .sign-btn{
    margin:10px 30px 10px 30px;
  }
  .sign-btn:hover{
    background: rgba(255,255,255,0.5);
    color:#000;
  }
.t{
  color:rgba(0,0,0,0);
}
</style>
