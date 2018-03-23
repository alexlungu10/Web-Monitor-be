package com.example.web.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.web.monitor.model.Coin;

@Repository
public class CoinVaultServiceImpl implements CoinVaultService {
	
	@Autowired
	CoinVaultRepository coinRepository;

	@Override
	public Coin save(Coin coin) {
		return coinRepository.save(coin);
		
	}
	
	@Override
	public List<Coin> findAllByCode(String code){
		return coinRepository.findTop20ByCodeOrderByTimestampDesc(code); 
	}

}
