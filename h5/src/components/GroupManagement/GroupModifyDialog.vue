<template>
  <el-dialog title="修改" :visible.sync="visible" width="80%" :before-close="handleDialogClose">
    <span>
      <el-form ref="groupModify" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="管理机构" prop="manageCom">
              <el-input v-model="form.manageCom" type="text" style="width:60%;" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="团队级别" prop="branchLevel">
              <el-input v-model="form.branchLevel" type="text" style="width:60%;" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="团队代码" prop="branchAttr">
              <el-input v-model="form.branchAttr" type="text" style="width:60%;" disabled />
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
              <el-input v-model="form.branchManager" type="text" style="width:60%;" @blur="handleBranchManagerQuery()" />
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
              <el-select v-model="form.branchStatus" placeholder="请选择" style="width:60%;" @change="form.branchStatus!=='Y'?form.branchTerminateEffDate='':''">
                <el-option label="是" value="Y" />
                <el-option label="否" value="N" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="操作员" prop="operator">
              <el-input v-model="form.operator" type="text" style="width:60%;" placeholder="" disabled />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="停业日期" prop="branchTerminateEffDate">
              <el-date-picker v-model="form.branchTerminateEffDate" type="date" placeholder="请输入停业日期" style="width:60%;" :disabled="(form.branchStatus!=='Y')" />
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
    <span slot="footer" class="dialog-footer">
      <el-button type="secondary" @click="$emit('CLOSE_GROUP_MODIFY_DIALOG')">取 消</el-button>
      <el-button type="primary" @click="handleSubmit">保 存</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { modifyGroup } from '@/api/group'
import { getManagerInfoByCode } from '@/api/branchManager'
export default {
  name: 'GroupModifyDialog',
  props: {
    visible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      // 表单数据（用于数据双向绑定）
      form: {
        manageCom: '',
        branchLevel: '',
        branchName: '',
        branchManager: '',
        branchAttr: '',
        branchManagerName: '',
        branchManagerPhone: '',
        branchEffDate: '',
        branchStatus: '',
        branchTerminateEffDate: '',
        operator: '',
        chatName: ''
      },
      // 数据校验规则
      rules: {
        chatName:
        [{ required: true, message: '请输入群聊名称', trigger: 'blur' }],
        branchName:
        [{ required: true, message: '请输入团队名称', trigger: 'blur' }],
        branchStatus:
        [{ required: true, message: '请选择停业标志', trigger: 'blur' }],
        branchTerminateEffDate:
        [{ validator: this.branchTerminateEffDateValidator, trigger: 'blur' }]
      }
    }
  },
  methods: {
    branchTerminateEffDateValidator(rule, value, callback) {
      if (this.form.branchStatus === 'Y' && value.length === 0) {
        return callback(new Error('请输入停业日期'))
      } else {
        callback()
      }
    },
    // 通过负责人代码查询负责人姓名和负责人电话号
    handleBranchManagerQuery() {
      if (this.form.branchManager.length === 0) {
        this.form.branchManagerName = ''
        this.form.branchManagerPhone = ''
        return
      }
      const data = {
        'agentCode': this.form.branchManager
      }
      if (this.form.branchManager.length === 0) {
        return
      }
      getManagerInfoByCode(data)
        .then(r => {
          this.form.branchManagerName = r.data.agentName
          this.form.branchManagerPhone = r.data.phone
          this.$message.success('获取负责人代码/电话号成功')
        })
        .catch(
          () => {
            this.form.branchManagerName = ''
            this.form.branchManagerPhone = ''
          }
        )
    },
    handleSubmit: function() {
      this.$refs['groupModify'].validate(
        valid => {
          if (valid) {
            // getManagerInfoByCode({ agentCode: this.form.branchManager })
            //   .then(r => {
            //     this.form.branchManagerName = r.data.agentName
            //     this.form.branchManagerPhone = r.data.phone
            //     this.$message.success('获取负责人代码/电话号成功')
            //   })
            //   .catch(
            //     () => {
            //       this.form.branchManagerName = ''
            //       this.form.branchManagerPhone = ''
            //     }
            //   )
            //   .then(() => {
            modifyGroup(this.form)
              .then(
                r => {
                  this.$message.success('修改成功')
                  this.$emit('REFRESH_QUERY')
                  this.$emit('CLOSE_GROUP_MODIFY_DIALOG')
                })
              // })
          } else {
            console.log('no valid')
            return false
          }
        })
    },
    handleDialogClose() {
      this.$emit('CLOSE_GROUP_MODIFY_DIALOG')
    }
  }
}
</script>

<style scoped>

</style>
