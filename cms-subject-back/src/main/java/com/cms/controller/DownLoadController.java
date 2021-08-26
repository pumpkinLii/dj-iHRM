package com.cms.controller;

import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@CrossOrigin
@RequestMapping("/down")
@Api("下载新增课题")
public class DownLoadController {
    @ApiOperation("下载新增接口")
    @PostMapping("/load")
    public R download(HttpServletResponse httpServletResponse, MultipartFile file) throws IOException {
        OutputStream outputStream=new FileOutputStream("D:\\附件目录\\"+file.getOriginalFilename());
        InputStream inputStream = file.getInputStream();
        byte[] bytes=new byte[1024];
        while ((inputStream.read(bytes))>0){
            outputStream.write(bytes);
        }
        outputStream.flush();
        outputStream.close();
        return R.ok();
    }

}
