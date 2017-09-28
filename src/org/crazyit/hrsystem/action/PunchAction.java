package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.service.EmpManager;
import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.action.base.EmpBaseAction;

import java.util.*;
import java.text.SimpleDateFormat;

public class PunchAction extends EmpBaseAction
{
	// ��װ�������punchIsValid��Ա����
	private int punchIsValid;
	// punchIsValid��Ա������setter��getter����
	public void setPunchIsValid(int punchIsValid)
	{
		this.punchIsValid = punchIsValid;
	}
	public int getPunchIsValid()
	{
		return this.punchIsValid;
	}
	public String execute()
		throws Exception
	{
		// ����ActionContextʵ��
		ActionContext ctx = ActionContext.getContext();
		// ��ȡHttpSession�е�user����
		String user = (String)ctx.getSession()
			.get(WebConstant.USER);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// ��ʽ����ǰʱ��
		String dutyDay = sdf.format(new Date());
		// ����ҵ���߼����������û�����
		int result = mgr.validPunch(user , dutyDay);
		setPunchIsValid(result);
		return SUCCESS;
	}
}