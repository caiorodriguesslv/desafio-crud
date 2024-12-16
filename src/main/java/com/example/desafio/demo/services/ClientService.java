package com.example.desafio.demo.services;

import com.example.desafio.demo.entities.Client;
import com.example.desafio.demo.repositories.ClientRepository;
import com.example.desafio.demo.services.exceptions.EntityNotFoundException;
import com.example.desafio.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<Client> findAllPaged(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Client findById(Long id) {
       return repository.findById(id).orElseThrow(
               ()-> new ResourceNotFoundException("Recurso não encontrado!"));
    }

    @Transactional
    public Client insert(Client client){
        return repository.save(client);
    }

    public Client update(Client client, Long id){
        try {
            Client entity = repository.getReferenceById(id);
            entity.setName(client.getName());
            entity.setCpf(client.getCpf());
            entity.setIncome(client.getIncome());
            entity.setBirthDate(client.getBirthDate());
            entity.setChildren(client.getChildren());
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado");
        }
    }

    public void delete(Long id){
        if(repository.existsById(id)) {
            throw new EntityNotFoundException("Id não encontrado" + id);
        }
        repository.deleteById(id);
    }

}
