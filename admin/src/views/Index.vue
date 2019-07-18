<template>
  <el-container>
    <el-aside>
      <el-row class="tac">
        <el-col>
          <h1>系统后台管理</h1>
          <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
            <el-menu-item index="1" class="title" @click="$router.push('/home')">
              <i class="el-icon-s-home"></i>
              <span slot="title">系统首页</span>
            </el-menu-item>
            <el-menu-item index="2" class="title" @click="$router.push('/consumer')">
              <i class="el-icon-user"></i>
              <span slot="title">用户管理</span>
            </el-menu-item>
            <el-menu-item index="3" class="title" @click="$router.push('/shop')">
              <i class="el-icon-s-shop"></i>
              <span slot="title">商家管理</span>
            </el-menu-item>
            <el-menu-item index="4" class="title" @click="$router.push('/order')">
              <i class="el-icon-s-order"></i>
              <span slot="title">订单管理</span>
            </el-menu-item>
<!--            <el-menu-item index="5" class="title" @click="$router.push('/chat')">-->
<!--              <i class="el-icon-chat-line-round"></i>-->
<!--              <span slot="title">互动管理</span>-->
<!--            </el-menu-item>-->
            <el-menu-item index="6" class="title" @click="$router.push('/monitor')">
              <i class="el-icon-monitor"></i>
              <span slot="title">访问监控</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </el-aside>

    <el-container>
<!--      header -->
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
<!--          <i class="el-icon-setting" style="margin-right: 15px"></i>-->
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item class="title" @click.native="$router.push('/admininfo')">个人中心</el-dropdown-item>
            <el-dropdown-item @click.native="dialogFormVisible = true" class="title">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="signOut" class="title">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
<!--        <span>{{userName}}</span>-->
      </el-header>

<!--      main -->
      <el-main>
<!--        tag-->
        <TagList></TagList>
        <br>
        <el-divider></el-divider>
<!--        view-->
        <router-view></router-view>
      </el-main>
    </el-container>

<!--    TODO: 修改密码对话框-->
    <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="旧密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.oldPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.newPass" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="changePass">确 定</el-button>
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
  import TagList from '../components/TagList'
  export default {
    name: "Index",
    data() {
      return {
        dialogFormVisible: false,
        form: {
          oldPass: '',
          pass: '',
          newPass: ''
        },
        formLabelWidth: '120px'
      }
    },
    components: {
      TagList
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      signOut() {
        this.$db.clear()
        this.$router.push('/login')
      },

      changePass() {
        if (this.form.pass !== this.form.newPass) {
          this.$message({
            message: '两次密码不一致',
            type: 'error'
          })
          return
        }
        this.$post('/admin/changePassword', {
          AID: this.$db.get('USER_ID'),
          oldPassword: this.form.oldPass,
          newPassword: this.form.newPass
        }).then(res => {
          this.$message({
            message: '修改成功',
            type: 'success'
          })
          this.dialogFormVisible = false
          this.form = { oldPass: '', pass: '', newPass: ''}
        })
      }
    },
    computed: {
      userName() {
        return this.$db.get('USER')
      }
    }
  }
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
  .demonstration {
    display: block;
    color: #8492a6;
    font-size: 14px;
    margin-bottom: 20px;
  }
  .el-header {
    background-color: #ffffff;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
  .title:hover{
    color:#FFCD56;
    border-color: #FFCD56;
    background-color: rgba(255,205,86,0.1);
  }
  .title:focus{
    color:#FFCD56;
    border-color: #FFCD56;
    background-color: rgba(255,205,86,0.1);
  }
</style>
