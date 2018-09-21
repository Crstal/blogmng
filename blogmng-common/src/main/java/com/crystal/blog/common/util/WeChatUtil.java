package com.crystal.blog.common.util;

import com.alibaba.fastjson.JSONObject;
import com.crystal.blog.common.bean.login.AccessToken;
import com.crystal.blog.common.bean.login.MyX509TrustManager;
import com.crystal.blog.common.bean.login.Oauth2Token;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 微信公众平台通用
 */
@Slf4j
public class WeChatUtil {

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public final static String o_auth_openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public final static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACC_TOKEN";
    public final static String authorize_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    /**
     * 获取access_token
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null,"");
        // 如果请求成功
        if (null != jsonObject) {
            accessToken = new AccessToken(jsonObject.getString("access_token"),jsonObject.getInteger("expires_in"));
        }
        return accessToken;
    }

    public static String getOAuthOpenId(String appid, String secret, String code ) {
        String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null,"");
        String openid = "";
        // 如果请求成功
        if (null != jsonObject) {
            openid = jsonObject.getString("openid");
        }
        return openid;
    }


    public static Oauth2Token getAccessTokenInfo(String appid, String secret, String code ) {
        Oauth2Token oauth2Token = new Oauth2Token();
        String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null,"");
        // 如果请求成功
        if (null != jsonObject) {
            oauth2Token.setOpenId(jsonObject.getString("openid"));
            oauth2Token.setAccessToken(jsonObject.getString("access_token"));
            oauth2Token.setExpiresIn(jsonObject.getInteger("expires_in"));
            oauth2Token.setRefreshToken(jsonObject.getString("refresh_token"));
            oauth2Token.setScope(jsonObject.getString("scope"));
        }
        return oauth2Token;
    }

    /**
     * 第一步：用户同意授权，获取code(引导关注者打开如下页面：)
     *  获取 code、state
     */
    public static String getStartURLToGetCode(String appid, String redirectUrl) {
        String takenUrl= authorize_url.replace("APPID", appid);
        takenUrl= takenUrl.replace("REDIRECT_URI", URLEncoder.encode(redirectUrl));
        //FIXME ： snsapi_userinfo
        takenUrl= takenUrl.replace("SCOPE", "snsapi_userinfo");
        log.info("微信登陆打开网址：{}", takenUrl);
        return takenUrl;
    }


    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr, String urlParameters) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            httpUrlConn.setRequestProperty("Content-Type", "application/json");

            httpUrlConn.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));

            httpUrlConn.setRequestProperty("Accept-Charset", "utf-8");
            httpUrlConn.setRequestProperty("contentType", "utf-8");

            //Send request
            DataOutputStream wr = new DataOutputStream (httpUrlConn.getOutputStream());


            System.out.println("send ---------- " + urlParameters);
            wr.write(urlParameters.getBytes("utf-8"));
            //wr.writeBytes(urlParameters);
            wr.close();

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                //outputStream.write(outputStr.getBytes("GBK"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            httpUrlConn.disconnect();
            System.out.println("get message : " + buffer.toString());
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }
}
