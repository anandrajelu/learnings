package com.fiserv.batch.model;

import java.util.HashSet;

import com.fiserv.batch.model.Product;

public class TransactionStreamData {

	private HashSet<Product> homeSet;

	/**
	 * @return the accountSet
	 */
	public HashSet<Product> getHomeSet() {
		return homeSet;
	}

	/**
	 * @param accountSet the accountSet to set
	 */
	public void setHomeSet(HashSet<Product> homeSet) {
		this.homeSet = homeSet;
	}
	
}
