<template>
  <div class="app-container">
    <h4>代理人信息</h4>
    <agent-information ref="agentData" />
    <el-divider />
    <h4>行政信息</h4>
    <administrative-information ref="adminData" />
    <el-row>
      <el-col style="text-align:center">
        <el-button type="primary" @click="submitForm">保存</el-button>
        <el-button type="secondary">重置</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AdministrativeInformation from '@/components/AdministrativeInformation.vue'
import AgentInformation from '@/components/AgentInformation.vue'

export default {
  name: 'PersonImport',
  components: { AdministrativeInformation, AgentInformation },
  data() {
    return {

    }
  },
  methods: {
    submitForm() {
      let error = false
      const form1 = this.$refs.agentData.$refs.agentForm
      const form2 = this.$refs.adminData.$refs.adminForm
      form1.validate()
        .then(valid => {
          console.log(valid)
        })
        .catch(err => {
          error = true
          console.log(err)
        })
        .then(
          () => {
            form2.validate()
              .then(
                valid => {
                  console.log('success')
                  console.log(error)
                },
                err => {
                  error = true
                  console.log(err)
                }
              )
          }
        )
    }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
.el-form-item__label{
  padding-left: 1rem;
}
.title{
  margin: 1rem;
  padding: 1rem;
}
</style>
