import request from '@/utils/request'

// 人员异动信息查询  张毅珑 已改
export function query(data, page) {
  // const url = process.env.NODE_ENV === 'development' ? 'http://10.11.116.111:9999/search/return' : '/search/return'
  // const url = 'http://10.11.116.111:9999/search/return?limit=' + page.pageSize + '&' + 'page=' + page.currentPage
  const url = '/login/YlAgentInsert/return'
  const param = '?limit=' + page.pageSize + '&' + 'page=' + page.currentPage
  return request({
    url: url + param,
    method: 'post',
    data
  })
}
// 已改 陈益轩
export function getSelectOptions() {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://10.11.114.126:9999/returnMes/com4Info' : '/returnMes/com4Info',
    url: '/login/back/com4info',
    method: 'post'
  })
}
// 已改 陈益轩
export function getNextOptions(code) {
  // const url = process.env.NODE_ENV === 'development' ? 'http://10.11.114.126:9999/returnMes/groupInfo' : '/returnMes/groupInfo'
  const url = '/login/back/groupinfo'
  const param = '?manageCode=' + code
  return request({
    url: url + param,
    method: 'post'
  })
}
// 已改 陈逸轩
export function submit(data) {
  // const url = process.env.NODE_ENV === 'development' ? 'http://10.11.114.126:9999/returnMes/managerInfo' : '/returnMes/managerInfo'
  const url = '/login/back/managerinfo'
  const param = '?branchAttr=' + data
  return request({
    url: url + param,
    method: 'post'
  })
}
// 已改 张晓成
export function change(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://10.11.114.123:9999/agent/change' : '/agent/change',
    url: '/login/YlAgentInsert/change',
    method: 'post',
    data
  })
}
// 已改 王佳智
export function threeOptions() {
  return request({
    url: '/login/get/comresource',
    method: 'post'
  })
}
