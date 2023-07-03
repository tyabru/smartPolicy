package com.jingyu.web.controller.qunfangqunzhi;

import com.jingyu.common.constant.Constants;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.framework.qunfang.service.CommonUserLoginService;

import com.jingyu.qunfangqunzhi.domain.CommonUserLoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qf")
public class CommonUserLoginController {

    @Autowired
    private CommonUserLoginService loginService;

    /**
     *
     * @param loginBody
     * @return 带有token的AjaxResult
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody CommonUserLoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}
