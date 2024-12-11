package org.sense.service;

import java.io.File;

public interface FileConverterService {
    /**
     * 将OFD文件转换为PDF文件
     *
     * @param inputFile  OFD文件
     * @param outputFile 转换后的PDF文件
     * @throws Exception 如果转换过程中出现错误
     */
    void convertOFDToPDF(File inputFile, File outputFile) throws Exception;
}
