import request from '@/utils/request'

// 用于获取码表
export function getAllCode() {
  return request({
    url: '/start/get',
    method: 'post'
  })
}
/*
  name:String,需要获取的码表的英文名
  buffer:Array,需要被更新的list
*/
export function getCode(name, buffer) {
  request({
    url: '/start/get',
    method: 'post'
  }).then(
    r => {
      if (!r.resource[name]) {
        return
      }
      Object.keys(r.resource[name])
        .forEach((key) => {
          buffer.push({
            label: r.resource[name][key],
            value: key
          })
        })
    }
  )
}
