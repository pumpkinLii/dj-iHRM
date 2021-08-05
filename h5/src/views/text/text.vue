<template>
  <div class="wscn-http404-container">ceshi123123</div>
</template>

<script>
import { text } from '@/api/user'
export default {
  name: 'Page404',
  computed: {
    message() {
      return 'The webmaster said that you can not enter this page...'
    }
  },
  created() {
    console.log('dayin')
    console.log(this.$router)
    const obj = {
      userId: '0'
    }

    const parme = 'page=1&limit=10'
    text(obj, parme).then((res) => {
      console.log('接口请求成功')
      console.log(res)
    })
  }
}
</script>
