package com.akchengtou.web.entity;

import java.util.List;
import java.util.Set;
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
 * Servicetype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Servicetype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ServicetypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ServicetypeDAO.class);
	// property constants
	public static final String NAME = "name";

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

	public void save(Servicetype transientInstance) {
		log.debug("saving Servicetype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Servicetype persistentInstance) {
		log.debug("deleting Servicetype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Servicetype findById(java.lang.Integer id) {
		log.debug("getting Servicetype instance with id: " + id);
		try {
			Servicetype instance = (Servicetype) getCurrentSession().get(
					"com.akchengtou.web.entity.Servicetype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Servicetype> findByExample(Servicetype instance) {
		log.debug("finding Servicetype instance by example");
		try {
			List<Servicetype> results = (List<Servicetype>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Servicetype")
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
		log.debug("finding Servicetype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Servicetype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Servicetype> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Servicetype instances");
		try {
			String queryString = "from Servicetype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Servicetype merge(Servicetype detachedInstance) {
		log.debug("merging Servicetype instance");
		try {
			Servicetype result = (Servicetype) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Servicetype instance) {
		log.debug("attaching dirty Servicetype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Servicetype instance) {
		log.debug("attaching clean Servicetype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServicetypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServicetypeDAO) ctx.getBean("ServicetypeDAO");
	}
}