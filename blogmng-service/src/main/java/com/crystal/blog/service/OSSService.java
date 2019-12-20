/**
 * 示例说明
 * 
 * HelloOSS是OSS Java SDK的示例程序，您可以修改endpoint、accessKeyId、accessKeySecret、bucketName后直接运行。
 * 运行方法请参考README。
 * 
 * 本示例中的并不包括OSS Java SDK的所有功能，详细功能及使用方法，请参看“SDK手册 > Java-SDK”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/preface.html?spm=5176.docoss/sdk/java-sdk/。
 * 
 * 调用OSS Java SDK的方法时，抛出异常表示有错误发生；没有抛出异常表示成功执行。
 * 当错误发生时，OSS Java SDK的方法会抛出异常，异常中包括错误码、错误信息，详细请参看“SDK手册 > Java-SDK > 异常处理”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/exception.html?spm=5176.docoss/api-reference/error-response。
 * 
 * OSS控制台可以直观的看到您调用OSS Java SDK的结果，OSS控制台地址是：https://oss.console.aliyun.com/index#/。
 * OSS控制台使用方法请参看文档中心的“控制台用户指南”， 指南的来链接地址是：https://help.aliyun.com/document_detail/oss/getting-started/get-started.html?spm=5176.docoss/user_guide。
 * 
 * OSS的文档中心地址是：https://help.aliyun.com/document_detail/oss/user_guide/overview.html。
 * OSS Java SDK的文档地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/install.html?spm=5176.docoss/sdk/java-sdk。
 * 
 */

package com.crystal.blog.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.crystal.blog.common.enums.ErrorCode;
import com.crystal.blog.common.exception.BussinessRuntimeException;
import com.crystal.blog.config.CommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
@Service
public class OSSService {

    @Autowired
    private CommonProperties commonProperties;


    public void uploadFile(String name, MultipartFile file) {
        name = commonProperties.getImagesPath() + "/" + name;
        log.info("开始上传文件");
        OSS ossClient = null;
        try {
            // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
            ossClient = new OSSClientBuilder().build(commonProperties.getEndpoint(), commonProperties.getAccessKeyId(), commonProperties.getAccessKeySecret());

            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (!ossClient.doesBucketExist(commonProperties.getBucketName())) {
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                ossClient.createBucket(commonProperties.getBucketName());
            }

            InputStream input = file.getInputStream();
            ObjectMetadata meta = new ObjectMetadata();                // 创建上传Object的Metadata
            meta.setContentType(file.getContentType());        // 设置上传内容类型
            meta.setCacheControl("no-cache");                    // 被下载时网页的缓存行为
            PutObjectRequest request = new PutObjectRequest(commonProperties.getBucketName(), name, input, meta);            //创建上传请求
            ossClient.putObject(request);

            log.info("上传成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BussinessRuntimeException(ErrorCode.FAILURE.getCode(), "上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }


    public String getFile(String name) {
        name = commonProperties.getImagesPath() + "/" + name;
        log.info("开始下载文件" + name);
        StringBuilder file = new StringBuilder();
        OSS ossClient = null;

        try {
            // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
            ossClient = new OSSClientBuilder().build(commonProperties.getEndpoint(), commonProperties.getAccessKeyId(), commonProperties.getAccessKeySecret());

            // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
            OSSObject ossObject = ossClient.getObject(commonProperties.getBucketName(), name);
            // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
            InputStream content = ossObject.getObjectContent();
            if (content != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) break;
                    file.append(line);
                }
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            }
            log.info("下载成功");

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BussinessRuntimeException(ErrorCode.FAILURE.getCode(), "下载文件失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return file.toString();
    }


    /**
     *
     * @MethodName: contentType
     * @Description: 获取文件类型
     * @param fileType
     * @return String
     */
    private static String contentType(String fileType){
        fileType = fileType.toLowerCase();
        String contentType = "";
        switch (fileType) {
            case "bmp":    contentType = "image/bmp";
                break;
            case "gif":    contentType = "image/gif";
                break;
            case "png":
            case "jpeg":
            case "jpg":    contentType = "image/jpeg";
                break;
            case "html":contentType = "text/html";
                break;
            case "txt":    contentType = "text/plain";
                break;
            case "vsd":    contentType = "application/vnd.visio";
                break;
            case "ppt":
            case "pptx":contentType = "application/vnd.ms-powerpoint";
                break;
            case "doc":
            case "docx":contentType = "application/msword";
                break;
            case "xml":contentType = "text/xml";
                break;
            case "mp4":contentType = "video/mp4";
                break;
            default: contentType = "application/octet-stream";
                break;
        }
        return contentType;
    }
}
