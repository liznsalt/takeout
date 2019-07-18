<template>
  <div>
    <el-upload
      class="avatar-uploader"
      action="http://localhost:8080/takeout/img/upload"
      ref="upload"
      name="picture"
      list-type="picture-card"
      :limit="1"
      :file-list="fileList"
      :on-exceed="onExceed"
      :before-upload="beforeUpload"
      :on-preview="handlePreview"
      :on-success="handleSuccess"
      :on-remove="handleRemove">
      <i class="el-icon-plus"></i>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>

  export default {
    name: "UploadPicture",
    props: ["upUrl"],
    data() {
      return{
        //文件上传的参数
        dialogImageUrl: '',
        dialogVisible: false,
        //图片列表（用于在上传组件中回显图片）
        fileList: [{name: '', url: ''}],
      }
    },
    created() {
      // console.log(this.upUrl)
      this.fileList = [{name: '', url: this.upUrl}]
      if (this.upUrl === '') {
        this.fileList = []
      }
      this.dialogImageUrl = this.upUrl
    },
    methods: {
      //文件上传成功的钩子函数
      handleSuccess(res, file) {
        this.$emit("getUrl", res.data)
        this.dialogImageUrl = res.data
        // console.log(res)
        // console.log(file)
        this.$message({
          type: 'info',
          message: '图片上传成功',
          duration: 6000
        });
        if (file.response.success) {
          this.editor.picture = file.response.message; //将返回的文件储存路径赋值picture字段
        }
      },
      //删除文件之前的钩子函数
      handleRemove(file, fileList) {
        this.$message({
          type: 'info',
          message: '已删除原有图片',
          duration: 6000
        });
      },
      //点击列表中已上传的文件事的钩子函数
      handlePreview(file) {
        this.dialogVisible = true
      },
      //上传的文件个数超出设定时触发的函数
      onExceed(files, fileList) {
        this.$message({
          type: 'info',
          message: '最多只能上传一个图片',
          duration: 6000
        });
      },
      //文件上传前的前的钩子函数
      //参数是上传的文件，若返回false，或返回Primary且被reject，则停止上传
      beforeUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isBMP = file.type === 'image/bmp';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG && !isGIF && !isPNG && !isBMP) {
          this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
      },

      /**
       * 让父组件通知上传
       */
      submitUpload() {
        this.$refs.upload.submit();
      }
    }
  }
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
