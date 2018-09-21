package com.crystal.blog.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WeChatAccessTokenJob {

    /*@Autowired
    private LoginProperties loginProperties;

    @Autowired
    private WeChatService weChatService;

    @Scheduled(fixedRate = 6000000)
    public void scheduled(){
        AccessToken accessToken = WeChatUtil.getAccessToken(loginProperties.getWeChatAppId(), loginProperties.getWeChatAppSecret());
        if (null != accessToken) {
            log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpire_in(), accessToken.getAccess_token());
            // 休眠7000秒
            weChatService.saveAccessToken(accessToken);
        } else {
            log.info("获取access_token失败");
        }
    }*/
}
