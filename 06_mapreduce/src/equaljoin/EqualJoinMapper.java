package equaljoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EqualJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		// 得到数据
		String data = value1.toString();
		
		//分词
		String[] words = data.split(",");
		
		if(words.length == 8){
			//处理的是员工表     部门号                                                                                                      员工姓名
			context.write(new IntWritable(Integer.parseInt(words[7])), new Text(words[1]));
		}else{
			//处理的部门表         部门号                                                                                                      部门名称
			context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*"+words[1]));
		}
	}
}















