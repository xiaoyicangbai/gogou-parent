package cn.itsource.gogou.controller;

import cn.itsource.gogou.util.AjaxResult;
import cn.itsource.gogou.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/file")
    public AjaxResult upload(MultipartFile file){
        try {
            byte[] fileBytesArray = file.getBytes();
            String filename = file.getOriginalFilename();
            String extName=filename.substring(filename.lastIndexOf(".")+1);
            String fileid = FastDfsApiOpr.upload(fileBytesArray, extName);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("上传成功").setResultObject(fileid);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("上传失败");
        }
    }

    /**
     * 删除文件
     * @param
     * @param
     */
    @DeleteMapping("/file")
    public void delete(@RequestParam("fileid")String fileid){
        System.out.println("===="+fileid);
        String str = fileid.substring(fileid.indexOf("/")+1);
        //组名
        String group = str.substring(0,str.indexOf("/"));
        //文件名
        String fielName=str.substring(str.indexOf("/")+1);
        System.out.println("group: "+group);
        System.out.println("fielName: "+fielName);
        FastDfsApiOpr.delete(group,fielName);
    }

}
