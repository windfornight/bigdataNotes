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
		//  创建一个job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(PartEmployeeMain.class);
		
		//指定job的mapper和输出的类型   k2  v2
		job.setMapperClass(PartEmployeeMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);  //部门号
		job.setMapOutputValueClass(Employee.class);   //员工
		
		//指定任务的分区规则
		job.setPartitionerClass(MyEmployeeParitioner.class);
		//指定建立几个分区
		job.setNumReduceTasks(3);
		
		
		//指定job的reducer和输出的类型  k4   v4
		job.setReducerClass(PartEmployeeReducer.class);
		job.setOutputKeyClass(IntWritable.class);  //部门号
		job.setOutputValueClass(Employee.class);   //员工
		
		//指定job的输入和输出的路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//执行任务
		job.waitForCompletion(true);

	}

}
