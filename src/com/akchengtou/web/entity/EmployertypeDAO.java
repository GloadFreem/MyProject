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
 * Employertype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Employertype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class EmployertypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EmployertypeDAO.class);
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

	public void save(Employertype transientInstance) {
		log.debug("saving Employertype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Employertype persistentInstance) {
		log.debug("deleting Employertype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Employertype findById(java.lang.Integer id) {
		log.debug("getting Employertype instance with id: " + id);
		try {
			Employertype instance = (Employertype) getCurrentSession().get(
					"com.akchengtou.web.entity.Employertype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Employertype> findByExample(Employertype instance) {
		log.debug("finding Employertype instance by example");
		try {
			List<Employertype> results = (List<Employertype>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Employertype")
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
		log.debug("finding Employertype instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Employertype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Employertype> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Employertype> findByValid(Object valid) {
		return findByProperty(VALID, valid);
	}

	public List findAll() {
		log.debug("finding all Employertype instances");
		try {
			String queryString = "from Employertype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Employertype merge(Employertype detachedInstance) {
		log.debug("merging Employertype instance");
		try {
			Employertype result = (Employertype) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Employertype instance) {
		log.debug("attaching dirty Employertype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Employertype instance) {
		log.debug("attaching clean Employertype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EmployertypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (EmployertypeDAO) ctx.getBean("EmployertypeDAO");
	}
}