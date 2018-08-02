package selfjoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelfJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//���ݣ�7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = value1.toString();
		
		//�ִ�
		String[] words = data.split(",");
		
		//���
		//1����Ϊ�ϰ��
		context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*" + words[1]));
		
		//2����ΪԱ����                                     ����ϰ�ſ��ܻ����Exception
		try{
			context.write(new IntWritable(Integer.parseInt(words[3])), new Text(words[1]));
		}catch(Exception ex){
			//��������쳣����ʾ�Ǵ��ϰ�
			context.write(new IntWritable(-1), new Text(words[1]));
		}
	}
}















