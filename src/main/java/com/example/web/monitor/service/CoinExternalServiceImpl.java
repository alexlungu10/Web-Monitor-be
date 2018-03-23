package com.example.web.monitor.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.web.monitor.model.Coin;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoinExternalServiceImpl implements CoinExternalService {

	
	  private static final String BTC = "BTC" ;
	  private static final String ETH = "ETH" ;
	  private static final String XRP = "XRP" ;
	  private static final String STR = "STR" ;
	 
	private static final String COIN_VALUE_KEY = "last";
	private static final HashMap<String, String> coinCodeToJsonCoinKey = new HashMap<>();

	static {
		coinCodeToJsonCoinKey.put(BTC, "USDT_BTC");
		coinCodeToJsonCoinKey.put(ETH, "BTC_ETH");
		coinCodeToJsonCoinKey.put(XRP, "BTC_XRP");
		coinCodeToJsonCoinKey.put(STR, "BTC_STR");
	}

	@Override
	public List<Coin> getCoinListByCodeList(List<String> coinListToCompute) {

		boolean isDummy = true;

		System.out.println("Request params: " + coinListToCompute);
		String body;
		if (isDummy) {
			body = new RestTemplate().getForEntity("http://pc1862:9001/returnTicker", String.class).getBody();
		} else {
			body = new RestTemplate().getForEntity("https://poloniex.com/public?command=returnTicker", String.class)
					.getBody();
		}

		// System.out.println("Response: " + body);

		List<Coin> coinList = new ArrayList<>();
		coinListToCompute.stream()
				.forEach(code -> coinList.add(computeCoinValueByCodeFromJsonResponse(isDummy, body, code)));

		return coinList;
	}

	private Coin computeCoinValueByCodeFromJsonResponse(boolean isDummy, String body, String code) {
		BigDecimal roundedCoinValue = BigDecimal.ZERO;
		try {
			JSONObject jsonObject = new JSONObject(body);
			double coinValue = jsonObject.getJSONObject(coinCodeToJsonCoinKey.get(code)).getDouble(COIN_VALUE_KEY);
			roundedCoinValue = new BigDecimal(coinValue).setScale(BTC.equalsIgnoreCase(code)?2:6, RoundingMode.HALF_UP);
			// System.out.println(roundedCoinValue);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(String.format("Parsed value: [%s]=%s", code, roundedCoinValue.toString()));

		if (isDummy) {
			return new Coin(code, (Math.random() *1000)+( roundedCoinValue.doubleValue())); // dummy
		} else {
			return new Coin(code, roundedCoinValue.doubleValue()); // real deal
		}
	}

}
