# cskaoyan_mall
cskaoyan商城项目

[TOC]

## 小组成员

刘鑫，穆杨，曾金泽，黄俊龙，张涛，周建达

## 模块分工表

### 后端

|  成员  |        模块        |
| :----: | :----------------: |
|  刘鑫  |      推广管理      |
|  穆杨  |      商场管理      |
| 曾金泽 | 商场管理、统计报表 |
| 黄俊龙 |      商品管理      |
|  张涛  |      系统管理      |
| 周建达 | 用户管理、配置管理 |

### 前端

```yml
// 曾金泽
IndexUrl: WxApiRoot + 'home/index', //首页数据接口
CatalogList: WxApiRoot + 'catalog/index', //分类目录全部分类数据接口
CatalogCurrent: WxApiRoot + 'catalog/current', //分类目录当前分类数据接口

AuthLoginByWeixin: WxApiRoot + 'auth/login_by_weixin', //微信登录
AuthLoginByAccount: WxApiRoot + 'auth/login', //账号登录
AuthLogout: WxApiRoot + 'auth/logout', //账号登出
AuthRegister: WxApiRoot + 'auth/register', //账号注册
AuthReset: WxApiRoot + 'auth/reset', //账号密码重置
AuthRegisterCaptcha: WxApiRoot + 'auth/regCaptcha', //验证码
AuthBindPhone: WxApiRoot + 'auth/bindPhone', //绑定微信手机号

// 黄俊龙
GoodsCount: WxApiRoot + 'goods/count', //统计商品总数
GoodsList: WxApiRoot + 'goods/list', //获得商品列表
GoodsCategory: WxApiRoot + 'goods/category', //获得分类数据
GoodsDetail: WxApiRoot + 'goods/detail', //获得商品的详情
GoodsRelated: WxApiRoot + 'goods/related', //商品详情页的关联商品（大家都在看）

BrandList: WxApiRoot + 'brand/list', //品牌列表
BrandDetail: WxApiRoot + 'brand/detail', //品牌详情

// 刘鑫
CartList: WxApiRoot + 'cart/index', //获取购物车的数据
CartAdd: WxApiRoot + 'cart/add', // 添加商品到购物车
CartFastAdd: WxApiRoot + 'cart/fastadd', // 立即购买商品
CartUpdate: WxApiRoot + 'cart/update', // 更新购物车的商品
CartDelete: WxApiRoot + 'cart/delete', // 删除购物车的商品
CartChecked: WxApiRoot + 'cart/checked', // 选择或取消选择商品
CartGoodsCount: WxApiRoot + 'cart/goodscount', // 获取购物车商品件数
CartCheckout: WxApiRoot + 'cart/checkout', // 下单前信息确认

CollectList: WxApiRoot + 'collect/list', //收藏列表
CollectAddOrDelete: WxApiRoot + 'collect/addordelete', //添加或取消收藏

CommentList: WxApiRoot + 'comment/list', //评论列表
CommentCount: WxApiRoot + 'comment/count', //评论总数
CommentPost: WxApiRoot + 'comment/post', //发表评论

TopicList: WxApiRoot + 'topic/list', //专题列表
TopicDetail: WxApiRoot + 'topic/detail', //专题详情
TopicRelated: WxApiRoot + 'topic/related', //相关专题

SearchIndex: WxApiRoot + 'search/index', //搜索关键字
SearchResult: WxApiRoot + 'search/result', //搜索结果
SearchHelper: WxApiRoot + 'search/helper', //搜索帮助
SearchClearHistory: WxApiRoot + 'search/clearhistory', //搜索历史清楚

// 周建达
AddressList: WxApiRoot + 'address/list', //收货地址列表
AddressDetail: WxApiRoot + 'address/detail', //收货地址详情
AddressSave: WxApiRoot + 'address/save', //保存收货地址
AddressDelete: WxApiRoot + 'address/delete', //保存收货地址

ExpressQuery: WxApiRoot + 'express/query', //物流查询

RegionList: WxApiRoot + 'region/list', //获取区域列表

// 穆杨
OrderSubmit: WxApiRoot + 'order/submit', // 提交订单
OrderPrepay: WxApiRoot + 'order/prepay', // 订单的预支付会话
OrderList: WxApiRoot + 'order/list', //订单列表
OrderDetail: WxApiRoot + 'order/detail', //订单详情
OrderCancel: WxApiRoot + 'order/cancel', //取消订单
OrderRefund: WxApiRoot + 'order/refund', //退款取消订单
OrderDelete: WxApiRoot + 'order/delete', //删除订单
OrderConfirm: WxApiRoot + 'order/confirm', //确认收货
OrderGoods: WxApiRoot + 'order/goods', // 代评价商品信息
OrderComment: WxApiRoot + 'order/comment', // 评价订单商品信息

FeedbackAdd: WxApiRoot + 'feedback/submit', //添加反馈
FootprintList: WxApiRoot + 'footprint/list', //足迹列表
FootprintDelete: WxApiRoot + 'footprint/delete', //删除足迹

UserFormIdCreate: WxApiRoot + 'formid/create', //用户FromId，用于发送模版消息

GroupOnList: WxApiRoot + 'groupon/list', //团购列表
GroupOn: WxApiRoot + 'groupon/query', //团购API-查询
GroupOnMy: WxApiRoot + 'groupon/my', //团购API-我的团购
GroupOnDetail: WxApiRoot + 'groupon/detail', //团购API-详情
GroupOnJoin: WxApiRoot + 'groupon/join', //团购API-详情

// 张涛
CouponList: WxApiRoot + 'coupon/list', //优惠券列表
CouponMyList: WxApiRoot + 'coupon/mylist', //我的优惠券列表
CouponSelectList: WxApiRoot + 'coupon/selectlist', //当前订单可用优惠券列表
CouponReceive: WxApiRoot + 'coupon/receive', //优惠券领取
CouponExchange: WxApiRoot + 'coupon/exchange', //优惠券兑换

StorageUpload: WxApiRoot + 'storage/upload', //图片上传,

// 穆杨
UserIndex: WxApiRoot + 'user/index', //个人页面用户相关信息
```

## 早会记录

### 10/2

1. 进度表

   |   模块   |             进度             |
   | :------: | :--------------------------: |
   | 用户管理 |             完成             |
   | 商场管理 |      订单管理操作未完成      |
   | 商品管理 |    商品列表、修改商品完成    |
   | 推广管理 |        团购活动未完成        |
   | 系统管理 | 操作日志、角色管理授权未完成 |
   | 配置管理 |         运费配置完成         |
   | 统计报表 |         用户统计完成         |

2. 文件上传系统完成，代码修改

3. 问题处理交流

### 10/3

1. 进度表

   |   模块   |        进度        |
   | :------: | :----------------: |
   | 用户管理 |        完成        |
   | 商场管理 |        完成        |
   | 商品管理 |   参数校验未完成   |
   | 推广管理 |        完成        |
   | 系统管理 | 角色管理授权未完成 |
   | 配置管理 |        完成        |
   | 统计报表 |        完成        |

2. 问题交流

