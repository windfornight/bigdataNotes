package part;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PartEmployeeMain {

	public static void main(String[] args) throws Exception {
		//  ����һ��job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(PartEmployeeMain.class);
		
		//ָ��job��mapper�����������   k2  v2
		job.setMapperClass(PartEmployeeMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);  //���ź�
		job.setMapOutputValueClass(Employee.class);   //Ա��
		
		//ָ������ķ�������
		job.setPartitionerClass(MyEmployeeParitioner.class);
		//ָ��������������
		job.setNumReduceTasks(3);
		
		
		//ָ��job��reducer�����������  k4   v4
		job.setReducerClass(PartEmployeeReducer.class);
		job.setOutputKeyClass(IntWritable.class);  //���ź�
		job.setOutputValueClass(Employee.class);   //Ա��
		
		//ָ��job������������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//ִ������
		job.waitForCompletion(true);

	}

}
