package com.cms.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@ExcelTarget("CertExcellPojo")
@Component
public class CertExcellPojo implements Serializable {
        @Excel(name = "人员工号", orderNum = "1", width = 25)
        private String agentCode;

        @Excel(name = "资格证书名称", orderNum = "2", width = 25)
        private String certificateName;

        @Excel(name = "资格证书号", orderNum = "3", width = 25)
        private String certificateNo;

        @Excel(name = "发放日期", orderNum = "4", width = 25,format = "yyyy-MM-dd")
        private Date releaseDate;

        @Excel(name = "补发日期", orderNum = "5", width = 25,format = "yyyy-MM-dd")
        private Date reissueDate;

        @Excel(name = "有效起期", orderNum = "6", width = 25,format = "yyyy-MM-dd")
        private Date startEffectiveDate;

        @Excel(name = "有效止期", orderNum = "7", width = 25, format = "yyyy-MM-dd")
        private Date endEffectiveDate;

        @Excel(name = "批准单位", orderNum = "8", width = 25)
        private String approver;

        @ExcelIgnore
        private String edorNo;
}

