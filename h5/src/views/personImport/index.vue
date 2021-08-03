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
        <el-button type="secondary" @click="resetForm">重置</el-button>
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
        .then(isValid => {
          console.log('代理人信息验证通过', isValid)
        })
        .catch(err => {
          error = true
          console.log('代理人信息验证不通过', err)
        })
        .then(
          () => {
            form2.validate()
              .then(
                isValid => {
                  if (!error && isValid) {
                    console.log('行政信息验证通过', isValid)
                    // 发起请求
                    // ......
                  } else {
                    console.log('行政信息验证不通过', error)
                  }
                },
                err => {
                  error = true
                  console.log('行政信息验证不通过', err)
                }
              )
          }
        )
    },
    resetForm() {
      this.$refs.agentData.$refs.agentForm.resetFields()
      this.$refs.adminData.$refs.adminForm.resetFields()
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
