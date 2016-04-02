package discount.model;  

import java.sql.Date;
import java.util.*;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  

public class InitializeDatabase {

	public static void main(String[] args) {
		Calendar myCal = new GregorianCalendar();                        // GregorianCalendar mit aktueller Zeit
		
		//creating configuration object  
	    Configuration cfg=new Configuration();
	    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
	      
	    //creating seession factory object  
	    SessionFactory factory=cfg.buildSessionFactory();  
	      
	    //creating session object  
	    Session session=factory.openSession();  
	      
	    //creating transaction object  
	    Transaction t=session.beginTransaction();  
	          
	    Customer customer1 = new Customer("Max","Mustermann", (Date) myCal.getTime());
	      
	    session.persist(customer1);//persisting the object  
	      
	    t.commit();//transaction is committed  
	    session.close();  
	      
	    System.out.println("successfully saved");  
	      
	}

}
