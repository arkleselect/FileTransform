package org.sense.Utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFToImageConverter {

    /**
     * 将 PDF 文件中的每一页转换为图片
     *
     * @param pdfPath   PDF 文件路径
     * @param outputDir 输出图片的文件夹路径
     * @param format    图片格式（如 "png", "jpg"）
     * @throws IOException 文件读取或写入时可能抛出的异常
     */
    public static void convertPDFToImages(String pdfPath, String outputDir, String format) throws IOException {
        // 打开 PDF 文件
        File pdfFile = new File(pdfPath);
        try (PDDocument document = PDDocument.load(pdfFile)) {
            // 创建 PDF 渲染器
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            // 确保输出目录存在
            File outputDirectory = new File(outputDir);
            if (!outputDirectory.exists()) {
                outputDirectory.mkdirs();
            }

            // 遍历每一页
            int pageCount = document.getNumberOfPages();
            for (int page = 0; page < pageCount; page++) {
                // 渲染当前页为图像
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

                // 生成输出文件路径
                String outputFilePath = String.format("%s/page_%d.%s", outputDir, page + 1, format);
                File outputFile = new File(outputFilePath);

                // 将图像写入文件
                ImageIO.write(bufferedImage, format, outputFile);

                System.out.println("Converted page " + (page + 1) + " to " + outputFilePath);
            }

            System.out.println("PDF conversion completed! Images saved to: " + outputDir);
        }
    }

    // Test Fiction
    public static void main(String[] args) {
        try {
            String pdfPath = "/Users/mortysmith/Desktop/广东发票.pdf"; // PDF 文件路径
            String outputDir = "/Users/mortysmith/Desktop/test"; // 输出文件夹路径
            String format = "png"; // 图片格式 (支持 png、jpg)

            convertPDFToImages(pdfPath, outputDir, format);
        } catch (IOException e) {
            System.err.println("Error occurred during PDF to image conversion: " + e.getMessage());
        }
    }
}
