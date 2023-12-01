package com.xjx.springboottest;


import java.io.*;
import java.util.Date;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import javax.swing.filechooser.FileSystemView;

public class exxel {
    public static void main(String[] args) throws IOException {
        A();//创建
//          B();//创建
//          C();//读取解析
    }
    private static void A() throws IOException {
        /*
         * 在盘下创建一个文件夹
         */
        String directory = "C:\\Users\\xjx\\Pictures\\素材";
        File file = new File(directory);// 创建文件夹对象
        if (!file.exists()) {// 如果不存在就创建
            file.mkdirs();
        }

        Workbook wb = new HSSFWorkbook();// 定义工作薄
        FileOutputStream fileout = new FileOutputStream("C:\\Users\\xjx\\Pictures\\素材\\poi_02.xls");// 输出流
        Sheet sheet = wb.createSheet("Sheet页1");// 定义sheet页面

        Row row = sheet.createRow(0);// 创建第一行
        Cell cell =  row.createCell(0);// 创建一个单元格，第一列。
        cell.setCellValue("house_code_1");//设置值
        row.createCell(1).setCellValue("3");
        row.createCell(2).setCellValue("UserID");
        row.createCell(3).setCellValue("house_serial");
        row.createCell(4).setCellValue("house_name");
        row.createCell(5).setCellValue("coordinates");

        /*
         * 时间格式，也可以转换好字符串格式
         */
//        CreationHelper creationHelper = wb.getCreationHelper();
//        CellStyle cellStyle = wb.createCellStyle();//创建样式
//        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyy/mm/dd"));//设置格式。可以加时分秒 hh:mm:ss  -/都可以
//        cell = row.createCell(3);
//        cell.setCellValue(new Date());
//        cell.setCellStyle(cellStyle);


//        for(int i = 4; i<10;i++) {
//            row.createCell(i).setCellValue("数值是"+i);
//        }

//        wb.createSheet("Sheet页2");// 定义sheet页面
        wb.write(fileout);// 将workbook写入流
        fileout.close();// 关闭输出流
    }

    private static void B() throws IOException {
        // 获取桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + "/template.xls";

        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("订单号");
        row.createCell(2).setCellValue("下单时间");
        row.createCell(3).setCellValue("个数");
        row.createCell(4).setCellValue("单价");
        row.createCell(5).setCellValue("订单金额");
        row.setHeightInPoints(30); // 设置行的高度

        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("NO00001");

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


        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }

    private static void C() throws IOException {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + "/template.xls";

        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheet("Sheet1");

        int lastRowIndex = sheet.getLastRowNum();
        System.out.println(lastRowIndex);
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) { break; }

            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {


                //这里直接执行会报一个异常 Cannot get a STRING value from a NUMERIC cell
                /**
                 * 在number类型转化为String类型的过程中造成了Cannot get a STRING value from a NUMERIC cell
                 * 这样的问题，因此需要在读取excel单元格数据转化之前设置单元格类型为String
                 */
//                String cellValue = row.getCell(j).getStringCellValue();
//                System.out.println(cellValue);

                //获取单元格
                Cell cell = row.getCell(j);
                //设置单元格类型
                cell.setCellType(CellType.STRING);
                //获取单元格数据
                String cellValue = cell.getStringCellValue();
                System.out.println(cellValue);
            }
        }


        bufferedInputStream.close();
    }
}
