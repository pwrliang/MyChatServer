package com.gl.mychat.database;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TblComment entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gl.mychat.database.TblComment
 * @author MyEclipse Persistence Tools
 */
public class TblCommentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TblCommentDAO.class);
	// property constants
	public static final String CONTENT = "content";

	public void save(TblComment transientInstance) {
		log.debug("saving TblComment instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TblComment persistentInstance) {
		log.debug("deleting TblComment instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TblComment findById(java.lang.Long id) {
		log.debug("getting TblComment instance with id: " + id);
		try {
			TblComment instance = (TblComment) getSession().get(
					"com.gl.mychat.database.TblComment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TblComment instance) {
		log.debug("finding TblComment instance by example");
		try {
			List results = getSession()
					.createCriteria("com.gl.mychat.database.TblComment")
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
		log.debug("finding TblComment instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TblComment as model where model."
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

	public List findAll() {
		log.debug("finding all TblComment instances");
		try {
			String queryString = "from TblComment";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TblComment merge(TblComment detachedInstance) {
		log.debug("merging TblComment instance");
		try {
			TblComment result = (TblComment) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TblComment instance) {
		log.debug("attaching dirty TblComment instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TblComment instance) {
		log.debug("attaching clean TblComment instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}