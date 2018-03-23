package com.example.web.monitor.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.web.monitor.model.Coin;

public interface CoinVaultRepository extends JpaRepository<Coin, Long> {

	@Query
	List<Coin> findTop20ByCodeOrderByTimestampDesc(String code);

}
