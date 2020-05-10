package com.example.demo;

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
			System.out.println(elements);
			elements.forEach(element ->{
				System.out.println(element);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
