package orderProcessingSys;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceOPS {
	
	@Autowired
	private OrderRequest repository;
	
	@Autowired 
	private OrderTracking statusRepository;
	
	
	
	public List RetrieveOrder() {
		List list= repository.RetrieveOrder();
		return list;
	}
	
	public List getMoreOrderStatus()
	{
		
		
		return this.statusRepository.getMoreOrderStatus();
	}
	
	public  void sendRequest(int orderId) {
		repository.sendRequest(orderId);
	}
	
//	this method will be displaying the status of an order on the page of the orderprocessing System
	public OrderStatus getOrderStatus(int orderId)
	{
		
		Map<String, Object> map=statusRepository.getOrderStatus(orderId);
		OrderStatus status =new OrderStatus();
		
		int orderID=(int) map.get("orderId");
		 String approvedStatus=(String) map.get("approvedStatus");
		String receiveStatus=(String) map.get("receiveStatus");
	     String stockStatus=(String) map.get("stockStatus");
		String shippedStatus=(String) map.get("shippedStatus");
		String fulfilled=(String) map.get("fulfilled");
		String deliveryStatus=(String) map.get("deliveryStatus");
		String customerConfirmedStatus=(String) map.get("customerConfirmedStatus");
		
		status.setApprovedStatus(approvedStatus);
		status.setReceiveStatus(receiveStatus);
		status.setStockStatus(stockStatus);
		status.setShippedStatus(shippedStatus);
		status.setFulfilled(fulfilled);
		status.setDeliveryStatus(deliveryStatus);
		status.setCustomerConfirmedStatus(customerConfirmedStatus);
		
		return status;
	}

}
