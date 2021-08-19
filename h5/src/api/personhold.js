import request from '@/utils/request'

// 查询人员信息
export function queryPerson(data, page) {
  return request({
    url: 'http://10.11.114.56:9999/test/QueryStaff?limit=' + page.pageSize + '&' + 'page=' + page.currentPage,
    method: 'post',
    data
  })
}

// 修改人员信息  搁置
// export function modifyPerson(data){
//   return request({
//     url:'',
//     method:'post',
//     data
//   })
// }
// 模板下载

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
// 管理机构下拉列表
export function xiala(abc) {
  return request({
    url: '/get/gets?Code=' + abc,
    method: 'post'
  })
}
export function xiala1() {
  return request({
    url: '/get/gets?Code=86',
    method: 'post'
  })
}
// 团队下拉列表
export function xiala3(data) {
  return request({
    url: '/test/QueryGroup',
    method: 'post',
    data
  })
}
// Excel 导入  搁置

