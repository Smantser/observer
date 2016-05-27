package onlinerParser;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		OnlinerParser op = new OnlinerParser();
		for(int i = 1; i < 10; i++)
			op.ParsePage("https://people.onliner.by/page/" + i);
		ArrayList<Page> pages =  op.getPages();
		for (Page page : pages) {
			System.out.println(page.getText());
		}
	}

}
