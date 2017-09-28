package org.crazyit.hrsystem.service;

import org.crazyit.hrsystem.vo.*;
import org.crazyit.hrsystem.domain.*;
import org.crazyit.hrsystem.exception.*;

import java.util.*;

public interface MgrManager
{
	/**
	 * ����Ա��
	 * @param emp ������Ա��
	 * @param mgr Ա�������ľ���
	 */
	void addEmp(Employee emp , String mgr)
		throws HrException;


	/**
	 * ��ݾ��?�����еĲ����ϸ��¹���
	 * @param mgr ������Ա����
	 * @return �����ϸ��¹���
	 */
	List<SalaryBean> getSalaryByMgr(String mgr);

	/**
	 * ��ݾ��?�ظò��ŵ�ȫ��Ա��
	 * @param mgr ������
	 * @return �����ȫ������
	 */
	List<EmpBean> getEmpsByMgr(String mgr);

	/**
	 * ��ݾ��?�ظò��ŵ�û���������
	 * @param mgr ������
	 * @return �ò��ŵ�ȫ������
	 */
	List<AppBean> getAppsByMgr(String mgr);

	/**
	 * ��������
	 * @param appid ����ID
	 * @param mgrName ��������
	 * @param result �Ƿ�ͨ��
	 */
	void check(int appid, String mgrName, boolean result);
}