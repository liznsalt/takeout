<template>
  <div >
    <el-steps :active="active" finish-status="success" dialogVisible="dialogVisible" align-center class="step">
      <el-step title="提交申请" description="填写店铺信息，提交经营资质照片，填写您的收款信息，方便开店后快速提现"></el-step>
      <el-step title="审核签约" description="等待资质审核（1-3个工作日），审核通过，短信签约"></el-step>
      <el-step title="录入菜单" description="录入商品"></el-step>
      <el-step title="正式营业" description="业务经理到店线下审核，审核通过即可开门营业"></el-step>
    </el-steps>

    <el-form :model="applyForm" :rules="rules" ref="ruleForm" label-width="150px" class="shop">
      <el-form-item label="门店名称" prop="name">
        <el-input v-model="applyForm.name" clearable></el-input>
      </el-form-item>

      <el-form-item label="联系人" prop="shopkeeper">
        <el-input v-model="applyForm.shopkeeper" clearable></el-input>
      </el-form-item>

      <el-form-item label="联系电话" prop="phone">
        <el-input type="text" prefix-icon="el-icon-mobile" placeholder="请输入联系电话"
                  clearable v-model="applyForm.phone" oninput = "value=value.replace(/[^\d]/g,'')" maxlength="11"/>
      </el-form-item>

      <el-form-item label="门店地址" prop="address">
        <el-input v-model="applyForm.address" clearable></el-input>
      </el-form-item>

      <el-form-item label="店铺商标图">
        <el-upload
          action="https://jsonplaceholder.typicode.com/posts/"
          list-type="picture-card"
          :limit="1"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>

      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="applyForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="applyForm.checkPass" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item label="营业执照照片">
        <el-upload
          action="https://jsonplaceholder.typicode.com/posts/"
          list-type="picture-card"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
      </el-form-item>

      <el-form-item>
        <el-button type="warning" class="btn" @click="next">下一步</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
  import selectAddress from '@/components/Address'
  export default {
    name: "ShopSignIn",
    components:{
      selectAddress
    },
    data() {
      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.applyForm.checkPass !== '') {
            this.$refs.applyForm.validateField('checkPass');
          }
          callback();
        }
      }
      const validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.applyForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      }
      return {
        active:0,
        dialogImageUrl: '',
        dialogVisible: true,

        applyForm: {
          name: '',
          shopkeeper: '',
          phone: '',
          address: '',
          options:food,
          value: '',
          imageUrl: '',
          license:'',
          registration:'',
          representative:'',
          manageLocation:'',
          pass: '',
          checkPass: ''
        },
        rules: {
          name: [
            { required: true, message: '请输入门店名称', trigger: 'blur' },
            { min: 2, max: 16, message: '门店名称长度在 2到 16 个字符', trigger: 'blur' }
          ],
          shopkeeper: [
            { required: true, message: '请输入联系人姓名', trigger: 'change' }
          ],
          phone: [
            { required: true, message: '请输入联系电话', trigger: 'blur' },
            { min: 11, max: 11, message: '号码长度必须为 11 位', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '请选择地址', trigger: 'change' }
          ],
          pass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 3, max: 30, message: '密码长度在 3 到 30 个字符', trigger: 'blur' }
          ],
          checkPass: [
            { required: true, message: '请确认密码', trigger: 'blur' },
            { min: 3, max: 30, message: '密码长度在 3 到 30 个字符', trigger: 'blur' }
          ]
        }
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleChange(value) {
      console.log(value);
    },
    next: function () {
      const reg = 11 && /^((13|14|15|17|18)[0-9]\d{8})$/;
      if (this.applyForm.name === '') {
        this.$message({
          message: '门店名称不能为空',
          type: 'error',
          center: true
        });
        return
      }
      if (this.applyForm.shopkeeper === '') {
        this.$message({
          message: '联系人不能为空',
          type: 'error',
          center: true
        });
        return
      }
      if (this.applyForm.phone === '') {
        this.$message({
          message: '联系电话不能为空',
          type: 'error',
          center: true
        });
        return
      }
      if (!reg.test(this.applyForm.phone)) {
        this.$message({
          message: '请输入正确的联系电话',
          type: 'error',
          center: true
        });
        return
      }
      if (this.applyForm.pass !== this.applyForm.checkPass) {
        this.$message({
          message: '两次密码不一致',
          type: 'error',
          center: true
        });
        return
      }

      // FIXME: 表单验证成功，开始注册
      this.$syspost('/signIn/shop', {
        name: this.applyForm.name,
        managerName: this.applyForm.shopkeeper,
        telephone: this.applyForm.phone,
        address: this.applyForm.address,
        password: this.applyForm.pass
      }).then(res => {
        let msg = res.data.msg
        // 注册成功
        if (msg === 'SUCCESS') {
          this.active = 4
          // 询问
          this.$confirm('入驻平台成功，是否前去登录?', '提示', {
            confirmButtonText: '前去登录',
            cancelButtonText: '回到主页',
            type: 'warning'
          }).then(() => {
            this.$router.push('/login')
          }).catch(() => {
            this.$router.push('/index')
          })
        } else {
          this.$message({
            message: msg,
            type: 'error',
            center: true
          })
        }
      }).then(err => console.log(err))

      // if (this.active++ > 2) {
      //   this.active = 0;
      //   //this.dialogVisible = false;
      // }
    }
  }
  }

  const food = [
    {
    "value": "美食",
    "label": "美食",
    "children": [{
      "value": "快餐小吃",
      "label": "快餐小吃",
      "children": [
        {"value": "快餐简餐", "label": "快餐简餐"}, {"value": "面馆", "label": "面馆"},
        {"value": "米粉/米线","label": "米粉/米线"}, {"value": "鸭脖/卤味/熟食", "label": "鸭脖/卤味/熟食"},
        {"value": "包子/粥", "label": "包子/粥"}, {"value": "麻辣烫/关东煮", "label": "麻辣烫/关东煮"},
        {"value": "炸鸡/鸡排/炸串", "label": "炸鸡/鸡排/炸串"}, {"value": "饺子", "label": "饺子"},
        {"value": "黄焖鸡米饭", "label": "黄焖鸡米饭"}, {"value": "沙县小吃", "label": "沙县小吃"},
        {"value": "羊汤/牛肉汤", "label": "羊汤/牛肉汤"}, {"value": "凉皮", "label": "凉皮"},
        {"value": "冒菜", "label": "冒菜"}, {"value": "烧饼/馅饼/手抓饼", "label": "烧饼/馅饼/手抓饼"},
        {"value": "盖浇饭", "label": "盖浇饭"}, {"value": "肉夹馍", "label": "肉夹馍"}]
    }]
  }];


</script>

<style scoped>
  .step{
    margin:100px 0 100px 0;
  }

  .btn{
    margin: 10px 50px 100px 50px;
  }

  .shop{
    width:500px;
    margin:auto;
    align:center;
  }

</style>
