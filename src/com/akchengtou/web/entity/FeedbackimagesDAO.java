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
 * Feedbackimages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Feedbackimages
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FeedbackimagesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FeedbackimagesDAO.class);
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

	public void save(Feedbackimages transientInstance) {
		log.debug("saving Feedbackimages instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Feedbackimages persistentInstance) {
		log.debug("deleting Feedbackimages instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Feedbackimages findById(java.lang.Integer id) {
		log.debug("getting Feedbackimages instance with id: " + id);
		try {
			Feedbackimages instance = (Feedbackimages) getCurrentSession().get(
					"com.akchengtou.web.entity.Feedbackimages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Feedbackimages> findByExample(Feedbackimages instance) {
		log.debug("finding Feedbackimages instance by example");
		try {
			List<Feedbackimages> results = (List<Feedbackimages>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Feedbackimages")
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
		log.debug("finding Feedbackimages instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Feedbackimages as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Feedbackimages> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Feedbackimages instances");
		try {
			String queryString = "from Feedbackimages";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Feedbackimages merge(Feedbackimages detachedInstance) {
		log.debug("merging Feedbackimages instance");
		try {
			Feedbackimages result = (Feedbackimages) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Feedbackimages instance) {
		log.debug("attaching dirty Feedbackimages instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Feedbackimages instance) {
		log.debug("attaching clean Feedbackimages instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FeedbackimagesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FeedbackimagesDAO) ctx.getBean("FeedbackimagesDAO");
	}
}