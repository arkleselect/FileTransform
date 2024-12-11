package org.sense.Utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.ofdrw.reader.OFDReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class OFDToPDFConverter {

    /**
     * 将 OFD 文件转换为 PDF 文件
     *
     * @param ofdPath  输入的 OFD 文件路径
     * @param pdfPath  输出的 PDF 文件路径
     * @throws IOException 转换过程中可能抛出的异常
     */
    public void convert(String ofdPath, String pdfPath) throws IOException {
        // 使用 try-with-resources 确保资源正确关闭
        try (OFDReader reader = new OFDReader(Paths.get(ofdPath));
             PDDocument document = new PDDocument()) {

            // 获取 OFD 文档的总页数
            int numberOfPages = reader.getNumberOfPages();

            for (int pageIndex = 0; pageIndex < numberOfPages; pageIndex++) {
                // 渲染 OFD 页为 BufferedImage
                BufferedImage image = reader.renderPage(pageIndex);
                reader.

                // 创建新的 PDF 页面
                PDPage pdfPage = new PDPage();
                document.addPage(pdfPage);

                // 将渲染的图像插入到 PDF 页面中
                PDImageXObject pdfImage = PDImageXObject.createFromFileByContent(
                        saveTempImage(image), document);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, pdfPage)) {
                    // 设置图像位置和大小
                    contentStream.drawImage(pdfImage, 0, 0, pdfPage.getMediaBox().getWidth(),
                            pdfPage.getMediaBox().getHeight());
                }
            }

            // 保存生成的 PDF
            document.save(pdfPath);
        }
    }

    /**
     * 将 BufferedImage 保存为临时文件
     *
     * @param image BufferedImage 对象
     * @return 保存的临时文件
     * @throws IOException 文件保存异常
     */
    private File saveTempImage(BufferedImage image) throws IOException {
        File tempFile = File.createTempFile("ofd_page", ".png");
        ImageIO.write(image, "png", tempFile);
        tempFile.deleteOnExit(); // 在程序退出时自动删除临时文件
        return tempFile;
    }
}
