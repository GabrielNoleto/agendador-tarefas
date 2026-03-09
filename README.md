# Tarefas Service

Microsserviço responsável pelo gerenciamento de tarefas em um sistema de gestão de tarefas baseado em arquitetura de microsserviços.

O serviço permite realizar cadastro, atualização, consulta, remoção de tarefas e atualização de status

A aplicação também integra com a API externa usuario para autenticação, para o usuário poder gerenciar as suas tarefas.
---

## Arquitetura

Este serviço faz parte de um sistema distribuído baseado em microsserviços.

Ele é responsável exclusivamente pelo domínio de **Tarefas**, garantindo separação de responsabilidades dentro da arquitetura.

---

## Tecnologias Utilizadas

### Backend

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA / Hibernate

### Integração

- OpenFeign
- API usuario

### Banco de Dados

- MongoDB

### DevOps

- Docker

### Versionamento

- Git
- GitFlow (branches: feature, develop e main)

---

## Segurança

A aplicação utiliza autenticação baseada em **JWT (JSON Web Token)** implementada com **Spring Security**.

Fluxo de autenticação:

1. Usuário realiza login
2. A API de usuario gera um token JWT através do login
3. O token deve ser enviado nas requisições protegidas da API de tarefas
4. O filtro de segurança valida o token antes de liberar acesso ao endpoint

---

## Integração com API Externa

O serviço realiza integração com a API **usuario** para validação do token que foi gerado pelo usuario

Essa comunicação é realizada utilizando **FeignClient**.

Fluxo:

1. Usuário informa o token
2. O serviço tarefas busca na API usuario se o token está válido e o email existe no banco de dados
3. Os dados retornados são utilizados para o gerenciamento das tarefas.

---

## Modelo de Dados

### Entidade TarefasEntity

Campos principais:

- id
- nomeTarefa
- descricao
- dataCriacao
- dataEvento
- emailUsuario
- dataAlteracao
- statusNotificacaoEnum

## Endpoints da API

### Endpoints Protegidos (Requerem JWT)

Post /tarefas   
Grava tarefas.

Get /tarefas/eventos  
Busca lista de tarefas por período.

Get /tarefas  
Busca tarefas por email.

Delete /tarefas{id}   
Deleta tarefas por id.

Patch /tarefas  
Altera status de notificação.

Put /tarefas  
Atualização de tarefas.

---

## Tratamento de Exceções

A aplicação utiliza um **tratamento global de exceções**, garantindo respostas padronizadas para erros da API.

Isso melhora:

- legibilidade das respostas
- padronização de erros
- experiência do cliente da API


## Como Executar o Projeto

```bash

https://github.com/GabrielNoleto/agendador-tarefas.git

cd agendador-tarefas

docker build -t agendadortarefas .

docker run -p 8082:8082 --name agendador agendadortarefas

http://localhost:8082
