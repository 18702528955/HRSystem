package org.crazyit.hrsystem.service.impl;

import org.crazyit.hrsystem.dao.*;
import org.crazyit.hrsystem.domain.*;
import org.crazyit.hrsystem.vo.*;
import org.crazyit.hrsystem.exception.*;
import org.crazyit.hrsystem.service.*;

import java.text.*;
import java.util.*;

public class EmpManagerImpl
	implements EmpManager
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
	 * �Ծ����������֤��¼
	 * @param mgr ��¼�ľ������
	 * @return ��¼������ȷ��:0Ϊ��¼ʧ�ܣ�1Ϊ��¼emp 2Ϊ��¼mgr
	 */
	public int validLogin(Manager mgr)
	{
		// ����ҵ�һ�����?�Ծ����¼
		if (mgrDao.findByNameAndPass(mgr).size() >= 1)
		{
			return LOGIN_MGR;
		}
		// ����ҵ���ͨԱ��������ͨԱ����¼
		else if (empDao.findByNameAndPass(mgr).size() >= 1)
		{
			return LOGIN_EMP;
		}
		else
		{
			return LOGIN_FAIL;
		}
	}

	/**
	 * �Զ��򿨣���һ�����壬����7��00Ϊÿ��Ա�����������¼
	 */
	public void autoPunch()
	{
		System.out.println("�Զ����������¼");
		List<Employee> emps = empDao.findAll(Employee.class);
		// ��ȡ��ǰʱ��
		String dutyDay = new java.sql.Date(
			System.currentTimeMillis()).toString();
		for (Employee e : emps)
		{
			// ��ȡ������Ӧ�ĳ�������
			AttendType atype = typeDao.get(AttendType.class , 6);
			Attend a = new Attend();
			a.setDutyDay(dutyDay);
			a.setType(atype);
			// ���ǰʱ���������ϣ���Ӧ���ϰ��
			if (Calendar.getInstance()
				.get(Calendar.HOUR_OF_DAY) < AM_LIMIT)
			{
				// �ϰ��
				a.setIsCome(true);
			}
			else
			{
				// �°��
				a.setIsCome(false);
			}
			a.setEmployee(e);
			attendDao.save(a);
		}
	}

	/**
	 * �Զ����㹤�ʣ�ÿ��1�ţ������ϸ��¹���
	 */
	public void autoPay()
	{
		System.out.println("�Զ����빤�ʽ���");
		List<Employee> emps = empDao.findAll(Employee.class);
		// ��ȡ�ϸ���ʱ��
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		// Ϊÿ��Ա�������ϸ��¹���
		for (Employee e : emps)
		{
			Payment pay = new Payment();
			// ��ȡ��Ա���Ĺ���
			double amount = e.getSalary();
			// ��ȡ��Ա���ϸ��µĳ��ڼ�¼
			List<Attend> attends = attendDao.findByEmpAndMonth(e , payMonth);
			// �ù����ۻ�����ڼ�¼�Ĺ���
			for ( Attend a : attends )
			{
				amount += a.getType().getAmerce();
			}
			// ��ӹ��ʽ���
			pay.setPayMonth(payMonth);
			pay.setEmployee(e);
			pay.setAmount(amount);
			payDao.save(pay);
		}
	}

	/**
	 * ��֤ĳ��Ա���Ƿ�ɴ�
	 * @param user Ա����
	 * @param dutyDay ����
	 * @return �ɴ򿨵����
	 */
	public int validPunch(String user , String dutyDay)
	{
		// ���ܲ��ҵ���Ӧ�û��������޷���
		Employee emp = empDao.findByName(user);
		if (emp == null)
		{
			return NO_PUNCH;
		}
		// �ҵ�Ա����ǰ�ĳ��ڼ�¼
		List<Attend> attends = attendDao.findByEmpAndDutyDay(emp , dutyDay);
		// ϵͳû��Ϊ�û��ڵ������մ򿨼�¼���޷���
		if (attends == null || attends.size() <= 0)
		{
			return NO_PUNCH;
		}
		// ��ʼ�ϰ��
		else if (attends.size() == 1
			&& attends.get(0).getIsCome()
			&& attends.get(0).getPunchTime() == null)
		{
			return COME_PUNCH;
		}
		else if (attends.size() == 1
			&& attends.get(0).getPunchTime() == null)
		{
			return LEAVE_PUNCH;
		}
		else if (attends.size() == 2)
		{
			// �����ϰࡢ�°��
			if (attends.get(0).getPunchTime() == null
				&& attends.get(1).getPunchTime() == null)
			{
				return BOTH_PUNCH;
			}
			// �����°��
			else if (attends.get(1).getPunchTime() == null)
			{
				return LEAVE_PUNCH;
			}
			else
			{
				return NO_PUNCH;
			}
		}
		return NO_PUNCH;
	}

	/**
	 * ��
	 * @param user Ա����
	 * @param dutyDay ������
	 * @param isCome �Ƿ����ϰ��
	 * @return �򿨽��
	 */
	public int punch(String user , String dutyDay , boolean isCome)
	{
		Employee emp = empDao.findByName(user);
		if (emp == null)
		{
			return PUNCH_FAIL;
		}
		// �ҵ�Ա�����δ򿨶�Ӧ�ĳ��ڼ�¼
		Attend attend =
			attendDao.findByEmpAndDutyDayAndCome(emp , dutyDay , isCome);
		if (attend == null)
		{
			return PUNCH_FAIL;
		}
		// �Ѿ���
		if (attend.getPunchTime() != null)
		{
			return PUNCHED;
		}
		System.out.println("============��==========");
		// ��ȡ��ʱ��
		int punchHour = Calendar.getInstance()
			.get(Calendar.HOUR_OF_DAY);
		attend.setPunchTime(new Date());
		// �ϰ��
		if (isCome)
		{
			// 9��֮ǰ����
			if (punchHour < COME_LIMIT)
			{
				attend.setType(typeDao.get(AttendType.class , 1));
			}
			// 9��11��֮����ٵ�
			else if (punchHour < LATE_LIMIT)
			{
				attend.setType(typeDao.get(AttendType.class , 4));
			}
			// 11��֮�������,�������
		}
		// �°��
		else
		{
			// 18��֮������
			if (punchHour >= LEAVE_LIMIT)
			{
				attend.setType(typeDao.get(AttendType.class , 1));
			}
			// 16~18��֮��������
			else if (punchHour >= EARLY_LIMIT)
			{
				attend.setType(typeDao.get(AttendType.class , 5));
			}
		}
		attendDao.update(attend);
		return PUNCH_SUCC;
	}

	/**
	 * ���Ա������Լ��Ĺ���
	 * @param empName Ա����
	 * @return ��Ա���Ĺ����б�
	 */
	public List<PaymentBean> empSalary(String empName)
	{
		// ��ȡ��ǰԱ��
		Employee emp = empDao.findByName(empName);
		// ��ȡ��Ա����ȫ�������б�
		List<Payment> pays = payDao.findByEmp(emp);
		List<PaymentBean> result = new ArrayList<PaymentBean>();
		// ��װVO����
		for (Payment p : pays )
		{
			result.add(new PaymentBean(p.getPayMonth()
				,p.getAmount()));
		}
		return result;
	}

	/**
	 * Ա���鿴�Լ��������������
	 * @param empName Ա����
	 * @return ��Ա�����������ķ����
	 */
	public List<AttendBean> unAttend(String empName)
	{
		// �ҳ����ϰ�
		AttendType type = typeDao.get(AttendType.class , 1);
		Employee emp = empDao.findByName(empName);
		// �ҳ������ϰ�ĳ��ڼ�¼
		List<Attend> attends = attendDao.findByEmpUnAttend(emp, type);
		List<AttendBean> result = new ArrayList<AttendBean>();
		// ��װVO����
		for (Attend att : attends )
		{
			result.add(new AttendBean(att.getId() , att.getDutyDay()
				, att.getType().getName() , att.getPunchTime()));
		}
		return result;
	}

	/**
	 * ����ȫ���ĳ������
	 * @return ȫ���ĳ������
	 */
	public List<AttendType> getAllType()
	{
		return typeDao.findAll(AttendType.class);
	}

	/**
	 * �������
	 * @param attId ����ĳ���ID
	 * @param typeId ���������ID
	 * @param reason ���������
	 * @return ��ӵĽ��
	 */
	public boolean addApplication(int attId , int typeId
		, String reason)
	{
		System.out.println("--------------" + attId);
		System.out.println("~~~~" + typeId);
		System.out.println("~~~~" + reason);
		// ����һ������
		Application app = new Application();
		// ��ȡ������Ҫ�ı�ĳ��ڼ�¼
		Attend attend = attendDao.get(Attend.class , attId);
		AttendType type = typeDao.get(AttendType.class , typeId);
		app.setAttend(attend);
		app.setType(type);
		if (reason != null)
		{
			app.setReason(reason);
		}
		System.out.println("====aaaaaaaaa====");
		appDao.save(app);
		return true;
	}
}