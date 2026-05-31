# Financeiro Core Service

Projeto de backend para gerenciamento financeiro, focado em alta performance, escalabilidade e manutenibilidade. Desenvolvido para servir como uma base robusta para serviços financeiros, utilizando práticas de Clean Code e arquitetura orientada a serviços.

## 🚀 Tecnologias e Ferramentas
- **Java 21**
- **Spring Boot 3.4.3**
- **Spring Data JPA & Hibernate**
- **Oracle Database** (Produção) / **H2 Database** (Testes)
- **JUnit 5 & Mockito** (Testes Automatizados)
- **Lombok** (Redução de boilerplate)

## 🛠 Arquitetura e Diferenciais
- **Testes de Integração:** Implementação de ambiente de testes isolado usando H2 em memória, garantindo build rápido e seguro.
- **Camada de Dados:** Uso de JPA/Hibernate com estratégias de persistência otimizadas para Oracle.
- **Padrões de Projeto:** Aplicação de princípios SOLID e padrões de Clean Architecture.
- **Versionamento:** Fluxo de trabalho baseado em boas práticas de Git/GitFlow.

## 📋 Como rodar o projeto
1. Clone o repositório:
   `git clone https://github.com/iromedesoliveira/financeiro-core-service.git`
2. Certifique-se de ter o JDK 21 instalado.
3. Para rodar os testes:
   `./mvnw clean test`
4. Para rodar a aplicação:
   `./mvnw spring-boot:run`

## 📈 Roadmap (Status de Desenvolvimento)
- [x] Configuração inicial e infraestrutura básica (Maven/Spring).
- [x] Integração com banco de dados (Oracle) e testes (H2).
- [ ] Implementação de camadas de Service e Repositories.
- [ ] Desenvolvimento de APIs REST com Spring Security.
- [ ] Implementação de Frontend (React/Angular).
- [ ] Containerização com Docker e CI/CD.

## ✒️ Autor
Iromedes C. de Oliveira - Desenvolvedor Full Stack Sênior