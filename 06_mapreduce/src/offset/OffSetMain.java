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
		// ����һ��job���������
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(OffSetMain.class);  //main�������ڵ�class
		
		//ָ��job��mapper�����������<k2 v2>
		job.setMapperClass(OffSetMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);    //k2������
		job.setMapOutputValueClass(Text.class);  //v2������
		
		//ָ��job����������
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//ִ��job
		job.waitForCompletion(true);
	}

}
