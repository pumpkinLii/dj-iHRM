import request from '@/utils/request'

// 添加一个新的资格证信息
export function insert(data) {
  return request({
    url: '/...', // 待修改
    method: 'post',
    data
  })
}

// 修改一个新的资格证信息
export function update(data) {
  return request({
    url: '/...', // 待修改
    method: 'post',
    data
  })
}

export function queryQualification(data, page) {
  return request({
    url: 'xxx/xxx?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
}

export function getNextOptions(code) {
  return request({
    url: 'xxx/xxx?code=' + code,
    method: 'post'
  })
}

export function getInitializeList() {
  return request({
    url: 'xxx/xxx?code=',
    method: 'post'
  })
}
