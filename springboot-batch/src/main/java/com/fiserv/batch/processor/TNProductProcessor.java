package com.fiserv.batch.processor;


import org.springframework.batch.item.ItemProcessor;

import com.fiserv.batch.model.Product;

/**
 * The Class ProductProcessor.
 * 
 * @author 
 */
public class TNProductProcessor extends BaseProductProcessor implements ItemProcessor<Product,Product> {


	@Override
	public Product process(Product product) throws Exception {
		super.processProduct(product);	
		System.out.println("TNProductProcessor processing : " 
                + product.getId() + " : " + product.getName());
		return product;
	}

}
