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
 * Building entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Building
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BuildingDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BuildingDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String VALID = "valid";

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

	public void save(Building transientInstance) {
		log.debug("saving Building instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Building persistentInstance) {
		log.debug("deleting Building instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Building findById(java.lang.Integer id) {
		log.debug("getting Building instance with id: " + id);
		try {
			Building instance = (Building) getCurrentSession().get(
					"com.akchengtou.web.entity.Building", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Building> findByHomeHouse(Homehouse house) {
		log.debug("getting Building instance with Homehouse: " + house);
		try {
			String sqlString = "from Building as model  where model.homehouse = ?";
			Query queryObject = getCurrentSession().createQuery(sqlString);
			queryObject.setParameter(0, house);
			
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Building> findByExample(Building instance) {
		log.debug("finding Building instance by example");
		try {
			List<Building> results = (List<Building>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Building")
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
		log.debug("finding Building instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Building as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Building> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Building> findByValid(Object valid) {
		return findByProperty(VALID, valid);
	}

	public List findAll() {
		log.debug("finding all Building instances");
		try {
			String queryString = "from Building";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Building merge(Building detachedInstance) {
		log.debug("merging Building instance");
		try {
			Building result = (Building) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Building instance) {
		log.debug("attaching dirty Building instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Building instance) {
		log.debug("attaching clean Building instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BuildingDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BuildingDAO) ctx.getBean("BuildingDAO");
	}
}