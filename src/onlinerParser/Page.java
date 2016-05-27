package onlinerParser;

import java.util.ArrayList;

public class Page {
	
	private String link;
	private String title;	
	private String imageLink;
	private String text;
	private ArrayList<String> tags = new ArrayList<String>();	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
}
