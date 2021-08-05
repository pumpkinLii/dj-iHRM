import request from '@/utils/request'

// 查询团队信息
export function queryGroup(data, page, buffer) {
  return request({
    url: '/query/group?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
    .then(r => {
      console.log(r)
      buffer.list = r.list
      page.totalCount = r.totalcount
    })
    .catch(err => {
      console.log(err)
    })
}

// 修改团队信息
export function modifyGroup(data) {
  return request({
    url: 'http://10.11.114.126:9999/test/UpdateGroup',
    method: 'post',
    data
  })
}

// 新增团队信息
export function addGroup(data) {
  return request({
    url: '/create/group',
    method: 'post',
    data
  })
}
