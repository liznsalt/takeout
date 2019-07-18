<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-aside >
      <el-row class="tac">
        <el-col >
          <h5>店铺信息</h5>
          <el-menu :default-active="nowPageIndex" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" >
            <el-menu-item index="1" class="title" @click="$router.push('/shopinfo/info')">
              <i class="el-icon-platform-eleme"></i>
              <span slot="title">店铺资料</span>
            </el-menu-item>
            <el-menu-item index="2" class="title" @click="$router.push('/shopinfo/history')">
              <i class="el-icon-s-order"></i>
              <span slot="title">历史订单</span>
            </el-menu-item>
            <el-menu-item index="3" class="title" @click="$router.push('/shopinfo/contact')">
              <i class="el-icon-bell"></i>
              <span slot="title">顾客互动</span>
            </el-menu-item>
            <el-menu-item index="4" class="title" @click="$router.push('/shopinfo/password')">
              <i class="el-icon-key"></i>
              <span slot="title">修改密码</span>
            </el-menu-item>
            <el-menu-item index="5" class="title" @click="$router.push('/shopinfo/setting')">
              <i class="el-icon-setting"></i>
              <span slot="title">账号设置</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </el-aside>

    <el-main>
      <!--      <component :is="componentName"></component>-->
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: "ShopInfo",
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    computed: {
      nowPageIndex() {
        if (this.$route.path === '/shopinfo/info') {
          return "1"
        } else if (this.$route.path === '/shopinfo/history') {
          return "2"
        } else if (this.$route.path === '/shopinfo/contact') {
          return "3"
        } else if (this.$route.path === '/shopinfo/password') {
          return "4"
        } else if (this.$route.path === '/shopinfo/setting') {
          return "5"
        } else {
          return "1"
        }
      }
    }
  }
</script>

<style scoped>
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
