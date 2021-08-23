<template>
  <el-dialog title="新增" :visible.sync="config.dialogFormVisible">
    <el-form :model="form" label-width="130px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="人员代码">
            <el-input v-model="form.adminCode" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="人员姓名">
            <el-input v-model="form.adminCode" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="管理机构代码">
            <el-input v-model="form.adminCode" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管理机构名称">
            <el-input v-model="form.adminCode" autocomplete="off" :disabled="true" style="width:80%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :span="12">
        <el-form-item label="职级">
          <el-input v-model="form.adminCode" autocomplete="off" :disabled="true" style="width:80%;" />
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="解约日期">
            <el-date-picker
              v-model="form.modifyDate"
              style="width:80%;"
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="解约原因">
            <el-select v-model="form.agentGroup" placeholder="请选择解约原因" style="width:80%;" @change="getInformation">
              <el-option v-for="(option,index) in list.departReason" :key="index" :label="option.name" :value="option.branchAttr">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.branchAttr }}</span>
                <span style="float: right">{{ option.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="说明">
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
export default {
  name: 'LeaveAddDialog',
  data() {
    return {
      config: {
        dialogFormVisible: false
      },
      form: {
        peopleCode: '',
        peopleName: '',
        manageCode: '',
        manageName: '',
        teamCode: '',
        teamName: '',
        rank: '',
        departData: '',
        departReason: '',
        description: ''
      },
      list: {
        departReason: []
      }
    }
  }
}
</script>
