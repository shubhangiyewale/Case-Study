package com.train.ticket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TicketApplication {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Train Number: ");
		int trainNo = sc.nextInt();
		TrainDAO trainDAO = new TrainDAO();
		Train train = null;
		try {
			train = trainDAO.findTrain(trainNo);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		sc.nextLine();
		System.out.println("Enter the travel date: ");
		String[] arr = sc.nextLine().split("/");
		LocalDate travelDate = LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0]));
		LocalDate Today = LocalDate.now();
		
		Ticket ticket = new Ticket(train, travelDate);
		System.out.println("Enter the number of pasengers: ");
		int n = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			System.out.println("Enter the name of the passenger" + (i+1));
			String name = sc.nextLine();
			
			System.out.println("Enter the age of the passenger" + (i+1));
			int age = sc.nextInt();
			
			System.out.println("Enter the gender of the passenger" + (i+1));
			char G = sc.next().charAt(0);
			
			ticket.addPassenger(name, age, G);
			
		}
		try {
			ticket.writeTicket();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(ticket.getPnr());
	}
}