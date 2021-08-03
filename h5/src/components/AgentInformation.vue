<template>
  <el-form ref="agentForm" :rules="rules" :model="agentForm" label-width="180px">
    <el-row>
      <el-col :span="8">
        <el-form-item label="业务员代码">
          <el-input v-model="agentForm.salesmanNum" type="text" style="width:100%;" disabled />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="SAP工号">
          <el-input v-model="agentForm.salesmanJobnum" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="业务员姓名" prop="salesmanName">
          <el-input v-model="agentForm.salesmanName" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="证件类型" prop="certificateType">
          <el-select v-model="agentForm.certificateType" type="text" style="width:100%;">
            <el-option label="身份证" value="身份证" />
            <el-option label="护照" value="护照" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="证件号码" prop="certificateNum">
          <el-input v-model="agentForm.certificateNum" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker v-model="agentForm.birthday" type="date" placeholder="填写您的出生日期" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="性别" prop="sex">
          <el-select v-model="agentForm.sex" type="text" style="width:100%;">
            <el-option label="男" value="男" />
            <el-option label="女" value="女" />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="户口类型" prop="accountType">
          <el-select v-model="agentForm.accountType" type="text" style="width:100%;">
            <el-option label="本市农村" value="本市农村" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="户口所在省" prop="provinceOfResidence">
          <el-select v-model="agentForm.provinceOfResidence" type="text" style="width:100%;">
            <el-option label="山西" value="山西" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="最高学历" prop="highestEducation">
          <el-select v-model="agentForm.highestEducation" type="text" style="width:100%;">
            <el-option label="本科" value="本科" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="第一学历">
          <el-select v-model="agentForm.firstDegree" type="text" style="width:100%;">
            <el-option label="..." value="..." />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="最高学位" prop="highestDegree">
          <el-select v-model="agentForm.highestDegree" type="text" style="width:100%;">
            <el-option label="..." value="..." />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="毕业院校" prop="graduatedSchool">
          <el-input v-model="agentForm.graduatedSchool" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="专业" prop="major">
          <el-input v-model="agentForm.major" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="民族" prop="nationality">
          <el-select v-model="agentForm.nationality" type="text" style="width:100%;">
            <el-option label="汉族" value="汉族" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="籍贯" prop="hometown">
          <el-select v-model="agentForm.hometown" type="text" style="width:100%;">
            <el-option label="山西省" value="山西省" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="最近一份工作行业类型" prop="lastJobIndustryType">
          <el-select v-model="agentForm.lastJobIndustryType" type="text" style="width:100%;">
            <el-option label="工作类型" value="工作类型" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="最近一份职业" prop="recentCareer">
          <el-input v-model="agentForm.recentCareer" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="最近一家工作单位" prop="recentWorkUnit">
          <el-input v-model="agentForm.recentWorkUnit" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="最近一份工作职务" prop="recentJobTitle">
          <el-input v-model="agentForm.recentJobTitle" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="从业年限" prop="workingYears">
          <el-input v-model="agentForm.workingYears" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="家庭地址" prop="familyAddress">
          <el-input v-model="agentForm.familyAddress" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="邮编">
          <el-input v-model="agentForm.postCode" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="住宅电话">
          <el-input v-model="agentForm.residencePhone" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="手机" prop="mobilePhone">
          <el-input v-model="agentForm.mobilePhone" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="E-mail" prop="email">
          <el-input v-model="agentForm.email" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="政治面貌" prop="politicalStatus">
          <el-select v-model="agentForm.politicalStatus" type="text" style="width:100%;">
            <el-option label="党员" value="党员" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="账户银行总行" prop="accountBankHeadOffice">
          <el-select v-model="agentForm.accountBankHeadOffice" type="text" style="width:100%;">
            <el-option label="工商银行" value="工商银行" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="银行账号" prop="bankAccount">
          <el-input v-model="agentForm.bankAccount" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="银行开户行联行号" prop="bankCardAccountBankJointNumber">
          <el-input v-model="agentForm.bankCardAccountBankJointNumber" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开户行省份" prop="bankProvince">
          <el-select v-model="agentForm.bankProvince" type="text" style="width:100%;">
            <el-option label="山西省" value="山西省" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开户行所在市" prop="cityWhereTheAccountBankIsLocated">
          <el-select v-model="agentForm.cityWhereTheAccountBankIsLocated" type="text" style="width:100%;">
            <el-option label="运城市" value="运城市" />
            <el-option label="..." value="..." />
          </el-select>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-form-item label="专业资格证书">
          <el-input v-model="agentForm.professionalQualificationCertificate" type="text" style="width:100%;" />
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>

export default {
  name: 'AgentInformation',
  data() {
    return {
      agentForm: {
        salesmanNum: '', // 业务员代码
        salesmanJobnum: '', // 业务员工号
        salesmanName: '', // 业务员姓名
        certificateType: '', // 证件类型
        certificateNum: '', // 证件号码
        birthday: '', // 出生日期
        sex: '', // 性别
        accountType: '', // 户口类型
        provinceOfResidence: '', // 户口所在省
        highestEducation: '', // 最高学历
        firstDegree: '', // 第一学历
        highestDegree: '', // 最高学位
        graduatedSchool: '', // 毕业院校
        major: '', // 专业
        nationality: '', // 民族
        hometown: '', // 籍贯
        lastJobIndustryType: '', // 最近一份工作行业类型
        recentCareer: '', // 最近一份职业
        recentWorkUnit: '', // 最近一家工作单位
        recentJobTitle: '', // 最近一份工作职务
        workingYears: '', // 从业年限
        familyAddress: '', // 家庭地址
        postCode: '', // 邮编
        residencePhone: '', // 住宅电话
        mobilePhone: '', // 手机
        email: '', // E-mail
        politicalStatus: '', // 政治面貌
        accountBankHeadOffice: '', // 账户银行总行
        bankAccount: '', // 银行账号
        bankCardAccountBankJointNumber: '', // 银行卡开户行联行号
        bankProvince: '', // 开户行省份
        cityWhereTheAccountBankIsLocated: '', // 开户行所在市
        professionalQualificationCertificate: '' // 专业资格证书
      },
      rules: {
        salesmanName:
          [{ required: true, message: '请输入业务员姓名', trigger: 'change' }],
        certificateType:
          [{ required: true, message: '请输入证件类型', trigger: 'change' }],
        certificateNum:
          [{ required: true, message: '请输入证件号码', trigger: 'change' }],
        birthday:
          [{ required: true, message: '请输入出生日期', trigger: 'change' }],
        sex:
          [{ required: true, message: '请输入性别', trigger: 'change' }],
        accountType:
          [{ required: true, message: '请输入户口类型', trigger: 'change' }],
        provinceOfResidence:
          [{ required: true, message: '请输入户口所在省', trigger: 'change' }],
        highestEducation:
          [{ required: true, message: '请输入最高学历', trigger: 'change' }],
        highestDegree:
          [{ required: true, message: '请输入最高学位', trigger: 'change' }],
        graduatedSchool:
          [{ required: true, message: '请输入毕业院校', trigger: 'change' }],
        major:
          [{ required: true, message: '请输入专业', trigger: 'change' }],
        nationality:
          [{ required: true, message: '请输入民族', trigger: 'change' }],
        hometown:
          [{ required: true, message: '籍贯', trigger: 'change' }],
        lastJobIndustryType:
          [{ required: true, message: '请输入最近一份工作行业类型', trigger: 'change' }],
        recentCareer:
          [{ required: true, message: '请输入最近一份职业', trigger: 'change' }],
        recentWorkUnit:
          [{ required: true, message: '请输入最近一家工作单位', trigger: 'change' }],
        recentJobTitle:
          [{ required: true, message: '请输入最近一份职务', trigger: 'change' }],
        workingYears:
          [{ required: true, message: '请输入从业年限', trigger: 'change' }],
        familyAddress:
          [{ required: true, message: '请输入家庭地址', trigger: 'change' }],
        mobilePhone:
          [{ required: true, message: '请输入手机', trigger: 'change' }],
        email:
          [{ required: true, message: '请输入E-mail', trigger: 'change' }],
        politicalStatus:
          [{ required: true, message: '请输入政治面貌', trigger: 'change' }],
        accountBankHeadOffice:
          [{ required: true, message: '请输入账户银行总行', trigger: 'change' }],
        bankAccount:
          [{ required: true, message: '请输入银行账号', trigger: 'change' }],
        bankCardAccountBankJointNumber: 
          [{ required: true, message: '请输入银行卡开户行联行号', trigger: 'change' }],
        bankProvince:
          [{ required: true, message: '请输入开户行省份', trigger: 'change' }],
        cityWhereTheAccountBankIsLocated:
          [{ required: true, message: '请输入开户行所在市', trigger: 'change' }]
      }
    }
  }
}
</script>
