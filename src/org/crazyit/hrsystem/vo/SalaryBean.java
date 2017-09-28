package org.crazyit.hrsystem.vo;

import java.io.Serializable;

public class SalaryBean
	implements Serializable
{
	private static final long serialVersionUID = 48L;
	private String empName;
	private double amount;

	// �޲���Ĺ�����
	public SalaryBean()
	{
	}
	// ��ʼ��ȫ����Ա�����Ĺ�����
	public SalaryBean(String empName , double amount)
	{
		this.empName = empName;
		this.amount = amount;
	}

	// empName��setter��getter����
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	public String getEmpName()
	{
		return this.empName;
	}

	// amount��setter��getter����
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public double getAmount()
	{
		return this.amount;
	}

}