package com.crud.challenge.services;

import com.crud.challenge.dto.ClientDTO;
import com.crud.challenge.entities.Client;
import com.crud.challenge.repositories.ClientRepository;
import com.crud.challenge.services.exceptions.EntityNotFoundException;
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
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
       Optional<Client> client = repository.findById(id);
       Client entity = client.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
       return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO client) {
        Client entity = new Client();
        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setIncome(client.getIncome());
        entity.setBirthDate(client.getBirthDate());
        entity.setChildren(client.getChildren());
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO client) {
        Client entity = repository.getById(id);
        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setIncome(client.getIncome());
        entity.setBirthDate(client.getBirthDate());
        entity.setChildren(client.getChildren());
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
