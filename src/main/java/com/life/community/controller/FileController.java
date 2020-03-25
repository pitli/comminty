package com.life.community.controller;

import com.life.community.dto.FileDTO;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author zp
 * @version v1.0.0
 * @date 2020/3/23
 * @see com.life.community.controller
 */
@Controller
public class FileController {

    @Value("${web.upload-path}")
    private String uploadPath;
    @Value("${web.upload.img-path}")
    private String imgPath;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
                          HttpServletRequest request) {
        //获取文件名称
        String trueFileName = file.getOriginalFilename();
        //获取文件后缀
        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));
        //生成新的文件名
        String fileName = System.currentTimeMillis() + "_" + RandomUtils.nextInt(100, 999) + suffix;
        //创建file 判断是否存在，不存在则递归新建
        File fileDir = new File(uploadPath, fileName);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        FileDTO fileDTO = new FileDTO();
        try {
            //将文件保存
            file.transferTo(fileDir);

            fileDTO.setSuccess(1);
            fileDTO.setMessage("上传图片成功");
            fileDTO.setUrl(imgPath + File.separator + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传图片失败");
        }

        return fileDTO;
    }
}
