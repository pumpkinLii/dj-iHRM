<template>
  <div>

    <el-dialog title="职级调整" :visible.sync="editDialogVisible" width="90%" :before-close="editDialogClosed">
      <!-- 主体 -->
      <span>
        <el-form ref="form" :model="form" :rules="rules" label-width="180px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="人员代码" prop="agentCode">
                <el-input v-model="form.agentCode" type="text" style="width:60%;" disabled />
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
              <el-form-item label="团队代码" prop="agentGroup">
                <el-input v-model="form.agentGroup" type="text" style="width:60%;" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="团队名称" prop="branchName">
                <el-input v-model="form.branchName" type="text" style="width:60%;" disabled />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="人员状态">
                <el-select v-model="form.agentState" placeholder="请选择" style="width: 60%" disabled>
                  <el-option label="在职" value="01">
                    <span style="float: left; color: #8492a6; font-size: 13px">01</span>
                    <span style="float: right">在职</span>
                  </el-option>
                  <el-option label="二次入司" value="02">
                    <span style="float: left; color: #8492a6; font-size: 13px">02</span>
                    <span style="float: right">二次入司</span>
                  </el-option>
                  <el-option label="离职" value="03">
                    <span style="float: left; color: #8492a6; font-size: 13px">03</span>
                    <span style="float: right">离职</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">

              <el-form-item label="当前职级" prop="curAgentGrade">
                <el-select
                  v-model="form.curAgentGrade"
                  placeholder="请选择"
                  style="width:60%"
                  disabled
                >
                  <el-option v-for="item in curAgentGrade" :key="item.gradecode" :value="item.gradecode" :label="item.gradename">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.gradecode }}</span>
                    <span style="float: right">{{ item.gradename }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="目标职级" prop="targetAgentGrade">
                <el-select
                  v-model="form.targetAgentGrade"
                  placeholder="请选择"
                  style="width:60%"
                  @change="getTeam"
                >
                  <el-option v-for="item in curAgentGrade" :key="item.gradecode" :value="item.gradecode" :label="item.gradename">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.gradecode }}</span>
                    <span style="float: right">{{ item.gradename }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <!--              {{form.targetBranchCode}}-->
              <!--              {{targetBranchCode}}-->
              <el-form-item label="目标团队架构" prop="targetBranchCode">
                <el-select
                  v-model="form.targetBranchCode"
                  placeholder="请选择"
                  style="width:60%"
                  :disabled="judge"
                  @click.native="getTargetBranch"
                >
                  <el-option v-for="item in targetBranchCode" :key="item.groupcode" :value="item.groupcode" :label="item.groupname">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.groupcode }}</span>
                    <span style="float: right">{{ item.groupname }}</span>
                  </el-option>
                </el-select>
              </el-form-item>

            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="目标四级管理机构" prop="targetManageCom">
                <el-select
                  v-model="form.targetManageCom"
                  placeholder="请选择"
                  style="width:60%"
                  :disabled="judgetarget"
                  @change="clearTar"
                >
                  <el-option v-for="item in targetManageCom" :key="item.value" :value="item.value" :label="item.label">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                    <span style="float: right">{{ item.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">

              <el-form-item label="合同类型" prop="contractType">
                <el-select v-model="form.contractType" style="width:60%" disabled>
                  <el-option label="合同制" value="02" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="入司日期" prop="employDate">
                <el-date-picker
                  v-model="form.employDate"
                  value-format="yyyy-MM-dd"
                  type="date"
                  placeholder="选择日期"
                  style="width:60%"
                  disabled
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">

              <el-form-item label="调整日期" prop="alterDate">
                <el-date-picker
                  v-model="form.alterDate"
                  value-format="yyyy-MM-dd"
                  type="date"
                  placeholder="选择日期"
                  style="width:60%"
                />
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="调整原因" prop="alterCause">
                <el-input v-model="form.alterCause" type="text" style="width:60%;" />
              </el-form-item>
            </el-col>
            <el-col :span="12" />
          </el-row>
        </el-form>
      </span>
      <!-- 底部 -->
      <span slot="footer" class="dialog-footer">
        <el-button type="secondary" @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveAdjust">保 存</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
import * as API from '@/api/agent'

export default {
  name: 'PersonEdit',
  data() {
    return {
      // 四级管理机构代码
      minComCode: '',
      // 中介  目标团队架构
      aBranchCode: '',
      BranchCodeData: {},
      // 职级
      curAgentGrade: [],
      // 四级下拉列表
      targetManageCom: [],
      // 目标团队架构
      targetBranchCode: [],
      // 表单信息
      form: {
        agentCode: '', // 人员代码
        agentName: '', // 人员姓名。。
        agentGroup: '', // 团队代码
        branchName: '', // 团队名称。。
        agentState: '', // 人员转态
        curAgentGrade: '', // 当前职级
        targetAgentGrade: '', // 目标职级
        targetBranchCode: '', // 目标团队架构
        targetManageCom: '', // 目标四级管理机构
        employDate: '', // 入司日期
        alterDate: '', // 调整日期
        alterCause: '' // 调整原因
      },
      rules: {
        targetAgentGrade:
        [
          { required: true, message: '请选择目标职级', trigger: 'blur' }
        ],
        targetBranchCode:
        [
          { required: true, message: '请选择目标团队架构', trigger: 'blur' }
        ],
        targetManageCom:
        [
          { required: true, message: '请选择目标四级管理机构', trigger: 'blur' }
        ],
        alterDate:
        [
          { required: true, message: '请选择调整日期', trigger: 'blur' }
        ],
        alterCause:
          [
            { required: true, message: '请输入调整原因', trigger: 'blur' }
          ]
      },
      // 窗口显示
      editDialogVisible: false,
      judge: false,
      judgetarget: false
    }
  },
  created() {
    this.getCurAgentGrade() // 获取职级下拉列表
    this.getTargetManageCom() // 获取四级管理机构
  },
  mounted() {
    this.$bus.$on('agent1', (item) => {
      this.form = { ...item } // 对象的深拷贝浅拷贝
      this.$set(this.form, 'targetBranchCode', '')
      this.$set(this.form, 'targetManageCom', '')
      this.form.agentGrade = undefined
      this.$set(this.form, 'curAgentGrade', item.agentGrade)
      this.editDialogVisible = true
      this.minComCode = item.comCode4
      // 处理targetBranchCode传码值
      this.aBranchCode = item.agentGroup
    })
  },
  beforeDestroy() {
    this.$bus.$off('agent1')
  },
  methods: {
    // 监听修改用户对话框的关闭事件
    editDialogClosed() {
      this.$refs.form.resetFields()
      this.editDialogVisible = false
    },
    // 获取职级
    getCurAgentGrade() {
      API.getCurAgentGrade().then(
        (res) => {
          this.curAgentGrade = res.list
        }
      )
    },
    // 获取四级下拉列表
    getTargetManageCom() {
      API.getTargetManageCom().then(
        (res) => {
          this.targetManageCom = res.list4
        }
      ).catch(() => {
      })
    },
    // 判断目标团队架构
    getTeam() {
      if (this.form.targetAgentGrade.substr(0, 2) === this.form.curAgentGrade.substr(0, 2)) {
        this.judge = true
        this.judgetarget = true
        this.form.targetBranchCode = this.form.agentGroup
        this.form.targetManageCom = this.minComCode
        const data = {
          oldgradecode: this.form.curAgentGrade, // 当前职级
          nowgradecode: this.form.targetAgentGrade, // 目标职级
          agentcode: this.form.agentCode, // 人员工号
          comecode: this.form.targetManageCom// 四级管理机构
        }
        API.getTargetBranchCode(data).then(
          (res) => {
            this.targetBranchCode = res.list
            if (res.msg !== 'success') {
              this.$message.success(res.msg)
            }
          }
        )
      } else {
        this.judge = false
        this.judgetarget = false
        this.$set(this.form, 'targetManageCom', '')
        this.$set(this.form, 'targetBranchCode', '')
        // this.form.targetManageCom = ''
      }
    },
    // 改变目标四级后目标团队架构
    clearTar() {
      this.$set(this.form, 'targetBranchCode', '')
    },
    // 获取目标团队架构
    getTargetBranch() {
      console.log('000000000000000000000000')
      console.log(this.form.targetAgentGrade)
      const data = {
        oldgradecode: this.form.curAgentGrade, // 当前职级
        nowgradecode: this.form.targetAgentGrade, // 目标职级
        agentcode: this.form.agentCode, // 人员工号
        comecode: this.form.targetManageCom// 四级管理机构
      }
      API.getTargetBranchCode(data).then(
        (res) => {
          this.targetBranchCode = res.list
          if (res.msg !== 'success') {
            this.$message.success(res.msg)
          }
        }
      )
    },
    // 保存修改
    saveAdjust() {
      if (this.form.curAgentGrade === this.form.targetAgentGrade) {
        this.$message('当前职级与目标职级一致，无法调整')
      } else {
        API.saveAdjust(this.form).then(
          (res) => {
            if (res.code === 0) {
              this.$message.success('调整成功')
              this.$bus.$emit('refreshAgent')
              this.editDialogVisible = false
            } else {
              this.$message.error('调整失败')
            }
          }
        )
      }
    }
  }
}
</script>

<style scoped>

</style>
