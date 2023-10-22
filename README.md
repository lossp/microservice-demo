# microservice-demo
A experimental microservices. And a fast demo to microservices.

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
### Issues encountered
  1. Spring boot 2.7.0 is working at the moment.
  2. Spring boot 3.x is not compatible with the dubbo 3.0 version. Which caused the others services cannot start successfully. The payment now is the correct version and organized in a more reasonable way.
  3. JDK 17 cannot work with the Dubbo 3.0.x version, unless add the `--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED
     ` to the JVM Options when the server started. otherwise a exception will be thrown.
