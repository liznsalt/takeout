<template>
  <div class="top">
    <el-row id="nav-fixed" :class="{nav_fixed : isFixed}">
      <el-col :span="2" class="logo"></el-col>
      <el-col :span="2"><div class="grid-content" @click="$router.push('/index')">首页</div></el-col>
<!--      左侧-->
      <template v-if="userRole === 'consumer'">
        <el-col :span="2"><div class="grid-content" @click="$router.push('/service')">外卖服务</div></el-col>
        <el-col :span="2"><div class="grid-content" @click="$router.push('/shoppingtrolley')">订单管理</div></el-col>
      </template>
      <template v-else-if="userRole === 'shop'">
        <el-col :span="2"><div class="grid-content" @click="$router.push('/menuedit')">菜单管理</div></el-col>
        <el-col :span="2"><div class="grid-content" @click="$router.push('/service')">评论管理</div></el-col>
        <el-col :span="2"><div class="grid-content" @click="$router.push('/ordermanager')">订单管理</div></el-col>
      </template>
      <template v-else>
        <el-col :span="2"></el-col>
      </template>
<!--      空白-->
      <el-col :span="emptySpan()"></el-col>
<!--      右侧， 分别为用户和商家 和 不登录状态-->
      <template v-if="userRole === 'consumer'">
        <el-col :span="2">
          <el-dropdown>
            <span class="el-dropdown-link">
              <i class="el-icon-s-custom"></i>
              {{userName}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu  slot="dropdown">
              <el-dropdown-item icon="el-icon-medal-1" class="dropdown" @click.native="$router.push('/consumerinfo')">{{userName}}</el-dropdown-item>
              <el-dropdown-item icon="el-icon-user" class="dropdown" @click.native="$router.push('/consumerinfo/info')" divided>个人资料</el-dropdown-item>
              <el-dropdown-item icon="el-icon-s-order" class="dropdown" @click.native="$router.push('/consumerinfo/history')">历史订单</el-dropdown-item>
              <el-dropdown-item icon="el-icon-bell" class="dropdown" @click.native="$router.push('/consumerinfo/contact')">店家互动</el-dropdown-item>
              <el-dropdown-item icon="el-icon-setting" class="dropdown" @click.native="$router.push('/consumerinfo/setting')">账号设置</el-dropdown-item>
              <el-dropdown-item icon="el-icon-circle-close" class="dropdown" @click.native="logout" divided>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </template>
      <template v-else-if="userRole === 'shop'">
        <el-col :span="2">
          <el-dropdown>
            <span class="el-dropdown-link">
              <i class="el-icon-s-shop"></i>
              {{userName}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-medal-1" class="dropdown" @click.native="$router.push('/shopinfo')">{{userName}}</el-dropdown-item>
              <el-dropdown-item icon="el-icon-platform-eleme" class="dropdown" @click.native="$router.push('/shopinfo/info')" divided>店铺资料</el-dropdown-item>
              <el-dropdown-item icon="el-icon-s-order" class="dropdown" @click.native="$router.push('/shopinfo/history')">历史订单</el-dropdown-item>
              <el-dropdown-item icon="el-icon-bell" class="dropdown" @click.native="$router.push('/shopinfo/contact')">顾客互动</el-dropdown-item>
<!--              <el-dropdown-item icon="el-icon-key" class="dropdown" @click.native="$router.push('/shopinfo/password')">修改密码</el-dropdown-item>-->
              <el-dropdown-item icon="el-icon-setting" class="dropdown" @click.native="$router.push('/shopinfo/setting')">账号设置</el-dropdown-item>
              <el-dropdown-item icon="el-icon-circle-close" class="dropdown" @click.native="logout" divided>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </template>
      <template v-else>
        <el-col :span="1"><div class="grid-content" @click="$router.push('/login')">登录</div></el-col>
        <el-col :span="1"><div class="grid-content" @click="$router.push('/signin')">注册</div></el-col>
      </template>
      <!--el-col :span="2"><div class="grid-content" @click="$router.push('/shoppingtrolley')">购物车</div></el-col-->
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "top-navbar",
    data() {
      return {
        activeIndex: '1',
        isFixed: false,
        offsetTop: 0,

      }
    },
    mounted() {
      // 设置bar浮动阈值为 #fixedBar 至页面顶部的距离
      this.offsetTop = document.querySelector('#nav-fixed').offsetTop;
      // 开启滚动监听
      // window.addEventListener('scroll', this.handleScroll);
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      // 滚动监听  滚动触发的效果写在这里
      handleScroll() {
        const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
        this.isFixed = scrollTop >= this.offsetTop;
      },

      logout() {
        console.log("登出")
        this.$db.clear()
        this.$router.push('/index')
        location.reload()
      },
      emptySpan() {
        if (this.$db.get('ROLES') === '"consumer"') {
          return 10
        } else if (this.$db.get('ROLES') === '"shop"') {
          return 7
        } else {
          return 12
        }
      }
    },
    destroyed() {
      // 离开页面 关闭监听 不然会报错
      window.removeEventListener('scroll', this.handleScroll);
    },
    computed: {
      userRole() {
        let role = this.$db.get('ROLES')
        if (role === '"consumer"') {
          role = 'consumer'
        } else if (role === '"shop"') {
          role = 'shop'
        } else {
          role = 'none'
        }
        return role
      },
      userName() {
        let name = this.$db.get('USER')
        return name.substr(1).substr(0, name.length - 2)
      }
    }
  }
</script>

<style scoped>
.top {
  margin-bottom: 1.25rem;
}
#nav-fixed {
  background: rgb(255, 205, 86);
  border: 0;
  border-radius: 10px;
}
.nav_fixed {
  position: fixed;
  z-index: 2;
  top: 0;
  width: 77.2%;
}
.el-row {
  background: rgba(255,255,255,0);
  border-radius: 10px;
  line-height: 2.2rem;
}
.el-col {
  margin: 0.625rem 1.5rem;
}
.el-col div:hover {
  background: rgba(255,255,255,0.6);
  border-radius: 0.625rem;
  cursor: pointer;
  transition: all 0.5s;
}
.logo{
  background-image: url("images/orange_logo.png");
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  width:50px;
  height:40px;
  background-repeat: no-repeat;
  position: relative;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.grid-content span {
  border: 1px solid;
  border-radius: 50px;
  padding: 5px;
  margin-right: 0.5rem;
}
  .dropdown:hover{
  color:#FFCD56;
  border-color: #FFCD56;
  background-color: rgba(255,205,86,0.1);
  }
</style>
