<template>
  <div>
    <div class="background-img">
      <el-row>
        <el-button type="primary" @click="$router.push('/service')">我要点外卖</el-button>
        <el-button type="primary" @click="$router.push('/signin/shop')">我要入驻</el-button>
      </el-row>
    </div>

    <div>
      <div class="title-0">
        <h1 style="color: darkgray">S E R V I C E</h1>
        <h1>服 务 介 绍</h1>
        <p>品类全覆盖，应有尽有</p>
      </div>

      <div class="service">
        <el-container>
          <el-aside class="deliver-1" width="50%">
            <div class="deliver">
              <img class="img-responsive" src="./images/deliver 3.png" alt="" @click="$router.push('/service')">
            </div>
          </el-aside>
          <el-container >
            <el-main>
              <div class="title-1">
                      <span class="el-icon">
                        <i class="el-icon-present" @click="$router.push('/service')"> 美食 Delicacy</i>
                      </span>
                <div class="subtitle">大牌优惠</div>
              </div>
              <div class="title-2">
                      <span class="el-icon">
                        <i class="el-icon-ice-cream" @click="$router.push('/service')"> 甜点饮品 Desserts&Drinks</i>
                      </span>
                <div class="subtitle">幸福甜蜜</div>
              </div>
              <div class="title-3">
                      <span class="el-icon">
                        <i class="el-icon-grape" @click="$router.push('/service')"> 水果生鲜 Fresh foods</i>
                      </span>
                <div class="subtitle">新鲜速达</div>
              </div>
              <div class="title-4">
                      <span class="el-icon">
                        <i class="el-icon-shopping-cart-2" @click="$router.push('/service')"> 超市便利 Supermarket</i>
                      </span>
                <div class="subtitle">优惠促销</div>
              </div>
            </el-main>
          </el-container>

        </el-container>
      </div>
    </div>

    <div>
      <div class="cases">
        <h1 style="color: darkgray">C A S E</h1>
        <h1>优秀合作案例</h1>
      </div>

      <el-row>
        <el-col :span="5" class="card">
          <el-card shadow="hover" :body-style="{ padding: '10px'}">
            <el-container>
              <el-header height="500" >
                <img src="./images/case1.png" class="image" alt="">
              </el-header>
              <el-main height="100">
                <div>外卖骑手人物：李君</div>
                <h3 class="card-title" v-on:click="">为减肥而成为兼职骑手，后来副业变“主业”</h3>
              </el-main>
            </el-container>
          </el-card>
        </el-col>

        <el-col :span="5" class="card">
          <el-card shadow="hover" :body-style="{ padding: '10px'}">
            <el-container>
              <el-header height="500" >
                <img src="./images/case2.png" class="image" alt="">
              </el-header>
              <el-main height="100">
                <div>外卖骑手人物：徐振</div>
                <h3 class="card-title" v-on:click="">平凡路上的英雄梦</h3>
              </el-main>
            </el-container>
          </el-card>
        </el-col>

        <el-col :span="5" class="card">
          <el-card shadow="hover" :body-style="{ padding: '10px'}">
            <el-container>
              <el-header height="500" >
                <img src="./images/case1.png" class="image" alt="">
              </el-header>
              <el-main height="100">
                <div>外卖服务市场</div>
                <h3 class="card-title" v-on:click="">接入经营助手服务</h3>
              </el-main>
            </el-container>
          </el-card>
        </el-col>

        <el-col :span="5" class="card">
          <el-card shadow="hover" :body-style="{ padding: '10px'}">
            <el-container>
              <el-header height="500" >
                <img src="./images/case4.png" class="image" alt="">
              </el-header>
              <el-main height="100">
                <div>《骑手安全生活指南》</div>
                <h3 class="card-title" v-on:click="">保障骑手安心无忧送餐路</h3>
              </el-main>
            </el-container>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <div class="join-container">
      <el-row>
        <el-button type="primary" class="join-btn" @click="$router.push('/signin')">加入我们</el-button>
      </el-row>
    </div>

    <div class="footer">
      <div class="widget">
        <h3>客服 1010-0101</h3>
        <h5>周一至周日 9:00~23:00</h5>
      </div>
    </div>

    <div class="copyright-container">
      <div class="copyright">&copy; orange.com 京ICP证1314号.</div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Index"
  }
</script>

<style scoped>
  .background-img{
    background-image: url("./images/orange.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    background-attachment: fixed;
  }

  .el-button--primary {
    color: #fff;
    background-color: #FFCD56;
    border-color: #FFCD56;
    font-weight: bolder;
    font-size: large;
    margin: 500px 100px 200px 100px;
  }

  .el-button--primary:hover{
    color: #FFCD56;
    background-color: #fff;
    border-color: #FFCD56;
  }

  .el-button--primary:focus{
    color: #FFCD56;
    background-color: #fff;
    border-color: #FFCD56;
  }

  .title-0{
    margin:100px 0 70px 0;
  }

  .title-1,.title-2,.title-3,.title-4{
    height:150px;
    width:500px;
    text-align: left;
  }

  .subtitle{
    font-size: 20px;
    margin:10px;
  }

  .el-icon{
    color:#000;
    font-size: 22px;
    width:40px;
    height:40px;
  }

  .el-icon:hover{
    color:#FFCD56;
    font-size: 35px;
    cursor: pointer;
  }

  .service{
    font-size:12px;
    height:600px;
    align-items: center;
    margin: 0 10% 0 10%;
  }

  .deliver-1{
    margin:50px 0 auto 0;
  }

  .image {
    width: 100%;
    display: block;
  }

  .card{
    margin:20px 30px 60px 30px;
  }

  .card-title:hover{
    color:#FFCD56;
    cursor: pointer;
  }

  .join-container {
    height:300px;
    background: rgba(255,205,87,0.1);
  }

.join-btn{
  margin:120px auto 120px auto;
}
  /*
  .join-btn{
    color: #fff;
    background-color: #FF7B00;
    border-color: #FF7B00;
    font-weight: bolder;
    font-size: large;
    margin: 30px 0 90px 0;
  }

  .join-btn:hover{
    color: #FFCD56;
    background-color: #fff;
    border-color: #FFCD56;
    margin: 20px 0 100px 0;
    width:180px;
    height:60px;
    font-size: 20px;
  }

  .join-btn:focus{
    color: #FFCD56;
    background-color: #fff;
    border-color: #FFCD56;
  }
*/

  .copyright-container{
    height:50px;
    background: rgba(13,14,15,0.9);
    vertical-align: middle;
    line-height:50px;
  }

  .copyright{
    color: #ffffff;
    font-size:15px;
  }

  .footer {
    background: #efefef;
    margin-top:100px;
    height:100px;
  }

  .widget {
    margin-bottom: 30px;
    text-align: center;
  }
</style>
