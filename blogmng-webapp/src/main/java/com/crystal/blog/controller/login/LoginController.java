package com.crystal.blog.controller.login;

import com.crystal.blog.common.bean.param.LoginOrRegisterParam;
import com.crystal.blog.common.bean.response.base.Result;
import com.crystal.blog.common.enums.ErrorCode;
import com.crystal.blog.common.exception.BussinessException;
import com.crystal.blog.common.util.SignUtil;
import com.crystal.blog.common.util.StringUtil;
import com.crystal.blog.config.LoginProperties;
import com.crystal.blog.service.LoginService;
import com.crystal.blog.service.UserService;
import com.crystal.blog.sso.bean.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    /**
     * 跳转到登陆页面
     * @return
     */
    @GetMapping(value = "login")
    public String login() {
        return "front/login";
    }

    /**
     * 登陆
     * @param loginOrRegisterParam
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result<String> login(@Valid LoginOrRegisterParam loginOrRegisterParam, HttpSession session){
        try {
            Principal principal = loginService.verifyLogin(loginOrRegisterParam);
            if (principal != null) {
                String authId = StringUtil.getRandomString(50);
                session.setAttribute(authId, principal);
                return Result.wrapSuccessfulResult(authId);
            }
            return Result.wrapErrorResult(ErrorCode.FAILURE.getCode(), "登陆失败");
        } catch (BussinessException e) {
            log.info("登陆异常{}", e.getMessage());
            return Result.wrapErrorResult(ErrorCode.FAILURE.getCode(), e.getMessage());
        }
    }

    /**
     * 登出
     * @param authId
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(@RequestParam(value = "authId") String authId, HttpSession session){
        session.removeAttribute(authId);
        return "redirect:/login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping(value = "register")
    public String register() {
        return "front/register";
    }


    /**
     * 注册
     * @param param
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result<String> register(@Valid LoginOrRegisterParam param){
        try {
            Boolean flag = loginService.register(param);
            if (flag) {
                return Result.wrapSuccessfulResult("注册成功");
            }
            return Result.wrapErrorResult(ErrorCode.FAILURE.getCode(), "注册失败");
        }catch (BussinessException e) {
            log.info("注册失败：{}", e.getMessage());
            return Result.wrapErrorResult(ErrorCode.FAILURE.getCode(), e.getMessage());
        }
    }




    /**
     * 微信服务验证
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(value = "wechat/security")
    @ResponseBody
    public String doGet(@RequestParam(value = "signature") String signature,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce,
            @RequestParam(value = "echostr") String echostr) {
        try {
            if (SignUtil.checkSignature(loginProperties.getWeChatToken(), signature, timestamp, nonce)) {
                return echostr;
            } else {
                log.info("微信服务验证，这里存在非法请求！");
            }
        } catch (Exception e) {
            log.error("微信服务验证异常", e);
        }
        return null;
    }
}
