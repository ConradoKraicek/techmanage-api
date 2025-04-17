# 🚀 TechManage API

API RESTful para gerenciamento de usuários desenvolvida com Spring Boot.

## 📦 Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL 12+ 
- Git (para clonar o repositório)

## 🛠️ Configuração da Aplicação

### Crie ou edite o arquivo src/main/resources/application.properties com:
#### Configurações do Banco de Dados
- spring.datasource.url=jdbc:postgresql://localhost:5432/techmanage
- spring.datasource.username=postgres
- spring.datasource.password=senha123

#### Configurações do JPA/Hibernate
- spring.jpa.hibernate.ddl-auto=validate
- spring.jpa.show-sql=true
- spring.jpa.properties.hibernate.format_sql=true

#### Configurações do Flyway
- spring.flyway.enabled=true
- spring.flyway.locations=classpath:db/migration
- spring.flyway.baseline-on-migrate=true

## 🐘 Banco de Dados

#### Instalação Manual
1. Instale o PostgreSQL em sua máquina

2. Crie um banco de dados chamado techmanage

3. Crie um usuário com permissões ou use o usuário padrão postgres

## ▶️ Executando a Aplicação

1. Clone o repositório:
-  git clone https://github.com/ConradoKraicek/techmanage-api.git
-  cd techmanage-api
2. Compile e execute a aplicação:
-  mvn spring-boot:run
-  A API estará disponível em: http://localhost:8080

## 🧪 Executando os Testes

1. Todos os testes
-  mvn test

## 📚 Documentação da API

🔗 Base URL: http://localhost:8080/api/users

## 📌 Endpoints Principais
#### 1. Criar Usuário
-   Método: POST /api/users
-   Content-Type: application/json
-   Exemplo de Request:

- {
    "fullName": "João Silva",
    "email": "joao.silva@example.com",
    "phone": "+55 11 99999-9999",
    "birthDate": "1990-01-01",
    "userType": "ADMIN"
    }

- Resposta de Sucesso (201 Created):
- {
  "id": 1,
  "fullName": "João Silva",
  "email": "joao.silva@example.com",
  "phone": "+55 11 99999-9999",
  "birthDate": "1990-01-01",
  "userType": "ADMIN",
  "createdAt": "2023-08-15T10:00:00Z"
  }
#### 2. Listar Todos os Usuários
-   Método: GET /api/users
-   Resposta de Sucesso (200 OK):
- [
{
"id": 1,
"fullName": "João Silva",
"email": "joao.silva@example.com",
"userType": "ADMIN"
}
]
#### 3. Buscar Usuário por ID
-   Método: GET /api/users/{id}
-   Resposta de Sucesso (200 OK):
- {
"id": 1,
"fullName": "João Silva",
"email": "joao.silva@example.com",
"phone": "+55 11 99999-9999",
"birthDate": "1990-01-01",
"userType": "ADMIN",
"createdAt": "2023-08-15T10:00:00Z"
}
#### 4. Atualizar Usuário
-   Método: PUT /api/users/{id}
-   Content-Type: application/json
-   Exemplo de Request:
- {
"fullName": "João Silva Santos",
"email": "joao.silva@example.com",
"phone": "+55 11 98888-8888",
"birthDate": "1990-01-01",
"userType": "ADMIN"
}
-   Resposta de Sucesso (200 OK):
- {
"id": 1,
"fullName": "João Silva Santos",
"email": "joao.silva@example.com",
"phone": "+55 11 98888-8888",
"birthDate": "1990-01-01",
"userType": "ADMIN",
"createdAt": "2023-08-15T10:00:00Z"
}
#### 5. Excluir Usuário
-   Método: DELETE /api/users/{id}
-   Resposta de Sucesso (204 No Content)


## 🧰 Tecnologias Utilizadas

Backend
- ✅ Java 17

- ✅ Spring Boot 3.4

- ✅ Spring Data JPA

- ✅ PostgreSQL

- ✅ Flyway

Testes
- 🧪 JUnit 5

- 🧪 Mockito



## 🗂 Estrutura do Projeto

```plaintext
techmanage-api/
├── src/
│   ├── main/
│   │   ├── java/com/techmanage/
│   │   │   ├── config/          # Configurações da aplicação
│   │   │   ├── controller/      # Controladores REST
│   │   │   ├── dto/             # Objetos de transferência de dados
│   │   │   ├── exception/       # Tratamento de erros
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositórios Spring Data
│   │   │   ├── service/         # Lógica de negócio
│   │   │   └── TechManageApplication.java  # Classe principal
│   │   └── resources/
│   │       ├── application.properties      # Configurações
│   │       └── db/migration/               # Scripts SQL do Flyway
│   │           ├── V1__create_tables.sql
│   │           └── V2__insert_initial_data.sql
│   └── test/                               # Testes unitários e de integração
├── pom.xml                                 # Configuração Maven
├── README.md                               # Este arquivo
└── LICENSE                                 # Licença MIT





