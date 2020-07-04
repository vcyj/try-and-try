package MyServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kingvcn.newgetway.ZigBeeAPI;
import cn.kingvcn.newgetway.bean.Relay;
import cn.kingvcn.newgetway.response.onRelayResponse;

public class GetRelayDataServlet extends HttpServlet {
	List<Relay> relaylist=new ArrayList<Relay>();
	Map map=new HashMap();
	/**
	 * Constructor of the object.
	 */
	public GetRelayDataServlet() {
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
			new Thread(new Runnable(){
				public void run(){
					ZigBeeAPI.getRelay(new onRelayResponse(){
						public void onData(Relay relay)
						{
							relaylist.clear();
							relaylist.add(relay);
							for(int i=0;i<relaylist.size();i++)
							{
								Relay rel=relaylist.get(i);
								for(int j=0;j<(rel.getRelayData().length()/2);j++)
								{
									Map map=new HashMap();
									map.put("sequence",rel.getRelaySequence());
									map.put("name", (j+1));
									map.put("newdata",rel.getRelayData());
									map.put("data",rel.getRelayData().subSequence(j*2, (j+1)*2));
									System.out.println(map);
								}
							}
							
						}
					});
				}
			}).start();
			request.setAttribute("relayData", map);
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
