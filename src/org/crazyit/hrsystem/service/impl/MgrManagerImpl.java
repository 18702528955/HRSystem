package org.crazyit.hrsystem.service.impl;

import org.crazyit.hrsystem.dao.*;
import org.crazyit.hrsystem.domain.*;
import org.crazyit.hrsystem.vo.*;
import org.crazyit.hrsystem.exception.*;
import org.crazyit.hrsystem.service.*;

import java.text.*;
import java.util.*;

public class MgrManagerImpl
	implements MgrManager
{
	private ApplicationDao appDao;
	private AttendDao attendDao;
	private AttendTypeDao typeDao;
	private CheckBackDao checkDao;
	private EmployeeDao empDao;
	private ManagerDao mgrDao;
	private PaymentDao payDao;

	public void setAppDao(ApplicationDao appDao)
	{
		this.appDao = appDao;
	}

	public void setAttendDao(AttendDao attendDao)
	{
		this.attendDao = attendDao;
	}

	public void setTypeDao(AttendTypeDao typeDao)
	{
		this.typeDao = typeDao;
	}

	public void setCheckDao(CheckBackDao checkDao)
	{
		this.checkDao = checkDao;
	}

	public void setEmpDao(EmployeeDao empDao)
	{
		this.empDao = empDao;
	}

	public void setMgrDao(ManagerDao mgrDao)
	{
		this.mgrDao = mgrDao;
	}

	public void setPayDao(PaymentDao payDao)
	{
		this.payDao = payDao;
	}

	/**
	 * ����Ա��
	 * @param emp ������Ա��
	 * @param mgr Ա�������ľ���
	 */
	public void addEmp(Employee emp , String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("���Ǿ����𣿻��㻹δ��¼��");
		}
		emp.setManager(m);
		empDao.save(emp);
	}

	/**
	 * ��ݾ��?�����еĲ����ϸ��¹���
	 * @param mgr ������Ա����
	 * @return �����ϸ��¹���
	 */
	public List<SalaryBean> getSalaryByMgr(String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("���Ǿ����𣿻��㻹δ��¼��");
		}
		//��ѯ�þ����Ӧ��ȫ��Ա��
		Set<Employee> emps = m.getEmployees();
		//������Ȼû��Ա��
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("��Ĳ���û��Ա��");
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH , -1);
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		List<SalaryBean> result = new ArrayList<SalaryBean>();
		//������ÿ��Ա��
		for (Employee e : emps)
		{
			Payment p = payDao.findByMonthAndEmp(payMonth , e);
			if (p != null)
			{
				result.add(new SalaryBean(e.getName()
					, p.getAmount()));
			}
		}
		return result;
	}

	/**
	* ��ݾ��?�ظò��ŵ�ȫ��Ա��
	* @param mgr ������
	* @return �����ȫ������
	*/
	public List<EmpBean> getEmpsByMgr(String mgr)
		throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("���Ǿ����𣿻��㻹δ��¼��");
		}
		//��ѯ�þ����Ӧ��ȫ��Ա��
		Set<Employee> emps = m.getEmployees();
		//������Ȼû��Ա��
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("��Ĳ���û��Ա��");
		}
		//��װVO��
		List<EmpBean> result = new ArrayList<EmpBean>();
		for (Employee e : emps)
		{
			result.add(new EmpBean(e.getName() ,
				e.getPass() , e.getSalary()));
		}
		return result;
	}

	/**
	 * ��ݾ��?�ظò��ŵ�û���������
	 * @param mgr ������
	 * @return �ò��ŵ�ȫ������
	 */
	public List<AppBean> getAppsByMgr(String mgr)throws HrException
	{
		Manager m = mgrDao.findByName(mgr);
		if (m == null)
		{
			throw new HrException("���Ǿ����𣿻��㻹δ��¼��");
		}
		//��ѯ�þ����Ӧ��ȫ��Ա��
		Set<Employee> emps = m.getEmployees();
		//������Ȼû��Ա��
		if (emps == null || emps.size() < 1)
		{
			throw new HrException("��Ĳ���û��Ա��");
		}
		//��װVO��
		List<AppBean> result = new ArrayList<AppBean>();
		for (Employee e : emps)
		{
			//�鿴��Ա����ȫ������
			List<Application> apps = appDao.findByEmp(e);
			if (apps != null && apps.size() > 0)
			{
				for (Application app : apps)
				{
					//ֻѡ��δ���������
					if (app.getResult() == false)
					{
						Attend attend = app.getAttend();
						result.add(new AppBean(app.getId() ,
							e.getName(), attend.getType().getName(),
							app.getType().getName(), app.getReason()));
					}
				}
			}
		}
		return result;
	}

	/**
	 * ��������
	 * @param appid ����ID
	 * @param mgrName ��������
	 * @param result �Ƿ�ͨ��
	 */
	public void check(int appid, String mgrName, boolean result)
	{
		Application app = appDao.get(Application.class , appid);
		CheckBack check = new CheckBack();
		check.setApp(app);
		Manager manager = mgrDao.findByName(mgrName);
		if (manager == null)
		{
			throw new HrException("���Ǿ����𣿻��㻹δ��¼��");
		}
		check.setManager(manager);
		//ͬ��ͨ������
		if (result)
		{
			//����ͨ������
			check.setResult(true);

			//�޸�����Ϊ�Ѿ���
			app.setResult(true);
			appDao.update(app);
			//Ϊ��ʱ������Ҫ�޸ĳ��ڵ�����
			Attend attend = app.getAttend();
			attend.setType(app.getType());
			attendDao.update(attend);
		}
		else
		{
			//û��ͨ������
			check.setResult(false);
			app.setResult(true);
			appDao.update(app);
		}
		//����������
		checkDao.save(check);
	}
}
