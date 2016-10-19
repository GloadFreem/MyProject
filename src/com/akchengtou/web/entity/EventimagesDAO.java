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
 * Eventimages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Eventimages
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class EventimagesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EventimagesDAO.class);
	// property constants
	public static final String URL = "url";

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

	public void save(Eventimages transientInstance) {
		log.debug("saving Eventimages instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Eventimages persistentInstance) {
		log.debug("deleting Eventimages instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Eventimages findById(java.lang.Integer id) {
		log.debug("getting Eventimages instance with id: " + id);
		try {
			Eventimages instance = (Eventimages) getCurrentSession().get(
					"com.akchengtou.web.entity.Eventimages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Eventimages> findByExample(Eventimages instance) {
		log.debug("finding Eventimages instance by example");
		try {
			List<Eventimages> results = (List<Eventimages>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Eventimages")
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
		log.debug("finding Eventimages instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Eventimages as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Eventimages> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Eventimages instances");
		try {
			String queryString = "from Eventimages";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Eventimages merge(Eventimages detachedInstance) {
		log.debug("merging Eventimages instance");
		try {
			Eventimages result = (Eventimages) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Eventimages instance) {
		log.debug("attaching dirty Eventimages instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Eventimages instance) {
		log.debug("attaching clean Eventimages instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EventimagesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (EventimagesDAO) ctx.getBean("EventimagesDAO");
	}
}