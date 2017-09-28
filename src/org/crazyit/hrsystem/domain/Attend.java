package org.crazyit.hrsystem.domain;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Table(name="attend_inf")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Attend
	implements Serializable
{
	private static final long serialVersionUID = 48L;

	// ����ʶ����
	@Id @Column(name="attend_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	// ��������
	@Column(name="duty_day", nullable=false, length=50)
	private String dutyDay;
	// ��ʱ��
	@Column(name="punch_time")
	private Date punchTime;
	// ��?�δ��Ƿ�Ϊ�ϰ��
	@Column(name="is_come" , nullable=false)
	private boolean isCome;
	// ���γ��ڵ�����
	@ManyToOne(targetEntity=AttendType.class)
	@JoinColumn(name="type_id", nullable=false)
	private AttendType type;
	// ���γ��ڹ�����Ա��
	@ManyToOne(targetEntity=Employee.class)
	@JoinColumn(name="emp_id", nullable=false)
	private Employee employee;

	// �޲���Ĺ�����
	public Attend()
	{
	}
	// ��ʼ��ȫ����Ա�����Ĺ�����
	public Attend(Integer id , String dutyDay ,
		Date punchTime , boolean isCome ,
		AttendType type , Employee employee)
	{
		this.id = id;
		this.dutyDay = dutyDay;
		this.punchTime = punchTime;
		this.isCome = isCome;
		this.type = type;
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

	// dutyDay��setter��getter����
	public void setDutyDay(String dutyDay)
	{
		this.dutyDay = dutyDay;
	}
	public String getDutyDay()
	{
		return this.dutyDay;
	}

	// punchTime��setter��getter����
	public void setPunchTime(Date punchTime)
	{
		this.punchTime = punchTime;
	}
	public Date getPunchTime()
	{
		return this.punchTime;
	}

	// isCome��setter��getter����
	public void setIsCome(boolean isCome)
	{
		this.isCome = isCome;
	}
	public boolean getIsCome()
	{
		return this.isCome;
	}

	// type��setter��getter����
	public void setType(AttendType type)
	{
		this.type = type;
	}
	public AttendType getType()
	{
		return this.type;
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

	// ���employee��isCome��dutyDay����дhashCode()����
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((dutyDay == null) ? 0 : dutyDay.hashCode());
		result = prime * result
			+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + (isCome ? 1231 : 1237);
		return result;
	}
	// ���employee��isCome��dutyDay����дequals()����
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Attend other = (Attend) obj;
		if (dutyDay == null)
		{
			if (other.dutyDay != null) return false;
		}
		else if (!dutyDay.equals(other.dutyDay)) return false;
		if (employee == null)
		{
			if (other.employee != null) return false;
		}
		else if (!employee.equals(other.employee)) return false;
		if (isCome != other.isCome) return false;
		return true;
	}
}