package shippingSys;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import accountpayableSys.InvoiceExtraction;
import orderProcessingSys.MakeOrder;
import users.Login;

@Service
@Transactional
public class ServiceShippingSys {
	
	@Autowired
	private UpdateInventory repository;
	public void StockNewProduct(NewProduct stolk)
	{
		repository.stolkProduct(stolk);
		
		
			
	}
	
	public void cancelledProduct(CancelledProduct stolk)
	{
		repository.stolkProduct(stolk);
			
	}
	
	public void returenedProduct(ReturnedProduct stolk)
	{
		repository.stolkProduct(stolk);
			
	}
	
//	This method will be displaying requested order on the page of Fulfiller clerk
	public List getRequest()
	{
		List list =repository.getFullfilOder();
		return list;
	}
	
//	updating status for fulfilled order
	
	public void fulfilledStatus(FulfilOrder orderStatus)
	{
		String typeOfStatus="filledOrder";
		String valueStatus="";
		boolean status=orderStatus.isFulfilmentStatus();
		if(status=true)
		{
			valueStatus="yes";
		}
		repository.UpdateStatus(orderStatus.getOrderId(), valueStatus, orderStatus.getDateOrderFilled(), typeOfStatus);
		
	}
//	This method will be deleting fulfilled order soon after the order is fulfilled by the filler on the page :controller
	public void RemoveFulfilled(String orderId) {
		
		repository.DeleteFulfilledOrder(orderId);
	}
//	method to the shipmet page
   public List ViewOrder()
   {
	   List order=repository.ViewOrder();
	   return order;
	   
   }
//   converting data from the database into a string to set as a desitination see the shippement page
   public void getDestination( ShippeProduct dest,int customerId)
	 {
		
		 Map<String, Object> map = repository.getDestination(customerId);
		  String city=(String) map.get("city");
		 String  province=(String) map.get("province");
		 String country=(String) map.get("country");
		 String place=(String) map.get("place");
		 String code=(String) map.get("code");
		 
		 String destination=city + " "+province +" " + country +" " + place + " "+code;
		 dest.setDestination(destination); 
		 
	 }
   
//   get Details from ship page
   public void shippeOrderInfo(ShippeProduct ship)
   {
	   
	   repository.shippeOrderInfo(ship.getOrderId(),ship.getTransportType(),ship.getTransportDetails(),ship.getDateshipped());
   }
//   transitStatus method will be used on the shipped page

   public void transitStatus(ShippeProduct orderStatus)
	{
		String typeOfStatus="transitStatus";
		String valueStatus="";
		boolean status=orderStatus.isTransitStatus();
		if(status=true)
		{
			valueStatus="yes";
		}
		repository.UpdateStatus(orderStatus.getOrderId(), valueStatus, orderStatus.getDateshipped(), typeOfStatus);
		
	}
   
// delivaryStatus;will be used on the page
   public void delivaryStatus(ShippeProduct orderStatus)
 	{
 		String typeOfStatus="delivered";
 		String valueStatus="";
 		@SuppressWarnings("unused")
		boolean status=orderStatus.isDelivaryStatus();
 		if(status=true)
 		{
 			valueStatus="yes";
 		}
 		repository.UpdateStatus(orderStatus.getOrderId(), valueStatus, orderStatus.getDateshipped(), typeOfStatus);
 		
 	}
   
//   public Map<String, Object> RetrieveInvoice(int orderId ){
//	   Map<String, Object> order=repository.RetrieveInvoice(orderId);
//	   return order;
//   }
   
	public InvoiceExtraction GetInvoiceToReceiver(int idParam )
	{
		Map<String, Object> map=repository.GetInvoiceToReceiver(idParam );
		
		
	    
		String itemName=(String) map.get("itemName");
		int quatinty=(int) map.get("quantityInStock");
		Date purchaseDate=(Date) map.get("orderDate");
		String description=(String) map.get("description");
		String category=(String) map.get("category");
		String packageName=(String) map.get("unitPrice");
		String customerId=(String) map.get("username");
		String descriptionn=(String) map.get("description");
		String supplierName=(String) map.get("companName");
		
		
		
		InvoiceExtraction dataInvoice=new InvoiceExtraction();
		
//		dataInvoice.retrieveInvoice(idParam, itemName, quatinty, purchaseDate, description, category, packageName, weight, customerId, supplierName);
		
		return dataInvoice;
	}
	
	public MakeOrder giveReseciever(int idParam, MakeOrder search) {
	    Map<String, Object> map = repository.GetInvoiceToReceiver(idParam);
	    
	    // Check if map is null or empty
	    if (map == null || map.isEmpty()) {
	        throw new RuntimeException("No order found with ID: " + idParam);
	    }

	    // Safe extraction with null checks
	    String itemName = map.containsKey("itemName") ? (String) map.get("itemName") : null;
//	    Integer quantity = map.containsKey("quantityInStock") ? (Integer) map.get("quantityInStock") : null;
	    Date purchaseDate = map.containsKey("orderDate") ? (Date) map.get("orderDate") : null;
	    Integer orderId = map.containsKey("orderID") ? (Integer) map.get("orderID") : null;
	    String category = map.containsKey("category") ? (String) map.get("category") : null;
	    BigDecimal unitPrice = map.containsKey("unitPrice") ? (BigDecimal) map.get("unitPrice") : null;
	    String customerId = map.containsKey("username") ? (String) map.get("username") : null;
	    String description = map.containsKey("description") ? (String) map.get("description") : null;
	    String supplierName = map.containsKey("companyName") ? (String) map.get("companyName") : null;

	    // Set values only if they're not null
	    if (itemName != null) search.setItemName(itemName);
	    if (orderId != null) search.setOrderId(orderId);
//	    if (quantity != null) search.setQuatinty(quantity);
	    if (purchaseDate != null) search.setPurchaseDate(purchaseDate);
	    if (description != null) search.setDescription(description);
	    if (category != null) search.setCategory(category);
	    if (unitPrice != null) search.setUnitPrice(unitPrice);
	    if (customerId != null) search.setCustomerId(customerId);
	    if (supplierName != null) search.setSupplierName(supplierName);

	    return search;
	}
	public Login  VerifyEmp(int idParam ,Login employee) {
		
		Map<String, Object> map = repository.VerifyEmp(idParam);
		 // Check if map is null or empty
	    if (map == null || map.isEmpty()) {
//	        throw new RuntimeException("No order found with ID: " + idParam);
	    }
	    else {
	    	
	    	  // Safe extraction with null checks
		    String password = map.containsKey("password") ? (String) map.get("password") : null;
		    String position = map.containsKey("position") ? (String) map.get("position") : null;
		    String firstname = map.containsKey("firstName") ? (String) map.get("firstName") : null;
		    String lastname = map.containsKey("lastName") ? (String) map.get("lastName") : null; 
	    	
		    // Set values only if they're not null
		    if (password != null) employee.setPassword(password);
		    if (position != null) employee.setPosition(position);
		    if (firstname != null) employee.setFirstName(firstname);
		    if (lastname != null) employee.setLastName(lastname);
	    }
		
		
		return employee;
	}
	
	 public List GetInvoiceToReceiverList(int id)
	 {
		 return this.repository.GetInvoiceToReceiverList(id);
	 }
//	 approved date from receiver when the receiver approves the order.
	 public void UpdateStatus(int orederId, String statusType)
	 {
		 LocalDate currentDate= LocalDate.now();
		 Date approvedDate=Date.valueOf(currentDate);
		 
		 
		 repository.UpdateStatus(orederId, statusType,approvedDate);
	 }
	 
	 public void UpdateStatusWithNoDate(int orderId, String statusType) {
		 repository.UpdateStatus(orderId, statusType);
	 }
//	 method which is returning all the orders that has been verified by the receiver clerk
	 public List ViewVeriFiedOrder()
	 {
		 return this.repository.ViewVeriFiedOrder();
	 }
	 
//	 orders from fufiler clerk will be shown
	 public List getFullfilOder()
	 {
		 return this.repository.getFullfilOder();
	 }
//	 this methode will be displayed on the page of viewFilled orders searched by query
	 public List ViewFilledOrder(int orderId) {
		 return this.repository.ViewFilledOrder(orderId);
	 }
	 public List SummaryViewFilledOrder(int orderId) {
		 return this.repository.SummaryViewFilledOrder(orderId);
	 }
	 public List addressOrderInfo(int orderId) {
		 return this.repository.addressOrderInfo(orderId);
	 }
	 public List ReadOrders() {
		 return this.repository.ReadOrders();
	 }
	 public void UpdateStatusLocation(ShippeProduct location) {
		 repository.UpdateStatusLocation(location);
	 }
	 public List getInventoryRecord() {
		 return this.repository.getInventoryRecord();
	 }
	
	 
	 
	 public Login  customerVerify(String username ,Login customer) {
			
			Map<String, Object> map = repository.customerVerifyEmp(username);
			 // Check if map is null or empty
		    if (map == null || map.isEmpty()) {
//		        throw new RuntimeException("No order found with ID: " + idParam);
		    }
		    else {
		    	
		    	  // Safe extraction with null checks
			    String password = map.containsKey("password") ? (String) map.get("password") : null;
			    String position = map.containsKey("username") ? (String) map.get("username") : null;
			    String firstname = map.containsKey("firstName") ? (String) map.get("firstName") : null;
			    String lastname = map.containsKey("lastName") ? (String) map.get("lastName") : null; 
		    	
			    // Set values only if they're not null
			    if (password != null) customer.setPassword(password);
			    if (position != null) customer.setPosition(position);
			    if (firstname != null) customer.setFirstName(firstname);
			    if (lastname != null) customer.setLastName(lastname);
		    }
		    
		    return customer;
			
	 }
	 
	 public Login  supplierVerify(String username ,Login supllier) {
			
			Map<String, Object> map = repository.suppLierVerifyEmp(username);
			 // Check if map is null or empty
		    if (map == null || map.isEmpty()) {
//		        throw new RuntimeException("No order found with ID: " + idParam);
		    }
		    else {
		    	
		    	  // Safe extraction with null checks
			    String password = map.containsKey("password") ? (String) map.get("password") : null;
			    String comp = map.containsKey("CompanyName") ? (String) map.get("CompanyName") : null;
			 
		    	
			    // Set values only if they're not null
			    if (password != null) supllier.setPassword(password);
			    if (comp != null) supllier.setUsername(username);
			   
			   
		    }
		    
		    return supllier;
			
	 }
	 
	 public List updateCustomerOrder(String username) {
		return this.repository.updateCustomerOrder(username);
	 }
	
	 public void confirmOrderReceived(int orderId)
	 {
		 String status="confirmationStatus";
		 repository.UpdateStatus(orderId,status);
	 }
	 
	 public void PaymentUpdateStatus(int orderID) {
		    
		

		    repository.PaymentUpdateStatus(orderID);
		}
}
