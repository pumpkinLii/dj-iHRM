package com.cms.service;

import com.cms.pojo.LaAgentPojo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface YlExcellInsertService {
    ArrayList<HashMap<String, String>> check(MultipartFile file) throws IOException, ParseException;
}
