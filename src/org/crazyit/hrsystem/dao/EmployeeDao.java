package org.crazyit.hrsystem.dao;

import java.util.*;

import org.crazyit.common.dao.BaseDao;
import org.crazyit.hrsystem.domain.*;

public interface EmployeeDao extends BaseDao<Employee>
{
	/**
	 * ����û���������ѯԱ��
	 * @param emp ��ָ���û��������Ա��
	 * @return ���ָ���û���������Ա������
	 */
	List<Employee> findByNameAndPass(Employee emp);

	/**
	 * ����û����ѯԱ��
	 * @param name Ա�����û���
	 * @return ����û����Ա��
	 */
	Employee findByName(String name);
}
