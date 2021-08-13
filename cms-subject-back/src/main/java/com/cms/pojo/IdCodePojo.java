package com.cms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class IdCodePojo {
    private String codeType;
    private String code;
    private String codeName;
}
