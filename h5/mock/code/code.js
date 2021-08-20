const Mock = require('mockjs')

const codesData = Mock.mock({
  id: '@id'
})

const managecomData = Mock.mock({
  'totallist|5-10': [
    {
      'label|+1': [
        '@string(5)'
      ],
      'value|+1': [
        '@id'
      ]
    }
  ]
})

module.exports = [
  {
    url: '/start/get',
    type: 'post',
    response: config => {
      return {
        code: 0,
        data: codesData
      }
    }
  },
  {
    url: '/get/managecom',
    type: 'post',
    response: config => {
      return {
        code: 0,
        totallist: managecomData.totallist
      }
    }
  }
]
