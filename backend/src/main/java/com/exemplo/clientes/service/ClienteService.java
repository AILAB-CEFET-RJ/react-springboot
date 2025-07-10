package com.exemplo.clientes.service;

import com.exemplo.clientes.dto.ClienteRequestDTO;
import com.exemplo.clientes.dto.ClienteResponseDTO;
import com.exemplo.clientes.exception.ClienteNaoEncontradoException;
import com.exemplo.clientes.model.Cliente;
import com.exemplo.clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        if (repository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Cliente cliente = new Cliente(dto.getNome(), dto.getCpf(), dto.getTelefone());
        Cliente salvo = repository.save(cliente);

        return new ClienteResponseDTO(salvo.getId(), salvo.getNome(), salvo.getTelefone());
    }

    public List<ClienteResponseDTO> listarTodos() {
        return repository.findAll().stream()
            .map(c -> new ClienteResponseDTO(c.getId(), c.getNome(), c.getTelefone()))
            .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
            .orElseThrow(() -> new ClienteNaoEncontradoException(id));

        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone());
    }
}