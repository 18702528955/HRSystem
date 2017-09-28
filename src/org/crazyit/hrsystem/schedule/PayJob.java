package org.crazyit.hrsystem.schedule;

import java.util.Date;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.crazyit.hrsystem.service.EmpManager;

public class PayJob extends QuartzJobBean
{
	// �ж���ҵ�Ƿ�ִ�е����
	private boolean isRunning = false;
	// ����ҵ����������ҵ���߼����
	private EmpManager empMgr;
	public void setEmpMgr(EmpManager empMgr)
	{
		this.empMgr = empMgr;
	}
	// ��������ִ����
	public void executeInternal(JobExecutionContext ctx)
		throws JobExecutionException
	{
		if (!isRunning)
		{
			System.out.println("��ʼ�����Զ����㹤��");
			isRunning = true;
			// ����ҵ���߼�����
			empMgr.autoPay();
			isRunning = false;
		}
	}
}