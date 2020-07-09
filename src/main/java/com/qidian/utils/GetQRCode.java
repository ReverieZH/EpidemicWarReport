package com.qidian.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.compiler.PluginProtos;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;

public class GetQRCode {

    public static void getURCodePicture(String content,String name){
        int width=600;
        int height=600;
        String type="png";

        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);


        try {
            BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);
            MatrixToImageWriter.writeToPath(bitMatrix,type, new File(name).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void QRReader(File file) throws IOException, NotFoundException {
        MultiFormatReader formatReader = new MultiFormatReader();
        //读取指定的二维码文件
        BufferedImage bufferedImage = ImageIO.read(file);
        BinaryBitmap binaryBitmap= new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
        //定义二维码参数
        Map hints= new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        com.google.zxing.Result result = formatReader.decode(binaryBitmap, hints);
        //输出相关的二维码信息
        System.out.println("解析结果："+result.toString());
        System.out.println("二维码格式类型："+result.getBarcodeFormat());
        System.out.println("二维码文本内容："+result.getText());
        bufferedImage.flush();
    }


}
