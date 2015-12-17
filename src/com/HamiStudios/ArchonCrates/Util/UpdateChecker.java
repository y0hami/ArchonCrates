package com.HamiStudios.ArchonCrates.Util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

import org.bukkit.Bukkit;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UpdateChecker {

	private URL url;

	private String version;
	private String link;
	
	public UpdateChecker(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean needsUpdated() {

		try {
			InputStream input = this.url.openConnection().getInputStream();
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);
			
			Node latestFile = doc.getElementsByTagName("item").item(0);
			NodeList children = latestFile.getChildNodes();
			
			this.version = children.item(1).getTextContent().replaceAll("ArchonCrates", "").replaceAll("v", "").replaceAll("1.7.X & 1.8.X", "");
			this.link = children.item(3).getTextContent();

			String currentVersion = Bukkit.getServer().getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion();
			
			if(Float.parseFloat(this.version) > Float.parseFloat(currentVersion)) {
				return true;
			}
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isBetaVersion() {
		String currentVersion = Bukkit.getServer().getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion();
		if(currentVersion.contains("BETA")) {
			return true;
		}
		return false;
	}
	
	public String getLatestVersion() {
		return this.version;
	}
	public String getLatestLink() {
		return this.link;
	}
	
}
