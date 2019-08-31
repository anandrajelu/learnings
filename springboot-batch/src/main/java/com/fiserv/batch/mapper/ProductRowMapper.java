package com.fiserv.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fiserv.batch.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		Product result = new Product();
		result.setName(rs.getString("NICK_NAME"));                     
		result.setId(rs.getLong("HOME_ID"));
		return result;
	} 

}
