import request from '@/utils/request'

// 用于获得下拉菜单选项
export function getSelectOptional() {
  return request({
    url: 'http://10.11.115.18:9999/start/get',
    method: 'post'
  })
}
// 检查身份证号
//  data:{
//   idNo:123,
//   idType:234
// }

// 王佳智的ip
export function check(data) {
  const Url = 'http://10.11.115.18:9999/test/idCheck'
  return request({
    url: Url,
    method: 'post',
    // data:data
    data
  })
}

// 获得城市列表  //王欣艺的ip
export function getcity() {
  return request({
    url: 'http://10.11.115.18:9999/area/get',
    method: 'post'
  })
}

export function getCode(name, buffer) {
  request({
    url: 'http://10.11.115.18:9999/start/get',
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
