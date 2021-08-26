<template>
  <div class="dashboard-container">
    <iframe src="http://www.djbx.com/" />
  </div>
</template>

<script>
export default {
  name: 'DaJia'
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
iframe{
  width: 100%;
  height: 100%;
  border: none;
}
.dashboard-container{
  width: 100%;
  height: 100vh;
  margin: 0;
}
.box-card{
  margin:0.5rem;
}
h2,p{
  margin: 0.4rem 0;
}

h4,i{
  margin: 0.4rem 0;
  width: 100%;
  text-align: center;
  border-radius: 0.25rem;
  padding: 0.25rem;
}
</style>
