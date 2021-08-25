import request from '@/utils/request'

export function find(data) {
  // const url = ''
  // const param = '?limit=' + page.pageSize + '&' + 'page=' + page.currentPage
  return request({
    // url: url + param,
    url: '/test',
    method: 'post',
    data
  })
}

// 审核通过 王佳智
// asd
export function check(data, par) {
  return request({
    url: '/resignate/approval?num=' + par,
    method: 'post',
    data
  })
}

