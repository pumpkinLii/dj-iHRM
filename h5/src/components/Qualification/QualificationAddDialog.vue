<template>
  <el-dialog title="新增" :visible.sync="config.visible" width="80%" :before-close="handleClose">
    <span>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="人员工号" prop="agentCode">
              <el-input v-model="form.agentCode" placeholder="请输入人员工号" type="text" style="width:60%;" @blur="getAgentName" />
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
            <el-form-item label="资格证书名称" prop="certificateName">
              <el-select v-model="form.certificateName" style="width:60%;">
                <el-option v-for="(option,index) in list.certificateName" :key="index" :label="option.label" :value="option.value">
                  <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                  <span style="float: right">{{ option.label }}</span>
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资格证书号" prop="certificateNo">
              <el-input v-model="form.certificateNo" placeholder="请输入资格证书号" type="text" style="width:60%;" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="发放日期" prop="releaseDate">
              <el-date-picker
                v-model="form.releaseDate"
                value-format="yyyy-MM-dd"
                type="date"
                style="width:60%;"
                @change="dateCheck"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="补发日期" prop="reissueDate">
              <el-date-picker
                v-model="form.reissueDate"
                value-format="yyyy-MM-dd"
                type="date"
                style="width:60%;"
                @change="dateCheck"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="有效起期" prop="startEffectiveDate">
              <el-date-picker
                v-model="form.startEffectiveDate"
                value-format="yyyy-MM-dd"
                type="date"
                style="width:60%;"
                @change="effDateCheck"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="有效止期" prop="endEffectiveDate">
              <el-date-picker
                v-model="form.endEffectiveDate"
                value-format="yyyy-MM-dd"
                type="date"
                style="width:60%;"
                @change="effDateCheck"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="批准单位" prop="approver">
              <el-input v-model="form.approver" type="text" style="width:60%;" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </span>
    <!-- 底部 -->
    <span slot="footer" class="dialog-footer">
      <el-button type="secondary" @click="handleClose">取 消</el-button>
      <el-button type="primary" @click="handleSubmit">保 存</el-button>
    </span>
  </el-dialog>
</template>

<script>
import { insert, getAgentNameByAgentCode } from '@/api/qualification'
import { getAllCode } from '@/api/code'
export default {
  name: 'QualificationAddDialog',
  data() {
    return {
      config: {
        visible: false
      },
      form: {
        agentCode: '', // 人员工号
        agentName: '', // 人员姓名
        certificateName: '', // 资格证书名称(对码值)
        certificateType: '1', // 写死
        certificateNo: '', // 资格证书号
        releaseDate: '', // 发放日期
        reissueDate: '', // 补发日期
        startEffectiveDate: '', // 有效起期
        endEffectiveDate: '', // 有效止期
        approver: '' // 批准单位
      },
      list: {
        certificateName: []
      },
      rules: {
        agentCode:
          [{ required: true, message: '请输入人员工号', trigger: 'blur' }],
        agentName:
          [{ required: true, message: '人员姓名获取失败', trigger: 'blur' }],
        certificateName:
          [{ required: true, message: '请选择资格证书类型', trigger: 'blur' }],
        certificateNo:
          [{ required: true, message: '请输入资格证书号', trigger: 'blur' }],
        releaseDate:
          [{ required: true, message: '请选择发放日期', trigger: 'blur' }],
        startEffectiveDate:
          [{ required: true, message: '请选择有效起期', trigger: 'blur' }],
        endEffectiveDate:
          [{ required: true, message: '请选择有效止期', trigger: 'blur' }]
      }
    }
  },
  mounted() {
    this.$bus.$on('OPEN_QUALIFICATION_ADD_DIALOG', () => {
      this.config.visible = true
    })
    this.getInitOptions()
  },
  beforeDestroy() {
    this.$bus.$off('OPEN_QUALIFICATION_ADD_DIALOG')
  },
  methods: {
    getInitOptions() {
      getAllCode()
        .then(r => {
          Object.keys(r['resource']['certificatename']).forEach(key => {
            this.list.certificateName.push(
              {
                label: r['resource']['certificatename'][key],
                value: key
              }
            )
          })
        })
    },
    handleSubmit() {
      this.$refs['form'].validate(
        (valid) => {
          if (valid) {
            // 发起请求
            this.sendSubmitRequest(this.form)
          } else {
            this.$message.warning('请检查表单是否有误')
            return false
          }
        })
    },
    handleClose() {
      this.config.visible = false
    },
    sendSubmitRequest(data) {
      insert(data)
        .then(r => {
          this.$bus.$emit('QUALIFICATION_SUCCESS')
          this.config.visible = false
          this.$message.success('添加成功')
        })
    },
    dateCheck() {
      if (this.form.reissueDate.length !== 0 &&
        this.form.releaseDate.length !== 0 &&
        new Date(this.form.releaseDate) > new Date(this.form.reissueDate)
      ) {
        this.form.reissueDate = ''
        this.$message.warning('发放日期不能大于补发日期')
      }
    },
    effDateCheck() {
      if (this.form.startEffectiveDate.length !== 0 &&
        this.form.endEffectiveDate.length !== 0 &&
        new Date(this.form.startEffectiveDate) > new Date(this.form.endEffectiveDate)) {
        this.form.endEffectiveDate = ''
        this.$message.warning('有效起期不能大于有效止期')
      }
    },
    getAgentName() {
      this.form.agentName = ''
      if (this.form.agentCode.length !== 0) {
        getAgentNameByAgentCode({ agentCode: this.form.agentCode })
          .then(r => {
            this.form.agentName = r['agentName']
            this.$message.success('获取人员姓名成功')
          })
      }
    }
  }
}
</script>

<style scoped>

</style>
