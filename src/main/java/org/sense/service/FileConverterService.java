package org.sense.service;

import java.io.File;
import java.io.IOException;

public interface FileConverterService {
    /**
     * 将OFD文件转换为PDF文件
     *
     * @param inputFile  OFD文件
     * @param outputFile 转换后的PDF文件
     * @throws Exception 如果转换过程中出现错误
     */
    void convertOFDToPDF(File inputFile, File outputFile) throws Exception;

    /**
     * 将PDF文件转换为图片
     * @param pdfPath PDF文件路径
     * @param outputDir 输出路径
     * @param format 图片格式（如 "png", "jpg"）
     * @throws IOException 文件读取或写入时可能抛出的异常
     */
    void convertPDFToImages(String pdfPath, String outputDir, String format) throws IOException;
}
