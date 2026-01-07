🛒 ShopEasy – Sistema de Gerenciamento de Produtos e Pedidos

O ShopEasy é uma API REST desenvolvida em Java 17 com Spring Boot, focada no gerenciamento de produtos, pedidos e usuários, simulando o backend de um sistema de e-commerce.
O projeto utiliza Docker para padronizar o ambiente de desenvolvimento e facilitar a execução da aplicação.

🚀 Tecnologias Utilizadas

Java 17

Spring Boot 3.3.4

Spring Web

Spring Data JPA / Hibernate

PostgreSQL

Maven

Lombok

BCrypt (jbcrypt)

Jakarta Bean Validation

JUnit

Postman

Docker & Docker Compose

Git

🧱 Arquitetura

Arquitetura MVC (Model–View–Controller)

Separação em Controller, Service, Repository e Entity

Versionamento de endpoints (/api/v1/...)

Código organizado seguindo boas práticas de Clean Code

⚙️ Funcionalidades

CRUD completo de produtos

CRUD de pedidos

Gerenciamento e autenticação básica de usuários

Criptografia de senhas utilizando BCrypt

Validação de dados com Bean Validation

Persistência em banco de dados relacional (PostgreSQL)

Testes unitários com JUnit

Testes manuais das rotas REST via Postman

🐳 Docker

Aplicação containerizada com Docker

Banco de dados PostgreSQL em container

Orquestração via Docker Compose, permitindo subir todo o ambiente com um único comando

docker-compose up -d

📂 Controle de Versão

Versionamento de código com Git

Commits organizados e documentação técnica
