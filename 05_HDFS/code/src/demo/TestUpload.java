package demo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class TestUpload {

	@Test
	public void test1() throws Exception{
		//构造一个输入流
		InputStream in = new FileInputStream("d:\\dowload\\hadoop-2.4.1.zip");
		
		
		//配置NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//客户端
		FileSystem client = FileSystem.get(conf);
		
		//得到一个输出流
		OutputStream out = client.create(new Path("/tools/a.zip"));
		
		//构造一个缓冲区
		byte[] buffer = new byte[1024];
		int len=0;
		while((len=in.read(buffer)) >0) {
			//读取到了数据
			out.write(buffer, 0, len);
		}
		
		out.flush();
		
		out.close();
		in.close();
	}
	
	@Test
	public void test2() throws Exception{
		//构造一个输入流
		InputStream in = new FileInputStream("d:\\dowload\\hadoop-2.4.1.zip");
		
		//配置NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//客户端
		FileSystem client = FileSystem.get(conf);
		
		//得到一个输出流
		OutputStream out = client.create(new Path("/tools/b.zip"));		
		
		//使用工具类简化程序
		IOUtils.copyBytes(in, out, 1024);
	}
}


















