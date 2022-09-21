package com.example.demo.repos;

import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.ElementNotFoundException;
import com.training.ifaces.CrudRepository;
import com.training.model.Student;
import java.sql.*;
import java.util.*;
public class StudentRepositoryImpl implements CrudRepository<Student> {

	private Connection con;
	
	
	public StudentRepositoryImpl(Connection con) {
		super();
		this.con = con;
	}

	public StudentRepositoryImpl() {
		// TODO Auto-generated constructor stub
		this.con = ConnectionFactory.getMySqlConnection();
	}

	@Override
	public boolean save(Student obj) {

		String sql = "insert into lumen_student values(?,?,?)";
		
		int rowAdded =0;

		try (PreparedStatement pstmt = con.prepareStatement(sql)){
			
			
			pstmt.setInt(1, obj.getRollNumber());
			pstmt.setString(2, obj.getFirstName());
			pstmt.setDouble(3, obj.getMarkScored());
			
			rowAdded = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return rowAdded==1?true:false;
	}

	@Override
	public Collection<Student> findAll() {

		List<Student> studList = new ArrayList<>();
		
		String sql = "select * from lumen_student";

		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
		
			
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Student obj =mapRowToObject(rs);
				
				studList.add(obj);
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return studList;
	}

	@Override
	public boolean delete(Student obj) throws ElementNotFoundException {
		
		return deleteById(obj.getRollNumber());
		
	}

	@Override
	public boolean deleteById(int key) throws ElementNotFoundException {
		
		String sql = "delete from lumen_student where rollNumber=?";

		Student found =findById(key);  
		
		int rowDeleted=0;

			
		
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
		
			pstmt.setInt(1, key);
			
			 rowDeleted  = pstmt.executeUpdate();
			

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rowDeleted==1?true:false;

	}

	@Override
	public Student findById(int key) throws ElementNotFoundException {

		String sql = "select * from lumen_student where rollNumber=?";

		Student student=null;
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
		
			pstmt.setInt(1, key);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				student =mapRowToObject(rs);
				
				
			}
			
			if(student==null) {
				throw new ElementNotFoundException("ERR-102","Element with Given Id "+ key+" Not Found");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return student;
		
	}

	private Student mapRowToObject(ResultSet rs) throws SQLException {
	
		int rollNumber = rs.getInt("rollNumber");
		String firstName = rs.getString("firstName");
		double markScored = rs.getDouble("markScored");
		return  new Student(rollNumber, firstName, markScored);

	}

	@Override
	public int update(Student obj) {

		
		  
		return 0;
	}
}
// SOLID PRINCIPLE - SINGLE OPEN LISKOV INTERFACE DEPENDENCY