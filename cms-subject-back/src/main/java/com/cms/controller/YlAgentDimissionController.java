package com.cms.controller;

import com.cms.pojo.*;
import com.cms.service.*;
import com.cms.service.impl.ResignationApprovalImpl;
import com.cms.util.R;
import com.cms.util.SlelectPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/login/YlAgentDimission")
@Api("人员离职模块")
public class YlAgentDimissionController {
    @Autowired
    DismissQueryService dismissQueryService;
    @Autowired
    InsertDimissionService insertDimissionService;
    @Autowired
    DepartModifyService departModifyService;
    @Autowired
    ResignationApprovalImpl resignationApproval;
    @Autowired
    SearchDimissionService searchDimissionService;
    @Autowired
    SubmitAuditService submitAuditService;

    //王欣艺
    @PostMapping("/query")
    @ApiOperation("离职申请查询")
    @ResponseBody
    public R returnList(@RequestParam(value ="page") int page, @RequestParam(value ="limit") int limit, @RequestBody DismissQueryPojo dismissQueryPojo) {
        List<DismissQueryReturnPojo> list = dismissQueryService.searchList(dismissQueryPojo);
        List page1 = SlelectPage.getPage(limit, page, list);
        if (page1.size()>0) {
            return R.ok().put("list",page1).put("totalCount",list.size());
        }else {
            return R.error("无符合数据").put("code",501).put("totalCount",0);
        }
    }

    //张毅珑
    @ApiOperation("插入离职信息")
    @PostMapping("/insertDimission")
    public R InsertDimission(@RequestBody AccInsertDimissionPojo accInsertDimissionPojo) {
        int i = insertDimissionService.InsertDimission(accInsertDimissionPojo);
        if (i==1) {
            return R.ok("离司申请已提交");
        }else {
            return R.error(501,"此人员已离职无法提交");
        }
    }

    //池浩玥
    @PostMapping("/modify")
    @ApiOperation("离司申请修改接口")
    public R departModify(@RequestBody DepartModifyPojo departModifyPojo) throws ParseException{
        String keyString = departModifyService.departModify(departModifyPojo);
        if (keyString.equals("0")){
            return R.ok().put("msg","修改成功");
        }else if (keyString.equals("1")){
            return R.error(501,"离职申请已提交审核或已通过审核");
        }else if (keyString.equals("2")){
            return R.error(501,"请补全必填项");
        }
        return R.error(500,"发生错误请重试");
    }

    //王佳智
    @ApiOperation("离职审核模块接口")
    @PostMapping("/approval")
    public R approval(@RequestBody ResignationApprovalPojo resignationApprovalPojo,int num) throws ParseException {
        return resignationApproval.approval(resignationApprovalPojo,num);
    }

    //张毅珑
    @PostMapping("/dimission")
    @ApiOperation("离职接口")
    public R SearchDimission(@RequestBody AcceptDimissionPojo acceptDimissionPojo ) {
        return searchDimissionService.SearchDimission(acceptDimissionPojo);
    }

    //陈益轩
    @ApiOperation("提交审核接口")
    @PostMapping("/audit")
    public  R audit(@RequestBody List<String> list){
        boolean result =submitAuditService.audit(list);
        if (result) return R.ok();
        else return R.error("失败");
    }
}
