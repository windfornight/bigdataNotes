package part;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/*
 * �����Լ��ķ������򣺸���Ա���Ĳ��źŽ��з���
                                              ����Map�����
 */
//                                                    k2    v2
public class MyEmployeeParitioner extends Partitioner<IntWritable, Employee>{

	/**
	 * numPartition�������������ٸ�����
	 */
	@Override
	public int getPartition(IntWritable k2, Employee v2, int numPartition) {
		// ��ν�������
		if(v2.getDeptno() == 10){
			//����1�ŷ�����
			return 1%numPartition;
		}else if(v2.getDeptno() == 20){
			//����2�ŷ�����
			return 2%numPartition;
		}else{
			//����0�ŷ�����
			return 3%numPartition;
		}
	}

}








