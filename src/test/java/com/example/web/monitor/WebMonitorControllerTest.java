package com.example.web.monitor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.web.monitor.model.Coin;
import com.example.web.monitor.service.CoinExternalService;
import com.example.web.monitor.service.CoinExternalServiceImpl;

@RunWith(SpringRunner.class)
public class WebMonitorControllerTest {

	//@Autowired
	CoinExternalService coinExternalService=new CoinExternalServiceImpl();

	@Test
	public void test_getCoinRatesByCoinList() throws Exception {

		String csvList = "USD,ETH";
		List<Coin> coinListByCodeList = coinExternalService.getCoinListByCodeList(csvToList(csvList));
		assertThat(coinListByCodeList.get(0).getCode(),is((csvToList(csvList).get(0))));
		coinListByCodeList.stream().forEach(System.out::println);

		/*
		 * MvcResult response =
		 * mockmvc.perform(get("/getCoinRatesByCoinList").param("coinListAsCsv",
		 * "USD,ETH")) .andDo(print()).andExpect(status().isOk()).andReturn();
		 */

	}

	private List<String> csvToList(String value) {
		return Arrays.asList(value.split(","));
	}

}
