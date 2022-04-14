package br.com.matheus.dsclients.services;

import br.com.matheus.dsclients.entities.Client;
import br.com.matheus.dsclients.respository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return repository.findAll();
    }
}
