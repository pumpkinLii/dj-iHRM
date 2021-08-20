/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

export function isValidEmail(rule, value, callback) {
  if (/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value)) {
    return callback()
  }
  callback(new Error('请输入合法的电子邮件'))
}

export function phoneNumberValidator(rule, value, callback) {
  if (/^1[34578]\d{9}$/.test(value)) {
    return callback()
  }
  callback(new Error('请输入合法的手机号'))
}

export function phoneNumberValidatorAllowNull(rule, value, callback) {
  if (!value || value.length === 0 || /^1[34578]\d{9}$/.test(value)) {
    return callback()
  }
  callback(new Error('请输入合法的手机号'))
}

export function isValidIdNumber(rule, value, callback) {
  if (/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(value)) {
    return callback()
  }
  callback(new Error('请输入合法的身份证号'))
}

export function isValidChineseName(rule, value, callback) {
  if ((/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(value)) || value === '') {
    return callback()
  }
  callback(new Error('请输入合法的中文姓名'))
}

export function isValidTel(rule, value, callback) {
  if ((/^(\d{4})\d{6,8}$/.test(value)) || /^1[34578]\d{9}$/.test(value) || value === '') {
    return callback()
  }
  callback(new Error('请输入区号+住宅电话/手机号'))
}

export function isZip(rule, value, callback) {
  if ((/^[0-9]{6}$/.test(value)) || value === '') {
    return callback()
  }
  callback(new Error('请输入正确的邮编!'))
}

export function isNum(rule, value, callback) {
  if ((/^[1-9][0-9]*$/.test(value)) || value === '') {
    return callback()
  }
  callback(new Error('请输入数字!'))
}

// 身份证校验
export function isIdentityId(identityId) {
  const reg = /(^\d{15}$)|(^\d{17}(\d|X|x)$)/
  // 地区校验
  const aCity = {
    11: '北京',
    12: '天津',
    13: '河北',
    14: '山西',
    15: '内蒙古',
    21: '辽宁',
    22: '吉林',
    23: '黑龙江',
    31: '上海',
    32: '江苏',
    33: '浙江',
    34: '安徽',
    35: '福建',
    36: '江西',
    37: '山东',
    41: '河南',
    42: '湖北',
    43: '湖南',
    44: '广东',
    45: '广西',
    46: '海南',
    50: '重庆',
    51: '四川',
    52: '贵州',
    53: '云南',
    54: '西藏',
    61: '陕西',
    62: '甘肃',
    63: '青海',
    64: '宁夏',
    65: '新疆',
    71: '台湾',
    81: '香港',
    82: '澳门',
    91: '国外'
  }
  // 出生日期验证
  const sBirthday = (
    identityId.substr(6, 4) +
    '-' +
    Number(identityId.substr(10, 2)) +
    '-' +
    Number(identityId.substr(12, 2))
  ).replace(/-/g, '/')
  const d = new Date(sBirthday)
  // 身份证号码校验 最后4位  包括最后一位的数字/字母X
  let sum = 0
  const weights = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]
  const codes = '10X98765432'
  for (let i = 0; i < identityId.length - 1; i++) {
    sum += identityId[i] * weights[i]
  }
  const last = codes[sum % 11] // 计算出来的最后一位身份证号码
  let errorMsg = ''
  if (identityId === '') {
    errorMsg = '身份证号不能为空'
  } else if (!reg.exec(identityId)) {
    errorMsg = '你输入的身份证长度或格式错误'
  } else if (!aCity[parseInt(identityId.substr(0, 2))]) {
    errorMsg = '你的身份证地区非法'
  } else if (identityId[identityId.length - 1] !== last) {
    errorMsg = '你输入的身份证号非法'
  } else if (sBirthday !== d.getFullYear() + '/' + (d.getMonth() + 1) + '/' + d.getDate()) {
    errorMsg = '身份证上的出生日期非法'
  }
  return errorMsg
}

export function isValidateBankNo(rule, value, callback) {
  if ((/^([1-9]{1})(\d{14}|\d{18}|\d{15})$/.test(value)) || value === '') {
    return callback()
  }
  callback(new Error('请输入正确的银行卡号!'))
}
