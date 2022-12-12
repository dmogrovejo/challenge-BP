package com.challengebp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.challengebp.model.Client;

/**
 * @author David Mogrovejo
 */
@Repository
public interface IClientRepository extends JpaRepository<Client, Long>{

	public Optional<Client> findByName(String name);
}
