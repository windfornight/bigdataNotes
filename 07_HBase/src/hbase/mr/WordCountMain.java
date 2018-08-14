package hbase.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class WordCountMain {

	public static void main(String[] args) throws Exception {
		//��HBase�ж�ȡ����
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");		
		
		//��������
		Job job = Job.getInstance(conf);
		job.setJarByClass(WordCountMain.class);
		
		//����һ��ɨ������ȡҪ���������
		Scan scan = new Scan();
		//ָ��ɨ����ɨ�������
		scan.addColumn(Bytes.toBytes("content"), Bytes.toBytes("info"));
				
		//ָ��Map,�����Ǳ�  word
		TableMapReduceUtil.initTableMapperJob("word", scan, WordCountMapper.class, 
				                              Text.class, IntWritable.class, job);
		
		//ָ��Reduce ����ı�  result
		TableMapReduceUtil.initTableReducerJob("result", WordCountReducer.class, job);
		
		job.waitForCompletion(true);
	}

}

















