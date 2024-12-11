package org.sense.service;

import java.io.File;

public interface FileTransformService {
    /**
     *
     * @param file 需要转换的文件
     * @return
     */
    File convertPdfToImage(File file);
}
