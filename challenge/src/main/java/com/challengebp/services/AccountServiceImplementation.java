package com.challengebp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challengebp.model.Account;
import com.challengebp.repository.IAccountRepository;

/**
 * @author David Mogrovejo
 */
@Service
public class AccountServiceImplementation implements IAccountService{

	@Autowired
	private IAccountRepository repositoryAccount;
	
	@Override
	@Transactional(readOnly = true)
	public List<Account> findAll() {
		return (List<Account>) repositoryAccount.findAll();
	}

	@Override
	@Transactional
	public Account save(Account client) {		
		return repositoryAccount.save(client);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repositoryAccount.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Account> findById(Long id) {
		return Optional.ofNullable(repositoryAccount.findById(id).orElse(null));
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Account> findByAccountNumber(String accountNumber){
		return Optional.ofNullable(repositoryAccount.findByAccountNumber(accountNumber).orElse(null));
	}
	
}
