package org.crazyit.hrsystem.dao;

import java.util.*;

import org.crazyit.common.dao.BaseDao;
import org.crazyit.hrsystem.domain.*;

public interface ApplicationDao extends BaseDao<Application>
{
	/**
	 * ���Ա����ѯδ������춯����
	 * @param emp ��Ҫ��ѯ��Ա��
	 * @return ��Ա����Ӧ��δ������춯����
	 */
	List<Application> findByEmp(Employee emp);
}
