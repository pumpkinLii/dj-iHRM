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
