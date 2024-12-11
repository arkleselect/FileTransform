package org.sense.controller;

import org.sense.service.FileTransformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping
public class FileTransformController {

    @Resource
    private FileTransformService fileTransformService;

    @PostMapping("/pdftoimage")
    public File convertPdfToImage(@RequestParam("file") File file) {
        File filed = fileTransformService.convertPdfToImage(file);
        return filed;
    }
}
