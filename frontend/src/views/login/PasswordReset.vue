<template>
  <div class="food-img">
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-row class="t">?</el-row><el-row class="t">?</el-row>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">

      <el-form-item label="手机号" prop="username"  class="form-item" >
        <el-input type="text" prefix-icon="el-icon-mobile" placeholder="请输入手机号" clearable v-model="form.phone" oninput = "value=value.replace(/[^\d]/g,'')" maxlength="11"/>
      </el-form-item>
      <el-form-item label="验证码" prop="password" class="form-item">
        <el-input type="code" placeholder="请输入验证码" clearable v-model="form.code" class="input-code"/>
        <el-button type="warning" size="small" class="get-code-btn" @click="sendcode" :disabled="disabled" v-if="disabled===false">获取验证码</el-button>
        <el-button type="button" size="small" @click="sendcode" :disabled="disabled" v-if="disabled===true">{{btntxt}}</el-button>
      </el-form-item>

      <el-row>
        <el-button type="warning" class="login-btn" v-on:click="onSubmit">提 交</el-button>
        <el-button class="login-btn" v-on:click="$router.push('/login')">返回登录</el-button>
      </el-row>

    </el-form>

    <el-dialog title="重置密码" :visible.sync="dialogFormVisible" width="35%"
               :before-close="handleClose">
      <el-form :model="dialogForm">
        <el-form-item label="新密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="dialogForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="dialogForm.newPass" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
<!--        <el-button @click="dialogFormVisible = false">取 消</el-button>-->
        <el-button type="primary" @click="changeFinish" style="text-align: center">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import jwtDecode from 'jwt-decode'
  import {mapMutations} from 'vuex'
  export default {
    name: "PasswordReset",
    data() {
      return {
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
        verifyCode: '',

        dialogFormVisible: false,
        formLabelWidth: '120px',
        dialogForm: {
          pass: '',
          newPass: ''
        }
      }
    },
    computed: {
      role() {
        return this.$route.params.role
      }
    },
    created() {

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
          this.dialogFormVisible = true // 打开对话框修改密码
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
      },
      handleClose(done) {
        this.$confirm('将丢失操作，确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      changeFinish() {
        // 先检查密码是否一致
        if (this.dialogForm.pass !== this.dialogForm.newPass) {
          this.$message({
            message: '两次密码不一致',
            type: 'error'
          })
          return
        }

        let url = ''
        if (this.$route.params.role === "consumer") {
          url = '/consumer/changePasswordByPhoneCode'
        } else if (this.$route.params.role === "shop") {
          url = '/shop/changePasswordByPhoneCode'
        }
        this.$syspost(url, {
          telephone: this.form.phone,
          password: this.dialogForm.pass
        }).then(res => {
          this.$confirm('重置密码成功，是否直接登录?', '提示', {
            confirmButtonText: '直接登录',
            cancelButtonText: '返回登录界面',
            type: 'warning'
          }).then(res => {
            this.doLogin()
          }).catch(err => {
            this.$router.push('/login')
          })
        }).catch(err => console.log(err))
      },
      doLogin() {
        let url = ''
        if (this.$route.params.role === "consumer") {
          url = '/consumer/loginWithoutPass'
        } else if (this.$route.params.role === "shop") {
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
