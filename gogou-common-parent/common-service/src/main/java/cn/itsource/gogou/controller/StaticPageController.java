package cn.itsource.gogou.controller;

import cn.itsource.gogou.util.VelocityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticPageController {
    @PostMapping("/page")
    public void genStaticPage(
            @RequestParam("templatePath") String templatePath,
            @RequestParam("targetPath") String targetPath,
            @RequestBody Object model){
        VelocityUtils.staticByTemplate(model,templatePath,targetPath);
    }
}
