import request from '@/utils/request'

// 查询接口
export function dismissQuery(data, page) {
  const url = 'http://10.11.114.128:9999/dismiss/query'
  const param = '?limit=' + page.pageSize + '&' + 'page=' + page.currentPage
  return request({
    url: url + param,
    method: 'post',
    data
  })
}

// 提交审核接口
export function submit(data) {
  return request({
    url: 'http://10.11.114.126:9999/dimission/audit',
    method: 'post',
    data
  })
}

export function getPeopleInformation(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://11.11.111.1111:9999/returnMes/com4Info' : '/xxx/xxx',
    url: 'http://10.11.114.63:9999/depart/Modify',
    method: 'post',
    data
  })
}

export function submitModifyInformation(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://11.11.111.1111:9999/returnMes/com4Info' : '/xxx/xxx',
    url: 'http://10.11.116.111:9999/Dimission/Dimission',
    method: 'post',
    data
  })
}

export function submitAddInformation(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://11.11.111.1111:9999/returnMes/com4Info' : '/xxx/xxx',
    url: 'http://10.11.116.111:9999/insertDimission/insertDimission',
    method: 'post',
    data
  })
}
