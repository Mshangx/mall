package com.ruoyi.web.controller.webapi;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/webapi")
public class WebApiController extends BaseController {


    @GetMapping("/userLogin")
    @ResponseBody
    public AjaxResult userLogin(@RequestParam("user_name") String user_name,@RequestParam("user_password") String user_password)
    {

        logger.info(" user_name : {} , userpassword : {}",user_name,user_password);

        UsernamePasswordToken token = new UsernamePasswordToken(user_name, user_password);
        Subject subject = SecurityUtils.getSubject();
        logger.info( " user token  >>> {}",subject.getSession().getId());

        Map<String,Object> map = new HashMap<>();
        try
        {
            SysUser user = (SysUser) subject.getPrincipal();
            subject.login(token);
            return AjaxResult.success(user,subject.getSession().getId().toString());
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误！";
            map.put("data","");
            return AjaxResult.error(1,msg);
        }catch (Exception e){
           return AjaxResult.error("程序异常！");
        }
    }

    @GetMapping("/getUserInfo")
    @ResponseBody
    public AjaxResult userLogin(@RequestParam("token") String token)
    {

        logger.info(" token : {}",token);

        return  null;

    }
}
