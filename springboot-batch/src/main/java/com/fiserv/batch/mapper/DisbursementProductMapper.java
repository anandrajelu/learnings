package com.fiserv.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fiserv.batch.model.Product;

public class DisbursementProductMapper extends ProductRowMapper {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		Product result = super.mapRow(rs, rowNum);
		result.setDescription(rs.getString("NICK_NAME"));
		return result;
	} 
}
