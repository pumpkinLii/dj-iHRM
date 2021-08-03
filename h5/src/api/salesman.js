import request from '@/utils/request'

//用于提交数据
export function submit(data) {
    return request({
      url: '/test/test',
      method: 'post',
      data
    })
  }

//用于获得下拉菜单选项
export function getSelectOptional() {
    return request({
      url: '/test/test',
      method: 'post'
    })
  }