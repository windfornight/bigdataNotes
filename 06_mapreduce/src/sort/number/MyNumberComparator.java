package sort.number;

import org.apache.hadoop.io.IntWritable;

//我自己的一个比较器，实现数字的降序排序
public class MyNumberComparator extends IntWritable.Comparator {

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		// 定义自己的排序比较规则：改成降序
		return -super.compare(b1, s1, l1, b2, s2, l2);
	}

}
