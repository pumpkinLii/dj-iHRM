<template>
  <h1>dialog</h1>
</template>

<script>
export default {
  name: 'PersonChangeDialog',
  data() {
    return {
      config: {
        visible: false
      }
    }
  },
  mounted() {
    this.$on('OPEN_PERSON_CHANGE_DIALOG', () => {
      this.config.visible = true
    })
  },
  beforeDestroy() {
    this.$off('OPEN_PERSON_CHANGE_DIALOG')
  }
}
</script>
