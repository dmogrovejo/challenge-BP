package com.challengebp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challengebp.model.Client;
import com.challengebp.repository.IClientRepository;

/**
 * @author David Mogrovejo
 */
@Service
public class ClientServiceImplementation implements IClientService{

	@Autowired
	private IClientRepository repositoryClient;
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) repositoryClient.findAll();
	}

	@Override
	@Transactional
	public Client save(Client client) {		
		return repositoryClient.save(client);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repositoryClient.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public  Optional<Client> findById(Long id) {
		return Optional.ofNullable(repositoryClient.findById(id).orElse(null));
	}
	
	public Optional<Client> findByName(String name){
		return Optional.ofNullable(repositoryClient.findByName(name).orElse(null));
	}

}
