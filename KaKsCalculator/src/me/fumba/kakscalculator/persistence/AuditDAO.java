package me.fumba.kakscalculator.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import me.fumba.kakscalculator.common.HibernateUtil;

/**
 * Performs CRUD operations for the Audit table.
 * 
 * @author Fumbani Chibaka
 *
 */
public class AuditDAO extends HibernateUtil {

	/**
	 * For adding items to the Audit table.
	 * 
	 * @param item
	 * @return
	 */
	public Audit add(Audit item) {
		Session session = HibernateUtil.createSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		return item;
	}

	/**
	 * For deleting item from Items table.
	 * 
	 * @param id
	 * @return
	 */
	public Audit delete(int id) {
		Session session = HibernateUtil.createSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		Audit item = (Audit) session.load(Audit.class, id);
		if (null != item) {
			session.delete(item);
		}
		session.getTransaction().commit();
		return item;
	}

	/**
	 * For generating , executing hibernate select query and returns items as a
	 * list.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Audit> list() {
		Session session = HibernateUtil.createSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		List<Audit> items = null;
		try {
			items = (List<Audit>) session.createQuery("from Audit").list();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		return items;
	}

}
