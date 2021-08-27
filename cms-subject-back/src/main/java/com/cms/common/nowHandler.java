package com.cms.common;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.util.HashMap;
import java.util.List;

public class nowHandler implements CellWriteHandler {
    List<Integer> columIndexs;//字段的下标
    Short colorIndex;//颜色的下标
    HashMap<Integer,String> annotationMaps;//注解下标
    HashMap<Integer,String[]> DropDownMaps;//用于装配下拉框的内容
    //三种构造方法

    public nowHandler(List<Integer> columIndexs, Short colorIndex, HashMap<Integer, String> annotationMaps) {
        this.columIndexs = columIndexs;
        this.colorIndex = colorIndex;
        this.annotationMaps = annotationMaps;
    }

    public nowHandler(List<Integer> columIndexs, Short colorIndex) {
        this.columIndexs = columIndexs;
        this.colorIndex = colorIndex;
    }

    public nowHandler(List<Integer> columIndexs, Short colorIndex, HashMap<Integer, String> annotationMaps, HashMap<Integer, String[]> dropDownMaps) {
        this.columIndexs = columIndexs;
        this.colorIndex = colorIndex;
        this.annotationMaps = annotationMaps;
        DropDownMaps = dropDownMaps;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }



    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.getRow(0).setHeight((short) (1.8*256));//设置第一行的行高为 普通行的1.8倍
        sheet.setColumnWidth(cell.getColumnIndex(),30*256);//为当前列的元素设置宽带
        //列宽列高设置完毕以后 进行设置表头的数据 也就是第一行标题的数据

        //工作溥 相当于一张表
        Workbook workbook=sheet.getWorkbook();


        //画笔 用于为字段添加注解
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        //设置标题的字体样式
        WriteCellStyle headwriteCellStyle=new WriteCellStyle();
        WriteFont headwriteFont=new WriteFont();
        headwriteFont.setBold(true);
        headwriteFont.setFontName("楷体");
        headwriteFont.setFontHeightInPoints((short)18);
        if (CollectionUtils.isNotEmpty(columIndexs)&&colorIndex!=null&&columIndexs.contains(cell.getColumnIndex())){
            headwriteFont.setColor(colorIndex);
        }
        headwriteCellStyle.setWriteFont(headwriteFont);//列头样式设置完毕
        headwriteCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());//设置背景颜色
        CellStyle cellStyle= StyleUtil.buildHeadCellStyle(workbook,headwriteCellStyle);//设置头的样式
        cell.setCellStyle(cellStyle);
        //设置注释
        if (null!=annotationMaps&&annotationMaps.containsKey(cell.getColumnIndex())){
            //在这里进行注释的设置
            String context=annotationMaps.get(cell.getColumnIndex());
            Comment cellComment= drawing.createCellComment(new XSSFClientAnchor(0,0,0,0,(short)cell.getColumnIndex(),0,(short)(cell.getColumnIndex()),2));
            cellComment.setString(new XSSFRichTextString(context));//设置里面的信息
            cell.setCellComment(cellComment);
        }

        //设置下拉框
        if (null!=DropDownMaps&&DropDownMaps.containsKey(cell.getColumnIndex())){
            //存在的时候 进行值的设置
            String[] datas=DropDownMaps.get(cell.getColumnIndex());
            String sheetname="hidden"+cell.getColumnIndex();
            Sheet sheet1 = workbook.createSheet(sheetname);
            Name name = workbook.createName();
            name.setNameName(sheetname);
            name.setRefersToFormula(sheetname+"!A1:A"+(100+datas.length));//datas.size+num


            for (int i = 0; i < datas.length; i++) {
                sheet1.createRow(i+100).createCell(cell.getColumnIndex()).setCellValue(datas[i]);
            }
            CellRangeAddressList addressList=new CellRangeAddressList(1,100,cell.getColumnIndex(),cell.getColumnIndex());
            DataValidationHelper dvh=sheet.getDataValidationHelper();
            DataValidationConstraint explicitListConstraint =dvh.createFormulaListConstraint(sheetname);
            DataValidation dataValidation= dvh.createValidation(explicitListConstraint,addressList);
            sheet.addValidationData(dataValidation);

        }

    }

}
