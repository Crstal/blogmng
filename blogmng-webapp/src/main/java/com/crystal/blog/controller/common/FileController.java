package com.crystal.blog.controller.common;

import com.crystal.blog.common.bean.response.base.Result;
import com.crystal.blog.common.config.CommonProperties;
import com.crystal.blog.common.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {

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
    public Result<String> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(commonProperties.getImagesPath() + fileName);
            Files.write(path, bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.wrapErrorResult(ErrorCode.UPLOAD_ERROR.getCode(), ErrorCode.UPLOAD_ERROR.getMessage());
        }
        return Result.wrapSuccessfulResult("上传成功!");
    }
}
