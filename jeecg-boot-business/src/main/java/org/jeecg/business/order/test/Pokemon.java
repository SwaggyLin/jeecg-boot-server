package org.jeecg.business.order.test;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-11-19 15:29
 */
public class Pokemon {
    public static void main(String[] args) {
        //String fetchResult=HttpUtil.get("https://cn.portal-pokemon.com/play/pokedex/api/v1");
        String fetchResult=HttpUtil.get("https://m.weibo.cn/profile/info?uid=2864508912");
        fetchResult=UnicodeUtil.toString(fetchResult).replace("\\","");
        System.out.println(fetchResult);
        System.out.println(System.currentTimeMillis());
    }
}
