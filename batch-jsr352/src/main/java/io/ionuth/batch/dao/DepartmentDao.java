package io.ionuth.batch.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import io.ionuth.batch.ito.impl.Department;

@Repository
public class DepartmentDao {
	
	private DataSource ds;
	
	// Spring constructor injection
	public DepartmentDao(DataSource ds) {
		this.ds = ds;
	}
	
	public Optional<Department> selectById(long id) {
		
		String query = """
        		SELECT DEPT_NAME, DEPT_DESCRIPTION, DEPT_CREATION_DATE
        		FROM DEPARTMENT
        		WHERE DEPT_ID=?
        		""";
        try {
	        PreparedStatement ps = ds.getConnection().prepareStatement(query);
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()) {
	        	Department dept = new Department();
	        	dept.setDeptId(id);
	        	dept.setDeptName(rs.getString("DEPT_NAME"));
	        	dept.setDeptDesc(rs.getString("DEPT_DESCRIPTION"));
	        	dept.setCreationDate(rs.getDate("DEPT_CREATION_DATE").toString());
	        	return Optional.of(dept);
	        }
        } catch(SQLException ex) {
        	System.out.println(ex);
        }
        return Optional.empty();
	}
	
}
