<template>
  <el-form ref="form" :model="form" :rules="rules" label-width="180px">
    <!-- 1-->
    <el-row>
      <el-col :span="8">
        <el-form-item label="二级管理机构" prop="manageCom2">
          <el-select v-model="form.manageCom2" placeholder="请选择" style="width:100%">
            <el-option v-for="(item,index) in list.manageCom2" :key="index" :value="item.value" :label="item.label" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="三级管理机构" prop="manageCom3">
          <el-select v-model="form.manageCom3" placeholder="请选择" style="width:100%" :disabled="!form.manageCom2||form.manageCom2.length===0">
            <el-option v-for="(item,index) in list.manageCom3" :key="index" :value="item.value" :label="item.label" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="四级管理机构" prop="manageCom4">
          <el-select v-model="form.manageCom4" placeholder="请选择" style="width:100%" :disabled="!form.manageCom3||form.manageCom3.length===0" @change="ranks">
            <el-option v-for="(item,index) in list.manageCom4" :key="index" :value="item.value" :label="item.label" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <!--2 -->
    <el-row>
      <el-col :span="8">
        <el-form-item label="岗位" prop="agentJob">
          <el-select v-model="form.agentJob" placeholder="请选择" style="width:100%" @change="rank">
            <el-option label="总监" value="总监" />
            <el-option label="经理" value="经理" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="人员职级" prop="agentGrade">
          <el-select v-model="form.agentGrade" placeholder="请选择" style="width:100%" :disabled="form.agentJob.length===0">
            <el-option v-for="(item,index) in list.agentGrade" :key="index" :value="item.label" :label="item.value" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="团队架构" prop="branchAttr">
          <el-select v-model="form.branchAttr" placeholder="请选择" style="width:100%" @change="echo">
            <el-option v-for="(item,index) in list.branchAttr" :key="index" :value="item.value" :label="item.label" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <!--3 -->
    <el-row>
      <el-col :span="8">
        <el-form-item label="团队主管工号" prop="teamSupervisorId">
          <el-input v-model="form.teamSupervisorId" type="text" style="width:100%;" disabled />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="团队主管姓名" prop="teamSupervisorName">
          <el-input v-model="form.teamSupervisorName" type="text" style="width:100%;" disabled />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="合同类型" prop="contractType">
          <el-select v-model="form.contractType" style="width:100%" disabled>
            <el-option label="合同制" value="02" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <!--4-->
    <el-row>
      <el-col :span="8">
        <el-form-item label="劳动合同起期" prop="contractStartDate">
          <el-date-picker
            v-model="form.contractStartDate"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="选择日期"
            style="width:100%"
          />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="劳动合同止期" prop="contractEndDate">
          <el-date-picker
            v-model="form.contractEndDate"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="选择日期"
            style="width:100%"
            @change="dateCheck"
          />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="入司日期" prop="employDate">
          <el-date-picker
            v-model="form.employDate"
            value-format="yyyy-MM-dd"
            type="date"
            placeholder="2021-07-28"
            style="width:100%"
          />
        </el-form-item>
      </el-col>
    </el-row>
    <!--5 -->
    <el-row>
      <el-col :span="8">
        <el-form-item label="操作员代码" prop="operator">
          <el-input v-model="form.operator" type="text" style="width:100%;" disabled />
        </el-form-item>
      </el-col>

    </el-row>
    <!-- 分界线 -->
    <el-divider />
  </el-form>
</template>

<script>

import * as A from '@/api/person'

export default {
  name: 'AdministrativeInformation',
  data() {
    return {
      // 数据绑定
      form: {

        manageCom2: '', // 二级管理系统
        manageCom3: '', // 三级管理系统
        manageCom4: '', // 四级管理系统

        agentJob: '', // 岗位
        agentGrade: '', // 人员职级
        branchAttr: '', // 团队架构

        contractStartDate: '', // 劳动合同起期
        contractEndDate: '', // 劳动合同止期
        employDate: '', // 入司日期

        // 请求中无需提交
        teamSupervisorId: '', // 团队主管工号
        teamSupervisorName: '', // 团队主管姓名
        operator: 'admin', // 操作员代码
        contractType: '02' // 合同类型
      },
      // 下拉列表
      list: {
        manageCom2: [],
        manageCom3: [],
        manageCom4: [],

        agentGrade: [],
        branchAttr: []
      },
      // 校验
      rules: {

        manageCom2: { required: true, message: '该项不可为空', trigger: 'change' }, // 二级管理系统
        manageCom3: { required: true, message: '该项不可为空', trigger: 'change' }, // 三级管理系统
        manageCom4: { required: true, message: '该项不可为空', trigger: 'change' }, // 四级管理系统

        agentJob: { required: true, message: '该项不可为空', trigger: 'change' }, // 岗位
        agentGrade: { required: true, message: '该项不可为空', trigger: 'change' }, // 人员职级
        branchAttr: { required: true, message: '该项不可为空', trigger: 'change' }, // 团队架构

        teamSupervisorId: { required: false, message: '该项不可为空', trigger: 'change' }, // 团队主管工号
        teamSupervisorName: { required: false, message: '该项不可为空', trigger: 'change' }, // 团队主管姓名
        contractType: { required: true, message: '该项不可为空', trigger: 'change' }, // 合同类型

        contractStartDate: { required: true, message: '该项不可为空', trigger: 'change' }, // 劳动合同起期
        contractEndDate: { required: true, message: '该项不可为空', trigger: 'change' }, // 劳动合同止期
        employDate: { required: true, message: '该项不可为空', trigger: 'change' }, // 入司日期

        operator: { required: true, message: '该项不可为空', trigger: 'change' } // 操作员代码
      }
    }
  },
  // computed: {
  //   rankdress() {
  //     const { manageCom4, agentJob } = this.form
  //     console.log('检测值是否发生变化', manageCom4, agentJob)
  //     return {
  //       manageCom4,
  //       agentJob
  //     }
  //   }
  // },
  // watch: {
  //   rankdress(nval, oval) {
  //     console.log('值发生变化')
  //   },
  //   const hs = {
  //     grrankeName: this.form.manageCom4,
  //     managerCom: this.form.agentJob
  //   },
  //   A.zhiji(data1).then(r => {
  //     this.branchAttr_1 = r.list
  //     console.log('成功了')
  //   }).catch(() => {
  //     console.log('失败了')
  //   })
  //   console.log('变化', nval, oval)
  // },
  // // 初级 下拉列表渲染
  created() {
    A.xiala().then((r) => {
      this.list.manageCom2 = r.list2
      this.list.manageCom3 = r.list3
      this.list.manageCom4 = r.list4
    }).catch((err) => {
      console.log('失败了', err)
    })
  },
  methods: {
    dateCheck() {
      if (this.form.contractStartDate > this.form.contractEndDate) {
        this.$message.error('终止日期不能小于起始时期')
        this.form.contractEndDate = ''
      } else {
        console.log('日期校验失败')
      }
    },
    // 人员职级
    rank() {
      this.list.agentGrade = []
      this.form.agentGrade = ''
      if (this.form.agentJob === '总监') {
        A.posit().then(r => {
          this.list.agentGrade = r.list
        }).catch(err => {
          console.log('失败了', err)
        })
      } else if (this.form.agentJob === '经理') {
        A.posit1().then(r => {
          this.list.agentGrade = r.list
        }).catch(() => {
          console.log('失败了')
        })
      } else {
        console.log('失败了')
      }
      this.ranks()
    },
    // 团队架构渲染
    ranks() {
      if (this.form.agentJob && this.form.manageCom4) {
        const data1 = {
          gradeName: this.form.agentJob,
          managerCom: this.form.manageCom4
        }
        A.zhiji(data1).then(r => {
          this.list.branchAttr = r.list
        }).catch(() => {
          console.log('调用失败了')
        })
      } else {
        console.log('失败了')
      }
    },
    // 团队回显
    echo() {
      const x = {
        groupId: this.form.branchAttr
      }
      A.huixian(x).then(r => {
        this.form.teamSupervisorId = r.data.branchManager
        this.form.teamSupervisorName = r.data.branchManagerName
      }).catch(() => {
        console.log('回显调用失败')
      })
    }

  }}

</script>
