@echo off
java  -Xmx512m -Xms512m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -jar target/wueasy-admin-web-1.0.6-beta.jar
@pause