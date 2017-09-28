package org.crazyit.hrsystem.action.base;

import com.opensymphony.xwork2.ActionSupport;

import org.crazyit.hrsystem.service.MgrManager;


public class MgrBaseAction extends ActionSupport
{
	protected MgrManager mgr;

	public void setMgrManager(MgrManager mgr)
	{
		this.mgr = mgr;
	}
}