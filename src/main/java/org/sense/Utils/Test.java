//package org.sense.Utils;
//
//import org.ofdrw.converter.ConvertHelper;
//
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class Test {
//    public static void main(String[] args) {
//        // 示例测试调用
//        String fileUrl = "/Users/mortysmith/Desktop/test.ofd";
//        String fileName = "test.ofd";
//
//        Test test = new Test();
//        test.pdfConvert(fileUrl, fileName);
//    }
//
//    public void pdfConvert(String fileUrl, String fileName) {
//        if (fileName.endsWith("ofd")) {
//            // 将 OFD 文件名更改为 PDF 文件名
//            String name = fileName.replace(".ofd", ".pdf");
//
//            // 获取文件的上级路径
//            File parentFile = new File(fileUrl).getParentFile();
//
//            // 文件的输入路径
//            Path ofdPath = Paths.get(fileUrl);
//
//            // 转换后 PDF 的路径
//            Path pdfPath = Paths.get(parentFile.getAbsolutePath(), name);
//
//            System.out.println("OFD 文件路径: " + ofdPath);
//            System.out.println("PDF 文件路径: " + pdfPath);
//
//            // 调用转换工具进行 OFD 到 PDF 的转换
//            try {
//                ConvertHelper.toPdf(ofdPath, pdfPath);
//                System.out.println("转换成功！");
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.err.println("OFD 转 PDF 失败：" + e.getMessage());
//            }
//        } else {
//            System.err.println("输入文件不是 OFD 格式！");
//        }
//    }
//}
