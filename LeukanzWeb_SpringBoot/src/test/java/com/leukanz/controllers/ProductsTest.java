package com.leukanz.controllers;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leukanz.response.Response;
import com.leukanz.services.ProductServiceImpl;

@SpringBootTest
class ProductsTest {
	@Autowired
	private ProductServiceImpl productService;
	
	@Test
	public void getAllImages() throws SQLException {
		Response resp = this.productService.getFotos();
		System.out.println(resp.getData());
		assertNotNull(resp);
	}
	@Test
	public void getAllFotos() throws SQLException {
		Response resp = this.productService.getFotos();
		System.out.println(resp.getData().toString());
		assertNotNull(resp);
	}

}
