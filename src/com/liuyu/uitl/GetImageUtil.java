package com.liuyu.uitl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: 获取图片的工具类
* @author YangSong
* @date 2019年8月20日 上午8:45:49
*
*/
public class GetImageUtil {

	//URL United
	//获取图的方法
	 public static Image getImg(String imgName) {
	  //反射
	  URL resource = GetImageUtil.class.getClassLoader().getResource("com/liuyu/img/"+imgName);
	  BufferedImage bufferedImage = null;
	  //System.out.println(resource);
	  try {
	   bufferedImage = ImageIO.read(resource);
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	  return  bufferedImage;
	 }
	}
	
