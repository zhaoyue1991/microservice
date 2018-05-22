# microservice
* 原书github链接：https://github.com/itmuch/spring-cloud-docker-microservice-book-code
## 多节点Eureka
### 配置
 * 修改hosts 127.0.0.1 stage1 stage2

## 服务user
## 服务movie

## 监控操作
* 启动eureka stage1
* 启动eureka stage2
* 启动user stage1
* 启动user stage2
* 启动movie stage1
* 启动movie stage2
* 启动turbine
* 启动dashboard
* 访问movie stage1、stage2接口产生监控数据
* 访问dashboard，域名/hystrix.stream，在url填写turbine地址，域名/turbine.stream

