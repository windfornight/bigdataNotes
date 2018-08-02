package combiner.avgsal;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//                                                                k2����      v2����
public class AvgSalaryMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		// ���ݣ�7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = value1.toString();
		
		//�ִ�
		String[] words = data.split(",");
		
		context.write(new Text("sal"), new IntWritable(Integer.parseInt(words[5])));
	}

}
