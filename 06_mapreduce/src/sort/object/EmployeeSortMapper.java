package sort.object;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
public class EmployeeSortMapper extends Mapper<LongWritable, Text, Employee, NullWritable> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//���ݣ�7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = value1.toString();
		
		//�ִ�
		String[] words = data.split(",");
		
		//����Ա������
		Employee e = new Employee();
		//����Ա��������
		
		//Ա����
		e.setEmpno(Integer.parseInt(words[0]));
		//����
		e.setEname(words[1]);
		//ְλ
		e.setJob(words[2]);
		//�ϰ��: ע�� ����û���ϰ��
		try{
			e.setMgr(Integer.parseInt(words[3]));
		}catch(Exception ex){
			//û���ϰ��
			e.setMgr(-1);
		}
		
		//��ְ����
		e.setHiredate(words[4]);
		
		//��н
		e.setSal(Integer.parseInt(words[5]));
		
		//����ע�⣺����Ҳ����û��
		try{
			e.setComm(Integer.parseInt(words[6]));
		}catch(Exception ex){
			//û�н���
			e.setComm(0);
		}	
		
		//���ź�
		e.setDeptno(Integer.parseInt(words[7]));	
		
		//���
		context.write(e, NullWritable.get());
	}

}
















