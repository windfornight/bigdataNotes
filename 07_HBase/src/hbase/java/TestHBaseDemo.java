package hbase.java;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

public class TestHBaseDemo {


	public static void main(String[] args) throws Exception{
		//createTable();
		//insertOne();
		//get();
		//scan();
		dropTable();
	}

	private static void dropTable() throws IOException {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//����һ��HBase�Ŀͻ���
		HBaseAdmin client = new HBaseAdmin(conf);
		
		client.disableTable("students");
		client.deleteTable("students");
		
		client.close();
	}

	private static void scan()throws Exception  {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//ָ����Ŀͻ���
		HTable table = new HTable(conf, "students");
		
		//����һ��ɨ���� Scan
		Scan scanner = new Scan(); //----> �൱��: select * from students;
		//scanner.setFilter(filter)  ----> ������
		
		//ִ�в�ѯ
		ResultScanner rs = table.getScanner(scanner); //����ScannerResult ---> Oracle�е��α�
		for(Result r:rs){
			String name = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
			String age = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("age")));
			System.out.println(name + "   "+ age);
		}
		table.close();
	}

	private static void get()throws Exception  {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");		
		
		//ָ����Ŀͻ���
		HTable table = new HTable(conf, "students");
		
		//ͨ��Get��ѯ
		Get get = new Get(Bytes.toBytes("stu001"));
		//ִ�в�ѯ
		Result record = table.get(get);
		
		//���
		String name = Bytes.toString(record.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
		System.out.println(name);
		
		table.close();
	}

	private static void createTable() throws Exception {
		//ָ��ZooKeeper��ַ����zk�л�ȡHMaster�ĵ�ַ 
		//ע�⣺ZK���ص���HMaster��������, ����IP��ַ ---> ����Windows��hosts�ļ�
		//C:\Windows\System32\drivers\etc\hosts
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//����һ��HBase�Ŀͻ���
		HBaseAdmin client = new HBaseAdmin(conf);
	
		//������: ͨ�����������
		HTableDescriptor htd = new HTableDescriptor(TableName.valueOf("students"));
		
		//�������Ϣ
		HColumnDescriptor h1 = new HColumnDescriptor("info");
		HColumnDescriptor h2 = new HColumnDescriptor("grade");
		
		//����������
		htd.addFamily(h1);
		htd.addFamily(h2);
		
		//������
		client.createTable(htd);
		client.close();
	}

	//���뵥������
	private static void insertOne() throws Exception{
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//ָ����Ŀͻ���
		HTable table = new HTable(conf, "students");
		
		//����һ������
		Put put = new Put(Bytes.toBytes("stu001"));
		put.addColumn(Bytes.toBytes("info"),      //���������
				      Bytes.toBytes("name"),   //�е�����
				      Bytes.toBytes("Tom"));       //ֵ
		
		//����
		table.put(put);
		
		table.close();
	}
}















