<template>
  <div class="food-img">
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <el-radio-group v-model="radio" class="text">
        <el-radio :label="1">用户</el-radio>
        <el-radio :label="2">商家</el-radio>
      </el-radio-group>

      <el-form-item label="手机号" prop="username"  class="form-item" >
        <el-input type="text" prefix-icon="el-icon-mobile" placeholder="请输入手机号" clearable v-model="form.phone" oninput = "value=value.replace(/[^\d]/g,'')" maxlength="11"/>
      </el-form-item>
      <el-form-item label="验证码" prop="password" class="form-item">
        <el-input type="code" placeholder="请输入验证码" clearable v-model="form.code" class="input-code"/>
        <el-button type="warning" size="small" class="get-code-btn" @click="sendcode" :disabled="disabled" v-if="disabled===false">获取验证码</el-button>
        <el-button type="button" size="small" @click="sendcode" :disabled="disabled" v-if="disabled===true">{{btntxt}}</el-button>
      </el-form-item>

      <el-row>
        <div class="code" @click="$router.push('/login')">>>>用户名密码登录>>></div>
      </el-row>
      <el-row>
        <el-button type="warning" class="login-btn" v-on:click="onSubmit">登 录</el-button>
        <el-button class="sign-btn" @click="$router.push('/signin')">注 册</el-button>
      </el-row>

    </el-form>
  </div>
</template>

<script>
  import jwtDecode from 'jwt-decode'
  import {mapMutations} from 'vuex'

  export default {
    name: "Verify",
    data() {
      return {
        radio: 1,
        form: {
          phone: '',
          code: ''
        },
        disabled: false,
        time: 0,
        btntxt: "重新发送",
        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          username: [
            {required: true, message: ' ', trigger: 'blur'}
          ],
          password: [
            {required: true, message: ' ', trigger: 'blur'}
          ]
        },
        verifyCode: ''
      }
    },
    methods: {
      sendcode() {
        const reg = 11 && /^((13|14|15|17|18)[0-9]\d{8})$/
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
        } else {
          console.log(this.form.phone);
          this.$syspost('/sms/smsXxs', {
            phone: this.form.phone
          }).then(res => {
            if (res.data.isOk === "OK") {
              this.$message({
                message: '发送成功，请注意接收',
                type: 'success',
                center:true
              });
              this.verifyCode = res.data.verifyCode
              this.time = 60;
              this.disabled = true;
              this.timer();
            } else {
              this.$message({
                message: '发送出错，可能服务器发生了错误',
                type: 'error',
                center:true
              });
            }
          }).catch(err => console.log(err))
        }
      },
      //60S倒计时
      timer() {
        if (this.time > 0) {
          this.time--;
          this.btntxt = this.time + "s后重新获取";
          setTimeout(this.timer, 1000);
        } else {
          this.time = 0;
          this.btntxt = "获取验证码";
          this.disabled = false;
        }
      },
      onSubmit() {
        const reg = 11 && /^((13|14|15|17|18)[0-9]\d{8})$/
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
        // 验证验证码
        // console.log(this.verifyCode)
        if (this.form.phone !== '' && this.form.code !== ''
          && this.verifyCode.length === 6
          && this.form.code === this.verifyCode) {
          let url = ''
          if (this.radio === 1) {
            url = '/consumer/loginWithoutPass'
          } else if (this.radio === 2) {
            url = '/shop/loginWithoutPass'
          }
          // 开始登录
          this.$syspost(url, {
            telephone: this.form.phone
          }).then((r) => {
            // console.log(r.data)
            // 保存用户信息
            this.saveLogin(r.data)
            // TODO: 返回前一页 ?
            // this.$router.go(-1)
            this.$router.push('/index')
            location.reload()
          }).catch((err) => {
            // 报错
            this.$message({
              message:'服务器错误啦',
              type:'error',
              center:true
            })
            // 清空用户信息
            this.$db.clear()
          })
        } else {
          this.$message({
            message:'验证码错误',
            type:'error',
            center:true
          })
          this.dialogVisible = true;
          return false;
        }
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
      saveLogin (data) {
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
    cursor: pointer;
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

  .input-code{
    width:140px;
    float:left;
  }

  .get-code-btn{
    margin:5px 10px 0 auto;
    float:right;
  }

  .t{
    color:rgba(0,0,0,0);
  }
</style>
