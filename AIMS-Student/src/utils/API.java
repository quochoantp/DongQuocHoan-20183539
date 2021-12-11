package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Class cung cap cac phuong thuc request len server va nhan du lieu response tra ve
 * Date: 11/12/2021
 * @author Hoan-20183539
 * @version 1.0
 */
public class API {
	
	/**
	 * Thuoc tinh giup format ngay thang theo format
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Thuoc tinh giup log thong tin ra console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * Thiet lap connection toi server
	 * @param url: duong dan toi server can request
	 * @param method: giao thuc api
	 * @param token: doan ma bam de xac thuc nguoi dung
	 * @return connection
	 * @throws IOException
	 */
	private static HttpURLConnection setupConnection(String url, String method) throws IOException{
		
		// phan 1: setup
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}

	/**
	 * Phuong thuc doc du lieu tra ve tu server
	 * @param conn: connection to server
	 * @return response: phan hoi tu server tra ve
	 * @throws IOException
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException {
		
		// phan 2: doc du lieu tra ve tu server
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		response.append(inputLine + "\n");
		in.close();
		String responseInfo = response.substring(0, response.length() - 1).toString();
		LOGGER.info("Respone Info: " + responseInfo);
		return responseInfo;
	}
	
	/**
	 * Phuong thuc giup goi cac API dang GET
	 * @param url: duong dan toi server can request
	 * @param token: doan ma hoa can cung cap de xac thuc nguoi dung
	 * @return response: phan hoi tu server (dang String)
	 * @throws Exception
	 */
	public static String get(String url) throws Exception {
		
		// phan 1: setup
		HttpURLConnection conn = setupConnection(url, "GET");
		
		// phan 2: doc du lieu tra ve tu server
		return readResponse(conn);
	}

	/**
	 * Phuong thuc giup goi cac API dang POST (thanh toan,...)
	 * @param url: duong dan toi server can request
	 * @param data: du lieu dua len server de xu ly (dang JSON)
	 * @return response: phan hoi tu server (dang String)
	 * @throws IOException
	 */
	public static String post(String url, String data) throws IOException {
		
		// cho phep PATCH protocol
		allowMethods("PATCH");
		
		// phan 1: setup
		HttpURLConnection conn = setupConnection(url, "POST");
		
		// phan 2: gui du lieu
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();
		
		// phan 3: doc du lieu tra ve tu server
		return readResponse(conn);
	}

	/**
	 * Phuong thuc cho phep goi cac loai giao thuc API khac nhau nhu PATCH, PUT,... (chi hoat dong voi Java 11)
	 * @deprecated chi hoat dong voi Java <= 11
	 * @param methods: giao thuc can cho cho phep (PATCH, PUT,...)
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
