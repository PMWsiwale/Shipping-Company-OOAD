package accountpayableSys;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryAPS {
	@Autowired
	 private JdbcTemplate jdbcTemplate;
	
//	
	public Map<String,Object> RetrieveInvoice( ) {
		 // SELECT statement
		 String query = "SELECT orderID FROM item" ;
		 // Search execution
		  return this.jdbcTemplate.queryForMap(query);
		 	 }
	public List ManagerInvoice( )
	{
		String query = "SELECT orderID FROM item" ;
		return this.jdbcTemplate.queryForList(query);
	}
	
	public List extractALLInvoice()
	{
		 
		 
			 String url="Select * From item";
			return this.jdbcTemplate.queryForList(url);
		 
	}
//	This method will be updating status after order is approved by the manager
	public void UpdateStatus(int orderId,String status,Date approvedDate)
	{
		
		    String queryStatus = "UPDATE status SET invoiced = , WHERE orderId = ?";
		    
		    // Correct order: username, password, userId
		    Object[] params = new Object[] { status, approvedDate, orderId };

		    jdbcTemplate.update(queryStatus, params);
		}
	
//	this methode wil be called after the invoice has been extracted and read by the Receive clerk in the controller
	public void markReceivedInvoice(int orderId)
	{
		
		    String queryStatus = "UPDATE status SET invoiced = 'yes' WHERE orderId = ?";
		    
		    // Correct order: username, password, userId
		    Object[] params = new Object[] { orderId };

		    jdbcTemplate.update(queryStatus, params);
		}

	}


