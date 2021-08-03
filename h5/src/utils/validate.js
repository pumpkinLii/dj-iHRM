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

export function isValidTel(tel) {
  return /^1(3|4|5|6|7|8|9)d{9}$/.test(tel)
}

export function isValidIdNumber(idNumber) {
  return /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(idNumber)
}

export function isValidChineseName(name) {
  return /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/.test(name)
}
