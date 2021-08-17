import request from '@/utils/request'

// 人员异动信息查询
export function query(data, page) {
  return request({
    url: '/person/change/query?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
}
