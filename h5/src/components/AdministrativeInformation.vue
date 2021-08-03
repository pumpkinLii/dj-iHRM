<template>
  <el-form ref="adminForm" :model="adminForm" :rules="rules" label-width="180px">
    <!-- 1-->
    <el-row>
      <el-col :span="8">
        <el-form-item label="二级管理机构" prop="secondManage">
          <el-select v-model="adminForm.secondManage" placeholder="请选择" style="width:100%">
            <el-option label="区域一" value="shanghai" />
            <el-option label="区域二" value="beijing" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="三级管理机构" prop="thirdManage">
          <el-select v-model="adminForm.thirdManage" placeholder="请选择" style="width:100%">
            <el-option label="区域一" value="shanghai" />
            <el-option label="区域二" value="beijing" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="四级管理机构" prop="fourthManage">
          <el-select v-model="adminForm.fourthManage" placeholder="请选择" style="width:100%">
            <el-option label="区域一" value="shanghai" />
            <el-option label="区域二" value="beijing" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>

    <!--2 -->
    <el-row>
      <el-col :span="8">
        <el-form-item label="岗位" prop="position">
          <el-select v-model="adminForm.position" placeholder="请选择" style="width:100%">
            <el-option label="区域一" value="shanghai" />
            <el-option label="区域二" value="beijing" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="人员职级" prop="staffRank">
          <el-select v-model="adminForm.staffRank" placeholder="请选择" style="width:100%">
            <el-option label="区域一" value="shanghai" />
            <el-option label="区域二" value="beijing" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="团队架构" prop="teamStructure">
          <el-select v-model="adminForm.teamStructure" placeholder="请选择" style="width:100%">
            <el-option label="区域一" value="shanghai" />
            <el-option label="区域二" value="beijing" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>

    <!--3 -->

    <el-row>
      <el-col :span="8">
        <el-form-item label="团队主管工号" prop="teamSupervisorId">
          <el-input v-model="adminForm.teamSupervisorId" type="text" style="width:100%;" disabled />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="团队主管姓名" prop="teamLeadersName">
          <el-input v-model="adminForm.teamLeadersName" type="text" style="width:100%;" disabled />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="合同类型" prop="typeOfContract">
          <el-select v-model="adminForm.typeOfContract" placeholder="合同制" style="width:100%" disabled>
            <el-option label="区域一" value="shanghai" />
            <el-option label="区域二" value="beijing" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>

    <!--4-->

    <el-row>
      <el-col :span="8">
        <el-form-item label="劳动合同起期" prop="startOfLaborContract">
          <el-date-picker
            v-model="adminForm.startOfLaborContract"
            type="date"
            placeholder="选择日期"
            style="width:100%"
          />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="劳动合同止期" prop="termOfLaborContract">
          <el-date-picker
            v-model="adminForm.termOfLaborContract"
            type="date"
            placeholder="选择日期"
            style="width:100%"
          />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="入司日期" prop="entryDate">
          <el-date-picker
            v-model="adminForm.entryDate"
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
        <el-form-item label="操作员代码" prop="operatorCode">
          <el-input v-model="adminForm.operatorCode" type="text" placeholder="admin" style="width:100%;" disabled />
        </el-form-item>
      </el-col>

    </el-row>
    <!-- 分界线 -->
    <el-divider />
  </el-form>
</template>

<script>

export default {
  name: 'AdministrativeInformation',
  data() {
    return {
      adminForm: {

        secondManage: '', // 二级管理系统
        thirdManage: '', // 三级管理系统
        fourthManage: '', // 四级管理系统

        position: '', // 岗位
        staffRank: '', // 人员职级
        teamStructure: '', // 团队架构

        teamSupervisorId: '', // 团队主管工号
        teamLeadersName: '', // 团队主管姓名
        typeOfContract: '', // 合同类型

        startOfLaborContract: '', // 劳动合同起期
        termOfLaborContract: '', // 劳动合同止期
        entryDate: '', // 入司日期

        operatorCode: '' // 操作员代码

      },
      rules: {
        secondManage: { required: true, message: '该项不可为空', trigger: 'change' }, // 二级管理系统
        thirdManage: { required: true, message: '该项不可为空', trigger: 'change' }, // 三级管理系统
        fourthManage: { required: true, message: '该项不可为空', trigger: 'change' }, // 四级管理系统

        position: { required: true, message: '该项不可为空', trigger: 'change' }, // 岗位
        staffRank: { required: true, message: '该项不可为空', trigger: 'change' }, // 人员职级
        teamStructure: { required: true, message: '该项不可为空', trigger: 'change' }, // 团队架构

        teamSupervisorId: { required: true, message: '该项不可为空', trigger: 'change' }, // 团队主管工号
        teamLeadersName: { required: true, message: '该项不可为空', trigger: 'change' }, // 团队主管姓名
        typeOfContract: { required: true, message: '该项不可为空', trigger: 'change' }, // 合同类型

        startOfLaborContract: { required: true, message: '该项不可为空', trigger: 'change' }, // 劳动合同起期
        termOfLaborContract: { required: true, message: '该项不可为空', trigger: 'change' }, // 劳动合同止期
        entryDate: { required: true, message: '该项不可为空', trigger: 'change' }, // 入司日期

        operatorCode: { required: true, message: '该项不可为空', trigger: 'change' } // 操作员代码
      }
    }
  }
}
</script>
