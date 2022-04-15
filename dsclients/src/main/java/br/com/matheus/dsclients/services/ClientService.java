package br.com.matheus.dsclients.services;

import br.com.matheus.dsclients.dto.ClientDTO;
import br.com.matheus.dsclients.entities.Client;
import br.com.matheus.dsclients.respository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {

        List<Client> list = repository.findAll();
        return list.stream().map(x -> new ClientDTO()).collect(Collectors.toList());
    }
}