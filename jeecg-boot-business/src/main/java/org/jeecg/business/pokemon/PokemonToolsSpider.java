package org.jeecg.business.pokemon;

import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-11-27 14:02
 */
public class PokemonToolsSpider extends PokemonSpider{
    public static final String  BASE_URL ="http://wiki.52poke.com/wiki/%E9%81%93%E5%85%B7%E5%88%97%E8%A1%A8";
    @Override
    public void getHtmlPage(String url) throws Exception {
        HtmlPage page = webClient.getPage(url);
        HtmlBody tbody = (HtmlBody) page.getBody();
        DomNodeList<HtmlElement> lists = tbody.getElementsByTagName("table");
        for(int i=0;i< lists.size();i++){
            String xml = lists.get(i).asXml();
            if(xml.startsWith("<table class=\"a-l bgd-")){
                Document document = Jsoup.parse(xml);
                if (i>=1&&i<=39){
                    String toolTypeStr=lists.get(i).getAttribute("class");
                    String toolType=toolTypeStr.substring(toolTypeStr.indexOf("bgd-")+4,toolTypeStr.lastIndexOf("eplist")-1);
                    List<Element> elements=document.getElementsByTag("tr");
                    for (int j=1;j<elements.size();j++){
                        List<Element> tdElements=elements.get(j).getElementsByTag("td");
                        if (tdElements.size()==5){
                            String toolImg="http:"+tdElements.get(0).getElementsByTag("img").attr("data-url");
                            String toolName=tdElements.get(1).getElementsByTag("a").html();
                            String japanese=tdElements.get(2).html();
                            String english=tdElements.get(3).html();
                            String toolDesc=tdElements.get(4).html();
                            System.out.println("道具类型:"+toolType+"    名称:"+toolName+"    日文名:"+japanese+"    英文名:"+english+"    描述:"+toolDesc+"    图片:"+toolImg);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PokemonSpider pokemonSpider=new PokemonToolsSpider();
        pokemonSpider.WebClientInit();
        pokemonSpider.getHtmlPage(BASE_URL);
        pokemonSpider.closeWebClient();
    }
}
