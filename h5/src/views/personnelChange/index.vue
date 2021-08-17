<template>
  <div class="app-container">
    <h4>人员异动</h4>
    <el-form ref="form" :rules="rules" :model="form" label-width="180px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="管理机构" prop="manageCom">
            <el-select v-model="form.manageCom" placeholder="请选择" style="width:100%;">
              <el-option v-for="(option,index) in list.manageCom" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队代码">
            <el-input v-model="form.branchAttr" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队名称">
            <el-input v-model="form.branchName" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="代理人代码">
            <el-input v-model="form.agentCode" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="代理人姓名">
            <el-input v-model="form.agentName" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机号" prop="agentPhone">
            <el-input v-model="form.agentPhone" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="证件号码" prop="idNo">
            <el-input v-model="form.idNo" type="text" style="width:100%;" placeholder="可选项" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item>
          <el-col style="text-align:left;margin-top: 1rem">
            <el-button type="primary" icon="el-icon-search" @click="handleQuery">查询</el-button>
            <el-button type="success" icon="el-icon-edit" @click="handleOpenChangeDialog">异动</el-button>
            <el-button type="secondary" icon="el-icon-refresh-left" @click="resetForm">重置</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
    <el-divider />
    <div>
      <el-table :data="table" stripe border fit height="300" :selection-change="handleSelectChange">
        <el-table-column type="selection" width="40" />
        <el-table-column label="人员工号" prop="agentCode" />
        <el-table-column label="人员姓名" prop="agentName" />
        <el-table-column label="分公司" prop="gradeName" />
        <el-table-column label="中心支公司" prop="manageCom2" />
        <el-table-column label="团队代码" prop="manageCom3" />
        <el-table-column label="团队名称" prop="manageCom4" />
        <el-table-column label="入司日期" prop="employDate" />
        <el-table-column label="人员状态" prop="stateName" />
        <el-table-column label="合同类型" prop="stateName" />
        <el-table-column label="职级" prop="stateName" />
      </el-table>
      <!-- 分页 -->
      <div class="block" style="text-align: right;margin-top: 1rem">
        <el-pagination
          :current-page="page.currentPage"
          :page-sizes="[10, 20, 50, 100, 200, 500]"
          :page-size="page.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="page.totalCount"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
      <PersonChangeDialog />
    </div>
  </div>
</template>

<script>
import { phoneNumberValidatorAllowNull } from '@/utils/validate'
import { getManageComCode } from '@/api/code'
import PersonChangeDialog from '@/components/PersonChange/PersonChangeDialog'
import { query } from '@/api/personChange'
export default {
  name: 'PersonnelChange',
  components: { PersonChangeDialog },
  data() {
    return {
      form: {
        manageCom: '',
        branchAttr: '',
        branchName: '',
        agentCode: '',
        agentName: '',
        agentPhone: '',
        idNo: ''
      },
      list: {
        manageCom: []
      },
      rules: {
        manageCom:
          [{ required: true, message: '请选择管理机构', trigger: 'change' }],
        agentPhone:
          [{ validator: phoneNumberValidatorAllowNull, trigger: 'blur' }]
      },
      table: [],
      selected: [],
      page: {
        currentPage: 1, // 当前第几页
        totalCount: 0, // 总共有几条
        pageSize: 10 // 每页的项目数
      }
    }
  },
  mounted() {
    this.getInitOptions()
  },
  methods: {
    resetForm() {
      this.$refs['form'].resetFields()
      Object.keys(this.form).forEach((key) => {
        this.form[key] = ''
      })
      this.table = []
      this.page.currentPage = 1
      this.page.totalCount = 0
    },
    handleQuery() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          query(this.form, { pageSize: this.page.pageSize, currentPage: this.page.currentPage })
            .then(r => {
              console.log(r)
              this.table = r['list']
              this.page.totalCount = r['totalCount']
              this.$message.success('查询完毕')
            })
        } else {
          return false
        }
      })
    },
    handleOpenChangeDialog() {
      this.$bus.$emit('OPEN_PERSON_CHANGE_DIALOG', this.selected)
    },
    // 一页有几行
    handleSizeChange(size) {
      this.page.pageSize = size
      this.$bus.$emit('refresh')
    },
    // 第几页
    handleCurrentChange(page) {
      this.page.currentPage = page
      this.$bus.$emit('refresh')
    },
    handleSelectChange(selection) {
      console.log(selection)
    },
    getInitOptions() {
      // 获取管理机构下拉菜单
      getManageComCode().then(
        r => {
          this.$log('成功获取管理机构下拉菜单请求', r)
          this.list.manageCom = r['totallist']
        }
      )
    }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>
