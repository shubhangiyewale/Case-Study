package com.train.ticket;

public class currentDate extends Exception {
	@Override
	public String getMessage() {
		return "Travel date should be after current date !!!" ;
	}
	

}
