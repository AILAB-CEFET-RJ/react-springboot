# react-springboot

Aplicação full stack com cadastro de clientes, utilizando:

- **Spring Boot (Java)** no backend
- **React + Vite** no frontend

O sistema permite cadastrar e listar clientes com os campos: nome, CPF e telefone.

---

## Tecnologias utilizadas

### Backend
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (desenvolvimento)
- Maven

### Frontend
- React 18
- Vite
- Axios

---

## Como rodar o projeto localmente

### 0. Pré-requisitos

Certifique-se de ter:

- **Java 17+** instalado e disponível no terminal (`java -version`)
- **Node.js 18+** com `npm` (`node -v` e `npm -v`)
- **Maven (opcional)** — o projeto usa Maven Wrapper (`./mvnw`), então não é obrigatório

---

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/react-springboot.git
cd react-springboot
```

### 2. Executar o Backend (Spring Boot)

```bash
cd backend

# (1) Garanta que o Maven Wrapper tenha permissão de execução
chmod +x mvnw

# (2) Remova arquivos obsoletos se necessário
./mvnw clean

# (3) Execute o servidor
./mvnw spring-boot:run
```

> A API estará disponível em `http://localhost:8080`

### 3. Executar o Frontend (React)

```bash
cd ../frontend
npm install
npm run dev
```

> A interface estará disponível em `http://localhost:5173` (ou porta informada pelo Vite)

Para testar a aplicação, acesse o navegador em http://localhost:5173.

- Preencha o formulário com nome, CPF e telefone

- Clique em "Salvar"

- O cliente deve aparecer na lista abaixo

- Os dados são salvos no banco H2 em memória

---

## API - Endpoints principais

| Método | Endpoint             | Descrição                |
|--------|----------------------|--------------------------|
| GET    | `/api/v1/clientes`   | Lista todos os clientes  |
| POST   | `/api/v1/clientes`   | Cadastra um novo cliente |

### Exemplo de `POST /api/v1/clientes`
```json
{
  "nome": "Maria Oliveira",
  "cpf": "12345678901",
  "telefone": "(21) 99999-0000"
}
```

---

## Funcionalidades implementadas

- [x] Cadastro de clientes com validação de CPF
- [x] Listagem de clientes
- [x] Validações no frontend
- [x] Tratamento de erros com feedback ao usuário
- [x] Banco em memória (H2)

---

## Estrutura de camadas do projeto

O projeto `react-springboot` segue uma arquitetura em camadas tradicionais, onde cada camada tem uma responsabilidade bem definida e as dependências fluem de forma unidirecional, de cima para baixo. Na camada superior, encontra-se o frontend implementado em React com Vite, responsável por interagir com o usuário e consumir os serviços do backend via chamadas HTTP. A primeira camada do backend é a de apresentação, composta por controladores Spring (`@Controller`), que recebem as requisições, realizam o mapeamento de dados e encaminham para a camada de serviço. A camada de serviço (`@Service`) contém a lógica de negócio da aplicação, como validações e orquestração de operações, e delega o acesso a dados à camada de repositório (`@Repository`), que utiliza Spring Data JPA para persistir e recuperar entidades do banco. Por fim, na base da arquitetura está a camada de infraestrutura, que engloba recursos como o banco de dados H2 em memória, arquivos de configuração (`application.yml`), o mecanismo de auto-configuração do Spring Boot e a classe `ClientesApplication.java`, responsável por iniciar o sistema. Essa estrutura promove organização, separação de responsabilidades e facilita a evolução e manutenção da aplicação.


```
┌──────────────────────────────────────────────┐
│                  Frontend                    │
│             (React + Vite)                   │
│                                              │
│ - Interface com o usuário                    │
│ - Formulário de cadastro e lista de clientes │
│ - Requisições HTTP para o backend            │
└──────────────────────────────────────────────┘
                    │
                    ▼
┌──────────────────────────────────────────────┐
│              Camada de Apresentação          │
│              (Spring @Controller)            │
│                                              │
│ - Recebe requisições HTTP                    │
│ - Converte para DTOs                         │
│ - Chama a camada de serviço                  │
└──────────────────────────────────────────────┘
                    │
                    ▼
┌──────────────────────────────────────────────┐
│               Camada de Serviço              │
│              (Spring @Service)               │
│                                              │
│ - Contém regras de negócio                   │
│ - Validações, orquestrações                 │
│ - Chama repositórios                         │
└──────────────────────────────────────────────┘
                    │
                    ▼
┌──────────────────────────────────────────────┐
│             Camada de Repositório            │
│           (Spring Data @Repository)          │
│                                              │
│ - Acesso ao banco de dados (JPA)             │
│ - CRUD sobre entidade Cliente                │
└──────────────────────────────────────────────┘
                    │
                    ▼
┌──────────────────────────────────────────────┐
│            Camada de Infraestrutura          │
│                                              │
│ - H2 Database (em memória)                   │
│ - Spring Boot auto-configuration             │
│ - Classe `ClientesApplication.java`          │
│ - Arquivos `application.yml`, etc.           │
└──────────────────────────────────────────────┘
```

## Estrutura de diretórios do projeto

```
react-springboot/
├── backend/              # Backend Java (Spring Boot)
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/com/exemplo/clientes/
│           │   ├── ClientesApplication.java
│           │   ├── controller/
│           │   ├── dto/
│           │   ├── model/
│           │   ├── repository/
│           │   └── service/
│           └── resources/
│               ├── application.yml
│               └── data.sql
├── frontend/             # Frontend React (Vite)
│   ├── index.html
│   ├── package.json
│   ├── vite.config.js
│   └── src/
│       ├── App.jsx
│       └── main.jsx
├── .gitignore
├── README.md
└── CONTRIBUTING.md
```

---

## Testes

- Backend: testes de integração com Spring Boot Test (em desenvolvimento)
- Frontend: suporte a testes com Jest e Testing Library (a configurar)

---

## Próximos passos

- [ ] Adicionar autenticação JWT
- [ ] Deploy via Docker
- [ ] Integração com PostgreSQL em produção
- [ ] CI/CD com GitHub Actions

---

## Licença

Este projeto está licenciado sob os termos da licença MIT.