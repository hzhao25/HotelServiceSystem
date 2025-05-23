package com.sky.controller.user;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AccessTokenUtil;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController("userCommonController")
@RequestMapping("/user/common")
@Api(tags = "C端通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private AccessTokenUtil accessTokenUtil;


    /*@Autowired
    private UploadPathProperties uploadPathProperties;*/

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(@RequestParam("file") MultipartFile file){
        log.info("文件上传：{}",file);

        //阿里云oss文件上传
        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀.jpg
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName= UUID.randomUUID().toString()+extension;
            //文件的请求路径
            String filePath= aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(filePath);
        }catch (IOException e){
            log.error("文件上传失败：{}",e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);


        /*//上传文件为空
        if(file.isEmpty()){
            throw new FIleUploadNullException(MessageConstant.FILE_UPLOAD_NULL);
        }
        try {
            String originalFilename = file.getOriginalFilename();
            String extensioin= originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName=UUID.randomUUID().toString()+extensioin;
            String uploadDir=uploadPathProperties.getUploadDir();
            Path filePath= Paths.get(uploadDir+ File.separator+fileName);

            Files.createDirectories(Paths.get(uploadDir));

            file.transferTo(filePath);

            String url=UploadUrlConstant.UPLOAD_URL+fileName;

            System.out.println(url);
            return Result.success(url);
        }catch (IOException e){
            log.info("文件上传失败：{}",e);
        }*/
    }


}
