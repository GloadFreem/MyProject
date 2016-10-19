package com.akchengtou.web.entity;

import java.sql.Timestamp;
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
 * Announcement entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Announcement
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AnnouncementDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AnnouncementDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String READED = "readed";

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

	public void save(Announcement transientInstance) {
		log.debug("saving Announcement instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Announcement persistentInstance) {
		log.debug("deleting Announcement instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Announcement findById(java.lang.Integer id) {
		log.debug("getting Announcement instance with id: " + id);
		try {
			Announcement instance = (Announcement) getCurrentSession().get(
					"com.akchengtou.web.entity.Announcement", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Announcement> findByExample(Announcement instance) {
		log.debug("finding Announcement instance by example");
		try {
			List<Announcement> results = (List<Announcement>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Announcement")
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
		log.debug("finding Announcement instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Announcement as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Announcement> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Announcement> findByReaded(Object readed) {
		return findByProperty(READED, readed);
	}

	public List findAll() {
		log.debug("finding all Announcement instances");
		try {
			String queryString = "from Announcement";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Announcement merge(Announcement detachedInstance) {
		log.debug("merging Announcement instance");
		try {
			Announcement result = (Announcement) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Announcement instance) {
		log.debug("attaching dirty Announcement instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Announcement instance) {
		log.debug("attaching clean Announcement instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AnnouncementDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AnnouncementDAO) ctx.getBean("AnnouncementDAO");
	}
}