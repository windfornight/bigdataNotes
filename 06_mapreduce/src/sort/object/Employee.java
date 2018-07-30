package sort.object;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

//1��Ҫ��Employee��Ϊkey2����Ҫʵ�����л�
//2��Ա������Employee�࣬�ɱ������

//����: 7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
public class Employee implements WritableComparable<Employee>{
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", sal=" + sal + ", deptno=" + deptno + "]";
	}
	
	@Override
	public int compareTo(Employee o) {
		// ����е�����select * from emp order by deptno,sal;
		//���ȡ�����deptno����
		if(this.deptno > o.getDeptno()){
			return 1;
		}else if(this.deptno < o.getDeptno()){
			return -1;
		}
		
		//���deptno��ȣ�����sal����
		if(this.sal >= o.getSal()){
			return 1;
		}else{
			return -1;
		}
	}	

//	@Override
//	public int compareTo(Employee o) {
//		// �����Լ��ıȽϹ���һ��������
//		//����������Ա����нˮ����
//		if(this.sal >= o.getSal()){
//			return 1;
//		}else{
//			return -1;
//		}
//	}
	
	@Override
	public void readFields(DataInput input) throws IOException {
		// �����л�
		this.empno = input.readInt();
		this.ename = input.readUTF();
		this.job = input.readUTF();
		this.mgr = input.readInt();
		this.hiredate = input.readUTF();
		this.sal = input.readInt();
		this.comm = input.readInt();
		this.deptno = input.readInt();
	}
	
	@Override
	public void write(DataOutput output) throws IOException {
		// ���л�
		output.writeInt(this.empno);
		output.writeUTF(this.ename);
		output.writeUTF(this.job);
		output.writeInt(this.mgr);
		output.writeUTF(this.hiredate);
		output.writeInt(this.sal);
		output.writeInt(this.comm);
		output.writeInt(this.deptno);
	}	
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
}
