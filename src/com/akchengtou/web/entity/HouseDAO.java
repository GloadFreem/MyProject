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
 * A data access object (DAO) providing persistence and search support for House
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.akchengtou.web.entity.House
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class HouseDAO {
	private static final Logger log = LoggerFactory.getLogger(HouseDAO.class);
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

	public void save(House transientInstance) {
		log.debug("saving House instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(House persistentInstance) {
		log.debug("deleting House instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public House findById(java.lang.Integer id) {
		log.debug("getting House instance with id: " + id);
		try {
			House instance = (House) getCurrentSession().get(
					"com.akchengtou.web.entity.House", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<House> findByExample(House instance) {
		log.debug("finding House instance by example");
		try {
			List<House> results = (List<House>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.House")
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
		log.debug("finding House instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from House as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<House> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all House instances");
		try {
			String queryString = "from House";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Building> findByUnit(Unit unit) {
		log.debug("getting Unit instance with Homehouse: " + unit);
		try {
			String sqlString = "from House as model  where model.unit = ?";
			Query queryObject = getCurrentSession().createQuery(sqlString);
			queryObject.setParameter(0, unit);
			
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public House merge(House detachedInstance) {
		log.debug("merging House instance");
		try {
			House result = (House) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(House instance) {
		log.debug("attaching dirty House instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(House instance) {
		log.debug("attaching clean House instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static HouseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (HouseDAO) ctx.getBean("HouseDAO");
	}
}