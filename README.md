# microservice-demo
A experimental microservices. And a fast demo to microservices.


### Need to be done.
- [ ] consume the messages sequentially
- [ ] consume the messages concurrently
- [ ] use lite pull
 
### Tasks
- [ ] build the transaction service
  - [x] build the framework
- [ ] build the product service
  - [x] build the framework
- [ ] import the dubbo framework
  - [ ] build rpc call from center-bff to transaction.
    - [ ] start dubbo service on local.
      - [x] use the standalone model. On local Machine.
      - [] add dependency to the transaction service. register transaction service when it starts up.
  - [ ] build rpc call from center-bff to payment
    - [x] start dubbo service on local.
      - [x] use the standalone model. On local Machine.
      - [x] add dependency to the payment service. register transaction service when it starts up.
      - [x] add dependency to the center-bff service. register center-bff service when it starts up.
- [ ] implement the rocketmq for bff & payment
  - [x] server setup for rocketmq (local machine. 1 instance)
  - [x] bff rocketmq setup
  - [x] payment rocketmq setup
  - [x] add the UI for the rocketMQ
    - [x] download the rocketmq-dashboard jar 
    - [x] build up the dashboard
  - [ ] the Arvo serialize implementation.
### Issues encountered
  1. Spring boot 2.7.0 is working at the moment.
  2. Spring boot 3.x is not compatible with the dubbo 3.0 version. Which caused the others services cannot start successfully. The payment now is the correct version and organized in a more reasonable way.
  3. JDK 17 cannot work with the Dubbo 3.0.x version, unless add the `--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED
     ` to the JVM Options when the server started. otherwise an exception will be thrown.
  4. When implemented the rocketMQ. issue arise. Said No such topic. The reason is that the nameserver and broker are start on the local machine. We need to explicit add the name server address in the broker configuration. in this example is that in the bin/release file. The configuration is broker.conf. The link here is the guide for fixing this issue. https://juejin.cn/post/7134227366481494046

### Using the RocketMQ to decouple the payment and bff
1. [ ] download the rocketMQ and run it locally. (RocketMQ works well with java-8. not the java-11. So took me sometime to fix)