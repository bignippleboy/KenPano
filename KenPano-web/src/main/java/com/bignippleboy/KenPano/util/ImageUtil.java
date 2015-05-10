/**
 * @Title:标题XXX
 * @Description:描述XXXX
 * @Company:foreveross
 * @ClassName:ImageUtil.java
 * @Author:chifangxiong
 * @CreateDate:2015年1月23日
 * @UpdateUser:chifangxiong
 * @Version:0.1
 */
package com.bignippleboy.KenPano.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class ImageUtil {

	public static void writeImage(String path, String fileName, String content) {

		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, 1);
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
			File file1 = new File(path, fileName);
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
