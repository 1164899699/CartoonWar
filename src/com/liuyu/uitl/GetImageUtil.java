package com.liuyu.uitl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: ��ȡͼƬ�Ĺ�����
* @author YangSong
* @date 2019��8��20�� ����8:45:49
*
*/
public class GetImageUtil {

	//URL United
	//��ȡͼ�ķ���
	 public static Image getImg(String imgName) {
	  //����
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
	
