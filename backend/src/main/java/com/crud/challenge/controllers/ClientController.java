package com.crud.challenge.controllers;

import com.crud.challenge.entities.Client;
import com.crud.challenge.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public List<Client> findAll(){
       List<Client> listClient = repository.findAll();
       return listClient;
    }

    @GetMapping(value = "/{id}")
    public Client findById(@PathVariable Long id){
        Client client = repository.findById(id).get();
        return client;
    }

    @PostMapping
    public Client insert(@RequestBody Client client){
        Client clientSave = repository.save(client);
        return clientSave;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
