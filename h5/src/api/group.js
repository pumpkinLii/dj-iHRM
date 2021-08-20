import request from '@/utils/request'

// 查询团队信息
export function queryGroup(data, page) {
  return request({
    url: '/query/group?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
}

// 修改团队信息
export function modifyGroup(data) {
  return request({
    url: '/test/UpdateGroup',
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
