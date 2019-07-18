<template>
<!--  <el-container>-->
    <div id="app">
      <topNavbar></topNavbar>
      <router-view></router-view>
      <!--可自定义按钮的样式、show/hide临界点、返回的位置  -->
      <!--如需文字提示，可在外部添加element的<el-tooltip></el-tooltip>元素  -->
      <el-tooltip placement="top" content="回到顶部">
        <back-to-top :custom-style="myBackToTopStyle" :visibility-height="300" :back-position="0" transition-name="fade"/>
      </el-tooltip>
    </div>
<!--  </el-container>-->
</template>

<script>
  import topNavbar from './views/TopNavbar'
  import BackToTop from './views/BackToTop'
  import jwtDecode from 'jwt-decode'
  import {mapMutations} from 'vuex'

  export default {
    name: 'App',
    components: {
      topNavbar,
      BackToTop
    },
    data() {
      return {
        myBackToTopStyle: {
          right: '50px',
          bottom: '50px',
          width: '40px',
          height: '40px',
          borderRadius: '4px',
          lineHeight: '45px', // 请保持与高度一致以垂直居中
          background: '#e7eaf1'// 按钮的背景颜色
        }
      }
    }
  }
</script>

<style>
#app {
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 10px;
  background-color: rgba(250,250,250,0.4);
}

.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
