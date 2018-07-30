package sort.object;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EmployeeSortMain {

	public static void main(String[] args) throws Exception {
		//  创建一个job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(EmployeeSortMain.class);
		
		//指定job的mapper和输出的类型   k2  v2
		job.setMapperClass(EmployeeSortMapper.class);
		job.setMapOutputKeyClass(Employee.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		//指定job的输入和输出的路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//执行任务
		job.waitForCompletion(true);
	}

}
