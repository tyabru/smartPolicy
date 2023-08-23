package com.jingyu.web.controller.qunfangqunzhi;


import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.framework.qunfang.service.CommonUserRegisterService;
import com.jingyu.qunfangqunzhi.domain.CommonUserRegisterBody;
import com.jingyu.qunfangqunzhi.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  普通用户注册
 */
@RestController
@RequestMapping("/qf")
public class CommonUserRegisterController {

    @Autowired
    private CommonUserRegisterService registerService;
    @Autowired
    private SMSService smsService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody CommonUserRegisterBody registerBody)
    {
        System.out.println(registerBody);
        String msg = registerService.register(registerBody);
        return StringUtils.isEmpty(msg) ? AjaxResult.success() : AjaxResult.error(msg);
    }

    /**
     *
     * @param phoneNumber 电话号码
     * @return 短信验证码
     */
    @GetMapping("/SMSCode")
    public AjaxResult getSMSCode(@RequestParam("phoneNumber")String phoneNumber){
        String SMSCode = smsService.generateSMSCaptcha(phoneNumber);
        System.out.println(SMSCode);
        return AjaxResult.success(SMSCode);
    }

}
