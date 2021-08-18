package com.cms.controller;

import com.alibaba.excel.EasyExcel;
import com.cms.common.ModelExcelListener;
import com.cms.dao.YlLaAgentAttrExcelUpdateDao;
import com.cms.dao.YlLaAgentDao;
import com.cms.entity.YlLaAgentEntity;
import com.cms.pojo.TablePropertiesPojo;
import com.cms.pojo.YlLaAgentAttrExcelUpdatePojo;
import com.cms.service.YlAgentInfoService;
import com.cms.service.impl.YlAgentInfoServiceImpl;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/excel")
@Api("养老渠道-人员管理-人员Excel导入")
public class YlLaAgentAttrExcelUpdateController {
    @Autowired
    YlLaAgentAttrExcelUpdateDao ylLaAgentAttrExcelUpdateDao;

    @Autowired
    ModelExcelListener modelExcelListener;
    @PostMapping("/dowload")
    @ApiOperation("模板下载模块未完待续")
    public R exceldowload(){
        //获取所有的数据库注释信息然后插入Excel表中
        List<TablePropertiesPojo> tableProperties = ylLaAgentAttrExcelUpdateDao.getTableProperties();
        return R.ok().put("list",tableProperties);
    }
    @PostMapping("/update")
    @ApiOperation("人员导入模块")
    public R readExcel(@RequestParam("file") MultipartFile file){
        List<YlLaAgentAttrExcelUpdatePojo> list = new ArrayList<>();//这个Pojo 从表中中获取的数据
        try {
            list = EasyExcel.read(file.getInputStream(),YlLaAgentAttrExcelUpdatePojo.class,modelExcelListener).sheet().doReadSync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("list",list);
    }

}
