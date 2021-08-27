import request from '@/utils/request'

// 添加一个新的资格证信息 张毅珑 已改
export function insert(data) {
  return request({
    url: '/login/YlAgentCertificate/insert',
    method: 'post',
    data
  })
}

// 修改一个新的资格证信息 王佳智 已改
export function update(data) {
  return request({
    url: '/login/YlAgentCertificate/update',
    method: 'post',
    data
  })
}

// 通过人员工号获取人员姓名 张毅隆  已改
export function getAgentNameByAgentCode(data) {
  return request({
    url: '/login/back/searchNameById',
    method: 'post',
    data
  })
}
// 陈益轩 已改
export function queryQualification(data, page) {
  return request({
    url: '/login/YlAgentCertificate/retrieve?pageSize=' + page.pageSize + '&' + 'currentPage=' + page.currentPage,
    method: 'post',
    data
  })
}
// 陈益轩 已改
export function getNextOptions(code) {
  return request({
    url: '/login/YlAgentCertificate/returnCom?code=' + code,
    method: 'post'
  })
}
// 陈益轩 已改
export function getInitializeList() {
  return request({
    url: '/login/YlAgentCertificate/initList',
    method: 'post'
  })
}

export function downloadTemplate() {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://10.11.114.128:9999/cert/board' : '/cert/board',
    url: '/cert/board',
    method: 'post',
    responseType: 'blob'
  })
}

export function exportList(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://10.11.114.128:9999/cert/ExcelOut' : '/cert/ExcelOut',
    url: '/cert/ExcelOut',
    method: 'post',
    responseType: 'blob',
    data
  })
}

