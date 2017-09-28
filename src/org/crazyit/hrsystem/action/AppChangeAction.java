package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.action.base.EmpBaseAction;
import org.crazyit.hrsystem.vo.*;

import java.util.*;
import java.text.SimpleDateFormat;

public class AppChangeAction extends EmpBaseAction
{
	// ��װ�����춯���б�
	private List types;
	// types��setter��getter����
	public void setTypes(List types)
	{
		this.types = types;
	}
	public List getTypes()
	{
		return this.types;
	}
	// �����û�����
	public String execute()
		throws Exception
	{
		setTypes(mgr.getAllType());
		return SUCCESS;
	}
}