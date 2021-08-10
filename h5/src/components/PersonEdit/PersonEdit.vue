<template>
  <div>
    <!-- 修改 -->
    <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog()" />
    <el-dialog title="修改用户" :visible.sync="editDialogVisible" width="90%" @close="editDialogClosed">
      <!-- 主体 -->
      <span>
        <h4>代理人信息</h4>
        <el-form ref="form" :rules="rules" :model="form" label-width="180px">
          <el-row>
            <el-col :span="8">
              <el-form-item label="业务员代码">
                <el-input type="text" style="width:100%;" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="8" />
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="业务员姓名" prop="agentName">
                <el-input v-model="form.agentName" type="text" style="width:100%;" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="证件类型" prop="idType">
                <el-select v-model="form.idType" type="text" style="width:100%;">
                  <el-option v-for="(option,index) in list.idType" :key="index" :label="option.label" :value="option.value">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
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
                <el-date-picker v-model="form.birthday" value-format="yyyy-MM-dd" type="date" placeholder="填写您的出生日期" style="width:100%;" disabled />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="性别" prop="sex">
                <el-select v-model="form.sex" type="text" style="width:100%;" disabled>
                  <el-option v-for="(option,index) in list.sex" :key="index" :label="option.label" :value="option.value" />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="户口类型" prop="rgtType">
                <el-select v-model="form.rgtType" type="text" style="width:100%;">
                  <el-option v-for="(option,index) in list.rgtType" :key="index" :label="option.label" :value="option.value">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="户口所在省" prop="rgtProvince">
                <el-select v-model="form.rgtProvince" type="text" style="width:100%;">
                  <el-option v-for="(option,index) in list.nativeplace" :key="index" :label="option.label" :value="option.value">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="最高学历" prop="highestDegree">
                <el-select v-model="form.highestDegree" type="text" style="width:100%;">
                  <el-option v-for="(option,index) in list.highestDegree" :key="index" :label="option.label" :value="option.value">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="第一学历">
                <el-select v-model="form.firstDegree" type="text" style="width:100%;">
                  <el-option v-for="(option,index) in list.highestDegree" :key="index" :label="option.label" :value="option.value">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="最高学位" prop="degree">
                <el-select v-model="form.degree" type="text" style="width:100%;">
                  <el-option v-for="(option,index) in list.degree" :key="index" :label="option.label" :value="option.value">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
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
                  <el-option v-for="(option,index) in list.nationality" :key="index" :label="option.label" :value="option.value">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
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
              <el-form-item label="邮编" prop="postCode">
                <el-input v-model="form.postCode" type="text" style="width:100%;" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="住宅电话" prop="homephone">
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
                  <el-option v-for="(option,index) in list.dajiapolityvisage" :key="index" :label="option.label" :value="option.value">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="账户银行总行" prop="bankCode">
                <el-select v-model="form.bankCode" type="text" style="width:100%;">
                  <el-option v-for="(option,index) in list.bankCode" :key="index" :label="option.label" :value="option.label">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
                </el-select>
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
                  <el-option v-for="(option,index) in list.bankProvince" :key="index" :label="option.label" :value="option.label">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                    <span style="float: right">{{ option.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="开户行所在市" prop="bankCity">
                <el-select v-model="form.bankCity" type="text" style="width:100%;" :disabled="form.bankProvince===''">  <!-- :disabled="data.isdisabled2" -->
                  <el-option v-for="(option,index) in list.city" :key="index" :label="option.name" :value="option.code">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ option.code }}</span>
                    <span style="float: right">{{ option.name }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="专业资格证书号">
                <el-input v-model="form.certificateNo" type="text" style="width:100%;" />
              </el-form-item>
            </el-col>
          </el-row>

          <el-divider />
          <h4>行政信息</h4>

          <el-row>
            <el-col :span="8">
              <el-form-item label="二级管理机构" prop="manageCom2">
                <el-select v-model="form.manageCom2" placeholder="请选择" style="width:100%">
                  <el-option v-for="(item,index) in list.manageCom2" :key="index" :value="item.value" :label="item.label">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                    <span style="float: right">{{ item.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="三级管理机构" prop="manageCom3">
                <el-select v-model="form.manageCom3" placeholder="请选择" style="width:100%" :disabled="!form.manageCom2||form.manageCom2.length===0">
                  <el-option v-for="(item,index) in list.manageCom3" :key="index" :value="item.value" :label="item.label">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                    <span style="float: right">{{ item.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="四级管理机构" prop="manageCom4">
                <el-select v-model="form.manageCom4" placeholder="请选择" style="width:100%" :disabled="!form.manageCom3||form.manageCom3.length===0" @change="ranks">
                  <el-option v-for="(item,index) in list.manageCom4" :key="index" :value="item.value" :label="item.label">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                    <span style="float: right">{{ item.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <!--2 -->
          <el-row>
            <el-col :span="8">
              <el-form-item label="岗位" prop="agentJob">
                <el-select v-model="form.agentJob" placeholder="请选择" style="width:100%" @change="rank">
                  <el-option label="总监" value="0">
                    <span style="float: left; color: #8492a6; font-size: 13px">0</span>
                    <span style="float: right">总监</span>
                  </el-option>
                  <el-option label="经理" value="1">
                    <span style="float: left; color: #8492a6; font-size: 13px">1</span>
                    <span style="float: right">经理</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="人员职级" prop="agentGrade">
                <el-select v-model="form.agentGrade" placeholder="请选择" style="width:100%" :disabled="form.agentJob.length===0">
                  <el-option v-for="(item,index) in list.agentGrade" :key="index" :value="item.value" :label="item.label">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                    <span style="float: right">{{ item.label }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="团队架构" prop="branchAttr">
                <el-select v-model="form.branchAttr" placeholder="请选择" style="width:100%" @change="echo">
                  <el-option v-for="(item,index) in list.branchAttr" :key="index" :value="item.value" :label="item.label">
                    <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                    <span style="float: right">{{ item.label }}</span>
                  </el-option>
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
        </el-form>
      </span>
      <!-- 底部 -->
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editUserInfo">保 存</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
import * as API from '@/api/salesman'
import * as A from '@/api/person'
import {
  isIdentityId,
  isValidChineseName,
  isValidEmail,
  isValidTel,
  isZip,
  phoneNumberValidator,
  isNum,
  isValidateBankNo
} from '@/utils/validate'

export default {
  name: 'PersonEdit',
  data() {
    const checkIdentityId = (rule, value, callback) => {
      if (this.form.idType === '0') {
        const errorMsg = isIdentityId(value)
        if (errorMsg !== '') {
          callback(new Error(errorMsg))
        }
      } else {
        if ((/^\d{1,18}$/.test(value)) || value === '') {
          return callback()
        }
        callback(new Error('证件号小于等于18位'))
      }
    }
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
        bankCode: [],

        manageCom2: [],
        manageCom3: [],
        manageCom4: [],

        agentGrade: [],
        branchAttr: []
      },
      reslist: {
      },
      form: {
        sapCode: '', // SAP
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
        bankCode: '', // 银行类型

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
      rules: {
        agentName:
          [{ required: true, message: '请输入业务员姓名', trigger: 'blur' },
            { validator: isValidChineseName, trigger: 'blur' }],
        idType:
          [{ required: true, message: '请输入证件类型', trigger: 'change' }],
        postCode:
          [{ validator: isZip, trigger: 'blur' }],
        idNo:
          [{ required: true, message: '请输入证件号码', trigger: 'blur' },
            { validator: checkIdentityId, trigger: 'blur' }],
        birthday:
          [{ required: true, message: '请输入出生日期', trigger: 'change' }],
        bankCode:
          [{ required: true, message: '请输入银行类别', trigger: 'change' }],
        homephone:
          [{ validator: isValidTel, trigger: 'blur' }],
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
          [{ required: true, message: '籍贯', trigger: 'blur' }],
        oldIndustryType:
          [{ required: true, message: '请输入最近一份工作行业类型', trigger: 'change' }],
        oldOccupation:
          [{ required: true, message: '请输入最近一份职业', trigger: 'blur' }],
        oldCom:
          [{ required: true, message: '请输入最近一家工作单位', trigger: 'blur' }],
        oldJobDuty:
          [{ required: true, message: '请输入最近一份职务', trigger: 'blur' }],
        workAge:
          [{ required: true, message: '请输入从业年限', trigger: 'blur' },
            { validator: isNum, trigger: 'blur' }],
        homeAddress:
          [{ required: true, message: '请输入家庭地址', trigger: 'blur' }],
        phone:
          [{ required: true, message: '请输入手机', trigger: 'blur' },
            { validator: phoneNumberValidator, trigger: 'blur' }],
        email:
          [{ required: true, message: '请输入E-mail', trigger: 'blur' },
            { validator: isValidEmail, trigger: 'blur' }],
        outlookStatus:
          [{ required: true, message: '请输入政治面貌', trigger: 'change' }],
        accountBankHeadOffice:
          [{ required: true, message: '请输入账户银行总行', trigger: 'change' }],
        bankAccount:
          [{ required: true, message: '请输入银行账号', trigger: 'blur' },
            { validator: isValidateBankNo, trigger: 'blur' }],
        uniteBankNum:
          [{ required: true, message: '请输入银行卡开户行联行号', trigger: 'blur' }],
        bankProvince:
          [{ required: true, message: '请输入开户行省份', trigger: 'change' }],
        bankCity:
          [{ required: true, message: '请输入开户行所在市', trigger: 'change' }],

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
      },
      // 窗口显示
      editDialogVisible: false
    }
  },
  created() {
    API.getcity().then(res => {
      this.reslist = res
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

    A.xiala().then((r) => {
      this.list.manageCom2 = r.list2
      this.list.manageCom3 = r.list3
      this.list.manageCom4 = r.list4
    })
  },
  methods: {
    submit() {
      const data = {
        idNo: this.form.idNo,
        idType: this.form.idType
      }
      API.check(data).then((res) => {
        if (res.code === 0) {
          this.$message.success('成功')
          const strBirthday = this.form.idNo.slice(6, 10) + '-' + this.form.idNo.slice(10, 12) + '-' + this.form.idNo.slice(12, 14)
          const strSex = this.form.idNo.slice(16, 17)
          const strProvince = this.form.idNo.slice(0, 2)
          console.log(this.list.nativeplace)
          this.form.sex = String((parseInt(strSex) + 1) % 2)
          this.form.birthday = strBirthday
          this.form.rgtProvince = String(strProvince)
        } else {
          this.$message.error(res.msg)
          this.form.idNO = ''
        }
      })
    },
    searchCity(province) {
      this.list.city = this.reslist[province]
      this.form.bankCity = ''
    },

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
      if (this.form.agentJob === '0') {
        A.posit().then(r => {
          this.list.agentGrade = r.list
        })
      } else if (this.form.agentJob === '1') {
        A.posit1().then(r => {
          this.list.agentGrade = r.list
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
    },

    // 监听修改用户对话框的关闭事件
    editDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    // 编辑修改用户对话框
    showEditDialog() {
      this.$bus.$on('agentcode', r => {
        A.queryAgent(r).then((res) => {
          console.log(r)
          if (res.code === 0) {
            this.$message.success('根据agentCode查询人员成功')
            this.form = res.data // 可能有问题wwwwwwwww

            this.editDialogVisible = true
          } else {
            this.$message.error(res.msg)
          }
        })
      })
      this.editDialogVisible = true
    },
    // 修改用户信息并提交
    editUserInfo() {
      A.editPerson(this.form).then(
        () => {
          this.$message.success('修改成功')
        }
      )
      this.editDialogVisible = false
    }
  }
}
</script>

<style scoped>

</style>
