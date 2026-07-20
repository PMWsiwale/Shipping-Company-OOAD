package accountpayableSys;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orderProcessingSys.MakeOrder;

@Service
@Transactional
public class ServiceAPS {
	
	@Autowired
	private RepositoryAPS repositoryAPS;
	
	@Autowired
 	private InvoiceRecievedRepository invoicerepol;
	
	//this methode will be called in controller class when the Receive clear enters the orderId

	public List RetrieveInvoice( )
	{
		return this.repositoryAPS.ManagerInvoice();
	}
	public  List extractALLInvoice()
	{
		List listInvoice=repositoryAPS.extractALLInvoice();
		return listInvoice;
	}
//	This method must be called in the invoice extracton controller after been approve by the manager on the page
     public void invoiceStatusUpdate(int orderId,String status,Date approvedDate)
     {
    	 repositoryAPS.UpdateStatus(orderId, status, approvedDate);
    	 
     }
     
//      this method should be called in the controller class after the invoice is read by the clerk
 	public void markReadInvoice(int orderId) {
 		 repositoryAPS.markReceivedInvoice(orderId);
 		
 	}
 	
 	
 	public void inserOrder(MakeOrder order) {
 		invoicerepol.insertOrder(order);
 		
 	}
}
