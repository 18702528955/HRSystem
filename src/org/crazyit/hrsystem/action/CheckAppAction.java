package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.service.EmpManager;
import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.action.base.MgrBaseAction;

public class CheckAppAction extends MgrBaseAction
{
	// ��Ҫ�����������ID
	private int appid;
	// ��װ������
	private String result;
	// appid��setter��getter����
	public void setAppid(int appid)
	{
		this.appid = appid;
	}
	public int getAppid()
	{
		return this.appid;
	}

	// result��setter��getter����
	public void setResult(String result)
	{
		this.result = result;
	}
	public String getResult()
	{
		return this.result;
	}

	public String execute()
		throws Exception
	{
		// ����ActionContextʵ��
		ActionContext ctx = ActionContext.getContext();
		// ��ȡHttpSession�е�user����
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		// ͨ������
		if (result.equals("pass"))
		{
			mgr.check(appid, mgrName, true);
		}
		// �ܾ�����
		else if (result.equals("deny"))
		{
			mgr.check(appid, mgrName, false);
		}
		else
		{
			throw new Exception("����ʧ");
		}
		return SUCCESS;
	}
}