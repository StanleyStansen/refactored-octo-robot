package discount.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DataHandler implements DataHandlerAPI {

	@Override
	public Customer getCustomer(int id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		Customer customer = (Customer) session.get(Customer.class, id);
		session.close();
		return customer;
	}

	@Override
	public boolean checkDiscount(Customer c) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		System.out.println("------------------------------------------------");
		System.out.println("CHECK V1: Umsatz des Kunden mehr als 10% der aggregierten Umsätze aller Kunden?");

		double totalTurnover = totalTurnover();

		System.out.println("10% der aggregierten Umsätze aller Kunden = " + (totalTurnover * 10 / 100));
		System.out.println("------------------------------------------------");
		if (turnoverOfCustomer(c) > totalTurnover * 10 / 100) {
			System.out.println("Check V1 bestanden, der Kunde erhält den Rabatt.");
			return true;
		} else {
			System.out.println("KEIN Rabatt");

			System.out.println("------------------------------------------------");
			System.out.println("CHECK V2: Umsatz des Kunden mehr als 20% des Umsatz seiner peer-group?");

			double turnoverOfPeergroup = turnoverOfPeergroup(c);

			System.out.println("Gesamtumsatz der peer group = " + turnoverOfPeergroup);
			System.out.println("20% des Umsatz der peer group = " + (turnoverOfPeergroup * 20 / 100));
			System.out.println("------------------------------------------------");
			if (turnoverOfCustomer(c) > turnoverOfPeergroup * 20 / 100) {
				System.out.println("Herzlichen Glückwunsch zum Rabatt!");
				return true;
			} else {
				System.out.println("KEIN Rabatt");
				System.out.println("------------------------------------------------");
				return false;
			}
		}
	}

	/** Nötige Funktionen für Verkäufer **/
	private static void showAllCustomers() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<Customer> customerList = session.createQuery("from Customer").list();
		session.close();

		Customer[] customerArray = new Customer[customerList.size()];
		customerList.toArray(customerArray);
		for (Customer customer : customerArray) {
			System.out.println(customer.toString());
		}
	}

	private List<Customer> listCustomers() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<Customer> customers = session.createQuery("from Customer").list();
		session.close();
		return customers;
	}

	private List<Order> listOrdersOfCustomer(Customer customer) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<Order> orders = session.createQuery("from Order where customerId = '" + customer.getId() + "'").list();

		session.close();
		return orders;
	}

	private double totalTurnover() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		String hql = "SELECT SUM(turnover) FROM Order";
		Query query = session.createQuery(hql);
		List<Double> results = query.list();

		session.close();
		return (double) results.get(0);
	}

	private double turnoverOfCustomer(Customer customer) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		String hql = "SELECT SUM(turnover) FROM Order WHERE customerId = " + customer.getId();
		Query query = session.createQuery(hql);
		List<Double> results = query.list();

		session.close();
		return (double) results.get(0);
	}

	private double turnoverOfPeergroup(Customer customer) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		Calendar calendarMax = Calendar.getInstance();
		Calendar calendarMin = Calendar.getInstance();
		calendarMax.setTime(customer.getBirthday());
		calendarMax.add(Calendar.YEAR, 5);
		calendarMin.setTime(customer.getBirthday());
		calendarMin.add(Calendar.YEAR, -5);

		java.util.Date utilDateMax = calendarMax.getTime();
		java.sql.Date sqlDateMax = new java.sql.Date(utilDateMax.getTime());
		java.util.Date utilDateMin = calendarMin.getTime();
		java.sql.Date sqlDateMin = new java.sql.Date(utilDateMin.getTime());

		System.out.println("Geburtstag: " + customer.getBirthday());
		System.out.println("Peergroup (max): " + sqlDateMax);
		System.out.println("Peergroup (min): " + sqlDateMin);

		// Liste aller Kunden innerhalb der Peergroup
		List<Customer> customersInPeerGroup = session
				.createQuery("from Customer WHERE birthday >='" + sqlDateMin + "' AND birthday <= '" + sqlDateMax + "'")
				.list();
		System.out.println("Anzahl Kunden in Peergroup: " + customersInPeerGroup.size());

		// Gesamtumsatz der Liste erreichen
		double turnover = 0;
		for (Object c : customersInPeerGroup) {
			turnover = turnover + turnoverOfCustomer(getCustomer(25));
		}

		session.close();
		return turnover;
	}

}
