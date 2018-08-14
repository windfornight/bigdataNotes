package hbase.filter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.ColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class TestHBaseFilter {

	public static void main(String[] args) throws Exception {
		//testSingleColumnValueFilter();
		//testColumnPrefixFilter();
		//testMultipleColumnPrefixFilter();
		//testRowFilter();
		testMoreFilter();
	}
	
	private static void testMoreFilter() throws Exception {
		/*
		 * ��ѯԱ������7839��Ա������   select ename from emp where empno=7839;
		 * 1��rowkey������ ����ѯ7839
		 * 2������ǰ׺����������ѯ����
		 */
		
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//������Ŀͻ���
		HTable emp = new HTable(conf,"emp");	
		
		//��һ��������
		RowFilter filter1 = new RowFilter(CompareOp.EQUAL, new RegexStringComparator("7839"));
		
		//�ڶ���������
		ColumnPrefixFilter filter2 = new ColumnPrefixFilter(Bytes.toBytes("ename"));
		
		//����һ��Filter��list
		/*
		 * Operator.MUST_PASS_ALL  �൱��  and
		 * Operator.MUST_PASS_ONE  �൱��  or
		 */
		FilterList filterList = new FilterList(Operator.MUST_PASS_ALL);
		filterList.addFilter(filter1);
		filterList.addFilter(filter2);
		
		//����Scanner
		Scan scanner = new Scan();
		scanner.setFilter(filterList);
		
		//ִ�в�ѯ
		ResultScanner rs = emp.getScanner(scanner);
		for(Result r:rs){
			String ename = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			System.out.println(ename);
		}
		
		emp.close();		
	}

	private static void testRowFilter() throws Exception {
		/*
		 * ��ѯ�м�Ϊ7839��Ա��нˮ  select * from emp where empno=7839;
		 */
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//������Ŀͻ���
		HTable emp = new HTable(conf,"emp");
		
		//����һ��������                                                                                                                     ���������һ��������ʽ
		RowFilter filter = new RowFilter(CompareOp.EQUAL, new RegexStringComparator("7839"));
		Scan scanner = new Scan();
		scanner.setFilter(filter);
		
		//ִ�в�ѯ
		ResultScanner rs = emp.getScanner(scanner);
		for(Result r:rs){
			String ename = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			String sal = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
			System.out.println(ename+"\t"+sal);
		}
		
		emp.close();		
	}

	private static void testMultipleColumnPrefixFilter() throws Exception {
		/*
		 * ��ѯԱ����������нˮ��select ename,sal from emp;
		 */
		
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//������Ŀͻ���
		HTable emp = new HTable(conf,"emp");
		
		//����һ����ά���ݣ�����ÿ���е�����
		byte[][] prefixes = new byte[][]{Bytes.toBytes("ename"),Bytes.toBytes("sal")};
		
		//����������ǰ׺������
		MultipleColumnPrefixFilter filter = new MultipleColumnPrefixFilter(prefixes);

		Scan scanner = new Scan();
		scanner.setFilter(filter);
		
		//ִ�в�ѯ
		ResultScanner rs = emp.getScanner(scanner);
		for(Result r:rs){
			String ename = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			String sal = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("sal")));
			System.out.println(ename+"\t"+sal);
		}
		
		emp.close();
		
	}

	private static void testColumnPrefixFilter() throws Exception {
		/*
		 * ��ѯԱ����������select ename from emp;
		 */
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//������Ŀͻ���
		HTable emp = new HTable(conf,"emp");
		
		//����һ��������
		ColumnPrefixFilter filter = new ColumnPrefixFilter(Bytes.toBytes("ename"));
		
		Scan scanner = new Scan();
		scanner.setFilter(filter);
		
		//ִ�в�ѯ
		ResultScanner rs = emp.getScanner(scanner);
		for(Result r:rs){
			String ename = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			System.out.println(ename);
		}
		
		emp.close();
	}

	//��ֵ�������� SingleColumnValueFilter
	public static void testSingleColumnValueFilter() throws Exception{
		/*
		 * ��ѯнˮ����3000��Ա��
		 * select * from emp where sal=3000;
		 */
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//������Ŀͻ���
		HTable emp = new HTable(conf,"emp");
		
		//����������
		SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("empinfo"),      //����
				                                                     Bytes.toBytes("sal"),   //����
				                                                     CompareOp.EQUAL,   //�Ƚ������
			                                                         Bytes.toBytes("3000"));      //ֵ
		
		//����һ��Scanner
		Scan scanner  = new Scan();
		scanner.setFilter(filter);
		
		//ִ�в�ѯ
		ResultScanner rs = emp.getScanner(scanner);
		for(Result r:rs){
			String ename = Bytes.toString(r.getValue(Bytes.toBytes("empinfo"), Bytes.toBytes("ename")));
			System.out.println(ename);
		}
		
		emp.close();
	}

}




















