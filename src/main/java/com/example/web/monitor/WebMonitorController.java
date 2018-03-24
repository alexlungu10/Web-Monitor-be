package com.example.web.monitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.monitor.model.Coin;
import com.example.web.monitor.service.CoinExternalService;
import com.example.web.monitor.service.CoinVaultService;

@RestController
public class WebMonitorController {

	@Autowired
	CoinExternalService coinMarketService;

	@Autowired
	CoinVaultService coinVaultService;

	@CrossOrigin()
	@RequestMapping("/getCoinRatesByCoinList")
	public @ResponseBody Map<String, List<Double>> getCoinRatesByCoinList(
			@RequestParam("coinListAsCsv") String coinListAsCsv) {

		List<String> coinCodeList = Arrays.asList(coinListAsCsv.split(","));
		
		//Fetch coins from external API
		List<Coin> coinListFromMarketByCodeList = coinMarketService.getCoinListByCodeList(coinCodeList); 

		// save Coins  into the Vault
		System.out.println("Saving data into db...");
		coinListFromMarketByCodeList.stream().filter(coin->coin.getValue() > 0).forEach(coinVaultService::save);
		System.out.println("Data Saved ok");
		
		// Fetch data from Db
		Map<String, List<Double>> responseMap = new HashMap<>();
		coinCodeList.stream().forEach(coinCode -> responseMap.put(coinCode, getCoinListByCodeFromDb(coinCode)));

		return responseMap;
	}

	private List<Double> getCoinListByCodeFromDb(String coinCode) {
		List<Double> coinListFromDb = coinVaultService.findAllByCode(coinCode).stream().map(coin -> Double.valueOf(coin.getValue()))
				.collect(Collectors.toList());
		List<Double> coinListFromDbReversed=new ArrayList<>();
		ListIterator<Double> lit=coinListFromDb.listIterator(coinListFromDb.size());
		while(lit.hasPrevious()) {
			coinListFromDbReversed.add(lit.previous());
		}
		return coinListFromDbReversed;
	}

}
