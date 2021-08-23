<template>
  <div class="app-container">
    <h4>主管任命</h4>
    <el-form label-width="180px">
      <!--      第一行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="二级管理机构">
            <el-select v-model="form.manageCom2" placeholder="请选择" style="width:100%" clearable @change="select(form.manageCom2)">
              <el-option v-for="(item,index) in list.manageCom2" :key="index" :value="item.comcode" :label="item.name">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.comcode }}</span>
                <span style="float: right">{{ item.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="三级管理机构" style="width: 100%">
            <el-select v-model="form.manageCom3" style="width: 100%" clearable>
              <el-option v-for="(item,index) in list.manageCom3" :key="index" :value="item.comcode" :label="item.name">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.comcode }}</span>
                <span style="float: right">{{ item.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队" style="width: 100%">
            <el-select v-model="form.branchAttr" style="width: 100%" clearable>
              <el-option v-for="(item,index) in list.branchAttr" :key="index" :value="item" :label="item">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ }}</span>
                <span style="float: right">{{ }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第二行-->
      <el-row>
        <el-form-item>
          <el-col style="text-align:left;margin-top: 1rem">
            <el-button type="primary" icon="el-icon-search" @click="hello">查询</el-button>
            <el-button type="primary" icon="el-icon-upload2" disabled>Excel导入</el-button>
            <el-button type="primary" icon="el-icon-download" disabled>模板下载</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
    <!--    分割线-->
    <el-divider />
    <Table1 />
  </div>
</template>

<script>

import Table1 from '@/components/Appointment/table'
import * as V from '@/api/personhold'

export default {
  name: 'Appoint',
  components: { Table1 },
  data() {
    return {
      form: {
        manageCom2: '', // 二级管理机构
        manageCom3: '', // 三级管理机构
        branchAttr: '' // 团队
      },
      list: {
        manageCom2: '',
        manageCom3: '',
        branchAttr: ''
      }
    }
  },
  created() {
    V.xiala1().then((r) => {
      this.list.manageCom2 = r.list
    })
  },
  methods: {
    // 查询按钮
    hello() {
      this.$bus.$emit('form', this.form)
    },
    // 三级管理机构下拉框
    select(abc) {
      // this.form.branchAttr = ''
      this.form.manageCom3 = ''
      V.xiala(abc).then((r) => {
        this.list.manageCom3 = r['list']
        console.log('成功了')
      })
    }
  }
}
</script>
<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>
