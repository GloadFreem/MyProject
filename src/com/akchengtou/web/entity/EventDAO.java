package com.akchengtou.web.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for Event
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.akchengtou.web.entity.Event
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class EventDAO {
	private static final Logger log = LoggerFactory.getLogger(EventDAO.class);
	// property constants
	public static final String CONTENT = "content";

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

	public void save(Event transientInstance) {
		log.debug("saving Event instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Event persistentInstance) {
		log.debug("deleting Event instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Event findById(java.lang.Integer id) {
		log.debug("getting Event instance with id: " + id);
		try {
			Event instance = (Event) getCurrentSession().get(
					"com.akchengtou.web.entity.Event", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Event> findByExample(Event instance) {
		log.debug("finding Event instance by example");
		try {
			List<Event> results = (List<Event>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Event")
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
		log.debug("finding Event instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Event as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Event> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Event instances");
		try {
			String queryString = "from Event";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(int size,Integer page) {
		log.debug("finding all Event instances");
		try {
			String queryString = "from Event";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setMaxResults(size);
			queryObject.setFirstResult(size*page);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Integer countOfInstance() {
		log.debug("finding all Event instances");
		try {
			String queryString = "select count(*) from event";
			SQLQuery queryObject = getCurrentSession().createSQLQuery(queryString);
			if(queryObject.list()!=null)
			{
				return Integer.parseInt(queryObject.list().get(0).toString());
			}
			return 0;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	

	public Event merge(Event detachedInstance) {
		log.debug("merging Event instance");
		try {
			Event result = (Event) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Event instance) {
		log.debug("attaching dirty Event instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Event instance) {
		log.debug("attaching clean Event instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EventDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EventDAO) ctx.getBean("EventDAO");
	}
}