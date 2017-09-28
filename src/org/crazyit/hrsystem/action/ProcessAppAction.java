package org.crazyit.hrsystem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.*;
import org.apache.struts2.interceptor.*;

import org.crazyit.hrsystem.service.EmpManager;
import org.crazyit.hrsystem.exception.HrException;
import org.crazyit.hrsystem.action.base.EmpBaseAction;
import org.crazyit.hrsystem.vo.*;

import java.util.*;
import java.text.SimpleDateFormat;

public class ProcessAppAction extends EmpBaseAction
{
	// �����춯�ĳ���ID
	private int attId;
	// ϣ��ı䵽��������
	private int typeId;
	// ��������
	private String reason;
	// attId��setter��getter����
	public void setAttId(int attId)
	{
		this.attId = attId;
	}
	public int getAttId()
	{
		return this.attId;
	}

	// typeId��setter��getter����
	public void setTypeId(int typeId)
	{
		this.typeId = typeId;
	}
	public int getTypeId()
	{
		return this.typeId;
	}

	// reason��setter��getter����
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	public String getReason()
	{
		return this.reason;
	}

	// �����û�����
	public String execute()
		throws Exception
	{
		// �����춯����
		boolean result = mgr.addApplication(attId , typeId , reason);
		// �������ɹ�
		if(result)
		{
			addActionMessage("���Ѿ�����ɹ����ȴ�������");
		}
		else
		{
			addActionMessage("����ʧ�ܣ���ע�ⲻҪ�ظ�����");
		}
		return SUCCESS;
	}
}