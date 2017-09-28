package org.crazyit.hrsystem.domain;

import java.io.Serializable;
import java.util.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@DiscriminatorValue(value="2")
public class Manager
	extends Employee implements Serializable
{
	private static final long serialVersionUID = 48L;
	// �þ������Ĳ���
	@Column(name="dept_name", length=50)
	private String dept;
	// �þ����Ӧ������Ա��
	@OneToMany(targetEntity=Employee.class, mappedBy="manager")
	private Set<Employee> employees = new HashSet<>();
	// �þ���ǩ���������
	@OneToMany(targetEntity=CheckBack.class , mappedBy="manager")
	private Set<CheckBack> checks = new HashSet<>();

	// �޲���Ĺ�����
	public Manager()
	{
	}
	// ��ʼ��ȫ����Ա�����Ĺ�����
	public Manager(String dept , Set<Employee> employees
		, Set<CheckBack> checks)
	{
		this.dept = dept;
		this.employees = employees;
		this.checks = checks;
	}

	// dept��setter��getter����
	public void setDept(String dept)
	{
		this.dept = dept;
	}
	public String getDept()
	{
		return this.dept;
	}

	// employees��setter��getter����
	public void setEmployees(Set<Employee> employees)
	{
		this.employees = employees;
	}
	public Set<Employee> getEmployees()
	{
		return this.employees;
	}

	// checks��setter��getter����
	public void setChecks(Set<CheckBack> checks)
	{
		this.checks = checks;
	}
	public Set<CheckBack> getChecks()
	{
		return this.checks;
	}
}