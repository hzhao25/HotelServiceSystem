package com.sky.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Component
@Slf4j
public class QrCodeUtil implements Serializable {

    @Autowired
    private AccessTokenUtil accessTokenUtil;

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 生成带参小程序二维码
     * @param roomId	客房id参数
     */
    public String getminiqrQr(Integer roomId) {
        log.info("绑定的客房id：{}",roomId);
        try
        {
            String accessToken=accessTokenUtil.postToken();
            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", roomId);
            paramJson.put("page", "pages/index/index");
            paramJson.put("width", 430);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            //把数据转成字节数组
            byte[] file=inputStreamToByteArray(bis);
            //生成图片名
            String objectName= UUID.randomUUID().toString()+".png";
            //oss文件的请求路径
            String filePath=aliOssUtil.upload(file,objectName);
            return filePath;
        }
        catch (Exception e)
        {
            log.error("文件上传失败：{}",e);
        }
        return null;
    }

    public static byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        // 创建一个 ByteArrayOutputStream 用于存储读取的数据
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        // 用于存储每次读取的字节数组
        byte[] data = new byte[1024];
        int nRead;
        // 从输入流中读取数据，直到读完
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        // 刷新缓冲区，确保所有数据都被写入
        buffer.flush();
        // 将 ByteArrayOutputStream 中的数据转换为字节数组
        return buffer.toByteArray();
    }
}
