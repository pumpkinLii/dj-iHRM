/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

export function isValidEmail(email) {
  return /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email)
}

export function phoneNumberValidator(rule, value, callback) {
  if (/^1[34578]\d{9}$/.test(value)) {
    return callback()
  }
  callback(new Error('请输入合法的手机号'))
}

export function phoneNumberValidatorAllowNull(rule, value, callback) {
  if (/^1[34578]\d{9}$/.test(value) || value.length === 0) {
    return callback()
  }
  callback(new Error('请输入合法的手机号'))
}

export function isValidIdNumber(idNumber) {
  return /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(idNumber)
}

export function isValidChineseName(name) {
  return /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(name)
}
