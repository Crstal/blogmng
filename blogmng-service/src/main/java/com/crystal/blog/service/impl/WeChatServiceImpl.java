package com.crystal.blog.service.impl;

import com.crystal.blog.service.WeChatService;
import org.springframework.stereotype.Service;

/**
 * 微信端登陆
 */
@Service
public class WeChatServiceImpl implements WeChatService {
/*
    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private AccessTokenRecordMapper accessTokenRecordMapper;

    @Override
    public AccessToken getAccessToken() {
        AccessTokenRecordExample example = new AccessTokenRecordExample();
        example.createCriteria().andTypeEqualTo(LoginMethodEnum.WECHAT.getCode()).andTokenEqualTo(loginProperties.getWeChatToken());
        List<AccessTokenRecord> accessTokenRecords = accessTokenRecordMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(accessTokenRecords) || accessTokenRecords.get(0).getExpireIn().after(new Date())) {
            return saveAccessToken(null);
        }
        AccessTokenRecord accessTokenRecord = accessTokenRecords.get(0);
        AccessToken accessToken = new AccessToken(accessTokenRecord.getAccessToken(), ((Long) accessTokenRecord.getExpireIn().getTime()).intValue());
        return accessToken;
    }

    @Override
    public synchronized AccessToken saveAccessToken(AccessToken accessToken) {
        if (accessToken == null) {
            AccessTokenRecordExample example = new AccessTokenRecordExample();
            example.createCriteria().andTypeEqualTo(LoginMethodEnum.WECHAT.getCode()).andTokenEqualTo(loginProperties.getWeChatToken());
            List<AccessTokenRecord> accessTokenRecords = accessTokenRecordMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(accessTokenRecords) || accessTokenRecords.get(0).getExpireIn().after(new Date())) {
                accessToken = WeChatUtil.getAccessToken(loginProperties.getWeChatAppId(), loginProperties.getWeChatAppSecret());
            } else {
                AccessTokenRecord accessTokenRecord = accessTokenRecords.get(0);
                accessToken = new AccessToken(accessTokenRecord.getAccessToken(), ((Long) accessTokenRecord.getExpireIn().getTime()).intValue());
                return accessToken;
            }
        }
        if (accessToken != null) {
            AccessTokenRecord accessTokenRecord = new AccessTokenRecord();
            accessTokenRecord.setType(LoginMethodEnum.WECHAT.getCode());
            accessTokenRecord.setToken(loginProperties.getWeChatToken());
            accessTokenRecord.setAccessToken(accessToken.getAccess_token());
            accessTokenRecord.setExpireIn(new Date(accessToken.getExpire_in()));
            accessTokenRecordMapper.insert(accessTokenRecord);
        }
        return accessToken;
    }*/
}
