package shippingSys;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateInventory {
	@Autowired
	 private JdbcTemplate jdbcTemplate ;

	//this method wil go to recive clerk where stolk details will be updated
//	working..
	public void stolkProduct(NewProduct newproduct)
	{
		
		String url="Update item set location=?,itemType=? where itemID=?";
		   
	    Object[] params = new Object[] { newproduct.getLocation(), newproduct.getStockType(),newproduct.getItemID() };

	    jdbcTemplate.update(url, params);
	}
	
	public void stolkProduct(ReturnedProduct rp)
	{
		String sql="INSERT INTO Order(itemName,quantity,purchaseDste,description,category,packageName"
				+ "weight,customerId,suppler) Values(?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, rp.getItemName(), rp.getQuatinty(), rp.getPurchaseDate(),
				 rp.getDescription(), rp.getCategory(), rp.getPackageName()
				 ,rp.getWeight(), rp.getCustomerId(), rp.getSupplierName(),
				 rp.getLocation(),rp.getSection(),rp.getStockType(),rp.getReasonReturnement(),
				 rp.getDateReturened());
	}
	public void stolkProduct(CancelledProduct cproduct)
	{
		String sql="INSERT INTO Order(itemName,quantity,purchaseDste,description,category,packageName"
				+ "weight,customerId,suppler) Values(?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, cproduct.getItemName(), cproduct.getQuatinty(), cproduct.getPurchaseDate(),
				 cproduct.getDescription(), cproduct.getCategory(), cproduct.getPackageName()
				 ,cproduct.getWeight(), cproduct.getCustomerId(), cproduct.getSupplierName(),
				 cproduct.getLocation(),cproduct.getSection(),cproduct.getStockType(),cproduct.getDatecancelled()
				, cproduct.getReasonForcancelling());
	}
//	methode retreiving request from the database and pass it to update inventory service class
	public List getFullfilOder()
	{
		String url="  select firstName, lastName, orderDetailsTem.orderID  ,itemNumber ,date\r\n"
				+ "       from\r\n"
				+ "       (select firstName, lastName, orderID\r\n"
				+ "          from customer, ordertable where customer.username=ordertable.username) as\r\n"
				+ "	  orderDetailsTem inner join \r\n"
				+ "    ( select item.orderID,count(itemName) as itemNumber ,date from item\r\n"
				+ "     inner join status on status.orderID=item.orderID  where (requestStatus='yes' and fulfillmentStatus is null) group by item.orderID,date) as \r\n"
				+ "     itemTemInfo on itemTemInfo.orderID=orderDetailsTem.orderID ";
				return this.jdbcTemplate.queryForList(url);
	}
	
// this method will be updating every kind of status : the typeOfStatus value will be changeing 
//	depending  the kind of status set  the other classes
	public void UpdateStatus(int orderId,String status,Date approvedDate,String typeOfStatus)
	{
		
		    String queryStatus = "UPDATE statusTable SET" + typeOfStatus +"  = ?, approvedDate = ? WHERE orderId = ?";
		    
		    Object[] params = new Object[] { status, approvedDate, orderId };

		    jdbcTemplate.update(queryStatus, params);
		}
//	This method will be deleting fulfilled order soon after the order is fulfilled see the service class
	 public void DeleteFulfilledOrder(String orderId)
	 {
		 String url="Delete from stolk where userId="+orderId;
		 jdbcTemplate.update(url);
	 }
//	 this methode will be displayed on the page of viewFilled orders searched by query
	 public List ViewFilledOrder(int orderId)
	 {
		 String url = "select itemID,itemName,description,category,unitprice,quantityInStock,itemType,location\r\n"
		 		+ "     from item where orderID=?";

		 
			return this.jdbcTemplate.queryForList(url,orderId);
		 
	 }
//	 this methode will be displayed on the page of viewFilled orders searched by query
	 public List SummaryViewFilledOrder(int orderId)
	 {
		 String url = "  select firstName, lastName, orderDetailsTem.orderID  ,itemNumber ,date"
		 		+ "       from"
		 		+ "       (select firstName, lastName, orderID"
		 		+ "          from customer, ordertable where customer.username=ordertable.username) as"
		 		+ "	  orderDetailsTem inner join "
		 		+ "    ( select item.orderID,count(itemName) as itemNumber ,date from item"
		 		+ "     inner join status on status.orderID=item.orderID  where (item.orderID=? and  requestStatus='yes') group by item.orderID,date) as "
		 		+ "     itemTemInfo on itemTemInfo.orderID=orderDetailsTem.orderID";

		 
			return this.jdbcTemplate.queryForList(url,orderId);
		 
	 }
		public List  Filled(int idParam ) {
			 // SELECT statement
//			 String query = "select * from item inner join "
//			 		+ "ordertable on ordertable.orderID=item.orderID "
//			 		+ "where ordertable.orderID=?";
			
//			another type of query
			 String query="  select itemID, temp.orderID, itemName, description, category, unitPrice, companyName,username, quantityInStock, addedDate, itemType \r\n"
			 		+ "			 					 		from(  select   itemID, item.orderID, itemName, description, category, unitPrice, item.companyName,username, quantityInStock, addedDate, itemType   from item  inner join\r\n"
			 		+ "			 					 					 		ordertable on ordertable.orderID=item.orderID\r\n"
			 		+ "											where ordertable.orderID=?) as temp where not exists \r\n"
			 		+ "			 		       (select orderID from status where (temp.orderID=status.orderID and  approvalstatus='yes'))";
			 // Search execution
			  List invoice = jdbcTemplate.queryForList(query , idParam );
			 
			 return invoice ;
			 }
	 
//	 this method will be returning orders from the database to shipping 
	 public List ViewOrder()
	 {
		 String url = "SELECT i.itemname, i.quantity, i.category, i.weight, i.category, o.orderId, o.customerId, o.suppliername " +
	             "FROM items i " +
	             "JOIN orders o ON i.orderId = o.orderId " +
	             "JOIN status s ON o.orderId = s.orderId " +
	             "WHERE s.filledStatus LIKE '%yes%'";

		 
			return this.jdbcTemplate.queryForList(url);
		 
	 }
	 
//	 this method will get all information about the address of a customer to use it as destination info
	 
	 public Map<String ,Object> getDestination(int customerId)
	 {
		 
		 String query="Select city,place, country, province From address "
		 		+ "where customerId=?";
//		 search query
		 Map<String, Object> login = jdbcTemplate .queryForMap(query , customerId );
		 return login;
	
		 
	 }
//	 data input for the stolk about to be shipped
	 public void shippeOrderInfo(int orderId, String transportType, String transportDetails, Date date) {
		    String sql = "INSERT INTO shipmentinformation (orderID,deliveryDate,transportType,transportDetails) VALUES (?, ?, ?, ?)";
		    
		    jdbcTemplate.update(sql, orderId, date, transportType, transportDetails);
		}
	
	 
//	 this method will be retrieving invoice to be displayed :see the service class
		public Map<String, Object> GetInvoiceToReceiver(int idParam ) {
			 // SELECT statement
			 String query = "select * from item inner join "
			 		+ "ordertable on ordertable.orderID=item.orderID "
			 		+ "where ordertable.orderID=?";
			 // Search execution
			 Map<String, Object> invoice = jdbcTemplate.queryForMap(query , idParam );
			 return invoice ;
			 }
		
		public List  GetInvoiceToReceiverList(int idParam ) {
			 // SELECT statement
//			 String query = "select * from item inner join "
//			 		+ "ordertable on ordertable.orderID=item.orderID "
//			 		+ "where ordertable.orderID=?";
			
//			another type of query
			 String query="  select itemID, temp.orderID, itemName, description, category, unitPrice, companyName,username, quantityInStock, addedDate, itemType \r\n"
			 		+ "			 					 		from(  select   itemID, item.orderID, itemName, description, category, unitPrice, item.companyName,username, quantityInStock, addedDate, itemType   from item  inner join\r\n"
			 		+ "			 					 					 		ordertable on ordertable.orderID=item.orderID\r\n"
			 		+ "											where ordertable.orderID=?) as temp where not exists \r\n"
			 		+ "			 		       (select orderID from status where (temp.orderID=status.orderID and  approvalstatus='yes'))";
			 // Search execution
			  List invoice = jdbcTemplate.queryForList(query , idParam );
			 
			 return invoice ;
			 }
		
//		This method will be updating status after order is approved by the manager
//		public void UpdateStatus(int orderID,String statusType,Date approvedDate)
//		{
////			String approved="yes";
////			    String queryStatus = "UPDATE status SET "+ statusType+ "= ?, date = ? WHERE orderID = ?";
//			    String queryStatus = "insert into status1 (orderID,status,approvedDate) Value(?,?,?)";
//			    // Correct order: username, password, userId
//			    Object[] params = new Object[] {orderID, statusType,approvedDate}; 
//
//			    jdbcTemplate.update(queryStatus, params);
//			    
//			    
////			    public void UpdateStatus(int orderId, String statusType, Date approvedDate) {
////			        String queryStatus = "UPDATE status SET statuse = :status, approvedDate = :approvedDate WHERE orderId = :orderId";
////			        
////			        MapSqlParameterSource params = new MapSqlParameterSource();
////			        params.addValue("status", statusType);
////			        params.addValue("approvedDate", approvedDate);
////			        params.addValue("orderId", orderId);
////
////			        jdbcTemplate.update(queryStatus, params);
////			    }
//			}
//		This method will be updating status after order is approved by the manager
//		 public void UpdateStatus(int orderId, String statusType, Date approvedDate) {
//			    String status="yes";
//		        String queryStatus = "UPDATE status SET "+ statusType +"= :status, date = :approvedDate WHERE orderID = :orderId";
//		        
//		        MapSqlParameterSource params = new MapSqlParameterSource();
//		        params.addValue("status",status);
//		        params.addValue("approvedDate", approvedDate);
//		        params.addValue("orderId", orderId);
//
//		        jdbcTemplate.update(queryStatus, params);
//		    }
		 public void UpdateStatus(int orderId, String statusType, Date approvedDate) {
			    String status = "yes";
			    String queryStatus = "UPDATE status SET " + statusType + " = ?, date = ? WHERE orderID = ?";

			    jdbcTemplate.update(queryStatus, status, approvedDate, orderId);
			}
		 
		 public void UpdateStatus(int orderId, String statusType) {
			    String status = "yes";
			    String queryStatus = "UPDATE status SET " + statusType + " = ? WHERE orderID = ?";

			    jdbcTemplate.update(queryStatus, status,  orderId);
			}
		 
		 public void UpdateStatusLocation(ShippeProduct location) {
			    
			    String queryStatus = "UPDATE status SET deliveryStatus = ?,currentLocation=?  WHERE orderID = ?";

			    jdbcTemplate.update(queryStatus,location.getDelivared(),location.getCurrentLocation(), location.getOrderId());
			}

		
		
		//	 this method will be returning orders from the database to shipping on filled order page
		 public List ViewVeriFiedOrder()
		 {
			 String url = "  select firstName, lastName, name.orderID,companyName,qitem,date from \r\n"
			 		+ "			 		( select firstName, lastName, orderID,companyName\r\n"
			 		+ "			 		 from customer, ordertable where customer.username=ordertable.username) as name\r\n"
			 		+ "			 		 inner join \r\n"
			 		+ "			 		 (select  dataT.orderID,qitem,statusTem.date from\r\n"
			 		+ "                     \r\n"
			 		+ "			 		 (select item.orderID,count(itemName) as qitem from item group by orderID)  as dataT\r\n"
			 		+ "			 		 inner join  (select orderID, date from status where approvalStatus='yes') as statusTem\r\n"
			 		+ "			 		 on statusTem.orderID=dataT.orderID) as temData on temData.orderID=name.orderID;";
	
			 
				return this.jdbcTemplate.queryForList(url);
			 
		 }
//		 this method will display orders information address on shipping page;
		 public List addressOrderInfo(int orderId)
		 {
			 String url="select orderID,Temdata.username,firstName,lastName,phoneNumber,email, country, PO_box, streetName, city, province, residentialAddress\r\n"
			 		+ "from\r\n"
			 		+ "(select username,firstName,lastName,phoneNumber, email,country, PO_box, streetName, city, province, residentialAddress\r\n"
			 		+ "from address join customer on customer.addressID=address.addressID) as Temdata\r\n"
			 		+ "inner join \r\n"
			 		+ "(select orderTable.orderID,username\r\n"
			 		+ " from orderTable\r\n"
			 		+ " where orderTable.orderID = (select orderID from status where ( orderID=? and fulfillmentStatus='yes' and shipmentStatus is null))) as orderTem \r\n"
			 		+ "on orderTem.username=Temdata.username ";
			 return this.jdbcTemplate.queryForList(url,orderId);
		 }
		
		 public List ReadOrders()
		 {
			 String url="select orderID,Temdata.username,firstName,lastName,country, city,orderDate,deliveryStatus,currentLocation\r\n"
			 		+ "			 		from\r\n"
			 		+ "			 		(select username,firstName,lastName,country,city\r\n"
			 		+ "			 		from address join customer on customer.addressID=address.addressID) as Temdata\r\n"
			 		+ "			 		inner join \r\n"
			 		+ "			 		(select orderTable.orderID,username,orderDate,deliveryStatus,currentLocation\r\n"
			 		+ "			 		 from orderTable\r\n"
			 		+ "                     inner join status on orderTable.orderID= status.orderID\r\n"
			 		+ "			 		   where (fulfillmentStatus='yes' and (deliveryStatus is null or deliveryStatus='no') and shipmentStatus='yes' )) as orderTem \r\n"
			 		+ "			 		on orderTem.username=Temdata.username ";
			 return this.jdbcTemplate.queryForList(url);
		 }
//		method taking records to inventory management page
		 public List getInventoryRecord()
		 {
			 String url="select itemType ,sum(quantityInStock) as quantity,location\r\n"
			 		+ "from item\r\n"
			 		+ "group by itemtype,location";
			 return this.jdbcTemplate.queryForList(url);
		 }
		 
			public Map<String, Object> VerifyEmp(int idParam ) {
				 // SELECT statement
				 String query = "select password,position,firstName,lastName from passwordEmp inner join employee on \r\n"
				 		+ "employee.SSN=passwordEmp.SSN where passwordEmp.SSN=?";
				 // Search execution
				 Map<String, Object> verify= jdbcTemplate.queryForMap(query , idParam );
				 return verify ;
				 }
			
			public Map<String, Object> customerVerifyEmp(String username ) {
				 // SELECT statement
				 String query = "select username, firstName, lastName,password\r\n"
				 		+ "from customer inner join passwordtable on passwordtable.Users=customer.username\r\n"
				 		+ "where customer.username=?";
				 // Search execution
				 Map<String, Object> verify= jdbcTemplate.queryForMap(query , username );
				 return verify ;
				 }
		 
			
			public Map<String, Object> suppLierVerifyEmp(String username ) {
				 // SELECT statement
				 String query = "select CompanyName, password\r\n"
				 		+ "from supplier inner join passwordtable on passwordtable.Users=supplier.CompanyName\r\n"
				 		+ "where supplier.CompanyName=?";
				 // Search execution
				 Map<String, Object> verify= jdbcTemplate.queryForMap(query , username );
				 return verify ;
				 }
			
			 public List updateCustomerOrder(String username)
			 {
				 String url="select   itemID, item.orderID, itemName,\r\n"
				 		+ " category,\r\n"
				 		+ " quantityInStock,\r\n"
				 		+ " location,Temdata.companyName,orderDate,country,\r\n"
				 		+ " streetName, city, province, \r\n"
				 		+ " residentialAddress,currentLocation\r\n"
				 		+ " \r\n"
				 		+ " from \r\n"
				 		+ "(select status.orderID, companyName,orderDate,country,streetName, city, province, residentialAddress,currentLocation \r\n"
				 		+ "\r\n"
				 		+ "from\r\n"
				 		+ "(select orderID, companyName,orderDate,country,streetName, city, province, residentialAddress\r\n"
				 		+ "from orderTable \r\n"
				 		+ "inner join \r\n"
				 		+ "(select username,country,streetName, city, province, residentialAddress from address\r\n"
				 		+ "inner join customer on customer.addressID=address.addressID where username=?)\r\n"
				 		+ "as addTem\r\n"
				 		+ "on addTem.username= ordertable.username)\r\n"
				 		+ "\r\n"
				 		+ "as orderInfTem\r\n"
				 		+ "\r\n"
				 		+ "inner join status on status.orderID=orderInfTem.orderID where status.confirmationStatus is null) \r\n"
				 		+ "as Temdata\r\n"
				 		+ "\r\n"
				 		+ "inner join item on item.orderID=Temdata.orderID";
				 return this.jdbcTemplate.queryForList(url,username);
			 }

			 public void PaymentUpdateStatus(int orderID) {
				    
				    String queryStatus = "UPDATE status SET paid='yes' WHERE orderID = ?";

				    jdbcTemplate.update(queryStatus,orderID);
				}
	
}
