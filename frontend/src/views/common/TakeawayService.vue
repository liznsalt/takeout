<template>
  <div>
    <component :is="componentName"></component>
  </div>
</template>

<script>
  import consumerHomePage from '../consumer/ConsumerHomePage'
  import shopHomePage from '../shop/ShopHomePage'

    export default {
      name: "TakeawayService",
      components:{
        consumerHomePage,
        shopHomePage
      },
      computed: {
        componentName() {
          if (this.$db.get('ROLES') === '"shop"') {
            return "shopHomePage"
          } else {
            return "consumerHomePage"
          }
        }
      }
    }

</script>

<style scoped>

</style>
