<template>
  <div class="app-container">
    <h4>代理调整</h4>
    <el-form :model="form" :rules="rules" label-width="180px">
      <!--      第一行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="管理机构" prop="manageCom">
            <el-cascader
              v-model="form.manageCom"
              :options="options"
              :show-all-levels="false"
              style="width: 100%"
              :props="{ expandTrigger: 'hover' ,checkStrictly: true}"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队代码">
            <el-input v-model="form.agentGroup" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队名称">
            <el-input v-model="form.branchName" />
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第二行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="代理人代码">
            <el-input v-model="form.branchManager" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="代理人姓名">
            <el-input v-model="form.branchManagerName" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="form.branchManagerPhone" placeholder="请输入手机号" />
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第三行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="证件号码">
            <el-input v-model="form.idNo" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="当前职级">
            <el-select v-model="form.agentGrade" style="width: 100%">
              <el-option v-for="(item,index) in list.agentGrade" :key="index" :value="item.gradecode" :label="item.gradename">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.gradecode }}</span>
                <span style="float: right">{{ item.gradename }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第四行-->
      <el-row>
        <el-form-item>
          <el-col style="text-align:left;margin-top: 1rem">
            <el-button type="primary" icon="el-icon-search" @click="hello">查询</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
    <el-divider />
    <!--    表格-->
    <Table />
  </div>
</template>
<script>
import Table from '@/components/Agentadjust/table'
import * as validator from '@/utils/validate'
import * as V from '@/api/zc'

export default {
  name: '',
  components: { Table },
  data() {
    return {
      form: {
        manageCom: '', // 管理机构
        agentGroup: '', // 团队代码
        branchName: '', // 团队名称
        branchManager: '', // 代理人代码
        branchManagerName: '', // 代理人姓名
        branchManagerPhone: '', // 手机号
        idNo: '', // 证件号码
        agentGrade: '' // 当前职级

      },
      list: {
        agentGrade: ''
      },
      rules: {
        phone:
          [
            { validator: validator.phoneNumberValidator, trigger: 'blur' }
          ],
        manageCom:
          [
            { required: true, message: '请选择管理机构', trigger: 'blur' }
          ]
      },
      options: []
    }
  },
  created() {
    V.abc().then((r) => {
      console.log(r)
      this.options = r.result
    })
    V.staff().then((r) => {
      this.list.agentGrade = r.list
    })
  },
  methods: {
    hello() {
      V.ttt().then((r) => {
        console.log(r)
        this.$bus.$emit('form3', r
        )
        console.log('成功了')
      }).catch(() => {
        console.log('失败了')
      })
    }
  }
}
</script>
<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>
