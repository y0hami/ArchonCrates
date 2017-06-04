package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Enums.UpdateState;
import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.X509Certificate;

public class UpdateChecker {

	private UpdateResponse updateResponse;

	public UpdateState check(String currentVersion) {

		try {

			SSLContext sc = SSLContext.getInstance("SSL");

			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());


			URL url = new URL("https://archoncrates.com/api/update?version=" + currentVersion);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			con.setRequestProperty("User-Agent", "Mozilla/5.0");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			Gson gson = new Gson();
			this.updateResponse = gson.fromJson(response.toString(), UpdateResponse.class);

			if(this.updateResponse.update) {
				return UpdateState.UPDATE;
			}

		} catch(Exception e) {
			e.printStackTrace();
			return UpdateState.ERROR;
		}

		return UpdateState.NO_UPDATE;
	}


	public String getNewVersion() {
		return this.updateResponse.response.version;
	}

	public String getNote() {
		return this.updateResponse.response.note;
	}

	public String getDownloadURL() {
		return this.updateResponse.response.downloadURL;
	}

}

class UpdateResponse {

	String status;
	boolean update;
	UpdateValue response;

}

class UpdateValue {

	String version;
	String note;
	String downloadURL;

}