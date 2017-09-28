package org.crazyit.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.crazyit.common.dao.BaseDao;
import org.hibernate.*;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.orm.hibernate4.HibernateCallback;

public class BaseDaoHibernate3<T> extends HibernateDaoSupport
	implements BaseDao<T>
{
	// ���ID����ʵ��
	public T get(Class<T> entityClazz, Serializable id)
	{
		return getHibernateTemplate().get(entityClazz, id);
	}

	// ����ʵ��
	public Serializable save(T entity)
	{
		return getHibernateTemplate().save(entity);
	}

	// ����ʵ��
	public void update(T entity)
	{
		getHibernateTemplate().saveOrUpdate(entity);
	}

	// ɾ��ʵ��
	public void delete(T entity)
	{
		getHibernateTemplate().delete(entity);
	}

	// ���IDɾ��ʵ��
	public void delete(Class<T> entityClazz, Serializable id)
	{
		delete(get(entityClazz , id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClazz)
	{
		return (List<T>)getHibernateTemplate().find("select en from "
			+ entityClazz.getSimpleName() + " en");
	}

	@Override
	@SuppressWarnings("unchecked")
	public long findCount(Class<T> entityClazz)
	{
		List<Long> list = (List<Long>)getHibernateTemplate().find(
			"select count(*) from " + entityClazz.getSimpleName() + " en");
		return list.get(0);
	}
	/**
	 * ʹ��hql �����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��hql���
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(final String hql,
		final int pageNo, final int pageSize)
	{
		// ͨ��һ��HibernateCallback������ִ�в�ѯ
		List<T> list = getHibernateTemplate()
			.execute(new HibernateCallback<List<T>>()
		{
			// ʵ��HibernateCallback�ӿڱ���ʵ�ֵķ���
			public List<T> doInHibernate(Session session)
			{
				// ִ��Hibernate��ҳ��ѯ
				List<T> result = session.createQuery(hql)
					.setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
				return result;
			}
		});
		return list;
	}

	/**
	 * ʹ��hql �����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��hql���
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @param params ���hql��ռλ�����params���ڴ���ռλ�����
	 * @return ��ǰҳ�����м�¼
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(final String hql , final int pageNo, 
		final int pageSize , final  Object... params)
	{
		// ͨ��һ��HibernateCallback������ִ�в�ѯ
		List<T> list = getHibernateTemplate()
			.execute(new HibernateCallback<List<T>>()
		{
			// ʵ��HibernateCallback�ӿڱ���ʵ�ֵķ���
			public List<T> doInHibernate(Session session)
			{
				// ִ��Hibernate��ҳ��ѯ
				Query query = session.createQuery(hql);
				// Ϊ��ռλ���HQL������ò���
				for(int i = 0 , len = params.length ; i < len ; i++)
				{
					query.setParameter(i + "" , params[i]);
				}
				List<T> result = query.setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
				return result;
			}
		});
		return list;
	}
}
