//package org.sense.service.serviceimpl;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.ImageType;
//import org.apache.pdfbox.rendering.PDFRenderer;
//import org.sense.Utils.OFDToPDFConverter;
//import org.sense.service.FileConverterService;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class FileConverterServiceImpl implements FileConverterService {
//    @Override
//    public void convertOFDToPDF(File inputFile, File outputFile) throws Exception {
//        // 检查输入文件是否存在
//        if (inputFile == null || !inputFile.exists()) {
//            throw new IllegalArgumentException("输入的OFD文件无效或不存在");
//        }
//
//        // 检查输出文件的父目录是否存在，如果不存在则创建
//        File parentDir = outputFile.getParentFile();
//        if (parentDir != null && !parentDir.exists()) {
//            if (!parentDir.mkdirs()) {
//                throw new Exception("无法创建输出目录：" + parentDir.getAbsolutePath());
//            }
//        }
//
//        // 调用OFD转PDF的具体逻辑
//        try {
//            // 示例：调用某个OFD到PDF的转换库（假设库名为`OFDToPDFConverter`）
//            OFDToPDFConverter converter = new OFDToPDFConverter();
//            converter.convert(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
//            System.out.println("OFD文件已成功转换为PDF: " + outputFile.getAbsolutePath());
//        } catch (Exception e) {
//            throw new Exception("OFD转换为PDF失败: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void convertPDFToImages(String pdfPath, String outputDir, String format) throws IOException {
//        // 打开 PDF 文件
//        File pdfFile = new File(pdfPath);
//        try (PDDocument document = PDDocument.load(pdfFile)) {
//            // 创建 PDF 渲染器
//            PDFRenderer pdfRenderer = new PDFRenderer(document);
//
//            // 确保输出目录存在
//            File outputDirectory = new File(outputDir);
//            if (!outputDirectory.exists()) {
//                outputDirectory.mkdirs();
//            }
//
//            // 遍历每一页
//            int pageCount = document.getNumberOfPages();
//            for (int page = 0; page < pageCount; page++) {
//                // 渲染当前页为图像
//                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
//
//                // 生成输出文件路径
//                String outputFilePath = String.format("%s/page_%d.%s", outputDir, page + 1, format);
//                File outputFile = new File(outputFilePath);
//
//                // 将图像写入文件
//                ImageIO.write(bufferedImage, format, outputFile);
//
//                System.out.println("Converted page " + (page + 1) + " to " + outputFilePath);
//            }
//
//            System.out.println("PDF conversion completed! Images saved to: " + outputDir);
//        }
//    }
//}
