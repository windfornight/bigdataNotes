package sort.number;

import org.apache.hadoop.io.IntWritable;

//���Լ���һ���Ƚ�����ʵ�����ֵĽ�������
public class MyNumberComparator extends IntWritable.Comparator {

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		// �����Լ�������ȽϹ��򣺸ĳɽ���
		return -super.compare(b1, s1, l1, b2, s2, l2);
	}

}
