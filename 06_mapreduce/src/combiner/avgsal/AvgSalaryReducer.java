package combiner.avgsal;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//                                                                k4常量      v4 平均工资
public class AvgSalaryReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {

	@Override
	protected void reduce(Text key3, Iterable<IntWritable> value3,Context context) throws IOException, InterruptedException {
		// 对value3求平均
		int count = 0;
		int total = 0;
		for(IntWritable sal:value3){
			total += sal.get();  //工资总额
			count ++;           //总的人数
		}
		
		//输出
		context.write(new Text("Avarge Salary is :"), new DoubleWritable(total/count));
	}

}
