package cn.itsource.gogou.controller;


import cn.itsource.gogou.domain.User;
import cn.itsource.gogou.util.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public AjaxResult login(@RequestBody User user){
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())){
            return AjaxResult.getAjaxResult().setMessage("登录成功").setResultObject(user);
        }
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("登录失败！！");
    }
}
