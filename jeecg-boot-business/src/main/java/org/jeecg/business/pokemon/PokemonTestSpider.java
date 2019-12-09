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
public class PokemonTestSpider extends PokemonSpider{
    public static final String  BASE_URL ="http://www.imomoe.io/player/7710-0-2.html";
    @Override
    public void getHtmlPage(String url) throws Exception {
        HtmlPage page = webClient.getPage(url);
        HtmlBody tbody = (HtmlBody) page.getBody();
        System.out.println(tbody.asXml());
    }

    public static void main(String[] args) throws Exception {
        PokemonSpider pokemonSpider=new PokemonTestSpider();
        pokemonSpider.WebClientInit();
        pokemonSpider.getHtmlPage(BASE_URL);
        pokemonSpider.closeWebClient();
    }
}
