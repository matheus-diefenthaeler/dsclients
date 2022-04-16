package br.com.matheus.dsclients.services;

import br.com.matheus.dsclients.dto.ClientDTO;
import br.com.matheus.dsclients.entities.Client;
import br.com.matheus.dsclients.respository.ClientRepository;
import br.com.matheus.dsclients.services.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {

        Page<Client> list = repository.findAll(pageRequest);
        return list.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        var entity = obj.orElseThrow(()-> new ClientNotFoundException("Entity not found!"));
        return new ClientDTO(entity);
    }
}
