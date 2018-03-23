package com.example.web.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.web.monitor.model.Coin;


public interface CoinExternalService {
	public List<Coin> getCoinListByCodeList(List<String> coinList);
}
