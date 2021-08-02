<template>
  <div class="app-container">
    <el-form ref="ruleForm" :rules="rules" label-width="160px">
      <!-- 1-->
      <el-divider />
      <el-row>
        <el-col :span="8">
          <el-form-item label="二级管理机构" prop="manage">
            <el-select v-model="ruleForm.secondManage" placeholder="请选择" style="width:100%">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="三级管理机构" prop="manage">
            <el-select v-model="ruleForm.thirdManage" placeholder="请选择" style="width:100%">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="四级管理机构" prop="manage">
            <el-select v-model="ruleForm.fourthManage" placeholder="请选择" style="width:100%">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!--2 -->
      <el-row>
        <el-col :span="8">
          <el-form-item label="岗位" prop="manage">
            <!-- <el-input v-model="ruleForm.name" type="text" placeholder="input your name" style="width:100%;" /> -->
            <el-select v-model="ruleForm.position" placeholder="请选择" style="width:100%">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人员职级" prop="manage">
            <el-select v-model="ruleForm.staffRank" placeholder="请选择" style="width:100%">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队架构" prop="manage">
            <el-select v-model="ruleForm.teamStructure" placeholder="请选择" style="width:100%">
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!--3 -->

      <el-row>
        <el-col :span="8">
          <el-form-item label="团队主管工号" prop="manage">
            <el-input v-model="ruleForm.teamSupervisorId" type="text" style="width:100%;" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队主管姓名" prop="manage">
            <el-input v-model="ruleForm.teamLeadersName" type="text" style="width:100%;" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="合同类型" prop="manage">
            <el-select v-model="ruleForm.typeOfContract" placeholder="合同制" style="width:100%" disabled>
              <el-option label="区域一" value="shanghai" />
              <el-option label="区域二" value="beijing" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!--4-->

      <el-row>
        <el-col :span="8">
          <el-form-item label="劳动合同起期" prop="manage">
            <el-date-picker
              v-model="ruleForm.startOfLaborContract"
              type="date"
              placeholder="选择日期"
              style="width:100%"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="劳动合同止期" prop="manage">
            <el-date-picker
              v-model="ruleForm.termOfLaborContract"
              type="date"
              placeholder="选择日期"
              style="width:100%"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="入司日期" prop="manage">
            <el-date-picker
              v-model="ruleForm.entryDate"
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
            <el-input v-model="ruleForm.operatorCode" type="text" placeholder="admin" style="width:100%;" disabled />
          </el-form-item>
        </el-col>

      </el-row>
      <!-- 分界线 -->
      <el-divider />

      <el-button type="primary" icon="el-icon-upload2">批量导入</el-button>
      <el-button type="primary" icon="el-icon-download">模板下载</el-button>
      <el-button type="primary" icon="el-icon-upload">上传附件</el-button>

      <el-button type="primary">保存</el-button>
      <el-button type="primary">重置</el-button>

    </el-form>
  </div>
</template>

<script>

export default {
  name: 'Sample',
  data() {
    return {
      ruleForm: {

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
      rules: [{ manage: { required: true, message: '请输入', trigger: 'blur' }}]
    }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
.el-form-item__label{
  padding-left: 1rem;
}
</style>
