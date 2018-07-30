package serializable.java;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable{
	
	//����
	private int stuID;
	private String stuName;
	
	public Student(){
		
	}
			
	public int getStuID() {
		return stuID;
	}

	public void setStuID(int stuID) {
		this.stuID = stuID;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public static void main(String[] args) throws Exception {
		// ����һ��ѧ������
		Student s1 = new Student();
		s1.setStuID(1);
		s1.setStuName("Tom");
		
		//����һ�������
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("d:\\temp\\student.1"));
		out.writeObject(s1);
		
		out.close();

	}
}






















