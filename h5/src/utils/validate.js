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
  if (/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(name)) {
    return callback()
  }
  callback(new Error('请输入合法的中文姓名'))
}
