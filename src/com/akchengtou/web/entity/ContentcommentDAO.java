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

import com.akchengtou.tools.AKConfig;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contentcomment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Contentcomment
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ContentcommentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ContentcommentDAO.class);
	// property constants
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

	public void save(Contentcomment transientInstance) {
		log.debug("saving Contentcomment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Contentcomment persistentInstance) {
		log.debug("deleting Contentcomment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Contentcomment findById(java.lang.Integer id) {
		log.debug("getting Contentcomment instance with id: " + id);
		try {
			Contentcomment instance = (Contentcomment) getCurrentSession().get(
					"com.akchengtou.web.entity.Contentcomment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Contentcomment> findByExample(Contentcomment instance) {
		log.debug("finding Contentcomment instance by example");
		try {
			List<Contentcomment> results = (List<Contentcomment>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Contentcomment")
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
		log.debug("finding Contentcomment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Contentcomment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Contentcomment> findByValid(Object valid) {
		return findByProperty(VALID, valid);
	}

	public List findAll() {
		log.debug("finding all Contentcomment instances");
		try {
			String queryString = "from Contentcomment";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPropertyByPage(String propertyName, Object value,Integer page) {
		log.debug("finding Contentcomment instance with page property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Contentcomment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			queryObject.setMaxResults(10);
			queryObject.setFirstResult(page*AKConfig.STRING_INVESTOR_LIST_MAX_SIZE);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public Contentcomment merge(Contentcomment detachedInstance) {
		log.debug("merging Contentcomment instance");
		try {
			Contentcomment result = (Contentcomment) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Contentcomment instance) {
		log.debug("attaching dirty Contentcomment instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contentcomment instance) {
		log.debug("attaching clean Contentcomment instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ContentcommentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ContentcommentDAO) ctx.getBean("ContentcommentDAO");
	}
}