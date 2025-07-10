# Guia de Contribuição

Este projeto adota o modelo de branches **GitFlow** e envolve duas subequipes principais: **Frontend** (React) e **Backend** (Spring Boot). Para garantir consistência e qualidade no desenvolvimento, siga as orientações abaixo.

---

## 🧭 Fluxo de GitFlow adotado

- `main`: branch de produção (estável)
- `develop`: branch de integração contínua (última versão em desenvolvimento)
- `feature/<escopo>-<nome>`: novas funcionalidades (ex: `feature/frontend-login`)
- `release/<versão>`: preparação para entrega (ex: `release/1.0.0`)
- `hotfix/<nome>`: correções urgentes em produção

> O desenvolvimento diário ocorre em `feature/*`, que será mergeado em `develop` via Pull Request. Nada deve ser commitado diretamente em `main` ou `develop`.

---

## 📂 Estrutura do repositório

```bash
react-springboot/
├── frontend/   # Backend (Java + Spring Boot)
├── backend/    # Frontend (React + Vite)
└── docs/       # Documentação (opcional)
```

---

## Como contribuir

### 1. Fork e clone o repositório

```bash
git clone https://github.com/<sua-conta>/react-springboot.git
cd react-springboot
```

### 2. Crie uma nova branch de funcionalidade

```bash
git checkout develop
git checkout -b feature/<escopo>-<nome>
```

Exemplos:
- `feature/frontend-formulario-cliente`
- `feature/backend-validacao-cpf`

### 3. Commit com mensagens claras

Padrão sugerido para commits:

```bash
[escopo]: descrição clara da mudança
```

Exemplos:
- `frontend: adiciona validação de CPF no formulário`
- `backend: refatora DTO de cliente`

### 4. Atualize a documentação se necessário

- Atualize o `README.md`, `CONTRIBUTING.md` ou arquivos auxiliares conforme mudanças

### 5. Envie um Pull Request para `develop`

- Crie um PR claro, com título descritivo e breve descrição da mudança
- Indique se há impacto em outras partes (ex: dependência entre frontend/backend)

---

## Revisão de código

- Todo PR será revisado por pelo menos um membro da equipe
- Código deve seguir as convenções de estilo e arquitetura do projeto
- Pull Requests com erros de build, testes ou conflitos não serão aceitos

---

## Testes

- Backend: execute os testes com `./mvnw test`
- Frontend: execute `npm run lint` e, se aplicável, `npm test`

---

## Boas práticas

- Faça commits pequenos e frequentes
- Use nomes de branch descritivos
- Evite duplicação de código
- Documente decisões relevantes diretamente no PR

---

## Labels e escopos sugeridos

- `frontend`
- `backend`
- `infra`
- `bug`
- `enhancement`
- `docs`
