package com.cms.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface IdCodeService {
    public Map<String, Map<String,String>> getResource();
}
