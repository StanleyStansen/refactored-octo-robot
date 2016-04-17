package discount.model;  

import java.util.Date;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  


public class TestHibernateWithDB {

	public static void main(String[] args) {
		System.out.println("AUSKOMMENTIERT. Das Skript war für das Testen der eigenen Datenbank gedacht und löscht teilweise Tabellen. NICHT mit der Datenbank von Jens verwenden!");
		/*
		System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        
		// Datenbank leeren
		Query query = session.createQuery("DELETE FROM Customer");
		int result = query.executeUpdate();
		System.out.println(result + "gelöschte Datensätze. Die Tabelle Customer ist nun leer.");
		
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
        */
	}

}
