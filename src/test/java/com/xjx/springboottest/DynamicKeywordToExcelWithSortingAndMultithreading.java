package com.xjx.springboottest;

import org.apache.poi.ss.formula.functions.Now;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class DynamicKeywordToExcelWithSortingAndMultithreading {
    public static void main(String[] args) {
        String mainUrl = "https://www.ccell.com"; // 主网站的URL
        String outputFilePath = "keyword_counts_"+ "ccell" +".xlsx"; // 输出的Excel文件路径

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Keyword Counts");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("URL");
            headerRow.createCell(1).setCellValue("Keyword");
            headerRow.createCell(2).setCellValue("Count");

            int rowNum = 1;

            Document mainDocument = Jsoup.connect(mainUrl).get(); // 获取主网站的内容
            Elements links = mainDocument.select("a[href]"); // 从主网站内容中提取所有链接

            CompletableFuture<Map<String, Integer>>[] futures = new CompletableFuture[links.size()];
            int index = 0;
            for (Element link : links) {
                String subUrl = link.attr("abs:href"); // 获取子网站的绝对URL
                if (!subUrl.equals(mainUrl) && subUrl.startsWith(mainUrl)) {
                    final int currentIndex = index;
                    futures[index++] = CompletableFuture.supplyAsync(() -> {
                        try {
                            Document document = Jsoup.connect(subUrl).get(); // 使用 Jsoup 进行子网站内容抓取
                            String text = document.text(); // 获取子网站文本内容

                            return countKeywords(text, subUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return new HashMap<String, Integer>();
                        }
                    });
                }
            }

            for (int i = 0; i < index; i++) {
                CompletableFuture<Map<String, Integer>> future = futures[i];
                Map<String, Integer> keywordCounts = future.get();
                for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(links.get(i).attr("abs:href"));
                    row.createCell(1).setCellValue(entry.getKey());
                    row.createCell(2).setCellValue(entry.getValue());
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {

                workbook.write(outputStream);
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> countKeywords(String text, String url) {
        // 在这里实现关键字的搜索和计数逻辑
        Map<String, Integer> counts = new HashMap<>();
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            StringBuilder phraseBuilder = new StringBuilder();
            int j = i;
            while (j < words.length && phraseBuilder.length() < 20) { // 限制短句长度为20个字符
                phraseBuilder.append(words[j]).append(" ");
                String phrase = phraseBuilder.toString().trim();
                counts.put(phrase, counts.getOrDefault(phrase, 0) + 1);
                j++;
            }
        }

        // 只保留出现次数大于两次的短句
        counts.entrySet().removeIf(entry -> entry.getValue() <= 2);

        // 将 Map 按值进行排序
        return counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }



}
