<template>
  <el-col :span="dynamicTags.length * 2">
    <el-tag :key="tag" v-for="tag in dynamicTags" closable :disable-transitions="true" @close="handleClose(tag)"
            @click="clickTag(tag)" :effect="effectValue(tag)" type="warning" class="tag">{{tag}}</el-tag>
  </el-col>
</template>

<script>
  import routerUtil from '../utils/routerUtil'

  export default {
    name: "TagList",
    data() {
      return {
        dynamicTags: ['首页']
      }
    },
    methods: {
      handleClose(tag) {
        let idx = this.dynamicTags.indexOf(tag)
        // 不允许关掉首页tag
        if (idx === 0) {
          return
        }
        // if关掉的是当前页面
        if (this.$route.path === routerUtil.tagToUrl(this.dynamicTags[idx])) {
          this.$router.push(routerUtil.tagToUrl(this.dynamicTags[idx-1]))
        }
        // 删掉此标签
        this.dynamicTags.splice(idx, 1);
      },
      clickTag(tag) {
        this.$router.push(routerUtil.tagToUrl(tag))
      },
      effectValue(tag) {
        if (this.$route.path === routerUtil.tagToUrl(tag)) {
          return "dark"
        }
        return "plain"
      }
    },
    watch: {
      $route (to, from) {
        let tagName = routerUtil.urlToTag(to.path)
        if (this.dynamicTags.indexOf(tagName) === -1) {
          this.dynamicTags.push(tagName)
        }
      }
    },
    created() {
      // 防止刷新
      let tagName = routerUtil.urlToTag(this.$route.path)
      if (this.dynamicTags.indexOf(tagName) === -1) {
        this.dynamicTags.push(tagName)
      }
    }
  }
</script>

<style scoped>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
  .tag:hover{
    cursor: pointer;
  }
</style>
