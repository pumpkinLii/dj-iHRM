<template>
  <div class="app-container">
    <h4>职级调整</h4>
    <el-form :model="form" :rules="rules" label-width="180px">
      <!-- 第一行-->
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
            />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="团队代码">
            <el-input v-model="form.agentGroup" placeholder="请输入团队代码" clearable />
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
          <el-form-item label="代理人代码">
            <el-input v-model="form.agentCode" placeholder="请输入代理人代码" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="代理人姓名">
            <el-input v-model="form.agentName" placeholder="请输入代理人姓名" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入手机号" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第三行-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="证件号码">
            <el-input v-model="form.idNo" placeholder="请输入证件号码" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="当前职级">
            <el-select v-model="form.agentGrade" style="width: 100%" clearable>
              <el-option v-for="(item,index) in list.agentGrade" :key="index" :value="item.gradecode" :label="item.gradename">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.gradecode }}</span>
                <span style="float: right">{{ item.gradename }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <!--      第四行-->
      <el-row>
        <el-form-item>
          <el-col style="text-align:left;margin-top: 1rem">
            <el-button type="primary" icon="el-icon-search" @click="hello">查询</el-button>
          </el-col>
        </el-form-item>
      </el-row>
    </el-form>
    <el-divider />
    <!--    表格-->
    <Table />
  </div>
</template>
<script>
import Table from '@/components/Agentadjust/table'
import * as validator from '@/utils/validate'
import * as V from '@/api/agent'

export default {
  name: '',
  components: { Table },
  data() {
    return {
      form: {
        manageCom: '', // 管理机构
        agentGroup: '', // 团队代码
        branchName: '', // 团队名称
        agentCode: '', // 代理人代码
        agentName: '', // 代理人姓名
        phone: '', // 手机号
        idNo: '', // 证件号码
        agentGrade: '' // 当前职级
      },
      list: {
        agentGrade: ''
      },
      rules: {
        phone:
          [
            { validator: validator.phoneNumberValidatorAllowNull, trigger: 'blur' }
          ],
        manageCom:
          [
            { required: true, message: '请选择管理机构', trigger: 'blur' }
          ]
      },
      options: []
    }
  },
  created() {
    V.manage().then((r) => {
      this.options.push(r.result)
    })
    V.staff().then((r) => {
      this.list.agentGrade = r.list
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
    // this.$bus.$off()
    // this.$bus.$off()
  },
  methods: {
    // 查询按钮
    hello() {
      if (this.form.manageCom !== undefined && this.form.manageCom !== '' && this.form.manageCom.length !== 0) {
        this.$bus.$emit('something', this.form)
      } else {
        this.$bus.$emit('something1')
      }
    },
    // 下拉框选择弹回
    changeVal() {
      // eslint-disable-next-line prefer-const
      let t
      clearTimeout(t)
      t = setTimeout(() => {
        this.$refs.elcascader.dropDownVisible = false
      }, 300)
    }
  }
}
</script>
<style  scoped>
.app-container{
  padding: 20px 5% 20px 1%;
}

</style>
