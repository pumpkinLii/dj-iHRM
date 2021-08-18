import request from '@/utils/request'
// 查询按钮
export function find(data, page) {
  return request({
    url: 'http://10.11.114.57:9999/test/GradeQuery?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
}
// 管理机构下拉框
export function abc() {
  return request({
    url: '/get/comresource',
    method: 'post'
  })
}
// 岗位职级下拉框
export function staff() {
  return request({
    url: '/get/grade',
    method: 'post'
  })
}

// export function abcd() {
//   return request({
//     url: 'http://10.11.215.28:9999/get/managecom',
//     method: 'post'
//   })
// }
