package com.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("yl_la_area")
@EqualsAndHashCode(callSuper = false)
public class YlLaAreaEntity {

    /**
     * placeType
     */
    private String placeType;

    /**
     * placeCode
     */
    @TableId("place_code")
    private String placeCode;

    /**
     * placeName
     */
    private String placeName;

    /**
     * comcode
     */
    private String upPlaceName;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 创建日期
     */
    private Date makeDate;

    /**
     * 创建时间
     */
    private String makeTime;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 修改时间
     */
    private String modifyTime;

}
