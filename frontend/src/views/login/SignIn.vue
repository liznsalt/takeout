<template>
  <div class="food-img">
    <el-button type="primary" v-on:click="shop">商 家 入 驻</el-button>
    <el-button type="primary" v-on:click="$router.push('/signin/consumer')">用 户 注 册</el-button>

    <el-dialog :visible.sync="dialogVisible" width="60%" >
      <p>尊敬的商家：</p>
      <p>您好，如果您已经准备完成全部资料，并准备提交申请，在您提交申请前，请务必仔细阅读如下全部声明事项：</p>
      <p>1. 您确认并知悉，您的申请需依次经过线上与线下两次审核，请您在线上审核通过后，不要操作上线，等待线下业务经理到门店现场
        审核，经线下审核通过后方可操作上线。如其中任一项审核未通过或您提交了虚假资质或信息，则美团外卖有权对申请主体提供的资质
        和信息进行删除、屏蔽及操作下线等处理。</p>
      <p>2. 您承诺您申请主体为依据中国法律合法成立并有效存续的法人或其它商事主体，能够独立承担法律责任，并具备从事申请业务应具
        备的行政许可、合法授权或同意，及具有履行合作所需的一切权力、权利及能力。</p>
      <p>3. 您承诺所提供证照等资质和信息真实、合法、有效且不侵犯任何第三方合法权益。</p>
      <p>4. 您承诺提交审核的证照等资质和信息应符合国家法律、法规、规章的规定，申请主体未伪造、变造、冒用或盗用他人证照等资质，
        未侵犯他人拥有合法权益的信息等；如因申请主体提供的证照等资质或信息造成外卖用户及相关第三方损失或美团外卖损失或承担责任
        的，申请主体存在质量和安全问题，由此造成的美团外卖用户及相关第三方投诉、索赔、经济损失等相关后果均由申请主体予以承担。</p>
      <p>5. 您承诺提供的商号、商标、图片等均具有发布和使用的充分权利或授权，否则因申请主体原因造成美团外卖或第三方损失的，申请
        主体承担全部赔偿责任。如因申请主体原因造成美团外卖被第三方要求赔偿或发生纠纷、行政处罚、诉讼的，申请主体应向美团外卖全
        额赔偿损失，美团外卖亦有权对申请主体提供的的证照等资质和信息进行删除、屏蔽及操作下线等处理。</p>
      <p> 对于以上声明，您如果存在异议，请停止申请或与【 10105557 】联系；如您继续填写并提交申请，则视为您理解上述声明，并确认
        遵守全部事项并在违反时承担责任。</p>
      <el-checkbox v-model="checked" >我已阅读上述声明</el-checkbox>
      <el-button type="warning" size="small" class="read-btn" @click="read">确 定</el-button>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    name: "SignIn",
    data(){
      return{
        checked:false,
        dialogVisible:false
      };
    },
    methods:{
      shop(){
        this.dialogVisible=true;
      },
      read(){
        if(this.checked === true){
          this.dialogVisible=false;
          this.$router.push('/signin/shop');
        }
        else{
          this.$message({
            message: '请阅读上述声明',
            center: true
          });
        }
      }
    }
  }
</script>

<style scoped>
  .food-img{
    background-image: url("../images/orange.jpg");
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
  /*
.shop{
  margin:500px 100px 100px 30px;
  background: rgba(236,228,207,0.5);
  color:#000;
}
.shop:hover{
  background:rgba(198,197,193,0.7);
}
  .user{
    margin:500px 30px 100px 100px;
    background: rgba(198,197,193,0.5);
    color:#000;
  }
  .user:hover{
    background:rgba(250,243,226,0.7);
  }
  */
.el-dialog p{
  text-align: left;
}
.read-btn{
  margin:30px;
}
</style>
