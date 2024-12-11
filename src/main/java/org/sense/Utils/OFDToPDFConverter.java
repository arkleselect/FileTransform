//package org.sense.Utils;
//
//import org.ofdrw.reader.OFDReader;
//import org.ofdrw.*;
//
//import java.io.File;
//import java.io.IOException;
//
//public class OFDToPDFConverter {
//    public static void main(String[] args) {
//        // 输入的OFD文件路径
//        String inputFilePath = "path/to/your/input.ofd";
//        // 输出的PDF文件路径
//        String outputFilePath = "path/to/your/output.pdf";
//
//        try {
//            // 读取OFD文件
//            OFDReader reader = new OFDReader(new File(inputFilePath));
//
//            // 使用 PdfConverter 来转换为 PDF
//            PdfConverter converter = new PdfConverter();
//            converter.convert(reader, new File(outputFilePath));
//
//            System.out.println("OFD 转换为 PDF 完成！");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("转换过程中发生错误: " + e.getMessage());
//        }
//    }
//}
