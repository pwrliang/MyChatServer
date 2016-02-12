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
 * TblTimeline entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.gl.mychat.database.TblTimeline
 * @author MyEclipse Persistence Tools
 */
public class TblTimelineDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TblTimelineDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String VISIBILITY = "visibility";

	public void save(TblTimeline transientInstance) {
		log.debug("saving TblTimeline instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TblTimeline persistentInstance) {
		log.debug("deleting TblTimeline instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TblTimeline findById(java.lang.Long id) {
		log.debug("getting TblTimeline instance with id: " + id);
		try {
			TblTimeline instance = (TblTimeline) getSession().get(
					"com.gl.mychat.database.TblTimeline", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TblTimeline instance) {
		log.debug("finding TblTimeline instance by example");
		try {
			List results = getSession()
					.createCriteria("com.gl.mychat.database.TblTimeline")
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
		log.debug("finding TblTimeline instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TblTimeline as model where model."
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

	public List findByVisibility(Object visibility) {
		return findByProperty(VISIBILITY, visibility);
	}

	public List findAll() {
		log.debug("finding all TblTimeline instances");
		try {
			String queryString = "from TblTimeline";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TblTimeline merge(TblTimeline detachedInstance) {
		log.debug("merging TblTimeline instance");
		try {
			TblTimeline result = (TblTimeline) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TblTimeline instance) {
		log.debug("attaching dirty TblTimeline instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TblTimeline instance) {
		log.debug("attaching clean TblTimeline instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}