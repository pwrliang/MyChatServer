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
 * TblMessageQueue entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gl.mychat.database.TblMessageQueue
 * @author MyEclipse Persistence Tools
 */
public class TblMessageQueueDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TblMessageQueueDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String CONTENT_TYPE = "contentType";
	public static final String STATUS = "status";

	public void save(TblMessageQueue transientInstance) {
		log.debug("saving TblMessageQueue instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TblMessageQueue persistentInstance) {
		log.debug("deleting TblMessageQueue instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TblMessageQueue findById(java.lang.Long id) {
		log.debug("getting TblMessageQueue instance with id: " + id);
		try {
			TblMessageQueue instance = (TblMessageQueue) getSession().get(
					"com.gl.mychat.database.TblMessageQueue", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TblMessageQueue instance) {
		log.debug("finding TblMessageQueue instance by example");
		try {
			List results = getSession()
					.createCriteria("com.gl.mychat.database.TblMessageQueue")
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
		log.debug("finding TblMessageQueue instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TblMessageQueue as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByContentType(Object contentType) {
		return findByProperty(CONTENT_TYPE, contentType);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all TblMessageQueue instances");
		try {
			String queryString = "from TblMessageQueue";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TblMessageQueue merge(TblMessageQueue detachedInstance) {
		log.debug("merging TblMessageQueue instance");
		try {
			TblMessageQueue result = (TblMessageQueue) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TblMessageQueue instance) {
		log.debug("attaching dirty TblMessageQueue instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TblMessageQueue instance) {
		log.debug("attaching clean TblMessageQueue instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}