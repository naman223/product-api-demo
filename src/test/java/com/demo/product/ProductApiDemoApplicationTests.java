package com.demo.product;

import com.demo.product.entity.Price;
import com.demo.product.entity.Product;
import com.demo.product.vo.PriceVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductApiDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper om = new ObjectMapper();

	/**
	 * Post and Get valid Product
	 * @throws Exception
	 */
	@Test
	public void validProductTest() throws Exception {
		Product product = new Product();
		product.setProduct_id(223);
		product.setTitle("Title");
		product.setSellerId("Naman");
		String jsonRequest = om.writeValueAsString(product);
		MvcResult result = mockMvc.perform(post("/product").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Product response = om.readValue(resultContent, Product.class);
		Assert.assertTrue(response.getTitle().equals("Title") &&
				response.getProduct_id()==223);


		result = mockMvc
				.perform(get("/product/223"))
				.andExpect(status().isOk()).andReturn();
		resultContent = result.getResponse().getContentAsString();
		response = om.readValue(resultContent, Product.class);
		Assert.assertTrue(response.getTitle().equals("Title") &&
				response.getProduct_id()==223);
	}

	/**
	 * Post and Get valid Product Price Test
	 * @throws Exception
	 */
	@Test
	public void validProductPriceTest() throws Exception {
		Product product = new Product();
		product.setProduct_id(1);
		product.setTitle("Title");
		product.setSellerId("Naman");
		Price price = new Price();
		price.setMin(4.0);
		price.setMax(5.0);
		price.setRange("4.0-5.0");
		product.setPrice(price);
		String jsonRequest = om.writeValueAsString(product);
		MvcResult result = mockMvc.perform(post("/product").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Product response = om.readValue(resultContent, Product.class);
		Assert.assertTrue(response.getPrice().getMin()==4.0);


		result = mockMvc
				.perform(get("/product/price/1"))
				.andExpect(status().isOk()).andReturn();
		resultContent = result.getResponse().getContentAsString();
		PriceVO priceVO = om.readValue(resultContent, PriceVO.class);
		Assert.assertTrue(priceVO.getPrice().getMin()==4.0);
	}


	/**
	 * Get product with wrong product Id
	 * @throws Exception
	 */
	@Test
	public void productNotFoundTest() throws Exception {
		mockMvc.perform(get("/product/1001"))
				.andExpect(status().isNotFound()).andReturn();
	}

	/**
	 * Get product Without passing product Id
	 * @throws Exception
	 */
	@Test
	public void productMethodNotAllowedTest() throws Exception {
		mockMvc.perform(get("/product"))
				.andExpect(status().isMethodNotAllowed()).andReturn();
	}

	/**
	 * Post Product without passing product Id
	 * @throws Exception
	 */
	@Test
	public void productBadRequestTest() throws Exception {
		Product product = new Product();
		product.setTitle("Title");
		product.setSellerId("Naman");
		String jsonRequest = om.writeValueAsString(product);
		mockMvc.perform(post("/product").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();
	}
}
