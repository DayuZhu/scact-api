# scact-api

活动商城

# 代售管理平台

# java

1. 创建活动， 活动id，活动名称，活动开始时间，结束时间，活动中奖名单(上传excel)
   中奖人名单: 中奖人姓名，手机号，银行卡号，开户行，中奖金额，预留字段1，预留字段2，预留字段3
2. 劵码表：劵id，劵码，卡密，卡面值，状态（初始，已使用），预留字段1，预留字段2，预留字段3
3. 中奖名单 对应的劵码关系；
4. 商品push,根据b2c商城push流程；
5. 对应B2C商城订单明细下载；
6. 代付分账；
7. 券面额只有 1 5 10 100 500 1000

# 页面：

1. 活动创建页
2. 活动列表页 ---上传中奖人名单
3. 分账列表页面 ---展示出入金方/出金金额/出金状态

# B2C商城

php功能

1.商品对接接口；


```
POST 请求 
请求体(JSON 一个productID的列表):
[
  {
    "price": 100000,
    "productId": 32
  }
]

响应体格式(对应productId和B2C的outProductId以及B2C的productName):
{
  "data": [
    {
      "outProductId": 1,
      "productId": 123,
      "productName": "hah哈哈"
    }
  ],
  "retCode": "0"
}

```

2.代付分账调用接口；

3.订单明细下载；


根据外部产品ID关联
# 流程图
![流程](https://github.com/DayuZhu/scact-api/blob/master/document/%E6%B5%81%E7%A8%8B%E5%9B%BE.png)

# 任务
1.活动增加修改 100%

2.中奖名单查询 100%

3.中奖名单上传 90% 差联调

4.券明细下载信息查询 100%

5.模板下载 100%

6.B2C-订单产品明细下载 php端任务

7.分账接口 80% 无第三方分账接口

8.分账列表查询 100%




# 前端项目

- 需要安装node环境
- 进入目录front
- 执行npm i => npm run dev 启动项目
- 打包执行 npm run build  => 打包完成后生成dist目录


# 商城项目
1 部署流程:https://ask.shopxo.net/article/5

2 配置scact-api项目对应的ip和端口,文件在shopox/public/core.php, 修改define('SCACT_API','http://scact-api:port');
