package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MyWebCrawler {

	public static void main(String[] args) {
		
		new MyWebCrawler().getArticles("https://www.shivajichandra.com/");
	}

	private void getArticles(String url) {
		
		//1. fetch the html code.
		try {
			Document documents = Jsoup.connect(url).get();
			System.out.println(documents);
			
			//2. get all the articles in Elements
			Elements elements = documents.getElementsByTag("article");
			elements.forEach(element ->{
				Elements baseUrlElements = element.getElementsByTag("a");
				String baseUrl = baseUrlElements.attr("href");
				System.out.println(baseUrl);
				try {
					Document baseDoc = Jsoup.connect(baseUrl).get();
					Elements baseElement = baseDoc.getElementsByTag("article");
					
					String fileName = baseUrl.replaceAll(".*/", "");
					FileWriter fw = new FileWriter("D:\\Tutorials\\MyBlogs\\"+fileName);
					fw.write(baseElement.html());
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
