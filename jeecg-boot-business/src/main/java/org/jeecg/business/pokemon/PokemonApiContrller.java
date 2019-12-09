package org.jeecg.business.pokemon;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author linminfeng
 * @Date 2019-11-25 13:06
 */
@Controller
@RequestMapping("/v2/pokemon")
public class PokemonApiContrller {
    @ResponseBody
    @RequestMapping("getLatestVideo.do")
    public String getLatestVideo(){
        String fetchResult= HttpUtil.get("https://m.weibo.cn/profile/info?uid=2864508912");
        JSONObject fetchData= JSONUtil.parseObj(fetchResult);
        JSONObject pokemonData=JSONUtil.parseObj(fetchData.get("data"));
        JSONArray pokemonArr=JSONUtil.parseArray(pokemonData.get("statuses"));
        JSONObject topData=JSONUtil.parseObj(pokemonArr.get(0).toString());
        JSONObject pageInfoData=JSONUtil.parseObj(topData.get("page_info"));
        JSONObject urlData=JSONUtil.parseObj(pageInfoData.get("urls"));
        String videoUrl=urlData.get("mp4_hd_mp4").toString();
        return videoUrl;
    }
}
