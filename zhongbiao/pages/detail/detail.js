//logs.js
var util = require('../../utils/util.js')
Page({
  data: {
    message:""
  },
  onLoad: function (options) {
     wx.showToast({
       title:options.slug,
       duration:2000
     })
    this.setData({
     message:options.slug
    })
  }
})
