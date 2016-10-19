package com.akchengtou.web.entity;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contact entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Contact
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ContactDAO {
	private static final Logger log = LoggerFactory.getLogger(ContactDAO.class);
	// property constants

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Contact transientInstance) {
		log.debug("saving Contact instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Contact persistentInstance) {
		log.debug("deleting Contact instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contact findById(com.akchengtou.web.entity.ContactId id) {
		log.debug("getting Contact instance with id: " + id);
		try {
			Contact instance = (Contact) getCurrentSession().get(
					"com.akchengtou.web.entity.Contact", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Contact> findByExample(Contact instance) {
		log.debug("finding Contact instance by example");
		try {
			List<Contact> results = (List<Contact>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Contact")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Contact instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Contact as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Contact instances");
		try {
			String queryString = "from Contact";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Contact merge(Contact detachedInstance) {
		log.debug("merging Contact instance");
		try {
			Contact result = (Contact) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Contact instance) {
		log.debug("attaching dirty Contact instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contact instance) {
		log.debug("attaching clean Contact instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ContactDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ContactDAO) ctx.getBean("ContactDAO");
	}
}