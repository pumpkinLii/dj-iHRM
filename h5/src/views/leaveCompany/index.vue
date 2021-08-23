<template>
  <div class="app-container">
    <h4>离司确认</h4>
    <el-form :model="form" :rules="rules" label-width="180px">
      <!--      第一行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="管理机构" prop="manageCom">
            <el-cascader
              ref="elcascader"
              v-model="form.manageCom"
              :options="options"
              :show-all-levels="false"
              style="width: 100%"
              :props="{ expandTrigger: 'hover' ,checkStrictly: true}"
              clearable
              @change="changeVal"
              @blur="changeValBind"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队代码">
            <el-select v-model="form.agentGroup" placeholder="请选择" clearable style="width:100%;">
              <el-option v-for="(option,index) in branchAttr" :key="index" :label="option.branchAttr" :value="option.branchAttr">
                <span style="float: right; color: #8492a6; font-size: 13px">{{ option.name }}</span>
                <span style="float: left">{{ option.branchAttr }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队名称">
            <el-input v-model="form.branchName" placeholder="请输入团队名称" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第二行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="人员代码">
            <el-input v-model="form.agentCode" placeholder="请输入代理人代码" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人员姓名">
            <el-input v-model="form.agentName" placeholder="请输入代理人姓名" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="职级">
            <el-select v-model="form.agentGrade" style="width: 100%" placeholder="请选择">
              <el-option label="合同制" value="0" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第三行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="审核状态">
            <el-select v-model="form.agydepart" style="width: 100%" placeholder="请选择">
              <el-option v-for="(item,index) in AgydepartList" :key="index" :value="item.value" :label="item.label">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                <span style="float: right">{{ item.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="解约原因">
            <el-select v-model="form.diffCause" style="width: 100%" placeholder="请选择">
              <el-option v-for="(item,index) in DiffCauseList" :key="index" :value="item.value" :label="item.label">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                <span style="float: right">{{ item.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="解约日期起期" prop="diffStartDate">
            <el-date-picker
              v-model="form.diffStartDate"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期"
              style="width:100%"
              @change="dateCheck"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="解约日期止期" prop="diffEndDate">
            <el-date-picker
              v-model="form.diffEndDate"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期"
              style="width:100%"
              @change="dateCheck"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第四行-->
      <el-row>
        <el-form-item>
          <el-col style="text-align:left;margin-top: 1rem">
            <el-button type="primary" icon="el-icon-search" @click="query">查询</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
    <el-divider />
    <!--    表格-->
    <div>
      <el-table :data="table" stripe border fit height="300" @selection-change="handleSelectChange">
        <el-table-column type="selection" width="40" />
        <el-table-column label="人员代码" prop="agentCode" />
        <el-table-column label="人员姓名" prop="agentName" />
        <el-table-column label="管理机构代码" prop="comCode4" />
        <el-table-column label="管理机构名称" prop="manageCom4" />
        <el-table-column label="团队代码" prop="agentGroup" />
        <el-table-column label="团队名称" prop="branchName" />
        <el-table-column label="职级" prop="agentGrade" />
        <el-table-column label="解约日期" prop="diffDate" />
        <el-table-column label="解约原因" prop="diffCause" />
        <el-table-column label="说明" prop="illustrate" />
        <el-table-column label="审核状态" prop="agentState" />
        <el-table-column label="操作" prop="operator" />
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
import * as Code from '@/api/code'
import * as Agent from '@/api/agent'
import { getNextOptions } from '@/api/personChange'

export default {
  name: 'LeaveCompany',
  data() {
    return {
      form: {
        manageCom: '', // 管理机构
        agentGroup: '', // 团队代码
        branchName: '', // 团队名称
        agentCode: '', // 人员代码
        agentName: '', // 人员姓名
        agentGrade: '', // 职级
        agydepart: '', // 审核状态
        diffCause: '', // 解约原因
        diffStartDate: '', // 解约日期起期
        diffEndDate: '' // 解约日期止期
      },
      branchAttr: [], // 团队代码下拉
      branchName: [], // 团队名称下拉
      AgydepartList: [], // 审核状态下拉列表
      DiffCauseList: [], // 解约原因下拉列表
      rules: {
      },
      page: {
        currentPage: 1, // 当前第几页
        totalCount: 0, // 总共有几条
        pageSize: 10 // 每页的项目数
      },
      options: []
    }
  },
  created() {
    this.getInitOptions() // 获取初始下拉菜单，获取码值
    Agent.manage().then((r) => {
      this.options.push(r.result)
    })
  },
  mounted() {
    this.$bus.$on('refreshAgent', () => {
      this.hello()
    })
    // 点击文字即可选中
    setInterval(function() {
      document.querySelectorAll('.el-cascader-node__label').forEach(el => {
        el.onclick = function() {
          if (this.previousElementSibling) this.previousElementSibling.click()
        }
      })
    }, 1000)
  },
  beforeDestroy() {
    this.$bus.$off('refreshAgent')
  },
  methods: {
    // 查询按钮
    query() {
      console.log('11111111')
    },
    // 下拉框选择弹回
    changeVal() {
      // eslint-disable-next-line prefer-const
      let t
      clearTimeout(t)
      t = setTimeout(() => {
        this.$refs.elcascader.dropDownVisible = false
      }, 300)
      this.form.agentGroup = ''
    },
    // 级联团队代码
    changeValBind() {
      getNextOptions(this.form.manageCom).then(res => {
        this.branchAttr = res.list
      })
    },
    // 校验日期
    dateCheck() {
      if (this.form.diffStartDate.length !== 0 && this.form.diffEndDate.length !== 0) {
        if (this.form.diffStartDate > this.form.diffEndDate) {
          this.$message.error('终止日期不能小于起始时期')
          this.form.diffEndDate = ''
        }
      }
    },
    // 获取码表
    getInitOptions() {
      Code.getAllCode().then(
        r => {
          this.codes = r['resource']
          this.fillCodes() // 将获取的码值填入需要的项中
        })
    },
    // 填充码表
    fillCodes() {
      if (!this.codes) {
        this.$message.error('未能正确获取下拉菜单')
        return
      }
      this.setCodes('assessadjuststate', this.AgydepartList)
      this.setCodes('departreason', this.DiffCauseList)
    },
    // 通过以获取的码表数据填充某一列表
    setCodes(name, buffer) {
      if (!(this.codes[name])) {
        return
      }
      Object.keys(this.codes[name])
        .forEach((key) => {
          buffer.push({
            label: this.codes[name][key],
            value: key
          })
        })
    }
  }
}
</script>
<style  scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>

