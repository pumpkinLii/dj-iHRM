<template>
  <div class="app-container">
    <h4>资格证管理</h4>
    <el-form ref="form" :rules="rules" :model="form" label-width="180px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="二级管理机构" prop="manageCom2">
            <el-select v-model="form.manageCom2" placeholder="请选择" style="width:100%;" @change="changeCom2">
              <el-option v-for="(option,index) in list.manageCom2List" :key="index" :label="option.name" :value="option.comcode">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.comcode }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="三级管理机构">
            <el-select v-model="form.manageCom3" placeholder="选择上一级下拉列表" style="width:100%;" @change="changeCom3">
              <el-option v-for="(option,index) in list.manageCom3List" :key="index" :label="option.name" :value="option.comcode">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.comcode }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="四级管理机构">
            <el-select v-model="form.manageCom4" placeholder="选择上一级下拉列表" style="width:100%;" @change="changeCom4">
              <el-option v-for="(option,index) in list.manageCom4List" :key="index" :label="option.name" :value="option.comcode">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.comcode }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="团队">
            <el-select v-model="form.branchAttr" style="width:100%;" placeholder="选择上一级下拉列表">
              <el-option v-for="(option,index) in list.branchAttrList" :key="index" :label="option.name" :value="option.code">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.code }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人员工号">
            <el-input v-model="form.agentCode" type="text" style="width:100%;" placeholder="可选项" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人员姓名">
            <el-input v-model="form.agentName" type="text" style="width:100%;" placeholder="可选项" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="有效起期">
            <el-date-picker v-model="form.startEffectiveDate" value-format="yyyy-MM-dd" type="date" placeholder="可选项" style="width:100%;" @change="changeStartEffectiveDate" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="有效止期" prop="endEffectiveDate">
            <el-date-picker v-model="form.endEffectiveDate" value-format="yyyy-MM-dd" type="date" placeholder="可选项" style="width:100%;" :disabled="form.startEffectiveDate === ''" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="资格证书名称">
            <el-select v-model="form.certificateType" placeholder="可选项" style="width:100%;">
              <el-option v-for="(option,index) in list.certificateTypeList" :key="index" :label="option.name" :value="option.code">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.code }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="">
          <el-col style="text-align:left;margin-top: 1rem">
            <el-button type="primary" icon="el-icon-search" @click="handleQuery(true)">查询</el-button>
            <el-button type="success" icon="el-icon-edit" @click="showQualificationAddDialog">新增</el-button>
            <el-button type="secondary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
    <el-divider />
    <QualificationTable />
    <QualificationAddDialog />
  </div>
</template>

<script>
import QualificationTable from '@/components/Qualification/QualificationTable'
import QualificationAddDialog from '@/components/Qualification/QualificationAddDialog'
import { getInitializeList, getNextOptions } from '@/api/qualification'
export default {
  name: 'Qualification',
  components: { QualificationAddDialog, QualificationTable },
  data() {
    const dataValid = (rule, value, callback) => {
      const startDate = new Date(Date.parse(this.form.startEffectiveDate.replace(/-/g, '/')))
      const endDate = new Date(Date.parse(this.form.endEffectiveDate.replace(/-/g, '/')))
      if (startDate > endDate) {
        callback(new Error('截至日期不能小于起始日期'))
      }
      callback()
    }
    return {
      form: {
        manageCom2: '',
        manageCom3: '',
        manageCom4: '',
        branchAttr: '',
        agentCode: '',
        agentName: '',
        startEffectiveDate: '',
        endEffectiveDate: '',
        certificateType: ''
      },
      code: '',
      list: {
        manageCom2List: [],
        manageCom3List: [],
        manageCom4List: [],
        branchAttrList: [],
        certificateTypeList: []
      },
      rules: {
        endEffectiveDate:
          [{ validator: dataValid, trigger: 'change' }]
      }
    }
  },
  beforeDestroy() {
    this.$bus.$off('QUALIFICATION_SUCCESS')
  },
  mounted() {
    this.$bus.$on('QUALIFICATION_SUCCESS', () => {
      this.$bus.$emit('QUERY', this.form)
    })
  },
  created() {
    getInitializeList().then(res => {
      this.list.manageCom2List = res.com2List
      this.list.certificateTypeList = res.certificateTypeList
    })
  },
  methods: {
    resetForm() {
      this.$refs.form.resetFields()
      this.$bus.$emit('RESET_QUALIFICATION_TABLE')
    },
    showQualificationAddDialog() {
      this.$bus.$emit('OPEN_QUALIFICATION_ADD_DIALOG', this.form)
    },
    handleQuery(withWarning) { // withWarning:表单检查失败时是否会红色提醒用户 true:会 false:不会提醒用户
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.$bus.$emit('QUERY', this.form)
        } else {
          if (withWarning) {
            return false
          }
        }
      })
    },
    changeCom2() {
      console.log(this.form.manageCom2)
      this.code = this.form.manageCom2
      getNextOptions(this.code).then(res => {
        this.list.manageCom3List = res.comList
      })
      this.form.manageCom3 = ''
      this.form.manageCom4 = ''
      this.form.branchAttr = ''
      this.list.manageCom4List = ''
      this.list.branchAttrList = ''
    },
    changeCom3() {
      this.code = this.form.manageCom3
      console.log('this.code' + this.code)
      getNextOptions(this.code).then(res => {
        this.list.manageCom4List = res.comList
      })
      this.form.manageCom4 = ''
      this.form.branchAttr = ''
      this.list.branchAttrList = ''
    },
    changeCom4() {
      this.code = this.form.manageCom4
      getNextOptions(this.code).then(res => {
        this.list.branchAttrList = res.comList
      })
      this.form.branchAttr = ''
    },
    changeStartEffectiveDate() {
      this.form.endEffectiveDate = ''
    }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>
