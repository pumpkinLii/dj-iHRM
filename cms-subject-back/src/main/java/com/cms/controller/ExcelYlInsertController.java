package com.cms.controller;

import com.cms.service.ExcelLaAgentService;
import com.cms.service.YlExcellInsertService;
import com.cms.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping({"/Excel"})
@Api("养老人管模块课题")
public class ExcelYlInsertController {
    @Autowired
    private YlExcellInsertService ylExcellService;


    @Autowired
    private ExcelLaAgentService laAgentService;

    @ApiOperation("测试接口")
    @PostMapping({"/ylLaAgentAttrIn"})
    public R ylLaAgentAttrExcelIn(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
        list=this.ylExcellService.check(file);
        HashMap<String,String> mapp=new HashMap<String,String>();
        if(list.size()==1)
        {
            HashMap<String,String> map= new HashMap<String, String>();
            map = list.get(0);
            if(map.get("批量导入失败！Excel文件的内容不能为空！")!=null)
            {
                return R.error(500,"批量导入失败！Excel文件的内容不能为空！");
            }
            else if(map.get("导入成功！")!=null)
            {
                return R.ok().put("0","导入成功!");
            }
            else{
                return R.error().put("500",list);
            }
        }
        else {
            return R.error(500,"批量导入失败！").put("错误原因:",list);
        }
    }
}