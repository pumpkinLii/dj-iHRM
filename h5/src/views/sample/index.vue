<template>
  <div class="app-container">
    <el-form ref="ruleForm" :rules="rules" label-width="160px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="Name" prop="name">
            <el-input v-model="ruleForm.name" type="text" placeholder="input your name" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Activity time">
            <el-date-picker type="date" placeholder="Pick a date" style="width:100%;" disabled />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="Activity time">
            <el-date-picker type="date" placeholder="Pick a date" style="width:100%;" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="Activity time">
            <el-date-picker type="date" placeholder="Pick a date" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="Activity time">
            <el-date-picker type="date" placeholder="Pick a date" style="width:100%;" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>

export default {
  name: 'Sample',
  data() {
    return {
      ruleForm: {
        name: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      }
    }
  }
}
</script>

<style>
.app-container{
  padding: 20px 5% 20px 1%;
}
.el-form-item__label{
  padding-left: 1rem;
}
</style>
