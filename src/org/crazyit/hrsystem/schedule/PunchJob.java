package org.crazyit.hrsystem.schedule;

import java.util.Date;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.crazyit.hrsystem.service.EmpManager;

public class PunchJob extends QuartzJobBean
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
			System.out.println("��ʼ�����Զ���");
			isRunning = true;
			// ����ҵ���߼�����
			empMgr.autoPunch();
			isRunning = false;
		}
	}
}