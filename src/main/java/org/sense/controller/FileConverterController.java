package org.sense.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/converter")
public class FileConverterController {

    @PostMapping("/ofd2pdf")
    public ResponseEntity<String> convertFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件为空");
        }
        System.out.println("上传的文件名: " + file.getOriginalFilename());
        System.out.println("文件类型: " + file.getContentType());

        try {
            String result = processFileConversion(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            e.printStackTrace();  // 打印堆栈信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件处理失败: " + e.getMessage());
        }
    }

    private String processFileConversion(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if ("ofd".equals(fileType)) {
            return convertOFDToPDF(file);
        } else if ("pdf".equals(fileType)) {
            return "PDF 文件无需转换";
        }

        return "不支持的文件格式";
    }

    private String convertOFDToPDF(MultipartFile file) throws IOException {
        // 将上传的 OFD 文件保存到临时文件
        File tempFile = File.createTempFile("uploaded-ofd-", ".ofd");
        try {
            Files.copy(file.getInputStream(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("文件保存失败", e);
        }

        // 解析 OFD 文件并生成 PDF
        try {
            File pdfFile = new File("converted.pdf");
            convertOFDToPDFLogic(tempFile, pdfFile);
            return "OFD 文件已成功转换为 PDF，文件保存为 converted.pdf";
        } catch (Exception e) {
            e.printStackTrace();
            return "OFD 文件转换失败";
        }
    }

    // 简化版的 OFD 转 PDF 逻辑
    private void convertOFDToPDFLogic(File ofdFile, File pdfFile) throws IOException {
        // 这个方法应该实现具体的 OFD 文件解析逻辑
        // 假设我们使用了 PDFBox 来生成一个简单的 PDF 作为示例

        PDDocument document = new PDDocument();

        // 创建一页 PDF
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(25, 750);

        // 这里我们只是简单地写一些文字作为示例，OFD 转换的核心解析部分需要根据 OFD 的结构来实现
        contentStream.showText("OFD 文件转换为 PDF 成功！");
        contentStream.endText();
        contentStream.close();

        // 保存 PDF 文件
        document.save(pdfFile);
        document.close();
    }
}
