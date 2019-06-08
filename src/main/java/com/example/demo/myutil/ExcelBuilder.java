package com.example.demo.myutil;

import java.io.*;
import java.util.*;

import com.example.demo.entity.Score;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.hssf.util.HSSFColor;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.filechooser.FileSystemView;

public class ExcelBuilder {
   private static ExcelBuilder excelBuilder;
    private ExcelBuilder(){};
    public static ExcelBuilder getInstance(){
        if(excelBuilder==null){
            excelBuilder=new ExcelBuilder();
        }
        return excelBuilder;
    }

    public ArrayList<Map<String,Object>> loadScoreInfo(String xlsPath){
        //默认文件置于桌面，获取文件绝对路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + xlsPath;

        String stuName ;
        Integer stuId ;

        ArrayList<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        try{
            FileInputStream fileIn = new FileInputStream(filePath);
            //根据指定的文件输入流导入Excel从而产生Workbook对象
            Workbook wb0 = new HSSFWorkbook(fileIn);
            //获取Excel文档中的第一个表单
            Sheet sht0 = wb0.getSheetAt(0);
            //对Sheet中的每一行进行迭代
            for (Row r : sht0) {
                //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
                if(r.getRowNum()<1){
                    continue;
                }
                Map<String,Object> hm = new HashMap<String, Object>();
                Score score = new Score();
                //单元格依次是，姓名、学号、title、六科成绩
                for(int i=0;i<9;i++){
                    System.out.println(r.getCell(i).getCellType());
                }

                stuName=r.getCell(0).getStringCellValue();

                stuId=new Integer((int)r.getCell(1).getNumericCellValue());
                score.setTitle(r.getCell(2).getStringCellValue());
                score.setChinese(new Integer((int)r.getCell(3).getNumericCellValue()));
                score.setMath(new Integer((int)r.getCell(4).getNumericCellValue()));
                score.setEnglish(new Integer((int)r.getCell(5).getNumericCellValue()));
                score.setPhysics(new Integer((int)r.getCell(6).getNumericCellValue()));
                score.setChemistry(new Integer((int)r.getCell(7).getNumericCellValue()));
                score.setBiology(new Integer((int)r.getCell(8).getNumericCellValue()));



                hm.put("score",score);
                hm.put("stuId",stuId);
                hm.put("stuName",stuName);
                mapList.add(hm);

            }

            fileIn.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return mapList;
    }
    public static void createExcel(List<Score> scoreList) throws IOException{
        int i;
        // 获取桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + "/template.xls";

        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("学号");
        row.createCell(2).setCellValue("标题");
        row.createCell(3).setCellValue("语文");
        row.createCell(4).setCellValue("数学");
        row.createCell(5).setCellValue("英语");
        row.createCell(6).setCellValue("物理");
        row.createCell(7).setCellValue("化学");
        row.createCell(8).setCellValue("生物");
        row.setHeightInPoints(30); // 设置行的高度

        for(i=0;i<scoreList.size();i++){
            Score score = scoreList.get(i);
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(score.getStudent().getName());
            row.createCell(1).setCellValue(score.getStudent().getStu_id());
            row.createCell(2).setCellValue(score.getTitle());
            row.createCell(3).setCellValue(score.getChinese());
            row.createCell(4).setCellValue(score.getMath());
            row.createCell(5).setCellValue(score.getEnglish());
            row.createCell(6).setCellValue(score.getPhysics());
            row.createCell(7).setCellValue(score.getChemistry());
            row.createCell(8).setCellValue(score.getBiology());

        }

        /*
        // 日期格式化
        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        HSSFCreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle2.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        sheet.setColumnWidth(2, 20 * 256); // 设置列的宽度

        HSSFCell cell2 = row1.createCell(2);
        cell2.setCellStyle(cellStyle2);
        cell2.setCellValue(new Date());

        row1.createCell(3).setCellValue(2);


        // 保留两位小数
        HSSFCellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        HSSFCell cell4 = row1.createCell(4);
        cell4.setCellStyle(cellStyle3);
        cell4.setCellValue(29.5);


        // 货币格式化
        HSSFCellStyle cellStyle4 = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("华文行楷");
        font.setFontHeightInPoints((short)15);
        font.setColor(HSSFColor.RED.index);
        cellStyle4.setFont(font);

        HSSFCell cell5 = row1.createCell(5);
        cell5.setCellFormula("D2*E2");  // 设置计算公式

        // 获取计算公式的值
        HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);
        cell5 = e.evaluateInCell(cell5);
        System.out.println(cell5.getNumericCellValue());
        */

        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }
}
