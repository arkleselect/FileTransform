package org.sense.service.serviceimpl;

import org.sense.Utils.OFDToPDFConverter;
import org.sense.service.FileConverterService;

import java.io.File;

public class FileConverterServiceImpl implements FileConverterService {

    @Override
    public void convertOFDToPDF(File inputFile, File outputFile) throws Exception {
        // 检查输入文件是否存在
        if (inputFile == null || !inputFile.exists()) {
            throw new IllegalArgumentException("输入的OFD文件无效或不存在");
        }

        // 检查输出文件的父目录是否存在，如果不存在则创建
        File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new Exception("无法创建输出目录：" + parentDir.getAbsolutePath());
            }
        }

        // 调用OFD转PDF的具体逻辑
        try {
            // 示例：调用某个OFD到PDF的转换库（假设库名为`OFDToPDFConverter`）
            OFDToPDFConverter converter = new OFDToPDFConverter();
            converter.convert(inputFile.getAbsolutePath(), outputFile.getAbsolutePath());
            System.out.println("OFD文件已成功转换为PDF: " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            throw new Exception("OFD转换为PDF失败: " + e.getMessage(), e);
        }
    }
}
