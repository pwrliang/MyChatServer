package com.gl.mychat.database;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TblFriendapplication entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gl.mychat.database.TblFriendapplication
 * @author MyEclipse Persistence Tools
 */
public class TblFriendapplicationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TblFriendapplicationDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String STATUS = "status";

	public void save(TblFriendapplication transientInstance) {
		log.debug("saving TblFriendapplication instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TblFriendapplication persistentInstance) {
		log.debug("deleting TblFriendapplication instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TblFriendapplication findById(
			com.gl.mychat.database.TblFriendapplicationId id) {
		log.debug("getting TblFriendapplication instance with id: " + id);
		try {
			TblFriendapplication instance = (TblFriendapplication) getSession()
					.get("com.gl.mychat.database.TblFriendapplication", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TblFriendapplication instance) {
		log.debug("finding TblFriendapplication instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.gl.mychat.database.TblFriendapplication")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TblFriendapplication instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TblFriendapplication as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all TblFriendapplication instances");
		try {
			String queryString = "from TblFriendapplication";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TblFriendapplication merge(TblFriendapplication detachedInstance) {
		log.debug("merging TblFriendapplication instance");
		try {
			TblFriendapplication result = (TblFriendapplication) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TblFriendapplication instance) {
		log.debug("attaching dirty TblFriendapplication instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TblFriendapplication instance) {
		log.debug("attaching clean TblFriendapplication instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}