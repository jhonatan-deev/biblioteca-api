# 📚 Biblioteca API

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de livros em uma biblioteca. O projeto permite cadastrar, listar, buscar e controlar o status de empréstimo dos livros.

---

## 🚀 Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

---

## 🧠 Objetivo do projeto

Este projeto foi criado com fins de **aprendizado em desenvolvimento backend**, com foco em:

* Arquitetura em camadas (Controller, Service, Repository)
* Mapeamento objeto-relacional com JPA
* Criação de APIs REST
* Boas práticas de organização de código

---

## 🏗️ Estrutura do projeto

```
src/main/java/com/biblioteca
 ├── controller
 ├── service
 ├── repository
 ├── model
 └── dto
```

---

## 📘 Entidade principal

### Livro

Representa um livro no sistema.

**Campos:**

* `id` – identificador único
* `titulo` – título do livro
* `autor` – autor do livro
* `ano` – ano de publicação
* `emprestado` – indica se o livro está emprestado

---

## 🔍 Funcionalidades

* Cadastrar livros
* Listar todos os livros
* Buscar livros por título
* Buscar livros por autor
* Listar livros disponíveis
* Listar livros emprestados

---

## 🛠️ Endpoints principais

| Método | Endpoint               | Descrição                |
| ------ | ---------------------- | ------------------------ |
| GET    | /livros                | Lista todos os livros    |
| GET    | /livros/titulo?titulo= | Busca por título         |
| GET    | /livros/autor?autor=   | Busca por autor          |
| GET    | /livros/disponiveis    | Lista livros disponíveis |
| GET    | /livros/emprestados    | Lista livros emprestados |
| POST   | /livros                | Cadastra um novo livro   |

---

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL**.

As tabelas são criadas automaticamente pelo Hibernate com base nas entidades JPA.

---

## ▶️ Como executar o projeto

1. Clonar o repositório

```
git clone https://github.com/seu-usuario/biblioteca-api.git
```

2. Configurar o banco de dados no `application.properties`

3. Executar o projeto

```
mvn spring-boot:run
```

---

## 📌 Status do projeto

Projeto em desenvolvimento, utilizado para prática e aprendizado em backend com Spring Boot.

---

## 👨‍💻 Autor

Desenvolvido por **Jhonatan Pereira**
