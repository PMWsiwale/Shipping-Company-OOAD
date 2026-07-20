package users;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository

public class UserRepository {

	@Autowired
	 private JdbcTemplate jdbcTemplate ;



     public void customerRegister(CustomerReg register)
    {

		
		
//		insert data into address table of a customer
			String addresssql="INSERT INTO address(country,PO_box,streetName,city,province,residentialAddress) Values(?,?,?,?,?,?)";
		jdbcTemplate.update(addresssql,register.getCountry(),register.getPobox(), register.getStreetName(), register.getCity(),register.getProvince()
				,register.getHouse()	);
	
//		 search query for addressId

		 String query="SELECT addressId FROM address WHERE country=? AND PO_box=? AND streetName=? AND city=? AND province=? AND residentialAddress=?";

		 Map<String, Object> map = jdbcTemplate .queryForMap(query ,register.getCountry(),register.getPobox() ,register.getStreetName(), register.getCity(),register.getProvince()
					,register.getHouse() );
		  int addressId=(int) map.get("addressId");
		
//		 then addressIdis used to update the table
			String sql="INSERT INTO customer(username,firstName,lastName,phoneNumber,email,addressID) Values(?,?,?,?,?,?)";
			jdbcTemplate.update(sql, register.getUsername(), register.getFname(),register.getLname(),register.getPhoneNumber(),register.getEmail(),
					addressId);
			
			String passwordurl="INSERT INTO passwordtable (Users,password) Values(?,?)";
 			jdbcTemplate.update(passwordurl, register.getUsername(),register.getPassword());
//		
		
    }
     
     
     public void SupplierRegister(SupplerReg register)
     {

 		
 		
// 		insert data into address table of a customer
    	 String addresssql="INSERT INTO address(country,PO_box,streetName,city,province) Values(?,?,?,?,?)";
 		jdbcTemplate.update(addresssql,register.getCountry(),register.getPobox(), register.getStreetName(),
 				register.getCity(),register.getProvince()
 				);
 	
// 		 search query for addressId

 		 String query="SELECT addressId FROM address WHERE country=? AND PO_box=? AND streetName=? AND city=? AND province=? ";

 		 Map<String, Object> map = jdbcTemplate .queryForMap(query ,register.getCountry(),register.getPobox()
 				 ,register.getStreetName(), register.getCity(),register.getProvince()
 					);
 		  int addressId=(int) map.get("addressId");
 		
// 		 then addressIdis used to update the table
 			String sql="INSERT INTO supplier(companyName,email,phoneNumber,addressID) Values(?,?,?,?)";
 			jdbcTemplate.update(sql, register.getSupplierName(),register.getEmail(),register.getPhoneNumber(),
 					addressId);
 		
 			String pasurl="INSERT INTO passwordtable (Users,password) Values(?,?)";
 			jdbcTemplate.update(pasurl, register.getSupplierName(),register.getPassword());
 					
 		
     }
     
     public void employeeRegister(Employee register)
     {

 			System.out.printf("The followin data is added froms service:-->%S",register.toString());
// 		
//// 		insert data into address table of a customer
// 			String addresssql="INSERT INTO address(country,PO_box,streetName,city,province,residentialAddress) Values(?,?,?,?,?,?)";
// 		jdbcTemplate.update(addresssql,register.getCountry(),register.getPobox(), register.getStreetName(), register.getCity(),register.getProvince()
// 				,register.getHouse());
// 	
//// 		 search query for addressId
//
// 		 String query="SELECT addressId FROM address WHERE country=? AND PO_box=? AND streetName=? AND city=? AND province=? AND residentialAddress=?";
//
//// 		 Map<String, Object> map = jdbcTemplate .queryForMap(query ,register.getCountry(),register.getPobox() ,register.getStreetName(), register.getCity(),register.getProvince()
//// 					,register.getHouse() );
//// 		  int addressId=(int) map.get("addressId");
// 		 List<Map<String, Object>> results = jdbcTemplate.queryForList(query, register.getCountry(), register.getPobox(), register.getStreetName(), register.getCity(), register.getProvince(), register.getHouse());
//
// 		if (results.isEmpty()) {
// 		    throw new RuntimeException("Address insertion failed or address lookup returned no results.");
// 		}
//
// 		int addressId = (int) results.get(0).get("addressId");
//
    		String addresssql="INSERT INTO address(country,PO_box,streetName,city,province,residentialAddress) Values(?,?,?,?,?,?)";
    		jdbcTemplate.update(addresssql,register.getCountry(),register.getPobox(), register.getStreetName(), register.getCity(),register.getProvince()
    				,register.getHouse()	);
    	
//    		 search query for addressId

    		 String query="SELECT addressId FROM address WHERE country=? AND PO_box=? AND city=? AND province=? AND residentialAddress=?";

    		 Map<String, Object> map = jdbcTemplate .queryForMap(query ,register.getCountry(),register.getPobox() , register.getCity(),register.getProvince()
    					,register.getHouse() );
    		  int addressId=(int) map.get("addressId");
// 		
// 		 then addressIdis used to update the table
 			String sql="INSERT INTO employee(firstName,lastName,branchName,departmentName,addressID,birthDate,phonenumber,email,position) Values(?,?,?,?,?,?,?,?,?)";
 			jdbcTemplate.update(sql, register.getFname(),register.getLname(),register.getBranchNo(),register.getDepartementname(),addressId,register.getBirthdate(), register.getPhoneNumber(),register.getEmail(),
 					register.getPosition());
 			
// 		password
 			String snn="select SSN from employee order by last_update desc limit 1";
 			Map<String,Object> SNN=jdbcTemplate.queryForMap(snn);
 			int ssn=(int) SNN.get("SSN");
 			
 			
 			
 			String storePassword = "INSERT INTO passwordEmp(SSN, password) VALUES (?, ?)";
 			jdbcTemplate.update(storePassword, ssn, register.getPassword());

 		
 		
     }
      
}
