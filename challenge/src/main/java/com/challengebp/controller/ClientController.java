package com.challengebp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challengebp.exceptions.ResourceNotFoundException;
import com.challengebp.model.Client;
import com.challengebp.services.IClientService;

/**
 * @author David Mogrovejo
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/clientes/")
@Validated
public class ClientController {

	@Autowired
	private IClientService clientService;

	@GetMapping(path = "/")
	public ResponseEntity<List<Client>> getAll() {	
		return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {						
		Client client = clientService.findById(id)
		           .orElseThrow(()->new ResourceNotFoundException("Client  with ID :" + id + " Not Found!"));			
		return new ResponseEntity<>(client, HttpStatus.OK);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@RequestBody Client client) {	
		try {
			return clientService.save(client);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Client with name " +client.getName()+" already exist");
		}
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client client, @PathVariable Long id) {
		Client clientCurrent = clientService.findById(id)
		           .orElseThrow(()->new ResourceNotFoundException("Client  with ID :" + id + " Not Found!"));			
		client.setId(clientCurrent.getId());
		return clientService.save(client);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Client clientCurrent = clientService.findById(id)
		           .orElseThrow(()->new ResourceNotFoundException("Client  with ID :" + id + " Not Found!"));		
		clientService.delete(clientCurrent.getId());
	}
}
