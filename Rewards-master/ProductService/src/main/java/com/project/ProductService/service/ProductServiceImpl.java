  package com.project.ProductService.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.ProductService.dao.ProductDao;
import com.project.ProductService.entity.ProductEntity;
import com.project.ProductService.pojo.ConversionPojo;
import com.project.ProductService.pojo.ProductPojo;




@Service
public class ProductServiceImpl implements ProductService {

	static Logger LOG = Logger.getLogger(ProductServiceImpl.class.getClass());
	
	@Autowired
	ProductDao productDao;
	
 //To retrieve all Items

	@Override
	public List<ProductPojo> getAllProducts() {
		LOG.info("entered getAllProducts()");
		List<ProductPojo> allProductPojo = new ArrayList();
		Iterable<ProductEntity> allProductEntity = productDao.findAll();
		Iterator itr = allProductEntity.iterator();
		
		while(itr.hasNext()) {
			
			ProductEntity productEntity = (ProductEntity) itr.next();
			
			//System.out.println("h");
			RestTemplate restTemplate = new RestTemplate();
			//System.out.println("i");
			 ConversionPojo conversionPojo = restTemplate.getForObject("http://localhost:8096/con/1", ConversionPojo.class);
			 //System.out.println(conversionPojo);
			int a = conversionPojo.getValue();
			//System.out.println(a);
			int price = productEntity.getPrice();
			//System.out.println(price);
			int result = price / a;
			//System.out.println(result);
			ProductPojo productPojo = new ProductPojo(productEntity.getId(),
													  productEntity.getName(),
													  productEntity.getImage(),
													  productEntity.getPrice(),
													  productEntity.getStock(),
													  productEntity.getDescription(),
													  productEntity.getRemarks(),
													  result);
			//System.out.println(productPojo);
			allProductPojo.add(productPojo);
		}
		
		LOG.info("exited getAllProducts()");
		BasicConfigurator.configure();
		//System.out.println(allProductPojo);
		return allProductPojo;
	}

}
