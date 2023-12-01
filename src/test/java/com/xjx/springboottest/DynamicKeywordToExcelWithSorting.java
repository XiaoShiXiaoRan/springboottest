package com.xjx.springboottest;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DynamicKeywordToExcelWithSorting {
    public static void main(String[] args) {
        String url = "https://xiaoshixiaoran.github.io/"; // 要抓取的网站URL
        String outputFilePath = "keyword_counts.xlsx"; // 输出的Excel文件路径

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Keyword Counts");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Keyword");
            headerRow.createCell(1).setCellValue("Count");

            Document document = Jsoup.connect(url).get(); // 使用 Jsoup 进行网页内容抓取
            String text = document.text(); // 获取网页文本内容

            Map<String, Integer> keywordCounts = countKeywords(text); // 计算关键字出现次数并排序
            int rowNum = 1;
            for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }

            try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> countKeywords(String text) {
        // 在这里实现关键字的搜索和计数逻辑
        Map<String, Integer> counts = new HashMap<>();
        String[] words = text.split("\\s+");
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        
        // 将 Map 按值进行排序
        return counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
