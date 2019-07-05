package com.crystal.blog.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.crystal.blog.common.util.DateUtil;
import com.crystal.blog.common.util.SerialNumUtil;
import com.crystal.blog.config.CommonProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private CommonProperties commonProperties;

    /**
     * 上传图片
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/uploadimg")
    public JSONObject uploadimg(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
        String filePath = DateUtil.getYearMonth() + "/";
        String fileName = SerialNumUtil.getSerialNum() + file.getOriginalFilename();
        JSONObject jsonObject=new JSONObject();
        try {
            byte[] bytes = file.getBytes();
            File f = new File(commonProperties.getImagesPath() + filePath);
            if (!f.exists() && f.mkdirs()) {
            }
            Path path = Paths.get(commonProperties.getImagesPath() + filePath + fileName);
            Files.write(path, bytes);
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", "/file/" + filePath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("success", 0);
            jsonObject.put("message", "上传失败");
        }
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value="/{path}/{fileName}")
    public void show(@PathVariable(value = "path") String path, @PathVariable(value = "fileName") String fileName, HttpServletResponse response) throws Exception {
        OutputStream os = response.getOutputStream();
        FileInputStream fips = new FileInputStream(new File(commonProperties.getImagesPath() + path + "/" + fileName));
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        int data = -1;
        while ((data = fips.read()) != -1) {
            bops.write(data);
        }
        byte[] btImg = bops.toByteArray();
        os.write(btImg);
        os.flush();
    }
}
