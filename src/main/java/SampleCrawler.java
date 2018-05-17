import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

public class SampleCrawler extends WebCrawler {

    public static final Pattern IMAGE_EXTENSION = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    public boolean shouldVisit(Page referPage, WebURL url){
        String href = url.getURL().toLowerCase();
        if(IMAGE_EXTENSION.matcher(href).matches()){
            return false;
        }
        return href.startsWith("http://en.wikipedia.org/wiki/");
    }

    public void visit(Page page){
        String url = page.getWebURL().getURL();

        if(page.getParseData() instanceof HtmlParseData){
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            if(text.contains("shipping route")){
                System.out.println("\nURL: " +url);
                System.out.println("Text: " +text);
                System.out.println("Text Length: " +text.length());
            }
        }
    }
}
