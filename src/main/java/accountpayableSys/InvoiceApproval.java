package accountpayableSys;

import java.sql.Date;

public class InvoiceApproval {
	private int orderId;
	private Date approvedDate;
	private boolean approvalStatus;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public boolean isApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String[] reviewInvoice(String[] retrieved)
	{
		
		String[] dataFromInvoice= new String[retrieved.length];
		
		for(int i = 0;i<retrieved.length;i++)
		{
			dataFromInvoice[i]=retrieved[i];
	
		}
		
		return dataFromInvoice;
		
	}

}
