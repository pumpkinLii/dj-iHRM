<template>
  <el-dialog title="新增" :visible.sync="config.visible" width="80%" :before-close="handleDialogClose">
    <span>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="人员工号" prop="agentCode">
              <el-input v-model="form.agentCode" placeholder="请输入人员工号" type="text" style="width:60%;" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="人员姓名" prop="agentName">
              <el-input v-model="form.agentName" type="text" style="width:60%;" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="资格证书名称"><!--????????-->
              <el-select v-model="form.certificateCode" placeholder="否" style="width:60%;" disabled>
                <el-option label="否" value="N" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="团队名称" prop="branchName">
              <el-input v-model="form.branchName" type="text" style="width:60%;" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="负责人代码" prop="branchManager">
              <el-input v-model="form.branchManager" placeholder="输入后自动获取姓名与手机号" type="text" style="width:60%;" @blur="handleBranchManagerQuery" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="负责人姓名" prop="branchManagerName">
              <el-input v-model="form.branchManagerName" type="text" style="width:60%;" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="负责人手机号" prop="branchManagerPhone">
              <el-input v-model="form.branchManagerPhone" type="text" style="width:60%;" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="成立时间" prop="branchEffDate">
              <el-date-picker
                v-model="form.branchEffDate"
                value-format="yyyy-MM-dd"
                type="date"
                placeholder=""
                style="width:60%;"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="停业标志" prop="branchStatus">
              <el-select v-model="form.branchStatus" placeholder="否" style="width:60%;" disabled>
                <el-option label="否" value="N" />
                <el-option label="是" value="Y" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作员" prop="operator">
              <el-input v-model="form.operator" type="text" style="width:60%;" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="停业日期" prop="branchTerminateEffDate">
              <el-date-picker v-model="form.branchTerminateEffDate" disabled type="date" placeholder="选择停业时间" style="width:60%;" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="群聊名称" prop="chatName">
              <el-input v-model="form.chatName" type="text" style="width:60%;" />
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
    </span>
    <!-- 底部 -->
    <span slot="footer" class="dialog-footer">
      <el-button type="secondary" @click="$emit('CLOSE_GROUP_ADD_DIALOG')">取 消</el-button>
      <el-button type="primary" @click="handleSubmit">保 存</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: 'QualificationAddDialog'
}
</script>

<style scoped>

</style>
