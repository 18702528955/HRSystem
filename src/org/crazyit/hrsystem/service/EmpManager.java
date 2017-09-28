package org.crazyit.hrsystem.service;

import org.crazyit.hrsystem.vo.*;
import org.crazyit.hrsystem.domain.*;
import org.crazyit.hrsystem.exception.*;

import java.util.*;

public interface EmpManager
{
	// ��¼ʧ��
	public static final int LOGIN_FAIL = 0;
	// ����ͨԱ����¼
	public static final int LOGIN_EMP = 1;
	// �Ծ����¼
	public static final int LOGIN_MGR = 2;

	// ���ܴ�
	public static final int NO_PUNCH = 0;
	// ֻ���ϰ��
	public static final int COME_PUNCH = 1;
	// ֻ���°��
	public static final int LEAVE_PUNCH = 2;
	// �ȿ����ϰ࣬Ҳ�����°��
	public static final int BOTH_PUNCH = 3;

	// ��ʧ��
	public static final int PUNCH_FAIL = 0;
	// �Ѿ���
	public static final int PUNCHED = 1;
	// �򿨳ɹ�
	public static final int PUNCH_SUCC = 2;

	// ������11��Ϊ����ʱ��
	public static final int AM_LIMIT = 11;


	// ������9��֮ǰΪ���ϰ�
	public static final int COME_LIMIT = 9;
	// ������9~11��֮����ٵ�
	public static final int LATE_LIMIT = 11;
	// ������18��֮�������°�
	public static final int LEAVE_LIMIT = 18;
	// ������16~18��֮����ٵ�
	public static final int EARLY_LIMIT = 16;

	/**
	 * �Ծ����������֤��¼
	 * @param mgr ��¼�ľ������
	 * @return ��¼������ȷ��:0Ϊ��¼ʧ�ܣ�1Ϊ��¼emp 2Ϊ��¼mgr
	 */
	int validLogin(Manager mgr);

	/**
	 * �Զ��򿨣���һ�����壬����7��00Ϊÿ��Ա�����������¼
	 */
	void autoPunch();

	/**
	 * �Զ����㹤�ʣ�ÿ��1�ţ������ϸ��¹���
	 */
	void autoPay();


	/**
	 * ��֤ĳ��Ա���Ƿ�ɴ�
	 * @param user Ա����
	 * @param dutyDay ����
	 * @return �ɴ򿨵����
	 */
	int validPunch(String user , String dutyDay);

	/**
	 * ��
	 * @param user Ա����
	 * @param dutyDay ������
	 * @param isCome �Ƿ����ϰ��
	 * @return �򿨽��
	 */
	public int punch(String user , String dutyDay , boolean isCome);

	/**
	 * ���Ա������Լ��Ĺ���
	 * @param empName Ա����
	 * @return ��Ա���Ĺ����б�
	 */
	List<PaymentBean> empSalary(String empName);

	/**
 	 * Ա���鿴�Լ��������������
	 * @param empName Ա����
	 * @return ��Ա�����������ķ����
	 */
	List<AttendBean> unAttend(String empName);

	/**
	 * ����ȫ���ĳ������
	 * @return ȫ���ĳ������
	 */
	List<AttendType> getAllType();

	/**
	 * �������
	 * @param attId ����ĳ���ID
	 * @param typeId ���������ID
	 * @param reason ���������
	 * @return ��ӵĽ��
	 */
	boolean addApplication(int attId , int typeId , String reason);
}