package com.challengebp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challengebp.model.Account;


/**
 * @author David Mogrovejo
 */
@Service
public interface IAccountService {
	
	public List<Account> findAll();
	
	public Account save(Account account);
	
	public void delete(Long id);
	
	public Optional<Account> findById(Long id);
	
	public Optional<Account> findByAccountNumber(String accountNumber);
	
}
