package offset;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class OffSetMain {

	public static void main(String[] args) throws Exception {
		// 创建一个job和任务入口
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(OffSetMain.class);  //main方法所在的class
		
		//指定job的mapper和输出的类型<k2 v2>
		job.setMapperClass(OffSetMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);    //k2的类型
		job.setMapOutputValueClass(Text.class);  //v2的类型
		
		//指定job的输入和输出
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//执行job
		job.waitForCompletion(true);
	}

}
