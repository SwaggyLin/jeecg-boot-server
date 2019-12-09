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
public class PokemonSkillsSpider extends PokemonSpider{
    public static final String  BASE_URL ="http://wiki.52poke.com/wiki/%E6%8B%9B%E5%BC%8F%E5%88%97%E8%A1%A8";
    @Override
    public void getHtmlPage(String url) throws Exception {
        HtmlPage page = webClient.getPage(url);
        HtmlBody tbody = (HtmlBody) page.getBody();
        DomNodeList<HtmlElement> lists = tbody.getElementsByTagName("table");

        for(int i=0;i< lists.size();i++){
            String xml = lists.get(i).asXml();
            if(xml.startsWith("<table class=\"eplist roundy")){
                Document document = Jsoup.parse(xml);
                if (i>=4&&i<=11){
                    String areaStr=lists.get(i).getAttribute("class");
                    String area=areaStr.substring(areaStr.indexOf("bg-")+3,areaStr.lastIndexOf("bd-")-1);
                    List<Element> elements=document.getElementsByTag("tr");
                    for (int j=1;j<elements.size();j++){
                        List<Element> tdElements=elements.get(j).getElementsByTag("td");
                        if (tdElements.size()==9){
                            String skillNo=tdElements.get(0).html();
                            String skillName=tdElements.get(1).getElementsByTag("a").get(0).html();
                            if (tdElements.get(1).getElementsByTag("a").size()==2){
                                skillName=tdElements.get(1).getElementsByTag("a").get(1).html();
                            }
                            String japanese=tdElements.get(2).html();
                            String english=tdElements.get(3).html();
                            String skillClassify=tdElements.get(4).getElementsByTag("a").get(0).html();
                            String skillType=tdElements.get(5).getElementsByTag("a").get(0).html();
                            String wl=tdElements.get(6).html();
                            String mz=tdElements.get(7).html();
                            String pp=tdElements.get(8).html();
                            System.out.println("地区:"+area+"        序号:"+skillNo+"        名称:"+skillName+"        日文名:"+japanese+"        英文名:"+english+"        属性:"+skillClassify+"        类型:"+skillType+"        威力:"+wl+"        命中:"+mz+"        pp:"+pp);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PokemonSpider pokemonSpider=new PokemonSkillsSpider();
        pokemonSpider.WebClientInit();
        pokemonSpider.getHtmlPage(BASE_URL);
        pokemonSpider.closeWebClient();
    }
}
