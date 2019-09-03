package org.jeecg.common.util;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class DySmsHelper {
	
	private final static Logger logger=LoggerFactory.getLogger(DySmsHelper.class);

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIgST8PXpSRW8w";
    static final String accessKeySecret = "xMidU4dZzwFRnj66Sye4MVviCfAFTF";
    
    /**
         * 登陆时采用的短信发送模板编码
     */
    public static final String LOGIN_TEMPLATE_CODE="SMS_146611032";
    
    /**
     * 忘记密码时采用的短信发送模板编码
     */
    public static final String FORGET_PASSWORD_TEMPLATE_CODE="SMS_146611032";
    
    
    /**
     * 注册时采用的短信发送模板编码
     */
    public static final String REGISTER_TEMPLATE_CODE="SMS_146611032";
    
    /**
         * 必填:短信签名-可在短信控制台中找到
     */
    public static final String signName="喵喵";

    /**
     * 发送短信接口
     * @param phone 电话号码
     * @param code 发送内容
     * @param templateCode 模板编码
     * @return
     * @throws ClientException
     */
    public static boolean sendSms(String phone,String code,String templateCode) throws ClientException {

        IAcsClient acsClient =initSmsClient();
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");
        
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        boolean result = false;

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        logger.info("短信接口返回的数据----------------");
        logger.info("{Code:" + sendSmsResponse.getCode()+",Message:" + sendSmsResponse.getMessage()+",RequestId:"+ sendSmsResponse.getRequestId()+",BizId:"+sendSmsResponse.getBizId()+"}");
        if ("OK".equals(sendSmsResponse.getCode())) {
            result = true;
        }

        return result;
        
    }

    /**
     * 查询短信接口
     * @param phone 电话号码
     * @param sendDate 发送日期(格式为 yyyyMMdd)
     * @param bizId 发送回执ID，即发送流水号
     * @return
     * @throws ClientException
     */
    public static QuerySendDetailsResponse querySendDetails(String phone,String sendDate,String bizId) throws ClientException {
        IAcsClient acsClient =initSmsClient();
        QuerySendDetailsRequest querySendDetailsRequest=new QuerySendDetailsRequest();
        querySendDetailsRequest.setCurrentPage(1l);
        querySendDetailsRequest.setPageSize(10l);
        querySendDetailsRequest.setPhoneNumber(phone);
        querySendDetailsRequest.setSendDate(sendDate);
        if (!StringUtils.isEmpty(bizId)){
            querySendDetailsRequest.setBizId(bizId);
        }
        QuerySendDetailsResponse sendSmsResponse=acsClient.getAcsResponse(querySendDetailsRequest);
        return sendSmsResponse;
    }

    /**
     * 初始化部分基础参数
     * @return
     * @throws ClientException
     */
    public static IAcsClient initSmsClient() throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        return acsClient;
    }

    /**
     * 日期格式化
     * @param date
     * @return
     */
    public static String getSmsFormatterDate(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }

//    public static void main(String[] args) throws ClientException {
//    	//sendSms("18695709329", "951020", "SMS_146611032");
//        querySendDetails("18695709329",getSmsFormatterDate(new Date()),null);
//    }
}
