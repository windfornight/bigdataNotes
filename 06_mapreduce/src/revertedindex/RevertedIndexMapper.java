package revertedindex;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class RevertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		// ���ݣ�I love Beijing and love Shanghai
		//�õ����������ĸ��ļ�������  /mydata/data01.txt
		String path = ((FileSplit)context.getInputSplit()).getPath().toString();
		
		//�õ��ļ���
		//���һ��б��
		int index = path.lastIndexOf("/");
		//�ļ���
		String fileName = path.substring(index+1);
		
		//�ִ�
		String[] words = value1.toString().split(" ");
		for(String w:words){
			//��ʽ��  ����:�ļ���
			context.write(new Text(w+":"+fileName), new Text("1"));
		}
	}
}
















