import request from '@/utils/request'

// 用于获得下拉菜单选项
export function getSelectOptional() {
  return request({
    url: '/start/get',
    method: 'post'
  })
}
// 检查身份证号
//  data:{
//   idNo:123,
//   idType:234
// }

export function check(data) {
  const Url = '/test/idCheck'
  return request({
    url: Url,
    method: 'post',
    // data:data
    data
  })
}

export function getcity() {
  return request({
    url: '/area/get',
    method: 'post'
  })
}

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
