<template>
  <div class="app-container">
    <el-form :model="form" label-width="180px">
      <!--    第一行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="二级管理机构" prop="manageCom2">
            <el-select v-model="form.manageCom2" placeholder="请选择" style="width:100%" @change="select(form.manageCom2)">
              <el-option v-for="(item,index) in list.manageCom2" :key="index" :value="item.comcode" :label="item.name">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.comcode }}</span>
                <span style="float: right">{{ item.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="三级管理机构" prop="manageCom3">
            <el-select v-model="form.manageCom3" placeholder="请选择" style="width:100%" :disabled="!form.manageCom2||form.manageCom2.length===0" @change="select(form.manageCom3)">
              <el-option v-for="(item,index) in list.manageCom3" :key="index" :value="item.comcode" :label="item.name">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.comcode }}</span>
                <span style="float: right">{{ item.name }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="四级管理机构" prop="manageCom4">
            <el-select v-model="form.manageCom4" placeholder="请选择" style="width:100%" :disabled="!form.manageCom3||form.manageCom3.length===0">
              <el-option v-for="(item,index) in list.manageCom4" :key="index" :value="item.comcode" :label="item.name">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.comcode }}</span>
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
            <el-select placeholder="请选择" style="width: 100%" v-model="form.branchAttr"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人员工号">
            <el-input v-model="form.agentCode"/>
          </el-form-item>
        </el-col>
      </el-row>
      <!--    第三行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="人员姓名">
            <el-input v-model="form.agentName"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="人员状态">
            <el-select placeholder="请选择" style="width: 100%" v-model="form.agentState">
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
            <el-button type="primary" icon="el-icon-download" >导出</el-button>
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
        agentState: []
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
    // 查询
    handleQuery1(){
      this.$bus.$emit("form2",this.form)
    },

    // 三四级下拉列表渲染
    select(abc) {
      V.xiala(abc).then((r)=>{
        if(abc.length===4){
          this.list.manageCom3 = r.list
        }else if(abc.length===6){
          this.list.manageCom4 = r.list
        }
      })
    },
    // 文件下载
    download1() {
      V.download().then((res) => {
        let url = window.URL.createObjectURL(new Blob([res.data]));
        let link =document.createElement("a");
        link.style.display = "none";
        //link.target = '_blank'
        link.href = url;
        link.setAttribute("download","all_sample.xls");
        document.body.appendChild(link);
        link.click();
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
