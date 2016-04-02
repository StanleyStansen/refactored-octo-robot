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
        
	}

}
