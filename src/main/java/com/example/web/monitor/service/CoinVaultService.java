package com.example.web.monitor.service;

import java.util.List;

import com.example.web.monitor.model.Coin;

public interface CoinVaultService {
	
	public Coin save(Coin coin);

	List<Coin> findAllByCode(String code);

}
