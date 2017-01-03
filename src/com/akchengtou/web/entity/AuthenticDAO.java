package com.akchengtou.web.entity;

import java.sql.Timestamp;
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
 * A data access object (DAO) providing persistence and search support for
 * Authentic entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Authentic
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AuthenticDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AuthenticDAO.class);
	// property constants
	public static final String STATUS = "status";

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

	public void save(Authentic transientInstance) {
		log.debug("saving Authentic instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Authentic transientInstance) {
		log.debug("saving Authentic instance");
		try {
			getCurrentSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Authentic persistentInstance) {
		log.debug("deleting Authentic instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List findAuthenticByUserId(Integer userId)
	{
		String sqlString = "select * from authentic where user_id=?";
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString).addEntity(Authentic.class);
		queryObject.setParameter(0, userId);
		
		return queryObject.list();
	}
	
	
	public List findAuthenticByIdentiytype(Integer typeId,Integer page,Integer size)
	{
		String sqlString = "select a.user_id,a.name,u.score,u.image,u.gender from authentic as a left join user as u on a.user_id = u.user_id where a.identiy_type_id =?  order by u.score desc";
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString);
		queryObject.setFirstResult(size*page);
		queryObject.setMaxResults(size);
		queryObject.setParameter(0, typeId);
		
		return queryObject.list();
	}
	
	public Integer countOfInstance(Integer typeId,Integer page,Integer size)
	{
		String sqlString = "select a.user_id,a.name,u.score,u.image,u.gender from authentic as a left join user as u on a.user_id = u.user_id where a.identiy_type_id =?  order by u.score desc";
		SQLQuery queryObject = getCurrentSession().createSQLQuery(sqlString);
		queryObject.setParameter(0, typeId);
		
		return queryObject.list().size();
	}
	
	public Authentic findById(java.lang.Integer id) {
		log.debug("getting Authentic instance with id: " + id);
		try {
			Authentic instance = (Authentic) getCurrentSession().get(
					"com.akchengtou.web.entity.Authentic", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Authentic> findByExample(Authentic instance) {
		log.debug("finding Authentic instance by example");
		try {
			List<Authentic> results = (List<Authentic>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Authentic")
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
		log.debug("finding Authentic instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Authentic as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Authentic> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all Authentic instances");
		try {
			String queryString = "from Authentic";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(int size,Integer page) {
		log.debug("finding all Authentic instances");
		try {
			String queryString = "from Authentic";
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
		log.debug("finding all Authentic instances");
		try {
			String queryString = "select count(*) from Authentic";
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


	public Authentic merge(Authentic detachedInstance) {
		log.debug("merging Authentic instance");
		try {
			Authentic result = (Authentic) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Authentic instance) {
		log.debug("attaching dirty Authentic instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Authentic instance) {
		log.debug("attaching clean Authentic instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AuthenticDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AuthenticDAO) ctx.getBean("AuthenticDAO");
	}
}