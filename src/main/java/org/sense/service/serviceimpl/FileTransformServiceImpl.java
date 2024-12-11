package org.sense.service.serviceimpl;

import org.sense.service.FileTransformService;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class FileTransformServiceImpl implements FileTransformService {

    @Override
    public File convertPdfToImage(File file) {
        return null;
    }
}
