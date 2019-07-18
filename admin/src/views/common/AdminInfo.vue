<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
<!--        <el-button style="float: right; padding: 3px 0" type="text">修改信息</el-button>-->
      </div>
      <div class="text item">
        名字: {{name}}
      </div>
      <div class="text item">
        性别: {{sex}}
      </div>
      <div class="text item">
        年龄: {{age}}
      </div>
      <div class="text item">
        学校: {{school}}
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "AdminInfo",
    data() {
      return {
        name: 'mai',
        sex: '男',
        age: 20,
        school: 'XMU'
      }
    }
  }
</script>

<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
</style>
