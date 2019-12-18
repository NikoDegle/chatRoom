package com.nciae.chatRoom.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    //信息发送人
    private String sender;
    //信息内容
    private String content;
    //发送时间
    private Date sendTime;

    public Message(){
        super();
    }

    public Message(String sender, String content){
        this.sender = sender;
        this.content = content;
        this.sendTime = new Date();
    }
}
