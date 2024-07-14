package com.cathaybk.newbiehomework;

import com.cathaybk.newbiehomework.model.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = NewbieHomeworkApplication.class)
@AutoConfigureMockMvc
class NewbieHomeworkApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetAllCurrencyInfo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/currency/"))
				.andExpect(status().isOk())
				.andDo(result -> {
					String responseContent = result.getResponse().getContentAsString();
					// 驗證不為空
					assertEquals("", result.getResponse().getContentAsString());

					CurrencyDataTestDto[] currencies = objectMapper.readValue(responseContent, CurrencyDataTestDto[].class);
					// 驗證長度為 5
					assertEquals(5, currencies.length); // 驗證數組長度為2
					// 驗證陣列內容
					assertEquals("USD", currencies[0].getCurrencyCode());
					assertEquals("Euro", currencies[1].getCurrencyName());
					assertEquals(BigDecimal.valueOf(0.29).stripTrailingZeros().toPlainString(),
							currencies[2].getExchangeRate().stripTrailingZeros().toPlainString());
					assertEquals("GBP", currencies[3].getCurrencyCode());
					assertEquals("Australian Dollar", currencies[4].getCurrencyName());
				});
	}

	@Test
	public void testGetCurrencyInfo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/currency/{currencyCode}", "USD"))
				.andExpect(status().isOk())
				.andDo(result -> {
					String responseContent = result.getResponse().getContentAsString();
					CurrencyDataTestDto currency = objectMapper.readValue(responseContent, CurrencyDataTestDto.class);

					assertNotNull(currency);
					assertEquals("USD", currency.getCurrencyCode());
					assertEquals("United States Dollar", currency.getCurrencyName());
					assertEquals(BigDecimal.valueOf(27.5).stripTrailingZeros().toPlainString(),
							currency.getExchangeRate().stripTrailingZeros().toPlainString());
				});
	}

	@Test
	public void testAddCurrencyInfo() throws Exception {
		CurrencyDto data = new CurrencyDto();
		data.setExchangeRate(BigDecimal.valueOf(31.2584));
		data.setCurrencyCode("TWD");
		data.setCurrencyName("New Taiwan dollar");

		mockMvc.perform(MockMvcRequestBuilders.post("/api/currency/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(data)))
				.andExpect(status().isOk())
				.andDo(result -> {
					String responseContent = result.getResponse().getContentAsString();
					assertNotEquals("", responseContent);

					CurrencyDataTestDto currency = objectMapper.readValue(responseContent, CurrencyDataTestDto.class);
					assertEquals("TWD", currency.getCurrencyCode());
					assertEquals("New Taiwan dollar", currency.getCurrencyName());
					assertEquals(BigDecimal.valueOf(31.2584).stripTrailingZeros().toPlainString(),
							currency.getExchangeRate().stripTrailingZeros().toPlainString());
				});
	}

	@Test
	public void testUpdateCurrencyInfo() throws Exception {
		// 先取 USD 出來
		StringBuilder res = new StringBuilder();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/currency/{currencyCode}", "USD"))
				.andExpect(status().isOk())
				.andDo(result -> {
					res.append(result.getResponse().getContentAsString());
				});

		// 將取出的資料做更新並檢查其內容
		CurrencyDataTestDto data = objectMapper.readValue(res.toString(), CurrencyDataTestDto.class);
		data.setCurrencyCode("TWD");
		data.setCurrencyName("New Taiwan dollar");
		data.setExchangeRate(BigDecimal.valueOf(25.22));

		mockMvc.perform(MockMvcRequestBuilders.put("/api/currency/{currencyCode}", "USD")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(data)))
				.andExpect(status().isOk())
				.andDo(result -> {
					String responseContent = result.getResponse().getContentAsString();
					assertNotEquals("", responseContent);

					CurrencyDataTestDto currency = objectMapper.readValue(responseContent, CurrencyDataTestDto.class);
					assertNotNull(currency);
					assertEquals("TWD", currency.getCurrencyCode());
					assertEquals("New Taiwan dollar", currency.getCurrencyName());
					assertEquals(BigDecimal.valueOf(25.22).stripTrailingZeros().toPlainString(),
							currency.getExchangeRate().stripTrailingZeros().toPlainString());
				});

		// 應該要找不到 USD
		mockMvc.perform(MockMvcRequestBuilders.get("/api/currency/{currencyCode}", "USD"))
				.andExpect(status().isOk())
				.andDo(result -> {
					assertEquals("", result.getResponse().getContentAsString());
				});
	}

	@Test
	public void testDeleteCurrencyInfo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/currency/{currencyCode}", "USD"))
				.andExpect(status().isOk());

		// 應該要找不到 USD
		mockMvc.perform(MockMvcRequestBuilders.get("/api/currency/{currencyCode}", "USD"))
				.andExpect(status().isOk())
				.andDo(result -> {
					assertEquals("", result.getResponse().getContentAsString());
				});
	}

	// 呼叫 coindesk 使用 swagger 測試
}
