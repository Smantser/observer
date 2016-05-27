package onlinerParser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OnlinerParser {
	
	ArrayList<Page> pages;
	
	public OnlinerParser(){
		pages = new ArrayList<>();
	}
	
	public void ParsePage(String page) throws IOException{
		Document document = Jsoup.connect(page).get();		
		Elements pageArticles = document.getElementsByTag("section").first().getElementsByTag("article");
		for (Element article : pageArticles) {
			Page p = new Page();
			
			p.setLink(article.attr("link"));
			p.setTitle(article.select("h3.b-posts-1-item__title").first().getElementsByTag("a").first().getElementsByTag("span").first().text());
			p.setImageLink(article.getElementsByTag("img").first().attr("src"));
			
			Element text = article.select("div.b-posts-1-item__text").first().getElementsByTag("p").first();
			text.getElementsByTag("a").remove();
			p.setText(text.text());
			ArrayList<String> pageTags = new ArrayList<>();
			Elements tags = article.select("div.b-post-tags-1").first().getElementsByTag("a");
			for (Element tag : tags) {
				pageTags.add(tag.text());
			}
			
			p.setTags(pageTags);			
			
			pages.add(p);
		}
	}
	
	public ArrayList<Page> getPages(){
		return pages;
	}
}
