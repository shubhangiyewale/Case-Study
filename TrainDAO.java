package com.train.ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainDAO {
	
//	public static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
//	public static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
//	public static final String USERNAME = "hr";
//	public static final String PASSWORD = "shubhangi4";
//	
//	
	public Train findTrain(int trainNo) throws SQLException, ClassNotFoundException {
//		Train train= null;
//		Class.forName(new TrainDAO().DRIVER_NAME);
//		Connection connection = DriverManager.getConnection(new TrainDAO().DB_URL, new TrainDAO().USERNAME, new TrainDAO().PASSWORD);
//		Statement statement = connection.createStatement();
//		String query = "SELECT * FROM TRAINS WHERE TRAIN_NO = "+ trainNo ;
//		ResultSet resultSet = statement.executeQuery(query);
//		
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "shubhangi4");
		Statement statement = connection.createStatement();
		String query = "SELECT * FROM TRAINS WHERE TRAIN_NO = " + trainNo ;
		ResultSet trainSet = statement.executeQuery(query);
		boolean flag = trainSet.next();
		try {
			if(!flag) {
				throw new TrainNotFoundException();
			}
		}catch(TrainNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		if(flag) {

			String trainName = trainSet.getString(2);
			String source = trainSet.getString(3);
			String destination = trainSet.getString(4);
			double ticketPrice = trainSet.getDouble(5);
			return new Train(trainNo, trainName, source, destination, ticketPrice);
		}
		connection.close();
		return null;
			
			
	}
}