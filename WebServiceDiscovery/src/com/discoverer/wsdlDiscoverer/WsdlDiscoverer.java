package com.discoverer.wsdlDiscoverer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class WsdlDiscoverer {

	/**
	 * Die Methode ließt Urls aus einer Datei. Die Datei muss dazu die URLs
	 * zeilenweise enthalten.
	 * 
	 * @param filePath
	 *            - Pfad zur Datei mit den Urls
	 * @return
	 */
	public static List<String> getWsdlUrlsFromFile(String filePath) {
		List<String> urls = new LinkedList<String>();

		FileReader fr;
		BufferedReader br = null;
		try {
			fr = new FileReader(new File(filePath));
			br = new BufferedReader(fr);

			String line = "";
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					urls.add(line);
				}
			}

		} catch (FileNotFoundException e) {
			System.err
					.println("WsdlDiscoverer discovered an issue. The specified file " + filePath + " cannot be read.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("WsdlDiscoverer discovered an issue while reading the urls from file.");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("The FileReader for file " + filePath + " could not be closed.");
				e.printStackTrace();
			}
		}
		return urls;
	}

	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}

}
