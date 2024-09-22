package com.hzp.aixiaoda.controller;

import cn.hutool.core.io.FileUtil;
import com.hzp.aixiaoda.constant.FileConstant;
import com.hzp.aixiaoda.service.UserService;
import com.hzp.aixiaoda.common.BaseResponse;
import com.hzp.aixiaoda.common.ErrorCode;
import com.hzp.aixiaoda.common.ResultUtils;
import com.hzp.aixiaoda.exception.BusinessException;
import com.hzp.aixiaoda.manager.CosManager;
import com.hzp.aixiaoda.model.dto.file.UploadFileRequest;
import com.hzp.aixiaoda.model.entity.User;
import com.hzp.aixiaoda.model.enums.FileUploadBizEnum;

import java.io.File;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hzp.aixiaoda.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private UserService userService;

    @Resource
    private CosManager cosManager;

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile multipartFile,UploadFileRequest uploadFileRequest) {
        log.info("文件上传:{}",multipartFile);
//        String biz = uploadFileRequest.getBiz();   UploadFileRequest uploadFileRequest, HttpServletRequest request
//        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
//        if (fileUploadBizEnum == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        validFile(multipartFile, fileUploadBizEnum);
//        User loginUser = userService.getLoginUser(request);
        // 文件目录：根据业务、用户来划分
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        //原始文件名：你上传前这个图片的名称
        String originalFilename = multipartFile.getOriginalFilename();
        //截取原始文件名的后缀  dfdfdf.png  就是截取出dfdfdf
        //String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //构造新文件名称:为了是防止你上传的图片名重名导致图片覆盖
        String objectName = uuid + "-" + originalFilename;
//        String filepath = String.format("/%s/%s/%s", fileUploadBizEnum.getValue(), loginUser.getId(), filename);
        String filePath = null;
        try {
            // 上传文件
//            file = File.createTempFile(filepath, null);
//            multipartFile.transferTo(file);
            //文件请求路径：在OOS中的
              filePath = aliOssUtil.upload(multipartFile.getBytes(), objectName);
//            cosManager.putObject(filepath, file);
            // 返回可访问地址
            return ResultUtils.success(filePath);
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filePath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        }
//        finally {
//            //log.error("file delete error, filepath = {}", filePath);
////            if (file != null) {
////                // 删除临时文件
////                boolean delete = file.delete();
////                if (!delete) {
////                    log.error("file delete error, filepath = {}", filePath);
////                }
////            }
//        }
    }

    /**
     * 校验文件
     *
     * @param multipartFile
     * @param fileUploadBizEnum 业务类型
     */
    private void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        final long ONE_M = 1024 * 1024L;
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小不能超过 1M");
            }
            if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件类型错误");
            }
        }
    }
}
