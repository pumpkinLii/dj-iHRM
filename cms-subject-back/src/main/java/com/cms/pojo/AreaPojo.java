package com.cms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
public class AreaPojo {
    String value;
    String label;
    List list;
}
