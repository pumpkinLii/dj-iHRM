<template>
  <div class="app-container">
    <h4>团队维护</h4>
    <el-form ref="form" :rules="rules" :model="form" label-width="180px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="管理机构" prop="manageComCode">
            <el-select v-model="form.manageComCode" placeholder="请选择" style="width:100%;">
              <!--这里label和value是反着的 注意-->
              <el-option v-for="(option,index) in list.manageComCode" :key="index" :label="option.label" :value="option.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队级别">
            <el-select v-model="form.branchLevel" placeholder="可选项" style="width:100%;">
              <el-option label="不选择" value="" />
              <el-option v-for="(option,index) in list.branchLevel" :key="index" :label="option.label" :value="option.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队代码">
            <el-input v-model="form.branchAttr" type="text" style="width:100%;" placeholder="可选项" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="负责人代码">
            <el-input v-model="form.branchManager" type="text" style="width:100%;" placeholder="可选项" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="负责人姓名">
            <el-input v-model="form.branchManagerName" type="text" style="width:100%;" placeholder="可选项" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="负责人手机号" prop="branchManagerPhone">
            <el-input v-model="form.branchManagerPhone" type="text" style="width:100%;" placeholder="可选项" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="成立时间">
            <el-date-picker v-model="form.branchEffDate" type="date" placeholder="可选项" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="停业标志">
            <el-select v-model="form.branchStatus" placeholder="可选项" style="width:100%;">
              <el-option label="否" value="N" />
              <el-option label="是" value="Y" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col style="text-align:center">
          <el-button type="primary" @click="handleQuery">查询</el-button>
          <el-button type="success" @click="config.groupAddDialogVisible=true">新增</el-button>
        </el-col>
      </el-row>
    </el-form>
    <el-divider />
    <GroupTable ref="groupTableData" @QUERY_GROUP="handleQuery(form) " />
    <GroupAddDialog :visible="config.groupAddDialogVisible" @CLOSE_GROUP_ADD_DIALOG="handleGroupAddDialogClose" />
  </div>
</template>

<script>
import GroupTable from '@/components/GroupManagement/GroupTable'
import GroupAddDialog from '@/components/GroupManagement/GroupAddDialog'
import { phoneNumberValidatorAllowNull } from '@/utils/validate'
import { setCodeByName, getManageComCode } from '@/api/code'
export default {
  name: 'TeamHold',
  components: { GroupAddDialog, GroupTable },
  data() {
    return {
      config: {
        groupAddDialogVisible: false
      },
      form: {
        manageComCode: '',
        branchLevel: '',
        branchAttr: '',
        branchManager: '',
        branchManagerName: '',
        branchManagerPhone: '',
        branchEffDate: '',
        branchStatus: ''
      },
      list: {
        manageComCode: [],
        branchLevel: []
      },
      rules: {
        manageComCode:
          [{ required: true, message: '请选择管理机构', trigger: 'change' }],
        branchManagerPhone:
          [{ validator: phoneNumberValidatorAllowNull, trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getInitOptions()
  },
  methods: {
    handleGroupAddDialogClose() {
      this.config.groupAddDialogVisible = false
    },
    handleQuery() {
      this.config.loading = true
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.$refs.groupTableData.handleQueryGroup(this.form)
        } else {
          return false
        }
      })
    },
    getInitOptions() {
      // 获取管理机构下拉菜单
      getManageComCode(this.list)
      // 获取团队级别下拉菜单
      setCodeByName('branchlevel', this.list.branchLevel)
    }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>
