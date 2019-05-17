package cn.datacharm.springboot_fileinputdemo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/show")
    public String show(Model model){
        String src = "img/pig.jpg";
        model.addAttribute("src",src);
        return "show";
    }

    @RequestMapping("/test")
    public String test(){

        return "test";
    }


    @RequestMapping("/uploadImg")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,Model model)  {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();

        File dest = null;
        String os = System.getProperty("os.name");
        System.out.println(os);
        if (os.toLowerCase().startsWith("win")) {
            String path = "G:"+File.separator+"img"+File.separator;
            System.out.println(path);
            dest= new File(path + fileName);
        }else {
            String path = "/webapps/img/";
            dest= new File(path + fileName);
        }
        model.addAttribute("src","img/"+fileName);
        try {
            file.transferTo(dest);
            return JSON.toJSONString("上传成功！");
        } catch (IOException e) {
            return JSON.toJSONString("上传失败！");
        }

    }

}
