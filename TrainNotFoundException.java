package com.train.ticket;

public class TrainNotFoundException extends Exception {
	@Override
	public String getMessage() {
		return "Train with given train number doesn't exist !!!" ;
	}
}
