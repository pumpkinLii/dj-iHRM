<template>
  <el-form ref="form" :rules="rules" :model="form" label-width="180px">
    <el-row>
      <el-col :span="8">
        <el-form-item label="业务员代码">
          <el-input type="text" style="width:100%;" disabled />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="SAP工号">
          <el-input v-model="form.sapCode" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="业务员姓名" prop="agentName">
          <el-input v-model="form.agentName" type="text" style="width:100%;" /> <!-- @blur="blurtest1" -->
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="证件类型" prop="idType">
          <el-select v-model="form.idType" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.idType" :key="index" :label="option.label" :value="option.value" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="证件号码" prop="idNo">
          <el-input v-model="form.idNo" type="text" style="width:100%;" :disabled="form.agentName === '' || form.idType ===''" @blur="submit" /><!-- :disabled="data.isdisabled1 -->
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker v-model="form.birthday" value-format="yyyy-MM-dd" type="date" placeholder="填写您的出生日期" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.sex" :key="index" :label="option.label" :value="option.value" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="户口类型" prop="rgtType">
          <el-select v-model="form.rgtType" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.rgtType" :key="index" :label="option.label" :value="option.value" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="户口所在省" prop="rgtProvince">
          <el-select v-model="form.rgtProvince" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.nativeplace" :key="index" :label="option.label" :value="option.value" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="最高学历" prop="highestDegree">
          <el-select v-model="form.highestDegree" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.highestDegree" :key="index" :label="option.label" :value="option.value" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="第一学历">
          <el-input v-model="form.firstDegree" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="最高学位" prop="degree">
          <el-select v-model="form.degree" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.degree" :key="index" :label="option.label" :value="option.value" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="毕业院校" prop="graduateSchool">
          <el-input v-model="form.graduateSchool" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="专业" prop="major">
          <el-input v-model="form.major" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="民族" prop="nationality">
          <el-select v-model="form.nationality" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.nationality" :key="index" :label="option.label" :value="option.value" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="籍贯" prop="nativeplace">
          <el-input v-model="form.nativeplace" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="最近一份工作行业类型" prop="oldIndustryType">
          <el-input v-model="form.oldIndustryType" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="最近一份职业" prop="oldOccupation">
          <el-input v-model="form.oldOccupation" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="最近一家工作单位" prop="oldCom">
          <el-input v-model="form.oldCom" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="最近一份工作职务" prop="oldJobDuty">
          <el-input v-model="form.oldJobDuty" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="从业年限" prop="workAge">
          <el-input v-model="form.workAge" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="家庭地址" prop="homeAddress">
          <el-input v-model="form.homeAddress" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="邮编">
          <el-input v-model="form.postcode" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="住宅电话">
          <el-input v-model="form.homephone" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="手机" prop="phone">
          <el-input v-model="form.phone" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="E-mail" prop="email">
          <el-input v-model="form.email" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="政治面貌" prop="outlookStatus">
          <el-select v-model="form.outlookStatus" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.dajiapolityvisage" :key="index" :label="option.label" :value="option.value" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="专业资格证书号">
          <el-input v-model="form.certificateNo" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="银行账号" prop="bankAccount">
          <el-input v-model="form.bankAccount" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="银行开户行联行号" prop="uniteBankNum">
          <el-input v-model="form.uniteBankNum" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开户行省份" prop="bankProvince">
          <el-select v-model="form.bankProvince" type="text" style="width:100%;" @change="searchCity(form.bankProvince)">
            <el-option v-for="(option,index) in list.bankProvince" :key="index" :label="option.label" :value="option.label" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开户行所在市" prop="bankCity">
          <el-select v-model="form.bankCity" type="text" style="width:100%;" :disabled="form.bankProvince===''">  <!-- :disabled="data.isdisabled2" -->
            <el-option v-for="(option,index) in list.city" :key="index" :label="option.name" :value="option.code" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="银行类型" prop="bankCode">
          <el-select v-model="form.bankCode" type="text" style="width:100%;">
            <el-option v-for="(option,index) in list.bankCode" :key="index" :label="option.label" :value="option.label" />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
import * as API from '@/api/salesman'

export default {
  name: 'AgentInformation',
  data() {
    return {
      list: {
        idType: [],
        sex: [],
        rgtType: [],
        nativeplace: [],
        highestDegree: [],
        degree: [],
        nationality: [],
        dajiapolityvisage: [],
        bankProvince: [],
        city: [],
        bankCode: []
      },
      reslist: {
      },
      form: {
        sapCode: '', // SAP工号
        agentName: '', // 业务员姓名
        idType: '', // 证件类型
        idNo: '', // 证件号码
        birthday: '', // 出生日期
        sex: '', // 性别
        rgtType: '', // 户口类型
        rgtProvince: '', // 户口所在省
        highestDegree: '', // 最高学历
        firstDegree: '', // 第一学历
        degree: '', // 最高学位
        graduateSchool: '', // 毕业院校
        major: '', // 专业
        nationality: '', // 民族
        nativeplace: '', // 籍贯
        oldIndustryType: '', // 最近一份工作行业类型
        oldOccupation: '', // 最近一份职业
        oldCom: '', // 最近一家工作单位
        oldJobDuty: '', // 最近一份工作职务
        workAge: '', // 从业年限
        homeAddress: '', // 家庭地址
        postCode: '', // 邮编
        homephone: '', // 住宅电话
        phone: '', // 手机
        email: '', // E-mail
        outlookStatus: '', // 政治面貌
        bankAccount: '', // 银行账号
        uniteBankNum: '', // 银行卡开户行联行号
        bankProvince: '', // 开户行省份
        bankCity: '', // 开户行所在市
        certificateNo: '', // 专业资格证书
        bankCode: '' // 银行类型
      },
      rules: {
        agentName:
          [{ required: true, message: '请输入业务员姓名', trigger: 'blur' }],
        idType:
          [{ required: true, message: '请输入证件类型', trigger: 'change' }],
        idNo:
          [{ required: true, message: '请输入证件号码', trigger: 'blur' }],
        birthday:
          [{ required: true, message: '请输入出生日期', trigger: 'change' }],
        bankCode:
          [{ required: true, message: '请输入性别', trigger: 'change' }],
        sex:
          [{ required: true, message: '请输入性别', trigger: 'change' }],
        rgtType:
          [{ required: true, message: '请输入户口类型', trigger: 'change' }],
        rgtProvince:
          [{ required: true, message: '请输入户口所在省', trigger: 'change' }],
        degree:
          [{ required: true, message: '请输入最高学历', trigger: 'change' }],
        highestDegree:
          [{ required: true, message: '请输入最高学位', trigger: 'change' }],
        graduatedSchool:
          [{ required: true, message: '请输入毕业院校', trigger: 'blur' }],
        major:
          [{ required: true, message: '请输入专业', trigger: 'blur' }],
        nationality:
          [{ required: true, message: '请输入民族', trigger: 'change' }],
        nativeplace:
          [{ required: true, message: '籍贯', trigger: 'change' }],
        oldIndustryType:
          [{ required: true, message: '请输入最近一份工作行业类型', trigger: 'change' }],
        oldOccupation:
          [{ required: true, message: '请输入最近一份职业', trigger: 'blur' }],
        oldCom:
          [{ required: true, message: '请输入最近一家工作单位', trigger: 'blur' }],
        oldJobDuty:
          [{ required: true, message: '请输入最近一份职务', trigger: 'blur' }],
        workAge:
          [{ required: true, message: '请输入从业年限', trigger: 'blur' }],
        homeAddress:
          [{ required: true, message: '请输入家庭地址', trigger: 'blur' }],
        phone:
          [{ required: true, message: '请输入手机', trigger: 'blur' }],
        email:
          [{ required: true, message: '请输入E-mail', trigger: 'blur' }],
        outlookStatus:
          [{ required: true, message: '请输入政治面貌', trigger: 'change' }],
        accountBankHeadOffice:
          [{ required: true, message: '请输入账户银行总行', trigger: 'change' }],
        bankAccount:
          [{ required: true, message: '请输入银行账号', trigger: 'blur' }],
        uniteBankNum:
          [{ required: true, message: '请输入银行卡开户行联行号', trigger: 'blur' }],
        bankProvince:
          [{ required: true, message: '请输入开户行省份', trigger: 'change' }],
        bankCity:
          [{ required: true, message: '请输入开户行所在市', trigger: 'change' }]
      }
    }
  },
  created() {
    API.getcity().then(res => {
      this.reslist = res
    }).catch(err => {
      console.log('获取省市失败', err)
    })
    API.getCode('idtype', this.list.idType)
    API.getCode('sex', this.list.sex)
    API.getCode('rgttype', this.list.rgtType)
    API.getCode('nativeplace', this.list.nativeplace)
    API.getCode('highestdegree', this.list.highestDegree)
    API.getCode('degree', this.list.degree)
    API.getCode('nationality', this.list.nationality)
    API.getCode('dajiapolityvisage', this.list.dajiapolityvisage)
    API.getCode('nativeplace', this.list.bankProvince)
    API.getCode('bankcode', this.list.bankCode)
  },
  methods: {
    submit() {
      console.log(this.form)
      API.check(this.form).then((res) => {
        console.log(res.data.code)
        if (res.data.code === 0) { this.$message.success('成功') } else { this.$message.error(res.data.msg) }
      })
    },
    searchCity(province) {
      this.list.city = this.reslist[province]
      console.log(this.list.city)
    }
  }
}

</script>
