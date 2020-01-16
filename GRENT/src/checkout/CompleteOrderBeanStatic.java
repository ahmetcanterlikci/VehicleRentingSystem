package checkout;

public abstract class CompleteOrderBeanStatic {
	private static String orderNoString; 

	public static String getOrderNoString() {
		return orderNoString;
	}
	
	public static void setOrderNoString(String orderNoString) {
		CompleteOrderBeanStatic.orderNoString = orderNoString;
	}
}
