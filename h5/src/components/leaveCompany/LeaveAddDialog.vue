<template>
  <el-dialog title="新增" :visible.sync="config.dialogFormVisible">
    <el-form :model="form" label-width="130px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="人员代码">
            <el-input v-model="form.agentCode" autocomplete="off" :disabled="true" style="width:80%;" />
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
      <el-row :span="12">
        <el-form-item label="职级">
          <el-input v-model="form.agentGrade" autocomplete="off" :disabled="true" style="width:80%;" />
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="解约日期">
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
          <el-form-item label="解约原因">
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
        <el-col :span="12">
          <el-form-item label="说明">
            <el-input
              v-model="form.explain"
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="config.dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { getPeopleInformation, submitInformation } from '@/api/peopleDepart'

export default {
  name: 'LeaveAddDialog',
  data() {
    return {
      config: {
        dialogFormVisible: false
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
        operator: 'admin'
      },
      list: {
        departReason: [{ code: '01', name: '主动离职' }, { code: '02', name: '单方解除' }, { code: '03', name: '协商解除' }, { code: '04', name: '外勤转内勤' }]
      },
      methods: {
        getInformation(data) {
          getPeopleInformation(data).then(res => {
            this.form.agentName = res.agentName
            this.form.comCode4 = res.manageCom
            this.form.manageCom4 = res.ylName
            this.form.agentGroup = res.branchAttr
            this.form.agentName = res.branchName
            this.form.agentGrade = res.agentGrade
          })
        },
        submit() {
          const data={
            xxx:
          }
          submitInformation()
          this.config.dialogFormVisible = true
        }
      }
    }
  }
}
</script>
