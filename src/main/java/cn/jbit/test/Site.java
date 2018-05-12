package cn.jbit.test;
public class Site implements Runnable{

	private int count=10;//几率剩余的票数
	private int number=0;//几率第几张票
	private int size=20;//
	/**
	 * 重写run方法
	 */
	public void run() {
		while(size>0){
			synchronized(this){
				//第一步修改数据
				number++;
				size--;
				count--;
				if(size<0){
					break;
				}
				try {
					Thread.sleep(500);//模拟网络延时
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(count>=0){
					//第二步显示信息
					System.out.println(number+Thread.currentThread().getName()+"抢到第"+number+"张票,剩余"+count+"张票!");
				}else {
					System.err.println(number+Thread.currentThread().getName()+"没有抢到票,剩余0张票!");
				}

			}
		}

	}

	public static void main(String[] args) {
		Site site=new Site();
		/*for (int i=0;i<20;i++){
			Thread perso=new Thread(site,"抢票代理"+i);
			perso.run();
		}*/
		Thread person1=new Thread(site,"逃跑跑");
		Thread person2=new Thread(site,"抢票代理");
		Thread person3=new Thread(site,"黄牛党");
		person1.start();
		person2.start();
		person3.start();
	}
}
