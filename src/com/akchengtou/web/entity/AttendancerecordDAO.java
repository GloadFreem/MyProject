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
 * Attendancerecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Attendancerecord
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AttendancerecordDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AttendancerecordDAO.class);
	// property constants

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

	public void save(Attendancerecord transientInstance) {
		log.debug("saving Attendancerecord instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Attendancerecord persistentInstance) {
		log.debug("deleting Attendancerecord instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Attendancerecord findById(java.lang.Integer id) {
		log.debug("getting Attendancerecord instance with id: " + id);
		try {
			Attendancerecord instance = (Attendancerecord) getCurrentSession()
					.get("com.akchengtou.web.entity.Attendancerecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Attendancerecord> findByExample(Attendancerecord instance) {
		log.debug("finding Attendancerecord instance by example");
		try {
			List<Attendancerecord> results = (List<Attendancerecord>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Attendancerecord")
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
		log.debug("finding Attendancerecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Attendancerecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Attendancerecord instances");
		try {
			String queryString = "from Attendancerecord";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Attendancerecord merge(Attendancerecord detachedInstance) {
		log.debug("merging Attendancerecord instance");
		try {
			Attendancerecord result = (Attendancerecord) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Attendancerecord instance) {
		log.debug("attaching dirty Attendancerecord instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Attendancerecord instance) {
		log.debug("attaching clean Attendancerecord instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AttendancerecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AttendancerecordDAO) ctx.getBean("AttendancerecordDAO");
	}
}