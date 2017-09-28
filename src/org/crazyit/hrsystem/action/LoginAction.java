package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.service.EmpManager;
import org.crazyit.hrsystem.domain.*;
import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.action.base.EmpBaseAction;
import static org.crazyit.hrsystem.service.EmpManager.*;

public class LoginAction extends EmpBaseAction
{
	// ����һ��������ΪԱ����¼�ɹ���Result��
	private final String EMP_RESULT = "emp";
	// ����һ��������Ϊ�����¼�ɹ���Result��
	private final String MGR_RESULT = "mgr";
	// ��װ�������
	private Manager manager;
	// ��¼����֤��
	private String vercode;
	// manager��setter��getter����
	public void setManager(Manager manager)
	{
		this.manager = manager;
	}
	public Manager getManager()
	{
		return this.manager;
	}

	// vercode��setter��getter����
	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		return this.vercode;
	}

	// �����û�����
	public String execute()
		throws Exception
	{
		// ����ActionContextʵ��
		ActionContext ctx = ActionContext.getContext();
		// ��ȡHttpSession�е�rand����
		String ver2 = (String)ctx.getSession().get("rand");
		if (vercode.equalsIgnoreCase(ver2))
		{
			// ����ҵ���߼������������¼����
			int result = mgr.validLogin(getManager());
			// ��¼���Ϊ��ͨԱ��
			if (result == LOGIN_EMP)
			{
				ctx.getSession().put(WebConstant.USER
					, manager.getName());
				ctx.getSession().put(WebConstant.LEVEL
					, WebConstant.EMP_LEVEL);
				addActionMessage("���Ѿ��ɹ���¼ϵͳ");
				return EMP_RESULT;
			}
			// ��¼���Ϊ����
			else if (result == LOGIN_MGR)
			{
				ctx.getSession().put(WebConstant.USER
					, manager.getName());
				ctx.getSession().put(WebConstant.LEVEL
					, WebConstant.MGR_LEVEL);
				addActionMessage("���Ѿ��ɹ���¼ϵͳ");
				return MGR_RESULT;
			}
			// �û�������벻ƥ��
			else
			{
				addActionMessage("�û���/���벻ƥ��");
				return ERROR;
			}
		}
		// ��֤�벻ƥ��
		addActionMessage("��֤�벻ƥ��,����������");
		return ERROR;
	}
}