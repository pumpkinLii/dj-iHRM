package com.cms.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public interface CertInsertExcellService {
    ArrayList<HashMap<String, String>> check(MultipartFile file) throws IOException, ParseException;
}
