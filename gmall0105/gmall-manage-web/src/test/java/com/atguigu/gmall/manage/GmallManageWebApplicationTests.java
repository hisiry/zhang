package com.atguigu.gmall.manage;


import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() throws IOException, MyException {


       String configPath = this.getClass().getResource("/tracker.conf").getPath();
       ClientGlobal.init(configPath);

       TrackerClient trackerClient = new TrackerClient();
       //获取一个trackerServer实例
       TrackerServer trackerServer = trackerClient.getConnection();
       //通过一个tracker去获取一个Storage连接客户端
        StorageClient storageClient=new StorageClient(trackerServer,null);

        String[] uploadInfos=storageClient.upload_file("d:/a.jpg","jpg",null);
        String url="http://203.195.167.162:8088";

        for (String uploadInfo:uploadInfos){
            url+="/"+uploadInfo;
            System.out.println(url);
        }
    }



}
