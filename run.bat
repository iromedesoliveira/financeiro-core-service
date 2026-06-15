@echo off
echo Iniciando Financeiro Core Service...

:: Verifica se a variavel de ambiente NV_PASSWORD existe
if "%NV_PASSWORD%"=="" (
    echo [ERRO] Variavel de ambiente NV_PASSWORD nao configurada!
    echo Por favor, configure o acesso ao seu banco de dados Oracle.
    pause
    exit /b
)

:: Verifica se a variavel de ambiente API_SECURITY_TOKEN_SECRET existe
if "%API_SECURITY_TOKEN_SECRET%"=="" (
    echo [AVISO] Variavel API_SECURITY_TOKEN_SECRET nao encontrada. Usando valor padrao de desenvolvimento.
    set API_SECURITY_TOKEN_SECRET=API0123456789
)

:: Executa a aplicacao passando as variaveis de ambiente para a JVM
mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-DAPI_SECURITY_TOKEN_SECRET=%API_SECURITY_TOKEN_SECRET%"

pause