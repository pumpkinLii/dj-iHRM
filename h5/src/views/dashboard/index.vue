<template>
  <div class="dashboard-container">
    <iframe frameborder="0" src="http://www.djbx.com/" />
  </div>
</template>

<script>
export default {
  name: 'Dashboard'
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
}
.dashboard-container{
  width: 100%;
  height: 100vh;
  margin: 0;
}
</style>
