import request from '@/utils/request'

// 人员异动信息查询
export function query(data, page) {
  return request({
    url: 'http://10.11.116.111:9999/search/return?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
}

export function getSelectOptions() {
  return request({
    url: 'http://10.11.114.126:9999/returnMes/com4Info',
    method: 'post'
  })
}

export function getNextOptions(code) {
  return request({
    url: 'http://10.11.114.126:9999/returnMes/groupInfo?manageCode=' + code,
    method: 'post'
  })
}

export function submit(data) {
  return request({
    url: 'http://10.11.114.126:9999/returnMes/managerInfo?branchAttr=' + data,
    method: 'post'
  })
}

export function change(data) {
  return request({
    url: 'http://10.11.114.123:9999/agent/change',
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
