package orderProcessingSys;

public class ReceiveProduct{
	private int orderId;
	private boolean confirmRecievedProduct;

	private boolean consolidatedStatus;
	private String descrepancyname;
	private String descrepancyDescription;
	
	
public String getDescrepancyname() {
		return descrepancyname;
	}
	public void setDescrepancyname(String descrepancyname) {
		this.descrepancyname = descrepancyname;
	}
	public String getDescrepancyDescription() {
		return descrepancyDescription;
	}
	public void setDescrepancyDescription(String descrepancyDescription) {
		this.descrepancyDescription = descrepancyDescription;
	
	}
//	Method to report a descrepancy of a product
   public String[] generateReport()
   {
	   String[] report=new String[2];
	   report[0]=getDescrepancyname();
	   report[1]= getDescrepancyDescription();
	   return report;
   }
	//	getters and setters
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public boolean isConsolidatedStatus() {
		return consolidatedStatus;
	}
	public void setConsolidatedStatus(boolean consolidatedStatus) {
		this.consolidatedStatus = consolidatedStatus;
	}
//	other methodes
	
//	This Method will be displaying the order details on the Received clerk page as soon as service class is called
	public String[] displayOrderDetails(String[] retrieved)
	{
		
		String[] dataFromInvoice= new String[retrieved.length];
		
		for(int i = 0;i<retrieved.length;i++)
		{
			dataFromInvoice[i]=retrieved[i];
	
		}
		
		return dataFromInvoice;
		
	}
	public boolean isConfirmRecievedProduct() {
		return confirmRecievedProduct;
	}
	public void setConfirmRecievedProduct(boolean confirmRecievedProduct) {
		this.confirmRecievedProduct = confirmRecievedProduct;
	}
	

}
