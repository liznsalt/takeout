<template>
  <el-container>
    <el-header>
      <el-row>
        <el-col :span="10" :offset="6"><i class=" el-icon-user"></i>{{welcome()}}</el-col>
      </el-row>
    </el-header>

    <el-main  style="margin-left: 350px">
      <el-row>
        <el-col :span="4"><el-tag type="warning">店铺姓名</el-tag></el-col>
        <el-col :span="4" effect="plain"><el-tag type="info" effect="plain" style="width: 200px">{{user.username}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">联系方式</el-tag></el-col>
        <el-col :span="4" ><el-tag type="info" effect="plain" style="width: 200px">{{user.telephone}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">店铺地址</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.address}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">店主姓名</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.managerName}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">店主邮箱</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.managerEmail}}</el-tag></el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="4"><el-tag type="warning">评分</el-tag></el-col>
        <el-col :span="4"><el-tag type="info" effect="plain" style="width: 200px">{{user.score}}</el-tag></el-col>
      </el-row>
      <br>
    </el-main>
  </el-container>
</template>

<script>
  import util from "@/utils/util"

  export default {
    name: "SInfo",
    data() {
      return {
        user: {
          name: String,
          telephone: String,
          address: String,
          managerName: String,
          managerEmail: String,
          pictureUrl: String,
          score: Number
        }
      }
    },
    methods: {
      welcome() {
        return util.welcome(this.$db.get('USER'))
      }
    },
    created() {
      this.$get('/shop/getInfoBySid', {
        SID: this.$db.get('USER_ID')
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
