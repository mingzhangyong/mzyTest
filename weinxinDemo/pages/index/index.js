//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    customInfo: {}
  },
  //事件处理函数
  bindViewTapMZY: function() {
    wx.navigateTo({
      url: '../mzyTestPage/mzyTestPage'
    })
  },
  onLoad: function () {
    console.log('onLoad')
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      that.setData({
        userInfo:userInfo
      })
    })

        wx.request({
  url: 'https://test.eyaos.com/enterprise/api/customerservice/', //仅为示例，并非真实的接口地址
 
  header: {
      'content-type': 'application/json'
  },
  success: function(res) {
   that.setData({
     customInfo:res.data
   })
  }
})
  }
})
