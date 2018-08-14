package hbase.mr;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

//�����HBase���е�һ����¼                                                                                           k2       v2
public class WordCountMapper extends TableMapper<Text, IntWritable> {

	@Override
	protected void map(ImmutableBytesWritable key, Result value,Context context)
			throws IOException, InterruptedException {
		/*
		 * key �൱���м�
		 * value һ�м�¼
		 */
		// I love Beijing
		String data = Bytes.toString(value.getValue(Bytes.toBytes("content"), Bytes.toBytes("info")));
		
		String[] words = data.split(" ");
		for(String w:words){
			context.write(new Text(w), new IntWritable(1));
		}
	}

}
