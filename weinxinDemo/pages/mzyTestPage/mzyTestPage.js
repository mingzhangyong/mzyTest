Page({
  data: {
    skus: []
  },
  onLoad: function () {
      var that = this;

        wx.request({
        url: 'https://test.eyaos.com//index/api/recent?type=2', //仅为示例，并非真实的接口地址
        // data: {
        //     type: '1' ,
        //     y: ''
        // },
        header: {
            'content-type': 'application/json'
        },
        success: function(res) {
            console.log(res.data.sku_list)
            that.setData({
               skus: res.data.sku_list
            })
        }
        })
  }
})