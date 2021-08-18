<template>
  <el-dialog title="收货地址" :visible.sync="config.dialogFormVisible">
    <el-form :model="form">
      <el-form-item label="目标中心支公司" :label-width="formLabelWidth">
        <el-select v-model="form.manageCom" placeholder="目标中心支公司" style="width:60%;">
          <el-option v-for="(option,index) in list.manageComList" :key="index" :label="option.label" :value="option.value">
            <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
            <span style="float: right">{{ option.label }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="目标团队" :label-width="formLabelWidth">
        <el-select v-model="form.agentGroup" placeholder="目标团队" style="width:60%;">
          <el-option v-for="(option,index) in list.agentGroupList" :key="index" :label="option.label" :value="option.value">
            <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
            <span style="float: right">{{ option.label }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="调动日期" :label-width="formLabelWidth">
        <el-date-picker
          v-model="form.modifyDate"
          type="date"
          placeholder="选择日期"
          :disabled="true"
        />
      </el-form-item>
      <el-form-item label="管理员" :label-width="formLabelWidth">
        <el-input v-model="form.operator" autocomplete="off" :disabled="true" />
      </el-form-item>
      <el-form-item label="异动人员列表" :label-width="formLabelWidth">
        <el-input v-model="form.agentCodeList" autocomplete="off" :disabled="true" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="config.dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="config.dialogFormVisible = false">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
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
        operations: '',
        agentCodeList: ''
      },
      list: {
        manageComList: '',
        agentGroupList: ''
      },
      formLabelWidth: '120px'
    }
  },
  mounted() {
    this.$bus.$on('OPEN_PERSON_CHANGE_DIALOG', () => {
      this.config.dialogFormVisible = true
      console.log(this.config.dialogFormVisible)
    })
  },
  beforeDestroy() {
    this.$off('OPEN_PERSON_CHANGE_DIALOG')
  }
}
</script>
