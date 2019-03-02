@echo off
java  -Xmx512m -Xms512m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m  -jar target/wueasy-admin-1.1.3.jar --spring.profiles.active=dev
@pause