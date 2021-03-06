package org.crazyit.hrsystem.vo;

import java.io.Serializable;

public class PaymentBean implements Serializable
{
	private static final long serialVersionUID = 48L;
	private String payMonth;
	private double amount;

	// �޲���Ĺ�����
	public PaymentBean()
	{
	}
	// ��ʼ��ȫ����Ա�����Ĺ�����
	public PaymentBean(String payMonth , double amount)
	{
		this.payMonth = payMonth;
		this.amount = amount;
	}

	// payMonth��setter��getter����
	public void setPayMonth(String payMonth)
	{
		this.payMonth = payMonth;
	}
	public String getPayMonth()
	{
		return this.payMonth;
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