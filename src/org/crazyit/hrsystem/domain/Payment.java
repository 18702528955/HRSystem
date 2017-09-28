package org.crazyit.hrsystem.domain;

import java.io.Serializable;
import java.sql.Date;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name="payment_inf")
public class Payment
	implements Serializable
{
	private static final long serialVersionUID = 48L;
	// ��ʶ����
	@Id @Column(name="pay_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="pay_month", nullable=false, length=50)
	private String payMonth;
	// ��н������
	@Column(name="pay_amount", nullable=false)
	private double amount;
	// ��н��Ա��
	@ManyToOne(targetEntity=Employee.class)
	@JoinColumn(name="emp_id", nullable=false)
	private Employee employee;

	// �޲���Ĺ�����
	public Payment()
	{
	}
	// ��ʼ��ȫ����Ա�����Ĺ�����
	public Payment(Integer id , String payMonth ,
		double amount , Employee employee)
	{
		this.id = id;
		this.payMonth = payMonth;
		this.amount = amount;
		this.employee = employee;
	}

	// id��setter��getter����
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
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

	// employee��setter��getter����
	public void setEmployee(Employee employee)
	{
		this.employee = employee;
	}
	public Employee getEmployee()
	{
		return this.employee;
	}
}