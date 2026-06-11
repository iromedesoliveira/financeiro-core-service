Financeiro Core Service
🎯 Visão de Engenharia
Este projeto é uma arquitetura de referência para serviços financeiros, projetada para atender requisitos rigorosos de segurança, integridade de dados e auditoria. O sistema adota os princípios de Clean Architecture para garantir que as regras de negócio permaneçam desacopladas de frameworks e infraestrutura.

Ao final deste roadmap, o Financeiro Core Service será uma solução robusta capaz de:

Consistência Transacional: Assegurar a integridade de registros financeiros via JPA/Hibernate otimizado.

Segurança de Nível Corporativo: Autenticação stateless via JWT, garantindo proteção de dados sensíveis.

Observabilidade e Resiliência: Tratamento de erros centralizado e rastreável, reduzindo o tempo de depuração e melhorando a experiência do cliente final.

Escalabilidade DevOps: Preparado para orquestração em containers e entregas automatizadas via CI/CD.

🚀 Tecnologias e Ferramentas
Java 21

Spring Boot 3.4.3

Spring Data JPA & Hibernate

Oracle Database (Produção) / H2 Database (Testes)

JUnit 5 & Mockito (Testes Automatizados)

Lombok (Redução de boilerplate)

🛠 Arquitetura e Diferenciais
Testes de Integração: Implementação de ambiente de testes isolado usando H2, garantindo segurança em cada build.

Camada de Dados: Estratégias de persistência otimizadas para Oracle Database.

Padrões de Projeto: Aplicação rigorosa de SOLID e Design Patterns.

Versionamento: Fluxo de trabalho baseado em GitFlow.

📋 Como rodar o projeto
Clone o repositório: git clone https://github.com/iromedesoliveira/financeiro-core-service.git

Certifique-se de ter o JDK 21 instalado.

Rodar testes: ./mvnw clean test

Rodar aplicação: run.bat (verifique as variáveis de ambiente necessárias).

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

[ ] Desenvolvimento de interface (React/Angular).
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

✒️ Autor
Iromedes C. de Oliveira - Desenvolvedor Full Stack Sênior