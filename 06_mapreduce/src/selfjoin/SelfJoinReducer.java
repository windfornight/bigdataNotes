package selfjoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SelfJoinReducer extends Reducer<IntWritable, Text, Text, Text> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Context context)
			throws IOException, InterruptedException {
		//��������������ϰ��������Ա��������
		String bossName = "";
		String empNameList = "";
		
		for(Text t:v3){
			String name = t.toString();
			
			//�ж��Ƿ����*
			int index = name.indexOf("*");
			if(index >= 0){
				//��ʾ�ϰ�����
				bossName = name.substring(1);
			}else{
				//��ʾԱ��������
				empNameList = name+";"+empNameList;
			}
		}
		
		//���
		//�жϣ����ϰ塢��Ա����ʱ��  �����
		if(bossName.length() >0 && empNameList.length() > 0)
			context.write(new Text(bossName), new Text(empNameList));
	}

}














