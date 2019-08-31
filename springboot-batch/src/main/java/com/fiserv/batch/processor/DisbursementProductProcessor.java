package com.fiserv.batch.processor;


import org.springframework.batch.item.ItemProcessor;

import com.fiserv.batch.model.Product;

/**
 * The Class ProductProcessor.
 * 
 * @author 
 */
public class DisbursementProductProcessor extends BaseProductProcessor implements ItemProcessor<Product,Product> {


	@Override
	public Product process(Product product) throws Exception {
		super.processProduct(product);
		product.setDescription(product.getDescription()+"Processor");
		System.out.println( "DisbursementProductProcessor processing : " 
                + product.getId() + " : " + product.getName());
		return product;
	}

}
