import request from '@/utils/request'

// 查询团队信息 王佳智 已改
export function queryGroup(data, page) {
  return request({
    url: '/login/Team/querygroup?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
}

// 修改团队信息 陈益轩 已改
export function modifyGroup(data) {
  return request({
    url: '/login/Team/updateGroup',
    method: 'post',
    data
  })
}

// 新增团队信息 王佳智 已改
export function addGroup(data) {
  return request({
    url: '/login/Team/insertgroup',
    method: 'post',
    data
  })
}
