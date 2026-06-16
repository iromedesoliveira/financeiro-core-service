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
    echo [AVISO] Variavel API_SECURITY_TOKEN_SECRET nao encontrada. Usando valor padrao.
    set API_SECURITY_TOKEN_SECRET=12345678901234567890123456789012
)

:: Executa a aplicacao passando as variaveis de ambiente e o perfil oracle
echo Executando com perfil: oracle...
mvnw spring-boot:run -Dspring-boot.run.profiles=oracle -Dspring-boot.run.jvmArguments="-DAPI_SECURITY_TOKEN_SECRET=%API_SECURITY_TOKEN_SECRET% -DNV_PASSWORD=%NV_PASSWORD%"

pause