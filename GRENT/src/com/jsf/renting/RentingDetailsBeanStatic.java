package com.jsf.renting;

import com.jsf.entity.Renting;

public abstract class RentingDetailsBeanStatic {
	private static Renting renting;
	
	public static Renting getRenting() {
		return renting;
	}
	
	public static void setRenting(Renting renting) {
		RentingDetailsBeanStatic.renting = renting;
	}

}
