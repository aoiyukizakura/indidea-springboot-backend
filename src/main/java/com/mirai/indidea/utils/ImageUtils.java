package com.mirai.indidea.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class ImageUtils {
    public static InputStream cutImage(MultipartFile file, int a, int b) throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(file.getInputStream());

        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
        ImageReader reader =iter.next();
        String ext=(reader.getFormatName());

        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        int imageIndex = 0;
        int x=reader.getWidth(imageIndex);
        int y=reader.getHeight(imageIndex);
        int w,h;
        if((x/y)>=(a/b)){
            if(a/b>1){
                w=x;
                h=x*b/a;
            }else{
                w=y*a/b;
                h=y;
            }
        }else{
            if(a/b<1){
                w=y*a/b;
                h=y;
            }else{
                w=x;
                h=x*b/a;
            }
        }
        Rectangle rect = new Rectangle((x-w)/2, (y-h)/2, w, h);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0,param);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, ext, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        iis.close();
        return is;
    }
}
