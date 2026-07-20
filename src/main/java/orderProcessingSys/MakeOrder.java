package orderProcessingSys;

import java.math.BigDecimal;
import java.sql.Date;

public class MakeOrder {
	
	private int orderId;
	private String itemName;
	private int quatinty;
	private Date purchaseDate;
	private String description;
	private String category;
	private String packageName;
	private int weight;
	private String customerId;
	private String supplierName;
	private String email;
	private BigDecimal unitPrice;
	
public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice2) {
		this.unitPrice = unitPrice2;
	}
public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//	getters and setter methods
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuatinty() {
		return quatinty;
	}
	public void setQuatinty(int quatinty) {
		this.quatinty = quatinty;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	@Override
	public String toString() {
		return "MakeOrder [orderId=" + orderId + ", itemName=" + itemName + ", quatinty=" + quatinty + ", purchaseDate="
				+ purchaseDate + ", description=" + description + ", category=" + category + ", packageName="
				+ packageName + ", weight=" + weight + ", customerId=" + customerId + ", supplierName=" + supplierName
				+ ", email=" + email + ", unitPrice=" + unitPrice + "]";
	}
	
	
	

}
