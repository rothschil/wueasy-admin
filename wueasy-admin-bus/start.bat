@echo off
java  -Xmx512m -Xms512m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Dcsp.sentinel.dashboard.server=127.0.0.1:8080 -jar target/wueasy-admin-bus-1.0.5-beta.jar
@pause