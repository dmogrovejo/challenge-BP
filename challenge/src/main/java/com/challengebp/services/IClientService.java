package com.challengebp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challengebp.model.Client;

/**
 * @author David Mogrovejo
 */
@Service
public interface IClientService {
	
	public List<Client> findAll();
	
	public Client save(Client client);
	
	public void delete(Long id);
	
	public Optional<Client> findById(Long id);

	public Optional<Client> findByName(String name);
	
}
