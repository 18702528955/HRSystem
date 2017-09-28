package org.crazyit.hrsystem.dao;

import java.util.*;

import org.crazyit.common.dao.BaseDao;
import org.crazyit.hrsystem.domain.*;

public interface ManagerDao extends BaseDao<Manager>
{
	/**
	 * ����û���������ѯ����
	 * @param emp ��ָ���û�������ľ���
	 * @return ���ָ���û��������ľ���
	 */
	List<Manager> findByNameAndPass(Manager mgr);

	/**
	 * ����û�����Ҿ���
	 * @param name ���������
	 * @return ���ֶ�Ӧ�ľ���
	 */
	Manager findByName(String name);
}
