package com.challengebp.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challengebp.dto.AccountDTO;
import com.challengebp.exceptions.AccountException;
import com.challengebp.exceptions.ResourceNotFoundException;
import com.challengebp.model.Account;
import com.challengebp.model.Client;
import com.challengebp.model.Movement;
import com.challengebp.model.object.MovementType;
import com.challengebp.services.IAccountService;
import com.challengebp.services.IClientService;
import com.challengebp.services.IMovementService;

/**
 * @author David Mogrovejo
 */
@RestController
@RequestMapping(path = "/api/cuentas/")
public class AccountController {

	@Autowired
	private IAccountService accountService; 
	
	@Autowired
	private IClientService clientService; 
	
	@Autowired
	private IMovementService movementService; 
	
	
	@GetMapping(path = "/")
	public ResponseEntity<List<Account>> getAll() {	
		return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);		
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<Account> findById(@PathVariable Long id) {
		Account account = accountService.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Cuenta con with ID :" + id + " Not Found!"));
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Account create(@RequestBody AccountDTO accountDTO) {	
		System.out.println("account balance ="+accountDTO.getSaldoInicial().toString());
		if (accountDTO.getSaldoInicial().compareTo(BigDecimal.ZERO)<=0 ) {
			throw new AccountException(
					" Saldo Incial "+accountDTO.getSaldoInicial().toString()+". Tiene que ser un valor positivo");
		}
		if (accountDTO.getNumeroCuenta()==null || accountDTO.getNumeroCuenta().isEmpty()) {
			throw new AccountException(
					" El campo NUMERO DE CUENTA no puede ser nulo");
		}
		try {
			Client client = clientService.findByName(accountDTO.getCliente())
			           .orElseThrow(()->new ResourceNotFoundException("Client with name :" + accountDTO.getCliente() + " Not Found!"));
			
			Account account =  new Account();
			account.setAccountNumber(accountDTO.getNumeroCuenta());
			account.setInicialBalance(accountDTO.getSaldoInicial());
			account.setAccountType(accountDTO.getTipo());
			account.setClient(client);
			account.setStatus(accountDTO.isEstado());
			account = accountService.save(account);
			Movement movement = new Movement();
			movement.setBalance(account.getInicialBalance());
			movement.setValue(account.getInicialBalance());
			movement.setDescription("Ingreso Inicial por Apertura de Cuenta");
			movement.setDateMovement(new Date());
			movement.setAccount(account);
			movement.setMovementType(MovementType.CREDITO);
			movementService.save(movement);
			return account;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("la Cuenta: " +accountDTO.toString()+" ya existe");
		}
		
	}
	
	@PutMapping("/{id}")	
	@ResponseStatus(HttpStatus.CREATED)
	public Account update(@RequestBody Account account, @PathVariable Long id) {
		Account currentAccount = accountService.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Cuenta con with ID :" + id + " Not Found!"));
		currentAccount = account;
		currentAccount.setId(id);
		return accountService.save(account);
	}
	
	
	@DeleteMapping("/{id}")		
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		try {
			Account currentAccount = accountService.findById(id)
					.orElseThrow(()->new ResourceNotFoundException("Cuenta con ID :" + id + " No Encontrado!"));
			accountService.delete(currentAccount.getId());			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("la Cuenta con ID: " +id+" NO HA PODIDO SER ELIMINADA");
		}
	}
}
