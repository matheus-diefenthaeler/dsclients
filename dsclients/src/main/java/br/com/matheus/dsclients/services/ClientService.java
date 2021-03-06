package br.com.matheus.dsclients.services;

import br.com.matheus.dsclients.dto.ClientDTO;
import br.com.matheus.dsclients.entities.Client;
import br.com.matheus.dsclients.respository.ClientRepository;
import br.com.matheus.dsclients.services.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
        var entity = obj.orElseThrow(() -> new ClientNotFoundException("Entity not found!"));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            var entity = repository.getById(id);
            entity.setName(dto.getName());
            entity.setCpf(dto.getCpf());
            entity.setBirthDate(dto.getBirthDate());
            entity.setChildren(dto.getChildren());
            entity.setIncome(dto.getIncome());
            return new ClientDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ClientNotFoundException("Id not found! " + id);
        }
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        var entity = new Client();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity.setIncome(dto.getIncome());
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ClientNotFoundException("Id not found!");
        }
    }
}
