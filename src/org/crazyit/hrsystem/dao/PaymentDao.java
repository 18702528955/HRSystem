package org.crazyit.hrsystem.dao;

import java.util.List;

import org.crazyit.common.dao.BaseDao;
import org.crazyit.hrsystem.domain.Employee;
import org.crazyit.hrsystem.domain.Payment;

public interface PaymentDao extends BaseDao<Payment>
{
	/**
	 * ���Ա����ѯ�½�нˮ
	 * @return ��Ա����Ӧ���½�нˮ����
	 */
	List<Payment> findByEmp(Employee emp);

	/**
	 * ���Ա���ͷ�н�·�����ѯ�½�нˮ
	 * @param payMonth ��н�·�
	 * @param emp ��н��Ա��
	 * @return ָ��Ա����ָ���·ݵ��½�нˮ
	 */
	Payment findByMonthAndEmp(String payMonth , Employee emp);
}
