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
 * Member entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.akchengtou.web.entity.Member
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String TELEPHONE = "telephone";
	public static final String IMAGE = "image";
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

	public void save(Member transientInstance) {
		log.debug("saving Member instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Member persistentInstance) {
		log.debug("deleting Member instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Member findById(java.lang.Integer id) {
		log.debug("getting Member instance with id: " + id);
		try {
			Member instance = (Member) getCurrentSession().get(
					"com.akchengtou.web.entity.Member", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Member> findByExample(Member instance) {
		log.debug("finding Member instance by example");
		try {
			List<Member> results = (List<Member>) getCurrentSession()
					.createCriteria("com.akchengtou.web.entity.Member")
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
		log.debug("finding Member instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Member as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Member> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Member> findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	public List<Member> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List<Member> findByGender(Object gender) {
		return findByProperty(GENDER, gender);
	}

	public List findAll() {
		log.debug("finding all Member instances");
		try {
			String queryString = "from Member";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByType(Servicetype type,Integer page) {
		log.debug("finding all Member instances");
		try {
			String queryString = "from Member where servicetype=?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, type);
			queryObject.setFirstResult(10*page);
			queryObject.setMaxResults(10);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Member merge(Member detachedInstance) {
		log.debug("merging Member instance");
		try {
			Member result = (Member) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Member instance) {
		log.debug("attaching dirty Member instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Member instance) {
		log.debug("attaching clean Member instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MemberDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MemberDAO) ctx.getBean("MemberDAO");
	}
}