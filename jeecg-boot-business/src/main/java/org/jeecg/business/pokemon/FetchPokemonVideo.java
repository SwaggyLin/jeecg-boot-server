package org.jeecg.business.pokemon;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-11-19 15:29
 */
public class FetchPokemonVideo {

    public static void main(String[] args) {
        String regex="(http|https)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        List<String> list=new ArrayList<String>();
        String fetchResult=HttpUtil.get("https://m.weibo.cn/profile/info?uid=2864508912");
//        System.out.println(fetchResult);
        JSONObject fetchData= JSONUtil.parseObj(fetchResult);
        JSONObject pokemonData=JSONUtil.parseObj(fetchData.get("data"));
        JSONArray pokemonArr=JSONUtil.parseArray(pokemonData.get("statuses"));
        JSONObject topData=JSONUtil.parseObj(pokemonArr.get(0).toString());
        JSONObject pageInfoData=JSONUtil.parseObj(topData.get("page_info"));
//        System.out.println(pageInfoData.toString());
        JSONObject urlData=JSONUtil.parseObj(pageInfoData.get("urls"));
        String hisData=pageInfoData.get("content2").toString();
//        System.out.println(hisData);
        String videoUrl=urlData.get("mp4_720p_mp4").toString();
        //获取历史数据
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hisData);
        while (matcher.find()){
            System.out.println(matcher.group());
            list.add(matcher.group());
        }
        System.out.println(HttpUtil.get("https://weibo.com/2864508912/IhOWmkb0W"));
        //System.out.println(Convert.convertCharset(HttpUtil.get(list.get(0)),CharsetUtil.UTF_8,CharsetUtil.GBK));
//        System.out.println(videoUrl);

    }
}
