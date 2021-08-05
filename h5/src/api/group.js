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
