<template>
  <div class="app-container">
    <h4>人员异动</h4>
    <el-form ref="form" :rules="rules" :model="form" label-width="180px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="管理机构" prop="manageCom">
            <el-select v-model="form.manageCom" placeholder="请选择" style="width:100%;">
              <el-option v-for="(option,index) in list.manageCom" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队代码">
            <el-input v-model="form.branchAttr" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队名称">
            <el-input v-model="form.branchName" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="代理人代码">
            <el-input v-model="form.agentCode" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="代理人姓名">
            <el-input v-model="form.agentName" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机号" prop="agentPhone">
            <el-input v-model="form.agentPhone" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="证件号码" prop="idNo">
            <el-input v-model="form.idNo" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item>
          <el-col style="text-align:left;margin-top: 1rem">
            <el-button type="primary" icon="el-icon-search" @click="handleQuery">查询</el-button>
            <el-button type="success" icon="el-icon-edit" @click="handleOpenChangeDialog">异动</el-button>
            <el-button type="secondary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
    <el-divider />
    <PersonChangeTable />
  </div>
</template>

<script>
import { phoneNumberValidatorAllowNull } from '@/utils/validate'
import { getManageComCode } from '@/api/code'
import PersonChangeTable from '@/components/PersonChange/PersonChangeTable'
export default {
  name: 'PersonnelChange',
  components: { PersonChangeTable },
  data() {
    return {
      form: {
        manageCom: '',
        branchAttr: '',
        branchName: '',
        agentCode: '',
        agentName: '',
        agentPhone: '',
        idNo: ''
      },
      list: {
        manageCom: []
      },
      rules: {
        manageCom:
          [{ required: true, message: '请选择管理机构', trigger: 'change' }],
        agentPhone:
          [{ validator: phoneNumberValidatorAllowNull, trigger: 'blur' }]
      }
    }
  },
  mounted() {
    this.getInitOptions()
  },
  methods: {
    resetForm() {
      this.$refs['form'].resetFields()
      Object.keys(this.form).forEach((key) => {
        this.form[key] = ''
      })
      this.$bus.$emit('RESET_PERSON_CHANGE_TABLE')
    },
    handleQuery() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.$bus.$emit('QUERY_PERSON_CHANGE', this.form)
        } else {
          return false
        }
      })
    },
    handleOpenChangeDialog() {
      this.$bus.$emit('OPEN_PERSON_CHANGE_DIALOG_FETCH')
    },
    getInitOptions() {
      // 获取管理机构下拉菜单
      getManageComCode().then(
        r => {
          this.$log('成功获取管理机构下拉菜单请求', r)
          this.list.manageCom = r['totallist']
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
</style>
