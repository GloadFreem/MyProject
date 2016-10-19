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
 * Publiccontentimages entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Publiccontentimages
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class PubliccontentimagesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PubliccontentimagesDAO.class);
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

	public void save(Publiccontentimages transientInstance) {
		log.debug("saving Publiccontentimages instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Publiccontentimages persistentInstance) {
		log.debug("deleting Publiccontentimages instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Publiccontentimages findById(java.lang.Integer id) {
		log.debug("getting Publiccontentimages instance with id: " + id);
		try {
			Publiccontentimages instance = (Publiccontentimages) getCurrentSession()
					.get("com.akchengtou.web.entity.Publiccontentimages", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Publiccontentimages> findByExample(Publiccontentimages instance) {
		log.debug("finding Publiccontentimages instance by example");
		try {
			List<Publiccontentimages> results = (List<Publiccontentimages>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Publiccontentimages")
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
		log.debug("finding Publiccontentimages instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Publiccontentimages as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Publiccontentimages> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Publiccontentimages instances");
		try {
			String queryString = "from Publiccontentimages";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Publiccontentimages merge(Publiccontentimages detachedInstance) {
		log.debug("merging Publiccontentimages instance");
		try {
			Publiccontentimages result = (Publiccontentimages) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Publiccontentimages instance) {
		log.debug("attaching dirty Publiccontentimages instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Publiccontentimages instance) {
		log.debug("attaching clean Publiccontentimages instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PubliccontentimagesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PubliccontentimagesDAO) ctx.getBean("PubliccontentimagesDAO");
	}
}