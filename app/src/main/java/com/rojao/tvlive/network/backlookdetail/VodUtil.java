package com.rojao.tvlive.network.backlookdetail;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class VodUtil
{

    public static String getReseeUrlFromStreamControl(String channelId,String assertId){
        Socket  socket = null;
        String result="";
        try {
            socket= new Socket("221.6.85.150",12320);  
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int contentLength = 72;
            RewindEntity contentEntity = new RewindEntity(
                    channelId,assertId, contentLength);
            MessageHeadEntity headEntity = new MessageHeadEntity(
                    "LIVE", "0", "0",MessageHeadEntity.STMSG_RESEE,
                    (short) 0x0000,contentEntity.getContentLen(),"00");
            contentEntity.setMessageHead(headEntity);
            byte[] array = contentEntity.toByte();
            dos.write(array);  
             byte[] byteArray = new byte[1024];
                     int length = dis.read(byteArray);
                     if (length > -1) {
                     String playUrl = RewindEntity.toEntity(byteArray).getProgramURL();
                     result=playUrl;
                     }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
       // return result;
        if(result!=null){
            result=result.replace("221.6.85.150:9000", "221.6.85.155");
        }
      return result;
    }  
}
