package com.store.baozi.service;

import com.store.baozi.dto.request.ClienteRequest;
import com.store.baozi.dto.response.ClienteResponse;
import com.store.baozi.model.ClienteEntity;
import com.store.baozi.repository.ClienteRepository;
import com.store.baozi.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final PedidoRepository pedidoRepository;

    public ClienteService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public ClienteResponse criar(ClienteRequest cliente) {
        var clienteCriado = clienteRepository.save(new ClienteEntity(cliente.nome(), cliente.clienteDesde()));

        return new ClienteResponse(clienteCriado);
    }

    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream().map(ClienteResponse::new).toList();
    }

    public ClienteResponse listarPorId(Long id) {
        return new ClienteResponse(buscar(id));
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um cliente com o id informado");
        }

        if (pedidoRepository.existsByClienteId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O cliente possui pedidos e não pode ser excluído");
        }

        clienteRepository.deleteById(id);
    }

    private ClienteEntity buscar(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado um cliente com o id informado"));
    }
}
