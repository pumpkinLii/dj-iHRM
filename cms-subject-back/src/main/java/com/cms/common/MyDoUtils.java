package com.cms.common;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.util.List;
@Component
public class MyDoUtils {

    /*
    * 从这里进行导出的操作
    * @param outputStream 输出流
    * @param dataList 导出的数据
    * @param classT 模板类
    * @param sheetName 当前的sheet的名称
    * @param cellWriteHandler 用于注册的Handler
    * */
    public static void writeExcelWithModel(OutputStream outputStream, List dadalist, Class classT, String sheetName, CellWriteHandler... cellWriteHandler){
        HorizontalCellStyleStrategy strategy=new HorizontalCellStyleStrategy(new WriteCellStyle(),new WriteCellStyle());
        ExcelWriterSheetBuilder excelWriterSheetBuilder;
        excelWriterSheetBuilder = EasyExcel.write(outputStream,classT).sheet(sheetName).registerWriteHandler(strategy);
        for (int i = 0; i < cellWriteHandler.length; i++) {
            excelWriterSheetBuilder.registerWriteHandler(cellWriteHandler[i]);
        }
        excelWriterSheetBuilder.doWrite(dadalist);
        //实现数据的输出
    }
}
