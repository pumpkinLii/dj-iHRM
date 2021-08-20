package com.cms.service;


import com.cms.pojo.LaAgentPojo;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Map;

public interface ExcelLaAgentServiceAttr {
    public String agentSubmit(LaAgentPojo laAgent);
    public String getNewstr();
}
