package com.train.ticket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import java.util.TreeMap;

public class Ticket {
	
	public static int counter = 100 ;
	private String pnr ;
	private LocalDate travelDate;
	private Train train;
	private TreeMap<Passengers, Integer> passengers = new TreeMap<>();
	
	public Ticket(Train train, LocalDate travelDate) {
		LocalDate today = LocalDate.now();
		boolean flag1 = today.isBefore(travelDate);
		try {
			if(!flag1) {
				throw new currentDate();
			}
		}catch (currentDate e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		this.train = train;
		this.travelDate = travelDate;
	}
	
	public Ticket() {
		travelDate=null;
		train=null;
	}
	

    public String generatePNR() throws IOException {
    	StringBuilder sb = new StringBuilder("");
    	this.pnr = String.valueOf(this.getTrain().getSource().charAt(0))+String.valueOf(this.getTrain().getDestination().charAt(0))+"_"+this.travelDate.getYear()+String.format("%02d",this.travelDate.getMonthValue())+String.format("%02d",this.travelDate.getDayOfMonth())+"_"+counter;
        String str = String.valueOf(counter);
        System.out.println(counter+" "+str);
        return this.pnr ;
    }
    
    private double calculatePassengerFare(Passengers p) {
    	double fare = this.getTrain().getTicketPrice();
		if(p.getAge()<=12)
			fare = fare*0.5;
		else if (p.getAge()>=60)
			fare = fare*0.6 ;
		else {
			if(p.getGender() == 'F')
				fare = fare*0.75;
		}
		return fare ;
	}
    
    public void addPassenger(String name, int age, char gender) {
    	Passengers p = new Passengers(name,age,gender);
    	double fare= calculatePassengerFare(p);
    	this.passengers.put(p, (int) fare);
    	
    
    }
    	
    public double calculateTotalTicketPrice() {
		int sum = 0;
		for(Passengers p:this.passengers.keySet()) {
			sum = sum + this.passengers.get(p);
		}
		return sum;
    }
    
    public StringBuilder generateTicket() throws IOException {
    	StringBuilder sb = new StringBuilder("");
    	sb.append("PNR: "+this.generatePNR()+"\n");
    	sb.append("Train No: "+this.train.getTrainNo()+"\n");
    	sb.append("From: "+this.train.getSource()+"\n");
    	sb.append("To: "+this.train.getDestination()+"\n");
    	sb.append("Travel Date: " + this.travelDate.getDayOfMonth()+ "/"+ this.travelDate.getMonthValue()+ "/"+ this.travelDate.getYear());
    	sb.append("Passengers: "+ "\n");
    	sb.append("Name\tAge\tGender\tFare\n");
    	for(Passengers p : this.passengers.keySet()) {
    		sb.append("\t"+p.getName()+"\t"+p.getAge()+"\t"+p.getGender()+"\t"+calculatePassengerFare(p));
    	}
    	sb.append("Total Fare: "+this.calculateTotalTicketPrice());
    	return sb;
    }
    
    public void writeTicket() throws Exception {
    	String str = this.generateTicket().toString();
    	String fileName = this.pnr + ".txt";
    	FileOutputStream fos = new FileOutputStream(fileName);
    	PrintWriter pr = new PrintWriter(fos);
		pr.write(str);
		pr.close();
    }

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Ticket.counter = counter;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public TreeMap<Passengers, Integer> getPassengers() {
		return passengers;
	}

	public void setPassengers(TreeMap<Passengers, Integer> passengers) {
		this.passengers = passengers;
	}
    
    
    
	
	
	
	
	
	

}
