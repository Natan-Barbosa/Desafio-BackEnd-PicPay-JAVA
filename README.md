# Desafio Backend PicPay

Uma aplicação Spring Boot para gerenciamento de transações e carteiras digitais, desenvolvida como parte do desafio técnico do PicPay.

## 📋 Sobre o Projeto

Esta aplicação implementa um sistema de transações financeiras que permite:
- Criação e gerenciamento de carteiras digitais
- Realização de transações entre usuários
- Autorização de transações via serviço externo
- Notificações de transações realizadas
- Controle de saldo e validações de negócio

## 🚀 Tecnologias Utilizadas

- **Java** - Linguagem principal
- **Spring Boot** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Cloud OpenFeign** - Cliente HTTP para APIs externas
- **Spring Validation** - Validação de dados
- **MySQL** - Banco de dados
- **Docker** - Containerização do banco de dados
- **Lombok** - Redução de código boilerplate
- **JUnit** - Testes unitários
- **Mockito** - Mocks para testes

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/barbosa/desafiobackendpicpay/
│   │   ├── Client/          # Clientes para APIs externas
│   │   │   ├── Authorization # Cliente para serviço de autorização
│   │   │   └── Notification  # Cliente para serviço de notificação
│   │   ├── Controller/      # Controladores REST
│   │   ├── Entities/        # Entidades JPA
│   │   ├── Exceptions/      # Exceções customizadas e handlers
│   │   ├── Repository/      # Interfaces de repositório JPA
│   │   ├── Services/        # Camada de serviços
│   │   │   ├── AuthorizationService    # Serviço de autorização
│   │   │   ├── NotificationService     # Serviço de notificação
│   │   │   ├── TransactionService      # Serviço principal de transações
│   │   │   └── WalletService          # Serviço de carteiras
│   │   └── Utils/           # Utilitários
│   └── resources/
│       ├── application.properties
│       ├── static/
│       └── templates/
├── test/                    # Testes unitários
└── docker/                  # Configuração Docker
```

## 🔧 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Docker e Docker Compose
- MySQL 8.0+ (ou usar via Docker)

## 🏃‍♂️ Como Executar

### 1. Clone o repositório
```bash
git clone https://github.com/KingBarbosa/Desafio-BackEnd-PicPay-JAVA
cd desafiobackendpicpay
```

### 2. Configure o banco de dados
```bash
# Inicie o MySQL via Docker
docker-compose up -d
```

### 3. Configure as propriedades da aplicação
Edite o arquivo `src/main/resources/application.properties` com suas configurações caso deseje:

```properties
# Configurações do banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/picpay_db
spring.datasource.username=admin
spring.datasource.password=admin

# Configurações JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Execute a aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## 📚 Endpoints da API

### Carteiras
- `POST /wallet/create` - Criar nova carteira
- `GET /wallet/{id}` - Buscar carteira por ID
- `PUT /wallet/increase` - Depositar valor na carteira

### Transações
- `POST /transfer` - Realizar transação


## 🧪 Executando os Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com relatório de cobertura
mvn test jacoco:report
```

## 🐳 Docker

O projeto inclui configuração Docker para o banco de dados:

```bash
# Iniciar serviços
docker-compose up -d

# Parar serviços
docker-compose down
```

## 🏗️ Arquitetura

A aplicação segue uma arquitetura em camadas:

1. **Controller Layer** - Recebe requisições HTTP e delega para os serviços
2. **Service Layer** - Contém a lógica de negócio
3. **Repository Layer** - Abstração para acesso aos dados
4. **Entity Layer** - Representação das entidades do domínio
5. **Client Layer** - Comunicação com APIs externas

### Fluxo de Transação

1. Controller recebe requisição de transação
2. TransactionService valida os dados
3. AuthorizationService consulta serviço externo de autorização
4. WalletService atualiza saldos das carteiras
5. TransactionService persiste a transação
6. NotificationService envia notificação

## 🔒 Validações e Regras de Negócio

- Validação de saldo suficiente antes da transação
- Autorização obrigatória via serviço externo
- Prevenção de transações para a mesma carteira
- Validação de tipos de usuário (comum/lojista)
- Tratamento de erros e rollback em caso de falha

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

**Natan Barbosa**
- GitHub: [https://github.com/KingBarbosa](https://github.com/seu-usuario)

## 🔗 Links Úteis

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Docker Documentation](https://docs.docker.com/)

---

⭐ Se este projeto foi útil para você, considere dar uma estrela!