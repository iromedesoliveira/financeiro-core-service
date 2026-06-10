@echo off  
mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-DAPI_SECURITY_TOKEN_SECRET=API0123456789"  
pause 
