package org.sense.Utils;

import org.ofdrw.converter.ofdconverter.DocConverter;
import org.ofdrw.converter.ofdconverter.PDFConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OFDToPDFConverter {

    /**
     * 将一个 OFD 文件转换为 PDF 文件。
     *
     * @param inputPath 输入的 OFD 文件路径。
     * @param outputPath 输出的 PDF 文件路径。
     * @throws IOException 如果在文件处理或转换过程中发生错误。
     */
    public static void convertOFDToPDF(Path inputPath, Path outputPath) throws IOException {
        // 调试输出路径信息
        System.out.println("输入文件路径: " + inputPath);
        System.out.println("输出文件路径: " + outputPath);

        // 确保输入文件存在
        if (inputPath == null || !Files.exists(inputPath)) {
            throw new IllegalArgumentException("输入的 OFD 文件不存在: " + inputPath);
        }

        // 确保输出路径所在目录存在
        if (!Files.exists(outputPath.getParent())) {
            Files.createDirectories(outputPath.getParent());
        }

        // 打印路径信息
        System.out.println("OFD 输入文件路径: " + inputPath.toAbsolutePath());
        System.out.println("PDF 输出文件路径: " + outputPath.toAbsolutePath());

        // 转换 OFD -> PDF
        try (DocConverter converter = new PDFConverter(outputPath)) {
            System.out.println("开始转换...");
            converter.convert(inputPath);
            System.out.println("转换完成！");
        }
    }

    public static void main(String[] args) {
        try {
            // 示例用法
            Path inputPath = Paths.get("/Users/mortysmith/Desktop/test.ofd");
            Path outputPath = Paths.get("/Users/mortysmith/Desktop/output.pdf");

            convertOFDToPDF(inputPath, outputPath);

            System.out.println("OFD 转 PDF 成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("OFD 转 PDF 失败。");
        }
    }
}
