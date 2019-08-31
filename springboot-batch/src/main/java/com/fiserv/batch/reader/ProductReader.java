package com.fiserv.batch.reader;

import org.springframework.batch.item.database.JdbcCursorItemReader;

import com.fiserv.batch.model.Product;

/**
 * The Class ProductReader.
 *
 * @author 
 */

public class ProductReader extends JdbcCursorItemReader<Product> {

	public static final String SELECT_CLAUSE = "SELECT HOME_ID,NICK_NAME,HOME_NAME";
	public static final String FROM_CLAUSE = " FROM HOME";
	public static final String WHERE_CLAUSE = " WHERE APPLICATION_TYPE=?";
	
	
	

}
