import request from '@/utils/request'

// 查询人员信息 池浩玥 已改
export function queryPerson(data, page) {
  // const url = process.env.NODE_ENV === 'development' ? 'http://10.11.114.56:9999/test/QueryStaff' : '/test/QueryStaff'
  const url = '/login/YlAgentUpdate/queryStaff'
  const param = '?limit=' + page.pageSize + '&' + 'page=' + page.currentPage
  return request({
    url: url + param,
    method: 'post',
    data
  })
}

export function download() {
  return request({
    url: '/Board/YlInsertExcel',
    method: 'post',
    responseType: 'blob'
  })
}

// 导出
export function download1(data) {
  return request({
    url: '/test/ylLaAgentAttrExcelOut',
    method: 'post',
    responseType: 'blob',
    data
  })
}
// 管理机构下拉列表 王佳智 已改
export function xiala(abc) {
  return request({
    url: '/login/get/gets?code=' + abc,
    method: 'post'
  })
}
export function xiala1() {
  return request({
    url: '/login/get/gets?code=86',
    method: 'post'
  })
}
// 团队下拉列表
export function xiala3(data) {
  return request({
    url: '/login/Team/queryGroupbyCom4',
    method: 'post',
    data
  })
}
// Excel 导入  搁置

