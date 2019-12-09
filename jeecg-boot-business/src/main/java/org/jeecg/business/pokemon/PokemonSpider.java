package org.jeecg.business.pokemon;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-11-27 14:00
 */
public abstract class PokemonSpider {
    static WebClient webClient=new WebClient(BrowserVersion.CHROME);
    //浏览器初始化
    public void WebClientInit(){
        webClient.getCookieManager().setCookiesEnabled(true);//设置cookie是否可用
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setRedirectEnabled(true);// 启动客户端重定向
        webClient.getOptions().setCssEnabled(false);//禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setJavaScriptEnabled(true); // 启动JS
        webClient.getOptions().setUseInsecureSSL(true);//忽略ssl认证
        webClient.getOptions().setThrowExceptionOnScriptError(false);//运行错误时，不抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 设置Ajax异步
        webClient.getOptions().setMaxInMemory(50000);
    }

    public void closeWebClient(){
        webClient.close();
        webClient=new WebClient(BrowserVersion.CHROME);
    }
    //抓取Url中的数据
    public abstract void getHtmlPage(String url) throws Exception;
}
