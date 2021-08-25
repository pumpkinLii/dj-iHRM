<template>
  <el-dialog title="修改" :visible.sync="config.dialogFormVisible" :before-close="handleClose">
    <el-form ref="modifyDialog" :model="form" label-width="130px" :rules="rules">
      <el-row>
        <el-col :span="12">
          <el-form-item label="人员代码">
            <el-input v-model="form.agentCode" autocomplete="off" style="width:80%;" :disabled="true" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="人员姓名">
            <el-input v-model="form.agentName" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="管理机构代码">
            <el-input v-model="form.comCode4" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管理机构名称">
            <el-input v-model="form.manageCom4" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="团队代码">
            <el-input v-model="form.agentGroup" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="团队名称">
            <el-input v-model="form.branchName" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="职级">
            <el-input v-model="form.agentGrade" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="解约日期" prop="diffDate">
            <el-date-picker
              v-model="form.diffDate"
              style="width:80%;"
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="解约原因" prop="diffCause">
            <el-select v-model="form.diffCause" placeholder="请选择解约原因" style="width:80%;">
              <el-option v-for="(option,index) in list.departReason" :key="index" :label="option.name" :value="option.code">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.code }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="23">
          <el-form-item label="说明">
            <el-input
              v-model="form.explain"
              type="textarea"
              :rows="4"
              placeholder="请输入内容"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { submitModifyInformation } from '@/api/peopleDepart'

export default {
  name: 'LeaveModifyDialog',
  data() {
    return {
      config: {
        dialogFormVisible: false
      },
      rules: {
        diffCause:
          [{ required: true, message: '请选择离职原因', trigger: 'change' }],
        diffDate:
          [{ required: true, message: '请选择离职日期', trigger: 'change' }]
      },
      form: {
        agentCode: '',
        agentName: '',
        comCode4: '',
        manageCom4: '',
        agentGroup: '',
        branchName: '',
        agentGrade: '',
        diffDate: '',
        diffCause: '',
        explain: '',
        agentState: '',
        operator: 'admin',
        diffCauseName: ''
      },
      list: {
        departReason: [{ code: '01', name: '主动离职' }, { code: '02', name: '单方解除' }, { code: '03', name: '协商解除' }, {
          code: '04',
          name: '外勤转内勤'
        }]
      }
    }
  },
  mounted() {
    // 打开新增对话框
    this.$bus.$on('MODIFY_DIALOG', (item) => {
      console.log('item:', item)
      this.form.agentCode = item.agentCode
      this.form.agentName = item.agentName
      this.form.comCode4 = item.comCode4
      this.form.manageCom4 = item.manageCom4
      this.form.agentGroup = item.agentGroup
      this.form.branchName = item.branchName
      this.form.agentGrade = item.agentGradeName
      this.form.diffDate = item.diffDate
      this.form.diffCause = item.diffCauseCom
      this.form.diffCauseName = item.diffCauseName
      this.form.explain = item.illustrate
      this.config.dialogFormVisible = true
    })
  },
  methods: {
    handleClose() {
      this.config.dialogFormVisible = false
      this.form.diffDate = ''
      this.form.diffCause = ''
      this.form.explain = ''
      this.$nextTick(() => {
        this.$refs['modifyDialog'].clearValidate() // 只清除清除验证
      })
    },
    submit() {
      const data = {
        agentCode: this.form.agentCode,
        diffDate: this.form.diffDate,
        diffCause: this.form.diffCause,
        explain: this.form.explain
      }
      submitModifyInformation(data).then(() => {
        this.$message.success('修改成功')
      })
      this.form.diffDate = ''
      this.form.diffCause = ''
      this.form.explain = ''
      this.$nextTick(() => {
        this.$refs['modifyDialog'].clearValidate() // 只清除清除验证
      })
      this.$bus.$emit('REFRESH_LEAVE')
      this.config.dialogFormVisible = false
    }
  }

}
</script>
