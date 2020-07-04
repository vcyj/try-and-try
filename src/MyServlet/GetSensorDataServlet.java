package MyServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kingvcn.newgetway.ZigBeeAPI;
import cn.kingvcn.newgetway.response.onCollectorResponse;

public class GetSensorDataServlet extends HttpServlet {
	List<String> sensorData=new ArrayList<String>();
	/**
	 * Constructor of the object.
	 */
	public GetSensorDataServlet() {
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
				ZigBeeAPI.getCollectorData(new onCollectorResponse(){
					public void onSoliTemperature(double data)
					{    
						String temp="土壤温度:"+data+"℃";
						sensorData.add(temp);
						System.out.println(temp);
					}
					public void onSoliHumidity(double data)
					{
						String temp="土壤湿度:"+data+"RH%";
						sensorData.add(temp);
						System.out.println(temp);
					}
					public void onLight(int data)
					{
						String temp="光照强度"+data+"lux";
						sensorData.add(temp);
						System.out.println(temp);
					}
					public void onCo2(int data)
					{
						String temp="CO2浓度:"+data+"ppm";
						sensorData.add(temp);
						System.out.println(temp);
					}
					public void onAirTemperature(double data)
					{
						String temp="空气温度:"+data+"℃";
						sensorData.add(temp);
						System.out.println(temp);
					}
					public void onAirHumidity(double data)
					{
						String temp="空气湿度"+data+"RH%";
						sensorData.add(temp);
						System.out.println(temp);
					}
				});
			}
		}).start();
		request.setAttribute("sensorData", sensorData);
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
