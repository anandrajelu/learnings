package com.fiserv.batch.processor;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fiserv.batch.model.TransactionStreamData;
import com.fiserv.batch.model.Product;

@Component
public class BaseProductProcessor {

	@Autowired 
	private TransactionStreamData transactionStream;
	
	protected void processProduct(Product product) throws Exception {		
		product.setName(product.getName()+"Processor");	
		HashSet<Product> homeSet = null;
		if(transactionStream.getHomeSet()!=null){
			homeSet = transactionStream.getHomeSet();
		}else{
			homeSet = new HashSet<Product>();
		}		
		homeSet.add(product);
		transactionStream.setHomeSet(homeSet);
	}
}
