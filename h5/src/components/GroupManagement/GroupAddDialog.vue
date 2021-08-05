<template>
  <el-dialog title="新增" :visible.sync="visible" width="80%" :before-close="handleDialogClose">
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
import { phoneNumberValidatorAllowNull } from '@/utils/validate'
import { setCodeByName, getManageComCode4 } from '@/api/code'
import { addGroup } from '@/api/group'
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
        branchStatus: 'N',
        branchTerminateEffDate: '',
        operator: 'admin',
        chatName: ''
      },
      list: {
        manageComCode4: [],
        branchLevel: []
      },
      rules: {
        manageComCode4:
          [{ required: true, message: '请选择管理机构', trigger: 'blur' }],
        branchLevel:
          [{ required: true, message: '请选择团队级别', trigger: 'blur' }],
        branchName:
          [{ required: true, message: '请输入团队名称', trigger: 'blur' }],
        branchManagerPhone:
          [{ validator: phoneNumberValidatorAllowNull, trigger: 'blur' }],
        branchEffDate:
          [{ required: true, message: '请选择成立时间', trigger: 'blur' }],
        operator:
          [{ required: true, message: '请选择操作员', trigger: 'blur' }],
        chatName:
          [{ required: true, message: '请输入群聊名称', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getInitOptions()
  },
  methods: {
    getInitOptions() {
      // 获取管理机构下拉菜单
      getManageComCode4(this.list)
      // 获取团队级别下拉菜单
      setCodeByName('branchlevel', this.list.branchLevel)
    },
    handleSubmit() {
      this.$refs['form'].validate(
        (valid) => {
          if (valid) {
            // 发起请求
            console.log('发起了请求')
            this.sendSubmitRequest(this.form)
          } else {
            return false
          }
        })
    },
    branchTerminateEffDateValidator(rule, value, callback) {
      if (this.form.branchStatus === 'Y') {
        if (this.form.branchTerminateEffDate.length === 0) {
          callback(new Error('请选择停业日期'))
        }
      }
      callback()
    },
    handleDialogClose() {
      this.$emit('CLOSE_GROUP_ADD_DIALOG')
    },
    sendSubmitRequest(data) {
      addGroup(data)
        .then(r => {
          this.$emit('CLOSE_GROUP_ADD_DIALOG')
          this.$message.success('添加成功')
          console.log(r)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>

</style>
