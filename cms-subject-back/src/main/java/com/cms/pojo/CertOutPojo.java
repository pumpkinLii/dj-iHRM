package com.cms.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import lombok.Data;

import java.util.Date;
@Data
public class CertOutPojo {
    private String agentCode;

    private String certificateName;

    private String certificateNo;

    private String releaseDate;

    private String reissueDate;

    private String startEffectiveDate;

    private String endEffectiveDate;

    private String approver;
    @Override
    public String toString() {
        return
        agentCode +
        "," + certificateName +
        "," + certificateNo +
        "," + releaseDate +
        "," + reissueDate +
        "," + startEffectiveDate +
        "," + endEffectiveDate +
        "," + approver ;
    }
}
