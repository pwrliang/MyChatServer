package com.gl.mychat.database;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TblFriend entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.gl.mychat.database.TblFriend
 * @author MyEclipse Persistence Tools
 */
public class TblFriendDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TblFriendDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String BLOCK = "block";

	public void save(TblFriend transientInstance) {
		log.debug("saving TblFriend instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TblFriend persistentInstance) {
		log.debug("deleting TblFriend instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TblFriend findById(com.gl.mychat.database.TblFriendId id) {
		log.debug("getting TblFriend instance with id: " + id);
		try {
			TblFriend instance = (TblFriend) getSession().get(
					"com.gl.mychat.database.TblFriend", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TblFriend instance) {
		log.debug("finding TblFriend instance by example");
		try {
			List results = getSession()
					.createCriteria("com.gl.mychat.database.TblFriend")
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
		log.debug("finding TblFriend instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TblFriend as model where model."
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

	public List findByBlock(Object block) {
		return findByProperty(BLOCK, block);
	}

	public List findAll() {
		log.debug("finding all TblFriend instances");
		try {
			String queryString = "from TblFriend";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TblFriend merge(TblFriend detachedInstance) {
		log.debug("merging TblFriend instance");
		try {
			TblFriend result = (TblFriend) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TblFriend instance) {
		log.debug("attaching dirty TblFriend instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TblFriend instance) {
		log.debug("attaching clean TblFriend instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}