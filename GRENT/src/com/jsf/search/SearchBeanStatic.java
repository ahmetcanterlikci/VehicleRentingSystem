package com.jsf.search;

import java.util.Date;

/**
 * Storage class for the Search bean. Stores the data which comes from search panel on the homepage if requested.
 */
public abstract class SearchBeanStatic {
	private static String receivingOffice;
	private static String returningOffice;
	private static Date receivingDate;
	private static Date returningDate;
	
	public static Date getReceivingDate() {
		return receivingDate;
	}
	public static String getReceivingOffice() {
		return receivingOffice;
	}
	public static Date getReturningDate() {
		return returningDate;
	}
	public static String getReturningOffice() {
		return returningOffice;
	}
	public static void setReceivingDate(Date receivingDate) {
		SearchBeanStatic.receivingDate = receivingDate;
	}
	public static void setReceivingOffice(String receivingOffice) {
		SearchBeanStatic.receivingOffice = receivingOffice;
	}
	public static void setReturningDate(Date returningDate) {
		SearchBeanStatic.returningDate = returningDate;
	}
	public static void setReturningOffice(String returningOffice) {
		SearchBeanStatic.returningOffice = returningOffice;
	}

}
