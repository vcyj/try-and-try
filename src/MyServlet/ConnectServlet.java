package MyServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kingvcn.newgetway.ZigBeeAPI;
import cn.kingvcn.newgetway.socket.onSocketLinkListener;

public class ConnectServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ConnectServlet() {
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
		String ip="172.17.134.12";//request.getParameter("ip");
		int port=4000;//Integer.parseInt(request.getParameter("port"));
		
		Connection con=new Connection();
		con.setIp(ip);
		con.setPort(port);
		con.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("reConnect", con.getBack());
//		while(true)
//			;
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
class Connection extends Thread
{
	private String ip;
	private int port;
	private String back;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public void run()
	{
		ZigBeeAPI.linkGetWay(ip,port,new onSocketLinkListener(){
			public void onConnectCallback(int event)
			{
				//String back=null;
				switch(event)
				{
					case 1:back="µÇÂ¼³É¹¦";break;
					case 2:back="µÇÂ¼³¬Ê±";break;
					case 3:back="µÇÂ¼Òì³£";break;
					default:back="Á¬½Ó´íÎó";break;
				}
				System.out.println(back);
			}
		});
	}
	
}
