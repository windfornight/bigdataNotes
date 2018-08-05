package mrunit;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

public class MRUnitWordCount {

	@Test
	public void testMapper() throws Exception{
		//����һ����������
		System.setProperty("hadoop.home.dir", "D:\\download\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//����һ�����Զ���
		WordCountMapper mapper = new WordCountMapper();
		
		//����һ��MapDriver�����е�Ԫ����
		MapDriver<LongWritable, Text, Text, IntWritable> driver = new MapDriver<>(mapper);
		
		//ָ��map�����룺 k1  v1
		driver.withInput(new LongWritable(1), new Text("I love Beijing"));
		
		//ָ��Map�����: k2   v2  -----> �����������õ��Ľ��
		driver.withOutput(new Text("I"), new IntWritable(1))
			  .withOutput(new Text("love"), new IntWritable(1))
			  .withOutput(new Text("Beijing"), new IntWritable(1));
		
		//ִ�е�Ԫ���ԣ��Աȣ������Ľ����ʵ�����еĽ��
		driver.runTest();
	}
	
	@Test
	public void testReducer() throws Exception{
		//����һ����������
		System.setProperty("hadoop.home.dir", "D:\\download\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//����һ�����Զ���
		WordCountReducer reducer = new WordCountReducer();
		
		//����һ��ReducerDriver�����е�Ԫ����
		ReduceDriver<Text, IntWritable, Text, IntWritable> driver = new ReduceDriver<>(reducer);
		
		//����һ��value3
		List<IntWritable> value3 = new ArrayList<>();
		value3.add(new IntWritable(1));
		value3.add(new IntWritable(1));
		value3.add(new IntWritable(1));
		
		//ָ��reducer������
		driver.withInput(new Text("Beijing"), value3);
		
		//ָ��reducer�����: �����õ��Ľ��
		driver.withOutput(new Text("Beijing"), new IntWritable(3));
		
		//ִ�е�Ԫ����
		driver.runTest();
	}
	
	@Test
	public void testJob() throws Exception{
		//����һ����������
		System.setProperty("hadoop.home.dir", "D:\\download\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//����һ�����Զ���
		WordCountMapper mapper = new WordCountMapper();
		WordCountReducer reducer = new WordCountReducer();	
		
		//����һ��MapReduceDriver�����е�Ԫ����
		MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>
			driver = new MapReduceDriver<>(mapper,reducer);
		
		//ָ��map������
		driver.withInput(new LongWritable(1), new Text("I love Beijing"))
			  .withInput(new LongWritable(1), new Text("I love China"))
			  .withInput(new LongWritable(1), new Text("Beijing is the capital of China"));
		
		//ָ��reducer�����  ---> �����õ����
		driver.withOutput(new Text("Beijing"), new IntWritable(2))
			  .withOutput(new Text("China"), new IntWritable(2))
			  .withOutput(new Text("I"), new IntWritable(2))
			  .withOutput(new Text("love"), new IntWritable(2))
			  .withOutput(new Text("is"), new IntWritable(1))
			  .withOutput(new Text("the"), new IntWritable(1))
			  .withOutput(new Text("of"), new IntWritable(1))
			  .withOutput(new Text("capital"), new IntWritable(1));
		
		//��ҵ��������ĳ���Ķ���
		
		driver.runTest();
	}
}














