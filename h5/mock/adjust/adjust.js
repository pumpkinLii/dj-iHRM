const Mock = require('mockjs')

const data = Mock.mock({
  totalCount: 42,
  'table|42': [{
    a: '@id',
    s: '@cname',
    d: '@cname',
    q: '@cname',
    w: '@id',
    e: '@cname',
    z: '@date',
    x: '@cname',
    c: '@cname',
    v: '@cname'
  }]
})

module.exports = [
  {
    url: '/text/zc',
    type: 'post',
    response: config => {
      const table = data.table
      return {
        code: 0,

        list: table

      }
    }
  }
]
