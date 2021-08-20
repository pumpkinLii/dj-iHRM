import request from '@/utils/request'

// 添加一个新的资格证信息
export function insert(data) {
  return request({
    url: '/certificate/insert',
    method: 'post',
    data
  })
}

// 修改一个新的资格证信息
export function update(data) {
  return request({
    url: '/certificate/Update',
    method: 'post',
    data
  })
}

// 通过人员工号获取人员姓名
export function getAgentNameByAgentCode(data) {
  return request({
    url: '/certificate/searchNameById',
    method: 'post',
    data
  })
}

export function queryQualification(data, page) {
  return request({
    url: '/certificate/retrieve?pageSize=' + page.pageSize + '&' + 'currentPage=' + page.currentPage,
    method: 'post',
    data
  })
}

export function getNextOptions(code) {
  return request({
    url: '/certificate/returnCom?code=' + code,
    method: 'post'
  })
}

export function getInitializeList() {
  return request({
    url: '/certificate/initList',
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

