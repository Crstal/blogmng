package com.crystal.blog.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.crystal.blog.common.util.DateUtil;
import com.crystal.blog.common.util.SerialNumUtil;
import com.crystal.blog.config.CommonProperties;
import com.crystal.blog.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;

@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private OSSService ossService;

    @Autowired
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
            File f = new File(filePath);
            if (!f.exists() && f.mkdirs()) {
            }
//            Path path = Paths.get(commonProperties.getImagesPath() + filePath + fileName);
//            Files.write(path, bytes);
            ossService.uploadFile(filePath + fileName, file);
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", commonProperties.getShowpoint() + "/" + commonProperties.getImagesPath() + "/" + filePath + fileName);
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
//        FileInputStream fips = new FileInputStream(new File(commonProperties.getImagesPath() + path + "/" + fileName));
//        ByteArrayOutputStream bops = new ByteArrayOutputStream();
//        int data = -1;
//        while ((data = fips.read()) != -1) {
//            bops.write(data);
//        }
//        byte[] btImg = bops.toByteArray();
        OutputStream os = response.getOutputStream();

        String btImg = ossService.getFile(path + "/" + fileName);
        os.write(btImg.getBytes());
        os.flush();
    }

}
