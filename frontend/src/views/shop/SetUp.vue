<template>
  <div >
    <el-steps :active="1" align-center class="step">
      <el-step title="提交申请" description="填写店铺信息，提交经营资质照片，填写您的收款信息，方便开店后快速提现"></el-step>
      <el-step title="审核签约" description="等待资质审核（1-3个工作日），审核通过，短信签约"></el-step>
      <el-step title="录入菜单" description="录入商品"></el-step>
      <el-step title="正式营业" description="业务经理到店线下审核，审核通过即可开门营业"></el-step>
    </el-steps>
    <el-row class="step">
      <el-button type="warning">申请开店</el-button>
    </el-row>
  </div>
</template>

<script>
    export default {
        name: "SetUp"
    }
</script>

<style scoped>
.step{
  margin-top: 100px;
  margin-bottom: 100px;
}

</style>
