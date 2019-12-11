package com.atguigu.gmall.manage.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description PmsUploadUtil
 * @Author zhonghua.zhang
 * @Date 2019/10/24 20:43
 * @Param
 * @return
 **/
public class PmsUploadUtil{

    public static String uploadImae(MultipartFile multipartFile){
        String imgUrl="http://203.195.167.162:8088";
        //将图片或者音视频上传到分布式的文件存储系统

        //将图片的存储路径返回给页面
        String configPath = PmsUploadUtil.class.getResource("/tracker.conf").getPath();

        try {
            ClientGlobal.init(configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();
        //获取一个trackerServer实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过一个tracker去获取一个Storage连接客户端
        StorageClient storageClient=new StorageClient(trackerServer,null);


        try {

            byte[] bytes = multipartFile.getBytes();//获得上传的二进制对象

            //获得文件后缀名
            String originalFilename = multipartFile.getOriginalFilename();//a.jpg
            System.out.println(originalFilename);
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i+1);

            String[] uploadInfos = storageClient.upload_file(bytes,extName,null);



            for (String uploadInfo:uploadInfos){
                imgUrl+="/"+uploadInfo;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return imgUrl;
    }
}
