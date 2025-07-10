# Tratamento de Erros no Backend (Spring Boot)

Este projeto utiliza uma estratégia centralizada de tratamento de erros REST, para garantir respostas claras e consistentes à interface web (frontend).

---

## 1. Exceções personalizadas

Criamos classes específicas para representar erros de negócio e domínio.

**Exemplo:**
```java
public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(Long id) {
        super("Cliente com ID " + id + " não encontrado.");
    }
}
```

---

## 2. Estrutura de erro: `ApiError`

Classe que define o padrão da resposta de erro:

```java
public class ApiError {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public ApiError(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
```

---

## 3. Manipulador global: `GlobalExceptionHandler`

Intercepta exceções comuns e converte para uma resposta JSON padronizada:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleNotFound(ClienteNaoEncontradoException ex, HttpServletRequest request) {
        ApiError error = new ApiError(404, "Not Found", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex, HttpServletRequest request) {
        ApiError error = new ApiError(400, "Bad Request", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
        ApiError error = new ApiError(500, "Internal Server Error", "Ocorreu um erro inesperado.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

---

## 4. Lançando exceções no serviço

```java
public ClienteResponseDTO buscarPorId(Long id) {
    Cliente cliente = repository.findById(id)
        .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    return new ClienteResponseDTO(cliente);
}
```

---

## 5. Exemplo de resposta 404

```json
{
  "timestamp": "2025-07-10T13:00:47.812",
  "status": 404,
  "error": "Not Found",
  "message": "Cliente com ID 42 não encontrado.",
  "path": "/api/v1/clientes/42"
}
```

---

## Boas práticas

- Use códigos HTTP corretos: `400`, `404`, `422`, `500`
- Nunca exponha `stack traces` ao usuário final
- Logue exceções com contexto (ver seção de logging)
- Garanta uma estrutura uniforme nas mensagens de erro
