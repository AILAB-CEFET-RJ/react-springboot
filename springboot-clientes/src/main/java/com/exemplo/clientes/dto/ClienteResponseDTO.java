package com.exemplo.clientes.dto;

public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String telefone;

    public ClienteResponseDTO(Long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
}