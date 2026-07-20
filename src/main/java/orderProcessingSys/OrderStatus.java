package orderProcessingSys;

public class OrderStatus {
//	all these method will passed to order processing system to display all the details , through the help of service class
	private int orderId;
	private String approvedStatus;
	private String receiveStatus;
	private String stockStatus;
	private String requestStatus;
	private String shippedStatus;
	private String fulfilled ;
	private String deliveryStatus;
	private String customerConfirmedStatus;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public String getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getShippedStatus() {
		return shippedStatus;
	}
	public void setShippedStatus(String shippedStatus) {
		this.shippedStatus = shippedStatus;
	}
	public String getFulfilled() {
		return fulfilled;
	}
	public void setFulfilled(String fulfilled) {
		this.fulfilled = fulfilled;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getCustomerConfirmedStatus() {
		return customerConfirmedStatus;
	}
	public void setCustomerConfirmedStatus(String customerConfirmedStatus) {
		this.customerConfirmedStatus = customerConfirmedStatus;
	}
	
	
	

}
