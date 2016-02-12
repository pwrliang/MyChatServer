package com.gl.mychat.database;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TblNotification entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gl.mychat.database.TblNotification
 * @author MyEclipse Persistence Tools
 */
public class TblNotificationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TblNotificationDAO.class);
	// property constants
	public static final String NOTIFICATION = "notification";
	public static final String STATUS = "status";

	public void save(TblNotification transientInstance) {
		log.debug("saving TblNotification instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TblNotification persistentInstance) {
		log.debug("deleting TblNotification instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TblNotification findById(java.lang.Long id) {
		log.debug("getting TblNotification instance with id: " + id);
		try {
			TblNotification instance = (TblNotification) getSession().get(
					"com.gl.mychat.database.TblNotification", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TblNotification instance) {
		log.debug("finding TblNotification instance by example");
		try {
			List results = getSession()
					.createCriteria("com.gl.mychat.database.TblNotification")
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
		log.debug("finding TblNotification instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TblNotification as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNotification(Object notification) {
		return findByProperty(NOTIFICATION, notification);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all TblNotification instances");
		try {
			String queryString = "from TblNotification";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TblNotification merge(TblNotification detachedInstance) {
		log.debug("merging TblNotification instance");
		try {
			TblNotification result = (TblNotification) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TblNotification instance) {
		log.debug("attaching dirty TblNotification instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TblNotification instance) {
		log.debug("attaching clean TblNotification instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}