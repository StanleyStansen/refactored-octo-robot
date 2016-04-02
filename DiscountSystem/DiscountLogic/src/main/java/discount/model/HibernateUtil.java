package discount.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
        
    }

}

/*
http://www.javatpoint.com/example-to-create-hibernate-application-in-eclipse-ide
http://blog.sencide.com/2011/03/hibernate-tutorial-for-beginners.html
http://www.mastertheboss.com/jboss-frameworks/hibernate-jpa/quickstart-tutorials-hibernate-jpa/hibernate-tutorial-with-eclipse?showall=&start=1
http://www.mastertheboss.com/jboss-frameworks/hibernate-jpa/quickstart-tutorials-hibernate-jpa/hibernate-tutorial-with-eclipse?showall=&start=1
*/