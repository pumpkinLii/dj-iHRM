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

export function getPeopleInformation(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://11.11.111.1111:9999/returnMes/com4Info' : '/xxx/xxx',
    url: 'http://11.11.111.1111:9999/xxx/xxx',
    method: 'post',
    data
  })
}

export function submitInformation(data) {
  return request({
    // url: process.env.NODE_ENV === 'development' ? 'http://11.11.111.1111:9999/returnMes/com4Info' : '/xxx/xxx',
    url: 'http://11.11.111.1111:9999/xxx/xxx',
    method: 'post',
    data
  })
}
