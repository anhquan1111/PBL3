package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Payment {
	  private String paymentID;
	  private String paymentCustomerID;
	  private String paymentTicketID; 
      private Date paymentDate;
	  private double paymentAmount;
	  private PaymentMethod paymentMethod;
	  private PaymentStatus paymentStatus;
}	
