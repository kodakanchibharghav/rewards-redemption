package com.project.ProductService.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.ProductService.pojo.ProductPojo;
import com.project.ProductService.service.ProductService;

import io.swagger.annotations.Api;

@CrossOrigin
@RestController
//@RequestMapping("/api")
@Api
public class ProductController {
	static Logger LOG = Logger.getLogger(ProductController.class.getClass());
	

	@Autowired
	ProductService productService;
	
	
//Rest EndPoint to Retrieve All the products
	@GetMapping("/prod/all")
	//@HystrixCommand(fallbackMethod = "getFallBackProductList")
		List<ProductPojo> getAllProducts() throws InterruptedException{
		LOG.info("Entered the end point \'/prod/all'");
		LOG.info("exited end point \'/prod/all'");
		
		return productService.getAllProducts();
		
		
	    //List<ProductPojo> list=productService.getAllProducts();
	    //Thread.sleep(3000);
//	    if(list.size()!=0) {
//	    	return new ResponseEntity<List<ProductPojo>>(list,HttpStatus.OK);
//	    }else {
//	    	return new ResponseEntity<String>("no products in the list",HttpStatus.NOT_FOUND);
//	    }
	}

	
	
	
//	ResponseEntity<?> getFallBackProductList() {
//		LOG.info("Fall Back invoked. Some Service is down or not responding");
//		return  new ResponseEntity<String>("Took too long to respond",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
