<template>
  <el-container>
    <el-header>
      <el-row>
        <el-col :span="10" :offset="6"><i class=" el-icon-user"></i>{{welcome()}}</el-col>
      </el-row>
    </el-header>

    <el-main style="margin-left: 350px">
      <el-row>
        <el-col :span="4"><el-tag type="warning">姓名</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.username}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">性别</el-tag></el-col>
        <el-col :span="4" ><el-tag type="info" effect="plain" style="width: 200px">{{user.sex}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">年龄</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.age}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">电话</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.telephone}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">邮箱</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.email}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">地址</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.address}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">余额</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.balance}}</el-tag></el-col>
      </el-row>
      <br>
    </el-main>
  </el-container>
</template>

<script>
  import util from '@/utils/util'

  export default {
    name: "Info",
    data() {
      return {
        user: {
          username: '',
          sex: '',
          age: '',
          telephone: '',
          email: '',
          address: '',
          balance: ''
        }
      }
    },
    methods: {
      welcome () {
        return util.welcome(this.$db.get('USER'))
      }
    },
    created() {
      this.$get('/consumer/getInfoByCid', {
        CID: this.$db.get('USER_ID')
      }).then((res) => {
        this.user = res.data.data
      }).catch(err => {
        console.log(err)
      })
    }
  }
</script>

<style scoped>

</style>
