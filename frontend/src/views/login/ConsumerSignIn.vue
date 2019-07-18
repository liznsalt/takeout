<template>
  <div class="food-img">
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-row class="t">?</el-row>
    <el-form ref="signInForm" :model="form" :rules="rules" label-width="80px" class="signIn-box">
      <h3 class="signIn-title">用 户 注 册</h3>
      <el-form-item label="用户名" prop="username" class="form-item">
        <el-input type="text" prefix-icon="el-icon-user" placeholder="请输入用户名" clearable v-model="form.username"/>
      </el-form-item>
      <el-form-item label="密码" prop="password" class="form-item">
        <el-input type="password" prefix-icon="el-icon-lock" placeholder="请输入密码" clearable v-model="form.password"/>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPassword" class="form-item">
        <el-input type="password" prefix-icon="el-icon-lock" placeholder="请再次输入密码" clearable v-model="form.checkPassword"/>
      </el-form-item>
      <el-form-item label="手机号" prop="phone" class="form-item">
        <el-input type="text" prefix-icon="el-icon-mobile" placeholder="请输入手机号" clearable v-model="form.phone" oninput = "value=value.replace(/[^\d]/g,'')" maxlength="11"/>
      </el-form-item>
      <el-button type="warning" class="sign-btn" v-on:click="onSubmit">注 册</el-button>
<!--      <el-button class="login-btn" v-on:click="$router.push('/login')">登 录</el-button>-->
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "ConsumerSignIn",
    data() {
      return {
        form: {
          username: '',
          password: '',
          checkPassword:'',
          phone:''
        },

        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          username: [
            {required: true, message: '用户名不可为空', trigger: 'blur'},
            { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
          ],
          password: [
            {required: true, message: '密码不可为空', trigger: 'blur'},
            { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
          ],
          checkPassword:[
            {required: true, message: '确认密码不可为空', trigger: 'blur'},
            { min: 3, max: 30, message: '长度在 3 到 30 个字符', trigger: 'blur' }
          ],
          phone:[
            {required: false, message: '手机号不可为空', trigger: 'blur'},
            { min: 11, max: 11, message: '长度必须为 11 位', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      onSubmit() {
        const reg = 11 && /^((13|14|15|17|18)[0-9]\d{8})$/
        // 为表单绑定验证功能
        if (this.form.username === '') {
          this.$message({
            message: '用户名不能为空',
            type:'error',
            center: true
          })
          return
        }
        if (this.form.password === '') {
          this.$message({
            message: '密码不能为空',
            type:'error',
            center: true
          })
          return
        }
        if(this.form.password !== this.form.checkPassword){
          this.$message({
            message:'请输入相同的密码',
            type:'error',
            center:true
          })
          return
        }
        if (this.form.phone === '') {
          this.$message({
            message:'手机号不能为空',
            type:'error',
            center: true
          })
          return
        }
        if (!reg.test(this.form.phone)) {
          this.$message({
            message:'请输入正确的手机号',
            type:'error',
            center:true
          })
          return
        }
        // FIXME: 全部验证成功，开始注册
        this.$syspost('/signIn/consumer', {
          name: this.form.username,
          password: this.form.password,
          telephone: this.form.phone
        }).then(res => {
          let msg = res.data.msg
          // 注册成功
          if (msg === 'SUCCESS') {
            this.$confirm('注册成功，是否前去登录?', '提示', {
              confirmButtonText: '前去登录',
              cancelButtonText: '回到主页',
              type: 'warning'
            }).then(() => {
              this.$router.push('/login')
            }).catch(() => {
              this.$router.push('/index')
            })
          } else {
            this.$message({
              message: msg,
              type:'error',
              center:true
            })
          }
        }).catch(err => console.log(err))
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
  .signIn-box {
    border: 1px solid #DCDFE6;
    width: 500px;
    margin: 0 auto 100px auto;
    background: rgba(255,255,255,0.7);
    padding: 20px 40px 20px 40px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px 	#DCDFE6;
  }
  .signIn-title {
    text-align: center;
    margin: 20px auto 40px auto;
    color: #E7B563;
  }
  .form-item{
    margin:10px 0 20px 0;
  }
  .sign-btn {
    margin: 10px 30px 10px 30px;
  }
  .sign-btn:hover{
    background: rgba(230,162,60,0.7);
  }
  .login-btn{
    margin:10px 30px 10px 30px;
  }
  .login-btn:hover{
    background: rgba(255,255,255,0.5);
    color:#000;
  }
  .t{
    color:rgba(0,0,0,0);
  }
</style>
