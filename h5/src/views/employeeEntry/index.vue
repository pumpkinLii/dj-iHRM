<template>
  <div class="app-container">
    <el-form ref="form" :rules="rules" :model="form" label-width="180px">
      <h4>代理人信息</h4>
      <el-row>
        <el-col :span="8">
          <el-form-item label="业务员代码">
            <el-input type="text" placeholder="admin" disabled />
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
            <el-input v-model="form.agentName" type="text" style="width:100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件类型" prop="idType">
            <el-select v-model="form.idType" type="text" style="width:100%;" @change="handleIdTypeChange">
              <el-option v-for="(option,index) in list.idType" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="证件号码" prop="idNo">
            <el-input v-model="form.idNo" type="text" :placeholder="(form.agentName === '' || form.idType ==='')?'请输入姓名并选择证件类型':''" style="width:100%;" :disabled="form.agentName === '' || form.idType ===''" @blur="idNumberCheck" /><!-- :disabled="data.isdisabled1 -->
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="出生日期" prop="birthday">
            <el-date-picker v-model="form.birthday" value-format="yyyy-MM-dd" type="date" placeholder="填写您的出生日期" style="width:100%;" :disabled="form.idType === '0'" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别" prop="sex">
            <el-select v-model="form.sex" type="text" style="width:100%;" :disabled="form.idType === '0'">
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
              <el-option v-for="(option,index) in list.bankProvince" :key="index" :label="option.label" :value="option.value">
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
            <el-select v-model="form.nativeplace" type="text" style="width:100%;">
              <el-option v-for="(option,index) in list.nativeplace" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="最近一份工作行业类型" prop="oldIndustryType">
            <el-select v-model="form.oldIndustryType" type="text" style="width:100%;">
              <el-option v-for="(option,index) in list.oldIndustryType" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
            </el-select>
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
              <el-option v-for="(option,index) in list.outlookStatus" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
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
            <el-select v-model="form.bankProvince" type="text" style="width:100%;" @change="getCitiesByProvince(form.bankProvince)">
              <el-option v-for="(option,index) in list.bankProvince" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="开户行所在市" prop="bankCity">
            <el-select v-model="form.bankCity" type="text" style="width:100%;" :disabled="form.bankProvince===''">
              <el-option v-for="(option,index) in list.city" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="银行类型" prop="bankCode">
            <el-select v-model="form.bankCode" type="text" style="width:100%;">
              <el-option v-for="(option,index) in list.bankCode" :key="index" :label="option.label" :value="option.value">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ option.value }}</span>
                <span style="float: right">{{ option.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-divider />
      <h4>行政信息</h4>
      <!-- 1-->
      <el-row>
        <el-col :span="8">
          <el-form-item label="二级管理机构" prop="manageCom2">
            <el-select v-model="form.manageCom2" placeholder="请选择" style="width:100%" @change="getManageComCode(2)">
              <el-option v-for="(item,index) in list.manageCom2" :key="index" :value="item.value" :label="item.label">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                <span style="float: right">{{ item.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="三级管理机构" prop="manageCom3">
            <el-select v-model="form.manageCom3" placeholder="请选择" style="width:100%" :disabled="!form.manageCom2||form.manageCom2.length===0" @change="getManageComCode(3)">
              <el-option v-for="(item,index) in list.manageCom3" :key="index" :value="item.value" :label="item.label">
                <span style="float: left; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                <span style="float: right">{{ item.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="四级管理机构" prop="manageCom4">
            <el-select v-model="form.manageCom4" placeholder="请选择" style="width:100%" :disabled="!form.manageCom3||form.manageCom3.length===0" @change="getBranchAttr">
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
            <el-select v-model="form.agentJob" placeholder="请选择" style="width:100%" @change="handleRankChange">
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
            <el-select v-model="form.branchAttr" placeholder="请选择" style="width:100%" @change="getTeamsByBranchAttr">
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
              @change="dateCheck"
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
              placeholder="选择日期"
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
      <!-- 分界线 -->
      <el-divider />
    </el-form>
    <el-row>
      <el-col style="text-align:center">
        <el-button type="primary" @click.native.prevent="submitForm">保存</el-button>
        <el-button type="secondary" @click.native.prevent="resetForm">重置</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as API from '@/api/employee'
import * as validator from '@/utils/validate'

export default {
  name: 'EmployeeEntry',
  data() {
    const checkIdentityId = (rule, value, callback) => {
      if (this.form.idType === '0') {
        const errorMsg = validator.isIdentityId(value)
        if (errorMsg !== '') {
          callback(new Error(errorMsg))
        } else {
          callback()
        }
      } else {
        if ((/^\d{1,18}$/.test(value)) || value === '') {
          callback()
        }
        callback(new Error('证件号小于等于18位,且为数字'))
      }
    }
    return {
      // 下拉菜单选项
      list: {
        // 代理人信息
        idType: [], // 证件类型
        sex: [], // 性别
        rgtType: [], // TODO 后续内容缺乏注释
        nativeplace: [],
        highestDegree: [],
        degree: [],
        nationality: [],
        outlookStatus: [],
        oldIndustryType: [], // 最近一份工作
        bankProvince: [],
        city: [],
        cityObject: [], // 同时有省和市
        bankCode: [],
        // 行政信息
        manageCom2: [],
        manageCom3: [],
        manageCom4: [],
        agentGrade: [],
        branchAttr: []
      },
      // 存放码表信息
      codes: {},
      // 表单信息
      form: {
        // 代理人信息
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
        // 行政信息
        manageCom2: '', // 二级管理系统
        manageCom3: '', // 三级管理系统
        manageCom4: '', // 四级管理系统
        agentJob: '', // 岗位
        agentGrade: '', // 人员职级
        branchAttr: '', // 团队架构
        contractStartDate: '', // 劳动合同起期
        contractEndDate: '', // 劳动合同止期
        employDate: '', // 入司日期
        // 行政信息请求中无需注意的部分，后端不接收
        teamSupervisorId: '', // 团队主管工号
        teamSupervisorName: '', // 团队主管姓名
        operator: 'admin', // 操作员代码
        contractType: '02' // 合同类型
      },
      // 校验规则
      rules: {
        // 代理人信息
        agentName:
          [
            { required: true, message: '请输入业务员姓名', trigger: 'blur' },
            { validator: validator.isValidChineseName, trigger: 'blur' }
          ],
        idType:
          [{ required: true, message: '请输入证件类型', trigger: 'change' }],
        postCode:
          [{ validator: validator.isZip, trigger: 'blur' }],
        idNo:
          [
            { required: true, message: '请输入证件号码', trigger: 'blur' },
            { validator: checkIdentityId, trigger: 'blur' }
          ],
        birthday:
          [{ required: true, message: '请输入出生日期', trigger: 'change' }],
        bankCode:
          [{ required: true, message: '请输入银行类别', trigger: 'change' }],
        homephone:
          [{ validator: validator.isValidTel, trigger: 'blur' }],
        sex:
          [{ required: true, message: '请选择性别', trigger: 'change' }],
        rgtType:
          [{ required: true, message: '请选择户口类型', trigger: 'change' }],
        rgtProvince:
          [{ required: true, message: '请选择户口所在省', trigger: 'change' }],
        degree:
          [{ required: true, message: '请选择第一学历', trigger: 'change' }],
        highestDegree:
          [{ required: true, message: '请选择最高学位', trigger: 'change' }],
        graduatedSchool:
          [{ required: true, message: '请输入毕业院校', trigger: 'blur' }],
        major:
          [{ required: true, message: '请输入专业', trigger: 'blur' }],
        nationality:
          [{ required: true, message: '请输入民族', trigger: 'change' }],
        nativeplace:
          [{ required: true, message: '请选择籍贯', trigger: 'blur' }],
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
            { validator: validator.isNum, trigger: 'blur' }],
        homeAddress:
          [{ required: true, message: '请输入家庭地址', trigger: 'blur' }],
        phone:
          [
            { required: true, message: '请输入手机', trigger: 'blur' },
            { validator: validator.phoneNumberValidator, trigger: 'blur' }
          ],
        email:
          [
            { required: true, message: '请输入E-mail', trigger: 'blur' },
            { validator: validator.isValidEmail, trigger: 'blur' }
          ],
        outlookStatus:
          [{ required: true, message: '请输入政治面貌', trigger: 'change' }],
        accountBankHeadOffice:
          [{ required: true, message: '请输入账户银行总行', trigger: 'change' }],
        bankAccount:
          [
            { required: true, message: '请输入银行账号', trigger: 'blur' },
            { validator: validator.isValidateBankNo, trigger: 'blur' }
          ],
        uniteBankNum:
          [
            { required: true, message: '请输入银行卡开户行联行号', trigger: 'blur' },
            { validator: validator.isNum, trigger: 'blur' }
          ],
        bankProvince:
          [{ required: true, message: '请输入开户行省份', trigger: 'change' }],
        bankCity:
          [{ required: true, message: '请输入开户行所在市', trigger: 'change' }],
        // 行政信息
        manageCom2:
          [{ required: true, message: '请选择二级管理系统', trigger: 'change' }], // 二级管理系统
        manageCom3:
          [{ required: true, message: '请选择三级管理系统', trigger: 'change' }], // 三级管理系统
        manageCom4:
          [{ required: true, message: '请选择四级管理系统', trigger: 'change' }], // 四级管理系统
        agentJob:
          [{ required: true, message: '请选择岗位', trigger: 'change' }], // 岗位
        agentGrade:
          [{ required: true, message: '请选择人员职级', trigger: 'change' }], // 人员职级
        branchAttr:
          [{ required: true, message: '请选择团队架构', trigger: 'change' }], // 团队架构
        contractType:
          [{ required: true, message: '请选择合同类型', trigger: 'change' }], // 合同类型
        contractStartDate:
          [{ required: true, message: '请选择劳动合同起期', trigger: 'change' }], // 劳动合同起期
        contractEndDate:
          [{ required: true, message: '请选择劳动合同止期', trigger: 'change' }], // 劳动合同止期
        employDate:
          [{ required: true, message: '请选择入司日期', trigger: 'change' }], // 入司日期
        operator:
          [{ required: true, message: '操作员代码不正确', trigger: 'change' }] // 操作员代码
      }
    }
  },
  created() {
    this.getInitOptions() // 获取初始下拉菜单，获取码值
  },
  methods: {
    getInitOptions() {
      API.getCodes().then(
        r => {
          this.codes = r['resource']
          this.fillCodes() // 将获取的码值填入需要的项中
        })
      API.getCityList().then(
        r => {
          this.list.cityObject = r
          this.list.bankProvince = r['province']
        })
      this.getManageComCode(1)
    },
    // 填充码表能够填充的下拉菜单
    fillCodes() {
      if (!this.codes) {
        this.$message.error('未能正确获取下拉菜单')
        return
      }
      this.setCodes('dajiaidtype', this.list.idType)
      this.setCodes('sex', this.list.sex)
      this.setCodes('rgttype', this.list.rgtType)
      this.setCodes('nativeplace', this.list.nativeplace)
      this.setCodes('highestdegree', this.list.highestDegree)
      this.setCodes('degree', this.list.degree)
      this.setCodes('nationality', this.list.nationality)
      this.setCodes('industrytype', this.list.oldIndustryType)
      this.setCodes('bankcode', this.list.bankCode)
      this.setCodes('polityvisage', this.list.outlookStatus)
      // 排序民族
      this.list.nationality.sort((a, b) => {
        return a.value - b.value
      })
      // 排序籍贯
      this.list.nativeplace.sort((a, b) => {
        return a.value - b.value
      })
    },
    // 通过以获取的码表数据填充某一列表
    setCodes(name, buffer) {
      if (!(this.codes[name])) {
        return
      }
      Object.keys(this.codes[name])
        .forEach((key) => {
          buffer.push({
            label: this.codes[name][key],
            value: key
          })
        })
    },
    // 提交表单
    submitForm() {
      this.$refs['form'].validate()
        .then(() => {
          this.sendSubmitRequest()
        })
        .catch(() => {
          this.$message.warning('请检查表单是否有误')
        })
    },
    // 重置表单
    resetForm() {
      this.$refs['form'].resetFields()
    },
    // 发送提交请求
    sendSubmitRequest() {
      API.submit(this.form)
        .then((r) => {
          alert(r['msg'])
          this.$message.success('添加成功')
          this.$refs['form'].resetFields()
        })
        .catch(() => {
          this.$message.error('添加失败')
        })
    },
    getManageComCode(level) {
      let comCode = 0
      if (level === 1) {
        comCode = '86'
      } else if (level === 2) {
        comCode = this.form.manageCom2
        this.list.manageCom3 = []
        this.form.manageCom3 = ''
        this.list.manageCom4 = []
        this.form.manageCom4 = ''
      } else if (level === 3) {
        comCode = this.form.manageCom3
        this.list.manageCom4 = []
        this.form.manageCom4 = ''
      } else {
        return
      }
      API.getManageCom(comCode) // 获取管理机构总的下拉菜单
        .then(r => {
          this.list['manageCom' + (level + 1)] = []
          if (!r['list']) {
            this.$message.warning('没有更多机构')
            return
          }
          r['list'].forEach((item) => {
            this.list['manageCom' + (level + 1)].push(
              {
                label: item['name'],
                value: item['comcode']
              }
            )
          })
        })
    },
    idNumberCheck() {
      const data = {
        idNo: this.form.idNo,
        idType: this.form.idType
      }
      API.idCheck(data)
        .then((res) => {
          if (res['code'] === 0) {
            // 验证通过
            this.$message.success('证件校验通过')
            if (this.form.idType === '0') {
              const strBirthday = this.form.idNo.slice(6, 10) + '-' + this.form.idNo.slice(10, 12) + '-' + this.form.idNo.slice(12, 14)
              const strSex = this.form.idNo.slice(16, 17)
              this.form.sex = String((parseInt(strSex) + 1) % 2)
              this.form.birthday = strBirthday
            }
          } else {
            // 验证不通过
            this.$message.error('证件校验不通过，请检查证件号码是否有误')
            if (this.form.idType === '0') {
              // 清空身份证带下来的信息
              this.form.sex = '' // TODO 后续服务器开服后需要测试下
              this.form.birthday = ''
            }
          }
        })
    },
    getCitiesByProvince(provinceCode) {
      this.list.cityObject.province.forEach(item => {
        if (item.value === provinceCode) {
          this.list.city = item.list
        }
      })
      this.form.bankCity = ''
    },
    dateCheck() {
      if (this.form.contractStartDate.length !== 0 && this.form.contractEndDate.length !== 0) {
        if (this.form.contractStartDate > this.form.contractEndDate) {
          this.$message.error('终止日期不能小于起始时期')
          this.form.contractEndDate = ''
        } else {
          console.log('日期校验失败')
        }
      }
    },
    // 人员职级
    handleRankChange() {
      this.list.agentGrade = []
      this.form.agentGrade = ''
      if (this.form.agentJob === '0') {
        API.posit().then(r => {
          this.list.agentGrade = r['list']
        })
      } else if (this.form.agentJob === '1') {
        API.posit1().then(r => {
          this.list.agentGrade = r['list']
        })
      }
      this.getBranchAttr()
    },
    // 团队架构渲染
    getBranchAttr() {
      if (this.form.agentJob && this.form.manageCom4) {
        const data = {
          gradeName: this.form.agentJob,
          managerCom: this.form.manageCom4
        }
        API.zhiji(data).then(r => {
          this.list.branchAttr = r['list']
        })
      }
    },
    // 团队回显
    getTeamsByBranchAttr() {
      const data = {
        groupId: this.form.branchAttr
      }
      API.huixian(data).then(r => {
        this.form.teamSupervisorId = r.data.branchManager
        this.form.teamSupervisorName = r.data.branchManagerName
      })
    },
    // 身份证类型改变时的回调
    handleIdTypeChange() {
      this.form.idNo = ''
      this.form.birthday = ''
      this.form.sex = ''
    }
  }
}
</script>

<style scoped>
.app-container{
  padding: 20px 4% 20px 1%;
}
</style>
