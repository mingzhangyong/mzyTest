Page({
  data: {
    skus: [],
    hidden:false,
    getMore:false,
    areas:[],
    hideArea:false
  },
  radioChange: function(e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value)
    var that = this
     page=1;
     that.setData({
       skus:[],
       hidden:false,
       hideArea:true
     })
     areaId = e.detail.value
    getSkus(that,areaId) 
  },
  selectArea:function(){
    this.setData({
      hideArea:!this.data.hideArea
    })
  },
  onLoad: function () {
      var that = this;
      getSkus(that,areaId)
      getAreas(that)
  },
  onPullDownRefresh: function () {  
    //下拉  
    console.log("下拉");  
     var that = this
     page=1;
     that.setData({
       skus:[],
       hidden:false
     })
    getSkus(that,areaId) 
  },  
  onReachBottom: function () {  
    //上拉  
    console.log("上拉")   
    var that = this
    getSkus(that,areaId)
  } ,

   //选择区域
   selectAreaOk: function(event){
     var selectAreaId = event.target.dataset.areaid;
     var that = this
     areaId = selectAreaId
     for(var i = 0;i<this.data.areas.length;i++){
       if(this.data.areas[i].id==selectAreaId){
         this.data.areas[i].isSelect=true
       }else{
         this.data.areas[i].isSelect=false
       }
     }
     this.setData({
       areas:this.data.areas,
       skus:[],
       hideArea:true
     })
     getSkus(that,selectAreaId)
  }
})


//分页获取产品
var page = 1;
var areaId = 1;
var getSkus = function (that,areaId) {
        console.log("id == "+areaId)
        wx.request({
        url: 'https://test.eyaos.com/data/api/zhongbiao/v3'+'?page='+page+'&area='+areaId, 
        header: {
            'content-type': 'application/json'
        },
        success: function(res) {
          console.log("success")
          var skus = that.data.skus
          for(var i = 0;i<res.data.results.length;i++){
            skus.push(res.data.results[i])
          }
          // skus.concat(res.data.results);
          that.setData({
            skus:skus,
            hidden:true,
            areas:that.data.areas
          });
          page++;
        }
        }) 
  }


//获取地区列表
  var getAreas = function (that) {
        wx.request({
        url: 'http://test.eyaos.com/area/cityList/v2/1', 
        success: function(res) {
          for(var i=0;i<res.data.length;i++){
            res.data.isSelect=false;
          }
         that.setData({
           areas:res.data
         })
        }
        }) 
  }

 