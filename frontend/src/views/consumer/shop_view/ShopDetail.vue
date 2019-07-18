<template>
  <div v-if="found">
    <el-row>
      <el-col :span="1">
        <div class="grid-content bg-white"></div>
      </el-col>
      <el-col :span="17">
        <el-container class="grid-content bg-light">
          <el-aside width="170px">
            <!--店铺图片 -->
            <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="shop-image">
          </el-aside>
          <el-container height="60px">
            <!--店铺信息-->
            <el-header height="30px">
              <div class="shop-name">{{shop.username}}</div>
              <el-rate v-model="shop.score" disabled show-score text-color="#ff9900" class="shop-name">{{shop.score}}</el-rate>
            </el-header>
            <el-main height="30px">
              <i class="el-icon-location-outline shop-info">{{shop.address}}</i>
              <i class="el-icon-phone-outline shop-info">{{shop.telephone}}</i>
              <i class="el-icon-message shop-info">{{shop.managerEmail}}</i>
            </el-main>
          </el-container>
        </el-container>
      </el-col>
      <el-col :span="1"><div class="grid-content bg-white"></div></el-col>
      <el-col :span="4">
        <div class="header">公 告</div>
        <div class="message">新品即将上架，敬请期待！</div>
      </el-col>
    </el-row>

    <!--  选择    -->
    <el-container>
       <el-aside width="400" style="margin-left: 60px;margin-top: 20px;">
         <el-menu>
           <el-menu-item index="1" @click="index1" class="title">
             <i class="el-icon-fork-spoon"></i>
             <span slot="title">点单</span>
           </el-menu-item>
           <el-menu-item index="2" @click="index2" class="title">
             <i class="el-icon-chat-square"></i>
             <span slot="title">评价</span>
           </el-menu-item>
           <el-menu-item index="3" @click="index3" class="title">
             <i class="el-icon-chat-line-round"></i>
             <span slot="title">联系</span>
           </el-menu-item>
         </el-menu>
        </el-aside>
        <el-main>
          <!--  菜单    -->
          <el-row v-if="index === 1">
            <el-col :span="19" :offset="1">
              <Menu :sid="SID"></Menu>
            </el-col>
          </el-row>
          <!--  评论  -->
          <el-row v-if="index === 2">
            <el-col :span="18" :offset="2">
              <Commentary :sid="SID"></Commentary>
            </el-col>
          </el-row>
          <!--  联系   -->
          <el-row v-if="index === 3">
            <el-col :span="19" :offset="1">
              <ContactDialog :other="SID" :id="SID"></ContactDialog>
            </el-col>
          </el-row>
        </el-main >
    </el-container>


  </div>

<!--  商店不存在-->
  <div v-else>
    <NotFound content="此商店不存在"></NotFound>
  </div>
</template>

<script>
  import NotFound from '@/components/NotFound'
  import Menu from './Menu'
  import Commentary from './Commentary'
  import ContactDialog from "@/components/ContactDialog"

  export default {
    name: "ShopDetail",
    components: {ContactDialog, NotFound, Menu, Commentary},
    data() {
      return {
        activeName: 'first',
        index: 1,

        SID: '',
        found: true,

        shop: {
          address: 1,
          id: 1,
          managerEmail: 1,
          managerName: 1,
          pictureUrl: 1,
          score: 1,
          telephone: 1,
          username: 1
        },
      }
    },
    created() {
      // console.log(this.$route.params)
      this.SID = this.$route.params.id
      this.refreshPage()
    },
    watch: {
      '$route' (to, from) {
        // console.log(this.$route.params)
        this.SID = this.$route.params.id
        this.refreshPage()
      }
    },
    methods: {
      // 根据 SID 重新刷新页面
      refreshPage() {
        // 商家信息
        this.$get('/shop/getInfoBySid',{
          SID: this.SID
        }).then(res => {
          // console.log(res.data);
          this.shop = res.data.data;
          this.found = true;
        }).catch(err => {
          console.log(err)
          // 没找到此商店
          this.found = false
        });
      },
      index1(){
        this.index=1;
        console.log(this.index)
      },
      index2(){
        this.index=2;
        console.log(this.index)
      },
      index3(){
        this.index=3;
        console.log(this.index)
      }
    }
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
  }
  .bg-light {
    background: 	#FDF2D4;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 80px;
  }
  .bg-white{
    background: #FDFDFD;
  }
  .shop-name{
    margin:10px 20px 10px;
    padding:0;
    background-color:#FDF2D4;
    font-weight: bold;
    float:left;
  }
  .header{
    margin:0;
    padding:0;
    background-color:#FDF2D4;
    font-weight: bold;
    height:25px;
    border-radius: 4px;
  }
  .message{
    height:75px;
    background-color: #F5F5F5;
  }
  .shop-image{
    width: 150px;
    height:80px;
    margin:10px;
    display: block;
  }
  .shop-info{
    float:left;
    margin-right:40px;
  }
  .food-image {
    width: 150px;
    height:100px;
    margin:10px;
    display: block;
  }
  .box-card {
    width: 400px;
    height: 150px;
    margin:10px auto 10px 70px;
  }
  .card{
    height:130px;
  }
  .card:hover{
    background-color:#F5F5F5;
  }
  .input{
    width:250px;
    margin:auto auto auto 25px;
  }
  .el-pagination.is-background .el-pager li:not(.disabled).active{
    background-color: #FFCD56 !important;
  }
  .el-pagination.is-background .el-pager li:hover{
    color: #FFCD56 !important;
  }
  .input{
    border-color: #FFCD56;
  }
  .search{
    margin:10px;
  }
  .search:hover{
    color:#FFCD56;
    border-color: #FFCD56;
    background-color: rgba(255,205,86,0.1);
  }
  .search:focus{
    color:#FFCD56;
    border-color: #FFCD56;
    background-color: rgba(255,205,86,0.1);
  }
  .trolley:hover{
    color:#FFCD56;
    border-color: #FFCD56;
    background-color: rgba(255,205,86,0.1);
  }
  .trolley:focus{
    color:#FFCD56;
    border-color: #FFCD56;
    background-color: rgba(255,205,86,0.1);
  }
  .dia-btn{
    background-color: #EBB563;
    border-color: #EBB563;
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
