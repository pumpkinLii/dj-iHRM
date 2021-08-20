<template>
  <el-dialog title="人员异动" :visible.sync="config.dialogFormVisible">
    <el-form :model="form" label-width="130px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="目标中心支公司">
            <el-select v-model="form.manageCom" placeholder="目标中心支公司" style="width:80%;" autocomplete="off" @change="getAgentGroupList">
              <el-option v-for="(option,index) in list.manageComList" :key="index" :label="option.name" :value="option.code">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.code }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="目标团队">
            <el-select v-model="form.agentGroup" placeholder="目标团队" style="width:80%;" @change="getInformation">
              <el-option v-for="(option,index) in list.agentGroupList" :key="index" :label="option.name" :value="option.branchAttr">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.branchAttr }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="调动日期">
            <el-date-picker
              v-model="form.modifyDate"
              style="width:80%;"
              type="date"
              placeholder="选择日期"
              :disabled="true"
              value-format="yyyy-MM-dd"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="目标团队主管代码">
            <el-input v-model="form.adminCode" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="目标团队主管姓名">
            <el-input v-model="form.adminName" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="config.dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="changeAgent">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { change, getNextOptions, getSelectOptions, submit } from '@/api/personChange'

export default {
  name: 'PersonChangeDialog',
  data() {
    return {
      config: {
        dialogFormVisible: false
      },
      form: {
        manageCom: '',
        agentGroup: '',
        modifyDate: '',
        adminCode: '',
        adminName: '',
        agentCodeList: []
      },
      list: {
        manageComList: '',
        agentGroupList: ''
      }
    }
  },
  mounted() {
    this.$bus.$on('OPEN_PERSON_CHANGE_DIALOG', (select) => {
      this.config.dialogFormVisible = true
      getSelectOptions().then(res => {
        this.list.manageComList = res.list
      })
      this.form.agentCodeList = select
    })
  },
  beforeDestroy() {
    this.$off('OPEN_PERSON_CHANGE_DIALOG')
  },
  methods: {
    getAgentGroupList() {
      getNextOptions(this.form.manageCom).then(res => {
        this.form.agentGroup = ''
        this.list.agentGroupList = res.list
      })
    },
    getInformation() {
      submit(this.form.agentGroup).then(res => {
        this.form.adminCode = res.managerId
        this.form.adminName = res.managerName
        this.form.modifyDate = new Date().toLocaleDateString().split('/').join('-')
        console.log(this.form.modifyDate)
      })
    },
    changeAgent() {
      change(this.form)
      this.config.dialogFormVisible = false
    }
  }
}
</script>
