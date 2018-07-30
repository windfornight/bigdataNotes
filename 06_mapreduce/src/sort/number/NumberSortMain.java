package sort.number;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class NumberSortMain {

	public static void main(String[] args) throws Exception {
		// ����һ��job���������
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(NumberSortMain.class);  //main�������ڵ�class
		
		//ָ��job��mapper�����������<k2 v2>
		job.setMapperClass(NumberSortMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);    //k2������
		job.setMapOutputValueClass(NullWritable.class);  //v2������
		
		//ָ���Լ��ıȽ���
		job.setSortComparatorClass(MyNumberComparator.class);
		
		//ָ��job����������
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//ִ��job
		job.waitForCompletion(true);
	}

}
