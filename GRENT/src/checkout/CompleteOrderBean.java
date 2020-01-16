package checkout;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class CompleteOrderBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String orderNumber;
	
	@PostConstruct
	public void init() {
		orderNumber = CompleteOrderBeanStatic.getOrderNoString();
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

}
