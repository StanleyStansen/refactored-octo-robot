package discount.model;  

import java.util.Date;
import java.util.*;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  

public class InitializeDatabase {

	public static void main(String[] args) {
		
		System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        System.out.println("Transaktion begonnen");
        
        Customer cust1 = new Customer("Max","Mustermann");
        System.out.println("Order erstellt!");
                
        //Save the Model object
        session.save(cust1);
        System.out.println("Session saved");
        
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Fertig!");

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
        System.out.println("CLOSED!");
        
		/*
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
	    */
	}

}
