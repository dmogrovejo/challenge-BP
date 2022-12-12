package com.challengebp.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challengebp.dto.MovementDTO;
import com.challengebp.exceptions.MovementException;
import com.challengebp.exceptions.ResourceNotFoundException;
import com.challengebp.model.Account;
import com.challengebp.model.Movement;
import com.challengebp.model.object.MovementType;
import com.challengebp.services.IAccountService;
import com.challengebp.services.IMovementService;


/**
 * @author David Mogrovejo
 */
@RestController
@RequestMapping(path = "/api/movimientos/")
public class MovementController {

	@Autowired
	private IMovementService movementService; 

	
	@Autowired
	private IAccountService accountService; 
	
	@GetMapping(path = "")	
	public List<Movement> getAll(){
		return movementService.findAll();
	}
	
	@GetMapping("/{id}")	
	public Movement findById(@PathVariable String id) {
		return movementService.findById(id);
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Movement create(@RequestBody MovementDTO movement) {		
		Account account = accountService.findByAccountNumber(movement.getNumeroCuenta())
				.orElseThrow(()->new ResourceNotFoundException("Cuenta con numero :" + movement.getNumeroCuenta() + " Not Found!"));
		
		// Verifica que el valor del moviento no sea 0 ni nulo
		if (movement.getValor()== null || movement.getValor().compareTo(BigDecimal.ZERO)==0) {
			throw new MovementException("El valor del movimiento no puede ser nulo ni 0");
		}
		
		Movement currentMovement =  new Movement();
		
		// Verifica si el valor es Negativo en ese caso se trata de un retiro
		if (movement.getValor().compareTo(BigDecimal.ZERO)<0) {
			/* 
			 * Recupera el balance de la cuenta ingresada si el valor del balance  es 0 
			 * O no se recupera el registro se debe indicar que el saldo es Insuficiente
			 */
			
			System.out.println("account.getId()="+account.getId());
			BigDecimal balance = movementService.getBalance(account.getId())
					.orElseThrow(()->new MovementException("Saldo de cuenta insuficiente"));
			
			System.out.println("recupero los valores");
			/**
			 * En el caso de que el saldo de la cuenta si sea superior a 0
			 * se verifica ni al retirar el nuevo valor el saldo no quedará en negativo
			 */
			System.out.println("balance="+balance.toString());
			System.out.println("movement.getValor()="+movement.getValor().toString());
			System.out.println("movement.getValor().abs()="+movement.getValor().abs().toString());
			System.out.println("balance.subtract(movement.getValor().abs())="+balance.subtract(movement.getValor().abs()).toString());
			if (balance.subtract(movement.getValor().abs()).compareTo(BigDecimal.ZERO)<0) {
				throw new MovementException("Saldo de cuenta insuficiente");
			}
			
			BigDecimal balanceDebit = movementService.getBalanceDebit(account.getId(), new Date())
					.orElse(BigDecimal.ZERO);
			
			
			if (balanceDebit.compareTo(new BigDecimal(1000))==0 || 
					balanceDebit.add(movement.getValor().abs()).compareTo(new BigDecimal(1000))>0) {
				throw new MovementException("Cupo diario Excedido");
			}
			currentMovement.setMovementType(MovementType.DEBITO);
			currentMovement.setBalance(balance.add(movement.getValor()));
			
			
		}else {
			BigDecimal balance = movementService.getBalance(account.getId())
					.orElse(BigDecimal.ZERO);
			
			currentMovement.setMovementType(MovementType.CREDITO);
			currentMovement.setBalance(balance.add(movement.getValor()));
		}
		currentMovement.setValue(movement.getValor());
		currentMovement.setDescription(movement.getDescripcion());
		currentMovement.setDateMovement(new Date());
		currentMovement.setAccount(account);
		return movementService.save(currentMovement);
	}
	
	@PutMapping("/{id}")	
	@ResponseStatus(HttpStatus.CREATED)
	public Movement update(@RequestBody Movement movement, @PathVariable String id) {
		Movement currentMovement = movementService.findById(id);
		currentMovement = movement;
		currentMovement.setId(id);
		return movementService.save(movement);
	}
	
	@DeleteMapping("/{id}")		
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		movementService.delete(id);
	}
}
