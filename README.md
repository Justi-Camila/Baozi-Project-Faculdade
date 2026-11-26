# Baozi Store API

API REST para controlar clientes, produtos e pedidos da **Baozi Store**, uma pequena loja que vende pão chinês. Trabalho da disciplina de Desenvolvimento Web Back-End (Uninter).

## Tecnologias

- Java 21
- Spring Boot 4.1.2 (Spring Web MVC)
- Spring Data JPA (Hibernate)
- Bean Validation
- Banco de dados H2 em memória
- Maven (wrapper incluído)
- Testes manuais com Postman

## Como rodar

Pré-requisito: JDK 21 instalado.

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A API sobe em `http://localhost:8080`.

O banco H2 fica em memória, então **os dados são apagados sempre que a aplicação é reiniciada**. Para consultar as tabelas, abra o console em `http://localhost:8080/h2-console` com:

| Campo | Valor |
| --- | --- |
| JDBC URL | `jdbc:h2:mem:testdb` |
| User | `sa` |
| Password | (vazio) |

## Estrutura do projeto

Padrão MVC do Spring, organizado em camadas:

```
src/main/java/com/store/baozi
├── controller   # recebe as requisições HTTP (/cliente, /produto, /pedido)
├── service      # regras de negócio e validações
├── repository   # acesso ao banco (Spring Data JPA)
├── model        # entidades JPA
└── dto          # objetos de entrada e saída da API
```

## Entidades

| Entidade | Campos principais |
| --- | --- |
| Cliente | `id`, `nome`, `clienteDesde` (preenchida automaticamente) |
| Produto | `id`, `nome`, `preco`, `estoque` |
| Pedido | `id`, cliente, produto, `quantidade` |

Um cliente faz muitos pedidos e um produto é vendido em muitos pedidos.

## Endpoints

| Método | Rota | Descrição | Sucesso |
| --- | --- | --- | --- |
| POST | `/cliente` | Cadastra um cliente | 201 |
| GET | `/cliente` | Lista todos os clientes | 200 |
| GET | `/cliente/{id}` | Consulta um cliente pelo id | 200 |
| DELETE | `/cliente/{id}` | Apaga um cliente | 204 |
| POST | `/produto` | Cadastra um produto | 201 |
| GET | `/produto` | Lista todos os produtos | 200 |
| GET | `/produto/{id}` | Consulta um produto pelo id | 200 |
| DELETE | `/produto/{id}` | Apaga um produto | 204 |
| POST | `/pedido` | Registra um pedido | 201 |
| GET | `/pedido` | Lista todos os pedidos | 200 |
| GET | `/pedido/{id}` | Consulta um pedido pelo id | 200 |
| DELETE | `/pedido/{id}` | Apaga um pedido | 204 |

## Exemplos de requisição

Todas as requisições e respostas usam JSON (`Content-Type: application/json`). Os valores abaixo são de exemplo.

**POST `/cliente`**

```json
{ "nome": "CamilaJusti5288904" }
```

**POST `/produto`**

```json
{ "nome": "bao", "preco": 5.00, "estoque": true }
```

**POST `/pedido`**

```json
{ "clienteId": 1, "produtoId": 1, "quantidade": 10 }
```

Resposta do pedido (`201 Created`):

```json
{
  "id": 1,
  "nomeCliente": "CamilaJusti5288904",
  "nomeProduto": "bao",
  "preco": 5.00,
  "quantidade": 10
}
```

## Regras de negócio

- Todo pedido tem um cliente, um produto e uma quantidade maior que zero.
- Só é possível registrar pedido de um produto com `estoque` igual a `true`.
- Cliente ou produto que já tem pedidos não pode ser apagado: apague os pedidos antes.
- Os campos obrigatórios são validados, e o preço do produto deve ser maior que zero.

## Códigos de erro

| Status | Quando acontece |
| --- | --- |
| 400 | Dados inválidos (campo obrigatório ausente, quantidade ou preço inválidos) ou produto sem estoque |
| 404 | Cliente, produto ou pedido não encontrado |
| 409 | Tentativa de apagar cliente ou produto que possui pedidos |
