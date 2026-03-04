# Estoque - Sistema de Controle de Inventário

Um sistema robusto e escalável para gerenciamento de estoque, desenvolvido em **Java 17** com **Spring Boot 3.2.3**, fornecendo uma API REST completa para controlar produtos, fornecedores, compras e entradas de estoque.

---

## Características Principais

- **Gestão de Produtos** - CRUD completo com preços e quantidades
- **Controle de Fornecedores** - Manutenção de dados de fornecedores
- **Registro de Compras** - Venda de produtos com atualização automática de estoque
- **Entradas de Estoque** - Reposição do inventário com sincronização de quantidade
- **Sincronização Automática** - Quantidade de produtos sempre atualizada
- **API REST** - Endpoints bem definidos com validações
- **Documentação Swagger** - Interface interativa para testar endpoints
- **H2 Database** - Banco em memória para fácil prototipagem

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.2.3 | Framework web |
| Spring Data JPA | - | Persistência de dados |
| H2 Database | - | Banco de dados em memória |
| Lombok | - | Redução de boilerplate |
| SpringDoc OpenAPI | 2.5.0 | Documentação Swagger |
| Maven | 3.6+ | Gerenciador de dependências |

---

## Modelos de Dados

### Produto
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | Long | Identificador único (auto-gerado) |
| `name` | String | Nome do produto |
| `quantity` | Integer | Quantidade em estoque |
| `classe` | String | Classificação/categoria |
| `price` | Integer | Preço unitário |
| `provider` | Fornecedor | Fornecedor associado |

### Fornecedor
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | Long | Identificador único |
| `name` | String | Nome do fornecedor |
| `address` | String | Endereço |
| `tel` | String | Telefone |
| `cnpj` | String | CNPJ |

### Compra (Saída de Estoque)
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | Long | Identificador único |
| `nomeCliente` | String | Nome do cliente |
| `produto` | Produto | Produto referenciado |
| `quantidade` | Integer | Qt. comprada (desconta do estoque) |
| `valorTotal` | Double | Valor total |

### EntradaEstoque (Reposição de Estoque)
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | Long | Identificador único |
| `dataEntrada` | LocalDateTime | Data/hora da entrada |
| `quantidade` | Integer | Qt. recebida (adiciona ao estoque) |
| `produto` | Produto | Produto referenciado |
| `fornecedor` | Fornecedor | Fornecedor da entrada |
| `descricao` | String | Descrição/motivo da entrada |

---

## Como Executar

### Pré-requisitos
- **Java 17+** instalado
- **Maven 3.6+** instalado

### Passos

1. **Clone o projeto**
   ```bash
   git clone https://github.com/mateus124/EstoqueAPI-Java
   cd EstoqueAPI-Java
   ```

2. **Compile e baixe dependências**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```
   Ou use o wrapper:
   ```bash
   ./mvnw spring-boot:run        # Linux/Mac
   mvnw.cmd spring-boot:run      # Windows
   ```

4. **Acesse**
   - API: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - Console H2: `http://localhost:8080/h2-console`

---

## API REST - Endpoints

### Produtos (`/api/produtos`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/produtos` | Listar todos |
| `GET` | `/api/produtos/{id}` | Obter por ID |
| `POST` | `/api/produtos` | Criar novo |
| `PUT` | `/api/produtos/{id}` | Atualizar |
| `DELETE` | `/api/produtos/{id}` | Deletar |

### Fornecedores (`/api/fornecedores`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/fornecedores` | Listar todos |
| `GET` | `/api/fornecedores/{id}` | Obter por ID |
| `POST` | `/api/fornecedores` | Criar novo |
| `PUT` | `/api/fornecedores/{id}` | Atualizar |
| `DELETE` | `/api/fornecedores/{id}` | Deletar |

### Compras - SAÍDA de Estoque (`/api/compras`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/compras` | Listar todas |
| `GET` | `/api/compras/{id}` | Obter por ID |
| `POST` | `/api/compras` | **Criar (desconta estoque)** |
| `PUT` | `/api/compras/{id}` | Atualizar (ajusta estoque) |
| `DELETE` | `/api/compras/{id}` | Deletar (revert estoque) |

### Entradas de Estoque - ENTRADA (`/api/entrada-estoque`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/entrada-estoque` | Listar todas |
| `GET` | `/api/entrada-estoque/{id}` | Obter por ID |
| `GET` | `/api/entrada-estoque/produto/{produtoId}` | Filtrar por produto |
| `GET` | `/api/entrada-estoque/fornecedor/{fornecedorId}` | Filtrar por fornecedor |
| `POST` | `/api/entrada-estoque` | **Criar (adiciona estoque)** |
| `DELETE` | `/api/entrada-estoque/{id}` | Deletar (revert estoque) |

---

## Exemplos de Requisições

### Criar Fornecedor
```json
POST /api/fornecedores
Content-Type: application/json

{
  "name": "Distribuidora ABC",
  "address": "Rua Principal, 123",
  "tel": "(11) 1234-5678",
  "cnpj": "12.345.678/0001-99"
}
```

### Criar Produto
```json
POST /api/produtos
Content-Type: application/json

{
  "name": "Parafuso M8",
  "quantity": 100,
  "classe": "Ferragens",
  "price": 150,
  "providerId": 1
}
```

### Registrar Compra (Desconta Estoque)
```json
POST /api/compras
Content-Type: application/json

{
  "nomeCliente": "João Silva",
  "produtoId": 1,
  "quantidade": 20,
  "valorTotal": 3000.00
}
```

Resultado: Produto passa de 100 → 80 unidades

### Registrar Entrada de Estoque (Adiciona)
```json
POST /api/entrada-estoque
Content-Type: application/json

{
  "dataEntrada": "2026-03-04T10:30:00",
  "quantidade": 50,
  "produtoId": 1,
  "fornecedorId": 1,
  "descricao": "Reposição de estoque"
}
```

Resultado: Produto passa de 80 → 130 unidades

---

## Sincronização de Quantidade

O sistema **sincroniza automaticamente** a quantidade de produtos:

### Operação: CREATE Compra
```
Produto.quantity = Produto.quantity - quantidade_comprada
```

### Operação: UPDATE Compra (mudança de produto)
```
Produto_Antigo.quantity = Produto_Antigo.quantity + quantidade_antiga
Produto_Novo.quantity = Produto_Novo.quantity - quantidade_nova
```

### Operação: DELETE Compra
```
Produto.quantity = Produto.quantity + quantidade_comprada  (revert)
```

### Operação: CREATE EntradaEstoque
```
Produto.quantity = Produto.quantity + quantidade_entrada
```

### Operação: DELETE EntradaEstoque
```
Produto.quantity = Produto.quantity - quantidade_entrada  (revert)
```

---