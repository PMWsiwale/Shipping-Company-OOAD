package shippingSys;

import java.sql.Date;
import java.util.List;

public class ShippeProduct {
	
	private int orderId;
	private boolean transitStatus;
	private boolean delivaryStatus;
	private String transportType;
	private String transportDetails;
	private String destination;
	private Date Dateshipped;
	private String delivared;
	private String currentLocation;
	
	
	public String getDelivared() {
		return delivared;
	}
	public void setDelivared(String delivared) {
		this.delivared = delivared;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public Date getDateshipped() {
		return Dateshipped;
	}
	public void setDateshipped(Date dateshipped) {
		Dateshipped = dateshipped;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public boolean isTransitStatus() {
		return transitStatus;
	}
	public void setTransitStatus(boolean transitStatus) {
		this.transitStatus = transitStatus;
	}
	public boolean isDelivaryStatus() {
		return delivaryStatus;
	}
	public void setDelivaryStatus(boolean delivaryStatus) {
		this.delivaryStatus = delivaryStatus;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public String getTransportDetails() {
		return transportDetails;
	}
	public void setTransportDetails(String transportDetails) {
		this.transportDetails = transportDetails;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "ShippeProduct [orderId=" + orderId + ", transitStatus=" + transitStatus + ", delivaryStatus="
				+ delivaryStatus + ", transportType=" + transportType + ", transportDetails=" + transportDetails
				+ ", destination=" + destination + ", Dateshipped=" + Dateshipped + ", delivared=" + delivared
				+ ", currentLocation=" + currentLocation + "]";
	}
     
	 
	
	

}
