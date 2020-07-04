package MyServlet;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kingvcn.newgetway.ZigBeeAPI;

public class ControlRelayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ControlRelayServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new Thread(new Runnable(){
			public void run(){
				Scanner scan=new Scanner(System.in);
				//设置继电器
				while(true)
				{
					String relayData=null;
					boolean sign=true;
					System.out.println("请输入继电器序列号(2001,2003)");
					String sequence=scan.next();
					if(sequence.equals("2001"))
					{
						System.out.println("2001waitting...");
						int loop=scan.nextInt();
						if(loop==0)
						{
							System.out.println("即将关闭...");
							scan.close();
							break;
						}					
						switch(loop)
						{
							case 11:relayData="01000000FF";sign=true;break;
							case 21:relayData="00010000FF";sign=true;break;
							case 31:relayData="00000100FF";sign=true;break;
							case 41:relayData="00000001FF";sign=true;break;
//							case 10:relayData="00000000FF";sign=false;break;
//							case 20:relayData="00000000FF";sign=false;break;
//							case 30:relayData="00000000FF";sign=false;break;
//							case 40:relayData="00000000FF";sign=false;break;
							default:System.out.println("erroe");break;
						}
						setRelay(loop,sequence,relayData,sign);
					}
					else if(sequence.equals("2003"))
					{
						System.out.println("2003waitting...");
						int loop=scan.nextInt();
						if(loop==0)
						{
							System.out.println("即将关闭...");
							scan.close();
							break;
						}					
						switch(loop)
						{
							case 11:relayData="01000000FF";sign=true;break;
							case 21:relayData="00010000FF";sign=true;break;
							case 31:relayData="00000100FF";sign=true;break;
							case 41:relayData="00000001FF";sign=true;break;
							
							default:System.out.println("erroe");break;
						}
						setRelay(loop,sequence,relayData,true);
					}
				}	
			}
		}).start();
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	//设置继电器
		private static void setRelay(int num,String sequence,String relayData,boolean loopState)
		{
			//设置继电器
			switch(num)
			{
				case 1:ZigBeeAPI.setRelayLoopOneState(sequence, relayData, loopState);break;
				case 2:ZigBeeAPI.setRelayLoopTwoState(sequence, relayData, loopState);break;
				case 3:ZigBeeAPI.setRelayLoopThreeState(sequence, relayData, loopState);break;
				case 4:ZigBeeAPI.setRelayLoopFourState(sequence, relayData, loopState);break;
				default:System.out.println("error");break;
			}
		}
}
