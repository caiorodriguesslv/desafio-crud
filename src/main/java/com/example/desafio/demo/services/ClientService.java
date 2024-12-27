package com.example.desafio.demo.services;

import com.example.desafio.demo.dto.ClientDTO;
import com.example.desafio.demo.entities.Client;
import com.example.desafio.demo.repositories.ClientRepository;
import com.example.desafio.demo.services.exceptions.EntityNotFoundException;
import com.example.desafio.demo.services.exceptions.ResourceNotFoundException;
import com.example.desafio.demo.services.exceptions.DataIntegrityViolationException;
import jakarta.validation.Valid;
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
    public Page<ClientDTO> findAllPaged(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(@Valid ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, @Valid ClientDTO dto) {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Id não encontrado " + id);
        }
        repository.deleteById(id);
    }


    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}