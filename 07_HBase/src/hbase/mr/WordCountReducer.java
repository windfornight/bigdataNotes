package hbase.mr;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//                                                  k3     v3         �൱����rowkey
public class WordCountReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable> {

	@Override
	protected void reduce(Text k3, Iterable<IntWritable> v3,Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable i:v3){
			sum = sum + i.get();
		}
		
		//��������е�һ����¼ Put����
		//ʹ�õ�����Ϊ�м�
		Put put = new Put(Bytes.toBytes(k3.toString()));
		put.addColumn(Bytes.toBytes("content"), Bytes.toBytes("count"), Bytes.toBytes(String.valueOf(sum)));
		
		//д��HBase
		context.write(new ImmutableBytesWritable(Bytes.toBytes(k3.toString())), put);
	}

}

















