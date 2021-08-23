import request from '@/utils/request'

// 人员异动信息查询
export function query(data, page) {
  // const url = process.env.NODE_ENV === 'development' ? 'http://10.11.116.111:9999/search/return' : '/search/return'
  // const url = 'http://10.11.116.111:9999/search/return?limit=' + page.pageSize + '&' + 'page=' + page.currentPage
  const url = '/search/return'
  const param = '?limit=' + page.pageSize + '&' + 'page=' + page.currentPage
  return request({
    url: url + param,
    method: 'post',
    data
  })
}

export function getSelectOptions() {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://10.11.114.126:9999/returnMes/com4Info' : '/returnMes/com4Info',
    url: '/returnMes/com4Info',
    method: 'post'
  })
}

export function getNextOptions(code) {
  // const url = process.env.NODE_ENV === 'development' ? 'http://10.11.114.126:9999/returnMes/groupInfo' : '/returnMes/groupInfo'
  const url = '/returnMes/groupInfo'
  const param = '?manageCode=' + code
  return request({
    url: url + param,
    method: 'post'
  })
}

export function submit(data) {
  // const url = process.env.NODE_ENV === 'development' ? 'http://10.11.114.126:9999/returnMes/managerInfo' : '/returnMes/managerInfo'
  const url = '/returnMes/managerInfo'
  const param = '?branchAttr=' + data
  return request({
    url: url + param,
    method: 'post'
  })
}

export function change(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://10.11.114.123:9999/agent/change' : '/agent/change',
    url: '/agent/change',
    method: 'post',
    data
  })
}

export function threeOptions() {
  return request({
    url: '/get/comresource',
    method: 'post'
  })
}
