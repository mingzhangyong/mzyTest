Page({
  data: {
    skus: []
  },
  onLoad: function () {
      var that = this;

        wx.request({
        url: 'https://test.eyaos.com/data/api/zhongbiao/v3/', 
        header: {
            'content-type': 'application/json'
        },
        success: function(res) {
            console.log(res.data.sku_list)
            that.setData({
               skus: res.data.results
            })
        }
        })
  }
})