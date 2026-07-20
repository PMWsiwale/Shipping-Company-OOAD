package shippingSys;

public class CancelledProduct extends StolkProduct {
	
	;
	private String reasonForcancelling;
	private String Datecancelled;
	public String getReasonForcancelling() {
		return reasonForcancelling;
	}
	public void setReasonForcancelling(String reasonForcancelling) {
		this.reasonForcancelling = reasonForcancelling;
	}
	public String getDatecancelled() {
		return Datecancelled;
	}
	public void setDatecancelled(String datecancelled) {
		Datecancelled = datecancelled;
	}
	

}
