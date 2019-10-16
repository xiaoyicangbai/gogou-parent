package cn.itsource.gogou.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient("GOGOU-COMMON")
public interface StaticPageClient {

    @PostMapping("/page")
    public void genStaticPage(
            @RequestParam("templatePath") String templatePath,
            @RequestParam("targetPath") String targetPath,
            @RequestBody Object model);
}
