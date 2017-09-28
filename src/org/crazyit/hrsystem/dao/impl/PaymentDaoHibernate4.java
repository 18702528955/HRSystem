package org.crazyit.hrsystem.dao.impl;

import java.util.*;

import org.crazyit.hrsystem.domain.*;
import org.crazyit.common.dao.impl.BaseDaoHibernate4;
import org.crazyit.hrsystem.dao.*;

public class PaymentDaoHibernate4 extends BaseDaoHibernate4<Payment>
	implements PaymentDao
{

	/**
	 * ���Ա����ѯ�½�нˮ
	 * @return ��Ա����Ӧ���½�нˮ����
	 */
	public List<Payment> findByEmp(Employee emp)
	{
		return find("select p from Payment as p where p.employee=?0" , emp);
	}


	/**
	 * ���Ա���ͷ�н�·�����ѯ�½�нˮ
	 * @param payMonth ��н�·�
	 * @param emp ��н��Ա��
	 * @return ָ��Ա����ָ���·ݵ��½�нˮ
	 */
	public Payment findByMonthAndEmp(String payMonth
		 , Employee emp)
	{
		List<Payment> pays = find("select p from Payment as p where"
			+ " p.employee=?0 and p.payMonth=?1" , emp , payMonth);
		if (pays != null && pays.size() > 0)
		{
			return pays.get(0);
		}
		return null;
	}
}
