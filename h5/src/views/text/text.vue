<template>
  <div class="wscn-http404-container">
    ceshi123123
  </div>
</template>

<script>

export default {
  name: 'Page404',
  computed: {
    message() {
      return 'The webmaster said that you can not enter this page...'
    }
  },
  created(){
    console.log("dayin")
    console.log(this.$router)
  }
}
</script>
