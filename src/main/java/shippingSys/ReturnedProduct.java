package shippingSys;

public class ReturnedProduct extends StolkProduct{
	
	private String reasonReturnement;
	private String DateReturened;
	public String getReasonReturnement() {
		return reasonReturnement;
	}
	public void setReasonReturnement(String reasonReturnement) {
		this.reasonReturnement = reasonReturnement;
	}
	public String getDateReturened() {
		return DateReturened;
	}
	public void setDateReturened(String dateReturened) {
		DateReturened = dateReturened;
	}
	
	
}
