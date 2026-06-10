Financeiro Core Service
Projeto de backend para gerenciamento financeiro, focado em alta performance, escalabilidade e manutenibilidade. Desenvolvido para servir como uma base robusta para serviços financeiros, utilizando práticas de Clean Code e arquitetura orientada a serviços.

🚀 Tecnologias e Ferramentas
Java 21

Spring Boot 3.4.3

Spring Data JPA & Hibernate

Oracle Database (Produção) / H2 Database (Testes)

JUnit 5 & Mockito (Testes Automatizados)

Lombok (Redução de boilerplate)

🛠 Arquitetura e Diferenciais
Testes de Integração: Implementação de ambiente de testes isolado usando H2 em memória, garantindo build rápido e seguro.

Camada de Dados: Uso de JPA/Hibernate com estratégias de persistência otimizadas para Oracle.

Padrões de Projeto: Aplicação de princípios SOLID e padrões de Clean Architecture.

Versionamento: Fluxo de trabalho baseado em boas práticas de Git/GitFlow.

📋 Como rodar o projeto
Clone o repositório:
git clone https://github.com/iromedesoliveira/financeiro-core-service.git

Certifique-se de ter o JDK 21 instalado.

Para rodar os testes:
./mvnw clean test

Para rodar a aplicação:
run.bat (configurado com variáveis de ambiente)

📈 Roadmap (Status de Desenvolvimento)
Fase 1: Core Service & Base de Dados (Concluído)

[x] Configuração inicial e infraestrutura básica (Maven/Spring).
[x] Integração com banco de dados (Oracle) e testes (H2).
[x] Implementação das camadas Service e Repository.
[x] Desenvolvimento de APIs REST (CRUD completo e Tratamento de Erros).
[x] Modelagem de relacionamentos (Usuario/Lancamento) com JPA.

Fase 2: Segurança & Evolução (Concluído)
[x] Segurança com Spring Security (Autenticação e Autorização).
[x] Implementação de DTOs e Validações de entrada.
[x] Refatoração e aplicação de Design Patterns.

Fase 3: Frontend & Integração

[ ] Desenvolvimento de interface utilizando React ou Angular.
[ ] Integração do Front com a API via Axios/HttpClient.
[ ] Gerenciamento de estado e dashboards financeiros.

Fase 4: DevOps & Infraestrutura

[ ] Containerização com Docker e Docker Compose.
[ ] Configuração de perfis de ambiente (Dev/Prod).
[ ] Automação de CI/CD via GitHub Actions.

Fase 5: Testes Finais, Refinamento & Entrega

[ ] Aumento da cobertura de testes (JUnit/Mockito).
[ ] Observabilidade, logs estruturados e monitoramento.
[ ] Otimização de performance e deploy final.

##✒️ Autor
Iromedes C. de Oliveira - Desenvolvedor Full Stack Sênior