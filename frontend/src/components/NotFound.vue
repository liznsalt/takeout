<template>
    <div>{{text}}</div>
</template>

<script>
  export default {
    name: "NotFound",
    props: ['content'],
    data() {
      return {
        text: this.content
      }
    }
  }
</script>

<style scoped>

</style>
