package accountpayableSys;

import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class InvoiceExtraction {
	
	private int invoiceNumber;
	private boolean receiptyVerifyStatus;
	

	 public int getInvoiceNumber() {
		return invoiceNumber;
	}


	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public boolean isReceiptyVerifyStatus() {
		return receiptyVerifyStatus;
	}


	public void setReceiptyVerifyStatus(boolean receiptyVerifyStatus) {
		this.receiptyVerifyStatus = receiptyVerifyStatus;
	}
	
	public String markInvoice()
	{
		String read="read";
		return read;
	}
	

	public String[] retrieveInvoice(int orderId,String itemName,int quatinty,
			Date purchaseDate,String description,String category,String packageName,
			int weight,String customerId,String supplierName)
	{
		String[] retrievedData=new String[10];
		
		return retrievedData;
		
	}


	
}
