package selfjoin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SelfJoinMain {

	public static void main(String[] args) throws Exception {
		//  ����һ��job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(SelfJoinMain.class);
		
		//ָ��job��mapper�����������   k2  v2
		job.setMapperClass(SelfJoinMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		//ָ��job��reducer�����������  k4   v4
		job.setReducerClass(SelfJoinReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//ָ��job������������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//ִ������
		job.waitForCompletion(true);

	}

}
