package accountpayableSys;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import orderProcessingSys.MakeOrder;


@Repository

public class InvoiceRecievedRepository {
	@Autowired
	 private JdbcTemplate jdbcTemplate ;
	
//	thid method will be inserting data into OrderTable from supplier GUI
	public void insertOrder(MakeOrder order) {
	    // First check if supplier exists
	    String checkSupplier = "SELECT COUNT(*) FROM supplier WHERE companyName = ?";
	    int count = jdbcTemplate.queryForObject(checkSupplier, Integer.class, order.getSupplierName());
	    
	    if (count == 0) {
	        throw new RuntimeException("Supplier with companyName " + order.getSupplierName() + " does not exist");
	    }

	    // Then proceed with insertion
	    String sql = "INSERT INTO OrderTable(CompanyName, username, orderdate) VALUES(?, ?, ?)";
	    jdbcTemplate.update(sql, order.getSupplierName(), order.getEmail(), order.getPurchaseDate());
	    
	    // Fixed SELECT query
	    String query = "SELECT orderID FROM OrderTable WHERE companyName = ? AND username = ? AND orderDate = ? limit 1";
	    Map<String, Object> map = jdbcTemplate.queryForMap(query, order.getSupplierName(), order.getEmail(), order.getPurchaseDate());
	    
	    int orderid = (int) map.get("orderID");
	    
	    // Fixed INSERT statement (corrected variable name from sql to st)
	    String st = "INSERT INTO item(orderID, itemName, description, category, unitPrice, companyName,quantityInStock,addedDate) VALUES(?,?,?, ?, ?, ?, ?,?)";
	    jdbcTemplate.update(st, orderid, order.getItemName(), order.getDescription(), order.getCategory(),
	            order.getUnitPrice(), order.getSupplierName(),order.getQuatinty(), order.getPurchaseDate());
	    
//	    updating the status of an order that it has been invoice and recieved in shipememt company system
	    String queryStatus = "insert into status (orderID, invoiced) Values(?,?)";
	    

        String status="yes";
	    jdbcTemplate.update(queryStatus, orderid,status);
	}

}
