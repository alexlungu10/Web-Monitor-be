package com.example.web.monitor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebMonitorBeApplicationTests {

	@Autowired
	MockMvc mockmvc;

	@Test
	public void test_getCoinRatesByCoinList() throws Exception {
		MvcResult response = mockmvc.perform(get("/getCoinRatesByCoinList").param("coinListAsCsv", "USD,ETH"))
				.andDo(print()).andExpect(status().isOk()).andReturn();

	}

}
