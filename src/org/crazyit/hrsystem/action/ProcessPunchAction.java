package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.service.EmpManager;
import org.crazyit.hrsystem.exception.HrException;
import static org.crazyit.hrsystem.service.EmpManager.*;

import java.util.*;
import java.text.SimpleDateFormat;

public class ProcessPunchAction extends ActionSupport
{
	// ��Action��������ҵ���߼����
	private EmpManager empMgr;
	// ����ע��ҵ���߼������setter����
	public void setEmpManager(EmpManager empMgr)
	{
		this.empMgr = empMgr;
	}
	// �����ϰ�򿨵ķ���
	public String come()
		throws Exception
	{
		return process(true);
	}
	// �����°�򿨵ķ���
	public String leave()
		throws Exception
	{
		return process(false);
	}
	private String process(boolean isCome)
		throws Exception
	{
		// ����ActionContextʵ��
		ActionContext ctx = ActionContext.getContext();
		// ��ȡHttpSession�е�user����
		String user = (String)ctx.getSession()
			.get(WebConstant.USER);
		System.out.println("-----��----" + user);
		String dutyDay = new java.sql.Date(
			System.currentTimeMillis()).toString();
		// ����ҵ���߼��������������
		int result = empMgr.punch(user ,dutyDay , isCome);
		switch(result)
		{
			case PUNCH_FAIL:
				addActionMessage("��ʧ��");
				break;
			case PUNCHED:
				addActionMessage("���Ѿ�����ˣ���Ҫ�ظ���");
				break;
			case PUNCH_SUCC:
				addActionMessage("�򿨳ɹ�");
				break;
		}
		return SUCCESS;
	}
}