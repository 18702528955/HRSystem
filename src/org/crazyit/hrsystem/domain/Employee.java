package org.crazyit.hrsystem.domain;

import java.io.Serializable;
import java.util.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Table(name="employee_inf")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorColumn(name="emp_type"
	, discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorValue(value="1")
public class Employee
	implements Serializable
{
	private static final long serialVersionUID = 48L;
	@Id @Column(name="emp_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	// ��ʶ����
	private Integer id;
	// Ա������
	@Column(name="emp_name", nullable=false, length=50 , unique=true)
	private String name;
	// Ա������
	@Column(name="emp_pass", nullable=false, length=50)
	private String pass;
	// Ա������
	@Column(name="emp_salary", nullable=false)
	private double salary;
	// Ա����Ӧ�ľ���
	@ManyToOne(targetEntity=Manager.class)
	@JoinColumn(name="mgr_id")
	private Manager manager;
	// Ա����Ӧ�ĳ��ڼ�¼
	@OneToMany(targetEntity=Attend.class, mappedBy="employee")
	private Set<Attend> attends = new HashSet<>();
	// Ա����Ӧ�Ĺ���֧����¼
	@OneToMany(targetEntity=Payment.class, mappedBy="employee")
	private Set<Payment> payments = new HashSet<>();

	// �޲���Ĺ�����
	public Employee()
	{
	}
	// ��ʼ��ȫ����Ա�����Ĺ�����
	public Employee(Integer id , String name , String pass ,
		double salary , Manager manager ,
		Set<Attend> attends , Set<Payment> payments)
	{
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.salary = salary;
		this.manager = manager;
		this.attends = attends;
		this.payments = payments;
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

	// name��setter��getter����
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	// pass��setter��getter����
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return this.pass;
	}

	// salary��setter��getter����
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	public double getSalary()
	{
		return this.salary;
	}

	// manager��setter��getter����
	public void setManager(Manager manager)
	{
		this.manager = manager;
	}
	public Manager getManager()
	{
		return this.manager;
	}

	// attends��setter��getter����
	public void setAttends(Set<Attend> attends)
	{
		this.attends = attends;
	}
	public Set<Attend> getAttends()
	{
		return this.attends;
	}

	// payments��setter��getter����
	public void setPayments(Set<Payment> payments)
	{
		this.payments = payments;
	}
	public Set<Payment> getPayments()
	{
		return this.payments;
	}
	// ���name��pass����дhashCode()����
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}
	// ���name��pass����дequals()������ֻҪname��pass��ͬ��Ա������Ϊ��ȡ�
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Employee other = (Employee) obj;
		if (name == null)
		{
			if (other.name != null) return false;
		}
		else if (!name.equals(other.name)) return false;
		if (pass == null)
		{
			if (other.pass != null) return false;
		}
		else if (!pass.equals(other.pass)) return false;
		return true;
	}
}