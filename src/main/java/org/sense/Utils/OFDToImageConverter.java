package org.sense.Utils;

import org.ofdrw.converter.export.ImageExporter;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class OFDToImageConverter {

    private Path ofdPath;  // OFD文档路径
    private Path imgDirPath;  // 导出图片的目录路径
    private String imageType;  // 图片格式类型（PNG、JPG、BPM）
    private double ppm;  // 图片质量（ppm参数）

    /**
     * 构造函数，初始化必要的参数
     */
    public  OFDToImageConverter(Path ofdPath, Path imgDirPath, String imageType, double ppm) {
        this.ofdPath = ofdPath;
        this.imgDirPath = imgDirPath;
        this.imageType = imageType;
        this.ppm = ppm;
    }

    /**
     * 转换OFD文档为图片
     */
    public void convert() throws IOException {
        // 确保输出目录存在
        if (!Files.exists(imgDirPath)) {
            Files.createDirectories(imgDirPath);
        }

        // 创建ImageExporter对象并设置参数
        try (ImageExporter exporter = new ImageExporter(ofdPath, imgDirPath, imageType, ppm)) {
            // 执行导出操作
            exporter.export();
        }
    }

    /**
     * 获取导出图片的文件路径列表
     */
    public List<Path> getExportedImagePaths() throws IOException {
        try (ImageExporter exporter = new ImageExporter(ofdPath, imgDirPath, imageType, ppm)) {
            return exporter.getImgFilePaths();
        }
    }

    /**
     * 设置导出图片的质量（ppm参数）
     */
    public void setPPM(double ppm) {
        this.ppm = ppm;
    }

    public static void main(String[] args) {
        // 示例路径
        Path ofdPath = Paths.get("src/test/resources/test.ofd");
        Path imgDirPath = Paths.get("target/test.ofd/");

        // 创建OFD转图片工具类对象
        OFDToImageConverter converter = new OFDToImageConverter(ofdPath, imgDirPath, "PNG", 20d);

        try {
            // 执行OFD到图片的转换
            converter.convert();

            // 获取导出的图片路径
            List<Path> imagePaths = converter.getExportedImagePaths();
            imagePaths.forEach(path -> System.out.println("Exported image: " + path.toString()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
