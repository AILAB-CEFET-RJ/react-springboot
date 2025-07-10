# Estratégia de Logging no Backend (Spring Boot)

O uso correto de logs contribui diretamente para a observabilidade e manutenção da aplicação. 

Este projeto adota boas práticas de logging para rastrear operações relevantes, identificar falhas e fornecer visibilidade durante o desenvolvimento e produção.

---

## Objetivos

- Facilitar a depuração de erros
- Fornecer rastreabilidade para operações de negócio
- Evitar o uso de `System.out.println`
- Garantir um padrão consistente e seguro

---

## Boas práticas

Sempre prefira um logging estruturado, com contexto e em nível apropriado.

| Prática | Descrição |
|--------|-----------|
| **Use SLF4J** | Utilize `org.slf4j.Logger` com `LoggerFactory.getLogger(...)` |
| **Evite logs excessivos** | Especialmente em produção |
| **Nunca logue dados sensíveis** | Como senhas, CPF, tokens |
| **Logue erros com exceções** | Use `logger.error("mensagem", e)` |
| **Níveis adequados** | `INFO`, `DEBUG`, `WARN`, `ERROR` |

---

## Níveis de log

- `TRACE`: Detalhes extremos (raramente usado)
- `DEBUG`: Informações de desenvolvimento e diagnóstico
- `INFO`: Fluxo normal da aplicação
- `WARN`: Situações inesperadas, mas que não quebram a execução
- `ERROR`: Falhas ou exceções que exigem atenção

---

## Exemplo no `ClienteService`

```java
private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
    logger.info("Tentando cadastrar cliente com CPF: {}", dto.getCpf());

    if (repository.existsByCpf(dto.getCpf())) {
        logger.warn("CPF já cadastrado: {}", dto.getCpf());
        throw new IllegalArgumentException("CPF já cadastrado");
    }

    Cliente cliente = new Cliente(dto.getNome(), dto.getCpf(), dto.getTelefone());
    Cliente salvo = repository.save(cliente);

    logger.info("Cliente cadastrado com ID: {}", salvo.getId());
    return new ClienteResponseDTO(salvo.getId(), salvo.getNome(), salvo.getTelefone());
}
```

---

## Configuração no `application.yml`

```yaml
logging:
  level:
    root: info
    com.exemplo.clientes: debug
  pattern:
    console: "%d{HH:mm:ss} %-5level [%logger{36}] - %msg%n"
```

---

## 📋 Onde aplicar logs

- Nos controllers (`@RestController`) para rastrear requisições
- Nos serviços (`@Service`) para operações de negócio
- Em tratamento de exceções (`@RestControllerAdvice`)
- No início e fim de processos importantes
- Em falhas e comportamentos inesperados

---

## Evite

- Logar informações sensíveis (ex: `senha: {}`, `cpf: {}`)
- Usar `System.out.println` ou `printStackTrace()`
- Gerar logs duplicados ou muito verborrágicos