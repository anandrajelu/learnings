package com.fiserv.batch.reader;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.fiserv.batch.mapper.DisbursementProductMapper;
import com.fiserv.batch.model.Product;

@Scope(value = "step")

public class DisbursementProductReader extends ProductReader {

	
	@Autowired	
	public void setSQL(DataSource dataSource){
		setSql(SELECT_CLAUSE+FROM_CLAUSE+WHERE_CLAUSE+" AND HOME_ID LIKE '8813%'");		
		PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {			   
		   public void setValues(PreparedStatement preparedStatement) throws SQLException {
		      preparedStatement.setString(1, "FT");
		   }
		};
		setPreparedStatementSetter(preparedStatementSetter);
	}


	@Autowired
	public RowMapper<Product> productRowMapper() {
		DisbursementProductMapper productRowMapper = new DisbursementProductMapper();
		setRowMapper(productRowMapper);
		return productRowMapper;
	}
}
