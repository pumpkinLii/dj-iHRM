<template>
  <el-dialog title="新增" :visible.sync="visible" width="80%" :show-close="false" :close-on-press-escape="false">
    <span>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="管理机构" prop="manageComCode4">
              <el-select v-model="form.manageComCode4" placeholder="请选择管理机构" style="width:60%;">
                <el-option v-for="(option,index) in list.manageComCode4" :key="index" :label="option.label" :value="option.value" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="团队级别" prop="branchLevel">
              <el-select v-model="form.branchLevel" placeholder="请选择" style="width:60%;">
                <el-option v-for="(option,index) in list.branchLevel" :key="index" :label="option.label" :value="option.value" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="团队代码"><!--这里没有对应的数据-->
              <el-input type="text" style="width:60%;" disabled />
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
              <el-input v-model="form.branchManager" type="text" style="width:60%;" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="负责人姓名" prop="branchManagerName">
              <el-input v-model="form.branchManagerName" type="text" style="width:60%;" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="负责人手机号" prop="branchManagerPhone">
              <el-input v-model="form.branchManagerPhone" type="text" style="width:60%;" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="成立时间" prop="branchEffDate">
              <el-date-picker
                v-model="form.branchEffDate"
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
              <el-select v-model="form.branchStatus" placeholder="否" disabled style="width:60%;">
                <el-option label="是" value="Y" />
                <el-option label="否" value="N" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作员" prop="operator">
              <el-input v-model="form.operator" type="text" style="width:60%;" placeholder="admin" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="停业日期" prop="branchTerminateEffDate">
              <el-date-picker v-model="form.branchTerminateEffDate" type="date" placeholder="选择停业时间" style="width:60%;" disabled />
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
      <el-button type="primary" @click="$emit('SUBMIT_GROUP_ADD_DIALOG',form,$refs.form)">保 存</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: 'GroupAddDialog',
  props: {
    visible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      form: {
        manageComCode4: '',
        branchLevel: '',
        branchName: '',
        branchManager: '',
        branchManagerName: '',
        branchManagerPhone: '',
        branchEffDate: '',
        branchStatus: '',
        branchTerminateEffDate: '',
        operator: '',
        chatName: ''
      },
      list: {
        manageComCode4: [],
        branchLevel: []
      },
      rules: {
        manageComCode4:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        branchLevel:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        branchName:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        branchManager:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        branchManagerName:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        branchManagerPhone:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        branchEffDate:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        branchStatus:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        branchTerminateEffDate:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        operator:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
        chatName:
          [{ required: true, message: '请输入活动名称', trigger: 'blur' }],
      }
    }
  }
}
</script>

<style scoped>

</style>
