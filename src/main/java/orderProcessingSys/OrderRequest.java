package orderProcessingSys;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRequest {
	@Autowired
	 private JdbcTemplate jdbcTemplate ;
	
//	this method will be displayed on the orderprocessing system 
	public List RetrieveOrder()
	{
		String url=" SELECT orderDetailsTem.orderID, itemID, itemName, quantityInStock,\r\n"
				+ "    firstName, lastName, companyName, description, addedDate \r\n"
				+ "FROM \r\n"
				+ "    (SELECT firstName, lastName, orderID, companyName\r\n"
				+ "     FROM customer, ordertable \r\n"
				+ "     WHERE customer.username = ordertable.username) AS orderDetailsTem\r\n"
				+ "INNER JOIN \r\n"
				+ "    (SELECT itemID, orderID, itemName, description, quantityInStock, addedDate\r\n"
				+ "     FROM item \r\n"
				+ "     WHERE orderID IN (SELECT orderID FROM status WHERE approvalStatus = 'yes' AND requestStatus IS NULL)\r\n"
				+ "    ) AS itemInfoTem \r\n"
				+ "ON itemInfoTem.orderID = orderDetailsTem.orderID ";

		return this.jdbcTemplate.queryForList(url);
	}
	
//	this method will allow the operator to send the request to fulfil the order from order processing system to fulfil
	public void sendRequest(int orderId)
	{
		
		    String queryStatus = "UPDATE status SET requestStatus  = 'yes' WHERE orderId = ?";
		    
		    Object[] params = new Object[] { orderId };

		    jdbcTemplate.update(queryStatus, params);
		}

}
