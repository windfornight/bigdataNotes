

/*
什么是-D的参数？ 获取命令行上的参数
1、使用main方法的args
2、使用-D的参数
*/

public class TestD{
	
	public static void main(String[] args){
		method1();
	}
	
	public static void method1(){
		//在这里，获取命令行上的参数  name=Tom gender=Male
		String name = System.getProperty("name");
		String gender = System.getProperty("gender");
		System.out.println(name+"\t"+gender);
	}
}