<template>
  <div class="app-container">
    <h4>资格证管理</h4>
    <el-form ref="form" :rules="rules" :model="form" label-width="180px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="二级管理机构" prop="mangeCom2">
            <el-select v-model="form.mangeCom2" placeholder="请选择" style="width:100%;">
              <el-option v-for="(option,index) in list.mangeCom2" :key="index" :label="option.label" :value="option.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="三级管理机构">
            <el-select v-model="form.mangeCom3" style="width:100%;">
              <el-option v-for="(option,index) in list.mangeCom3" :key="index" :label="option.label" :value="option.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="四级管理机构">
            <el-select v-model="form.mangeCom4" style="width:100%;">
              <el-option v-for="(option,index) in list.mangeCom4" :key="index" :label="option.label" :value="option.value" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="团队">
            <el-select v-model="form.branchAttr" style="width:100%;">
              <el-option v-for="(option,index) in list.branchAttr" :key="index" :label="option.label" :value="option.value" />
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
            <el-date-picker v-model="form.startEffectiveDate" value-format="yyyy-MM-dd" type="date" placeholder="可选项" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="有效止期">
            <el-date-picker v-model="form.endEffectiveDate" value-format="yyyy-MM-dd" type="date" placeholder="可选项" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="资格证书名称">
            <el-select v-model="form.certificateType" placeholder="可选项" style="width:100%;">
              <el-option v-for="(option,index) in list.certificateType" :key="index" :label="option.label" :value="option.value" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="">
          <el-col style="text-align:left;margin-top: 1rem">
            <el-button type="primary" icon="el-icon-search" @click="handleQuery(true)">查询</el-button>
            <el-button type="success" icon="el-icon-edit" @click="config.groupAddDialogVisible=true">新增</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
    <el-divider />
    <QualificationTable ref="qualificationTableData" @QUERY_GROUP="handleQuery(false)" />
    <QualificationAddDialog :visible="config.qualificationAddDialogVisible" @QUERY_GROUP="handleQuery(false)" @CLOSE_GROUP_ADD_DIALOG="handleQualificationAddDialogClose" />
  </div>
</template>

<script>
import QualificationTable from '@/components/Qualification/QualificationTable'
import QualificationAddDialog from '@/components/Qualification/QualificationAddDialog'
export default {
  name: 'Qualification',
  components: { QualificationAddDialog, QualificationTable },
  data() {
    return {
      config: {
        qualificationAddDialogVisible: false
      },
      form: {
        mangeCom2: '',
        mangeCom3: '',
        mangeCom4: '',
        branchAttr: '',
        agentCode: '',
        agentName: '',
        startEffectiveDate: '',
        endEffectiveDate: '',
        certificateType: ''
      },
      list: {
        mangeCom2: [],
        mangeCom3: [],
        mangeCom4: [],
        branchAttr: [],
        certificateType: []
      },
      rules: {
      }
    }
  },
  created() {
    // this.getInitOptions()
  },
  methods: {
    handleQualificationAddDialogClose() {
      this.config.qualificationAddDialogVisible = false
    },
    handleQuery(withWarning) { // withWarning:表单检查失败时是否会红色提醒用户 true:会 false:不会提醒用户
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 要在qualificationtable.vue 中加入handleQueryGroup方法查询
          this.$refs.qualificationTableData.handleQueryGroup(this.form)
        } else {
          if (withWarning) {
            return false
          }
        }
      })
    },
    handleRefreshTable() {
      // 要在qualificationtable.vue 中加入handleQueryGroup方法查询
      this.$refs.qualificationTableData.handleQueryGroup(this.form)
    }
    // getInitOptions() {
    //   // 获取管理机构下拉菜单
    //   getManageComCode()
    //     .then(
    //       r => {
    //         this.list.manageComCode = r.totallist
    //       }
    //     )
    // }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>
