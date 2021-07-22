<template>
  <div class="hello">
    <button @click="text">接口测试</button>
  </div>
</template>

<script>
import { getList } from '@/api/table'
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  created() {
  },
  methods: {
    text(){
      let obj={
        "partner":"01"
      }
      getList(obj).then(res=>{
        console.log(res)
      })
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
