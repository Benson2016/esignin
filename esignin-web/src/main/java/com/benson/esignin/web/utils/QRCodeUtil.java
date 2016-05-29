package com.benson.esignin.web.utils;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.utils.StringUtil;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码工具类
 *
 * @author Benson Xu
 * @version 1.0
 * @copyright ESiginIn-Benson Copyright (c) 2016
 * @since 2016年05月25日 9:41
 */
public class QRCodeUtil {

    private final static Logger logger = Logger.getLogger(QRCodeUtil.class);

    private BitSource zz;
    public BitMatrix bitMatrix;

    /**
     * 生成二维码
     *
     * @param contents 内容
     * @param width 宽度
     * @param height 高度
     * @param imgPath 绝对路径
     */
    public static void generate(String contents, int width, int height, String imgPath) {

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, CommonCons.CHARSET_UTF8);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(imgPath));
        } catch (Exception e) {
            logger.error("生成二维码时异常：{}", e);
        }

    }

    /**
     * 生成二维码到输出流中
     *
     * @param contents 内容
     * @param width 图片宽度
     * @param height 图片高度
     * @param response Servlet输出流
     */
    public static void generate(String contents, int width, int height, HttpServletResponse response) {

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, CommonCons.CHARSET_UTF8);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
        } catch (Exception e) {
            logger.error("生成二维码时异常：{}", e);
        }

    }


    /**
     * 解析二维码
     *
     * @param imgPath 二维码路径
     */
    public static String analysis(String imgPath) {
        if (StringUtil.isNullString(imgPath)) {
            logger.error("解析二维码时参数错误：参数imgPath不能为空！");
            return null;
        }

        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                logger.error("解析二维码时参数错误：目标文件不存在！");
                return null;
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Map<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, CommonCons.CHARSET_UTF8);

            result = new MultiFormatReader().decode(bitmap, hints);

            return result.getText();

        } catch (Exception e) {
            logger.error("解析二维码时异常：{}", e);
            //e.printStackTrace();
        }

        return null;
    }

}
