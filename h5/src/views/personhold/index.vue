<template>
  <div class="app-container">
    <h4>人员维护</h4>
    <el-form :model="form" label-width="180px">
      <!--    第一行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="二级管理机构" prop="manageCom2">
            <el-select v-model="form.manageCom2" placeholder="请选择" style="width:100%" @change="select(form.manageCom2)" clearable>
              <el-option v-for="(item,index) in list.manageCom2" :key="index" :value="item.comcode" :label="item.name">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.comcode }}</span>
                <span style="float: right">{{ item.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="三级管理机构" prop="manageCom3">
            <el-select v-model="form.manageCom3" placeholder="请选择" style="width:100%" :disabled="!form.manageCom2||form.manageCom2.length===0" @change="select1(form.manageCom3)" clearable>
              <el-option v-for="(item,index) in list.manageCom3" :key="index" :value="item.comcode" :label="item.name">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.comcode }}</span>
                <span style="float: right">{{ item.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="四级管理机构" prop="manageCom4">
            <el-select v-model="form.manageCom4" placeholder="请选择" style="width:100%" :disabled="!form.manageCom3||form.manageCom3.length===0" @change="group(form.manageCom4)" clearable>
              <el-option v-for="(item,index) in list.manageCom4" :key="index" :value="item.comcode" :label="item.name">
                <span style="float: left; color: #313336; font-size: 13px">{{ item.comcode }}</span>
                <span style="float: right">{{ item.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!--    第二行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="团队">
            <el-select placeholder="请选择" style="width: 100%" v-model="form.branchAttr" clearable>
              <el-option v-for="(item,index) in list.branchAttr" :key="index" :value="item.value" :label="item.label">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                <span style="float: right">{{ item.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人员工号">
            <el-input v-model="form.agentCode" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <!--    第三行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="人员姓名">
            <el-input v-model="form.agentName" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人员状态">
            <el-select placeholder="请选择" style="width: 100%" v-model="form.agentState" clearable>
              <el-option label="在职" value="0">
                <span style="float: left; color: #8492a6; font-size: 13px">0</span>
                <span style="float: right">在职</span>
              </el-option>
              <el-option label="二次入司" value="1">
                <span style="float: left; color: #8492a6; font-size: 13px">1</span>
                <span style="float: right">二次入司</span>
              </el-option>
              <el-option label="离职" value="2">
                <span style="float: left; color: #8492a6; font-size: 13px">2</span>
                <span style="float: right">离职</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!--    第四行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="入司日期起期" style="width:100%">
            <el-date-picker
              type="date"
              aria-placeholder="入司日期起期"
              style="width: 100%"
              v-model="form.startDate"
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="入司日期止期" style="width: 100%">
            <el-date-picker
              type="date"
              aria-placeholder="入司日期止期"
              style="width: 100%"
              v-model="form.endDate"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <!--     第五行-->
      <el-row>
        <el-form-item>
          <el-col style="text-align:left;margin-top: 1rem">
<!--            <el-button type="primary" icon="el-icon-search" @click="handleQuery(true)">查询</el-button>-->
            <el-button type="primary" icon="el-icon-search" @click="handleQuery1()">查询</el-button>
            <el-button type="primary" icon="el-icon-download" @click="download2" >导出</el-button>
            <el-button type="primary" icon="el-icon-upload2">Excel导入</el-button>
            <el-button type="primary" icon="el-icon-download" @click="download1">模板下载</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
<!--    分割线-->
    <el-divider />
<!--    表格-->
    <GroupTable/>
  </div>
</template>
<script>
import GroupTable from '@/components/PersonEdit/personTable'
import * as V from '@/api/personhold';

export default {
  name: 'zc',
  components:{GroupTable },
  data() {
    return {
      // 数据绑定
      form: {
        manageCom2: '', //二级管理机构
        manageCom3: '' ,// 三级管理机构
        manageCom4: '' , //四级管理机构
        branchAttr: '', // 团队
        agentCode: '',  // 人员工号
        agentName: '',  //人员姓名
        agentState: '', //人员状态
        startDate: '',  // 入司日期起期
        endDate: '' //入司日期止期
      },
      // 下拉列表
      list: {
        manageCom2: [],
        manageCom3: [],
        manageCom4: [],
        branchAttr: []
      },
    }
  },
  created() {
    // 二级下拉列表 渲染
    V.xiala1().then((r) => {
      this.list.manageCom2 = r.list
    });
  },
  methods:{
    //团队下拉列表
    group(data){
      const f ={"manageCom4" : data}
      V.xiala3(f).then((r)=>{
        this.list.branchAttr = r.list
        console.log("成功导入")
      })
    },
    // 查询按钮
    handleQuery1(){
      this.$bus.$emit("form2",this.form)
    },
    // 三四级下拉列表渲染
    select(abc) {
      this.form.manageCom3 = ''
      this.form.manageCom4 = ''
      V.xiala(abc).then((r)=>{
          this.list.manageCom3 = r.list

      })
    },
    select1(abc){
      this.form.manageCom4 = ''
      V.xiala(abc).then((r)=>{
        this.list.manageCom4 = r.list
      })
    },
    // 模板下载
    download1() {
      V.download().then((res) => {
        const link = document.createElement('a')
        const blob = new Blob([res], { type: 'application/vnd.ms-excel' })
        link.style.display = 'none'
        link.href = URL.createObjectURL(blob)
        link.setAttribute('download', `${"员工批量导入模板"}.xlsx`)
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
      })
    },
    // 结果导出
    download2(){
      V.download1(this.form).then((res) =>{
        const link = document.createElement('a')
        const blob = new Blob([res], { type: 'application/vnd.ms-excel' })
        link.style.display = 'none'
        link.href = URL.createObjectURL(blob)
        link.setAttribute('download', `${"查询结果导出"}.xlsx`)
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
      })
    }
  },
  mounted() {
    this.$bus.$on('refresh',()=>{
      this.handleQuery1()
      //this.$bus.$off('refresh')
    })
  },
  beforeDestroy() {
    this.$bus.$off('refresh')
  }
}
</script>
<style scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}
</style>
