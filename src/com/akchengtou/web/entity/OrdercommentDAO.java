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
 * Ordercomment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Ordercomment
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class OrdercommentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OrdercommentDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String SCORE = "score";
	public static final String GENDER = "gender";

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

	public void save(Ordercomment transientInstance) {
		log.debug("saving Ordercomment instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Ordercomment persistentInstance) {
		log.debug("deleting Ordercomment instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ordercomment findById(java.lang.Integer id) {
		log.debug("getting Ordercomment instance with id: " + id);
		try {
			Ordercomment instance = (Ordercomment) getCurrentSession().get(
					"com.akchengtou.web.entity.Ordercomment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Ordercomment> findByExample(Ordercomment instance) {
		log.debug("finding Ordercomment instance by example");
		try {
			List<Ordercomment> results = (List<Ordercomment>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Ordercomment")
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
		log.debug("finding Ordercomment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Ordercomment as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Ordercomment> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Ordercomment> findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List<Ordercomment> findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List findAll() {
		log.debug("finding all Ordercomment instances");
		try {
			String queryString = "from Ordercomment";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Ordercomment merge(Ordercomment detachedInstance) {
		log.debug("merging Ordercomment instance");
		try {
			Ordercomment result = (Ordercomment) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Ordercomment instance) {
		log.debug("attaching dirty Ordercomment instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ordercomment instance) {
		log.debug("attaching clean Ordercomment instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrdercommentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OrdercommentDAO) ctx.getBean("OrdercommentDAO");
	}
}