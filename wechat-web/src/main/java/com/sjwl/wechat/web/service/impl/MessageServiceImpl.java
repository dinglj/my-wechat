package com.sjwl.wechat.web.service.impl;

import com.sjwl.wechat.web.service.MessageService;
import com.yesway.wechat.platform.message.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
	private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);


	private String testMesssge(String xml) throws Exception {
		String result="";
		TextMessage textMessage = TextMessage.parseMessage(xml);
		String content=textMessage.getContent();

		if ("text".equals(content)) {
			// 文本消息
			TextMessage textMsg=new TextMessage();
			textMessage.setToUserName(textMessage.getFromUserName());
			textMessage.setFromUserName(textMessage.getToUserName());
			textMessage.setMsgType("text");
			textMessage.setContent("测试123sd@1");
			result=textMessage.buildXmlMessage();
		} else if ("image".equalsIgnoreCase(content)) {
			// 图片消息




		} else if ("voice".equalsIgnoreCase(content)) {
			// 语音消息
		} else if ("video".equalsIgnoreCase(content)) {
			// 视频消息
		}



		return null;
	}

	// 接收到微信平台发过来的消息进行处理
	@Override
	public String processMessage(String xml) {
		String result = "";
		try {
			BaseMessage baseMessage = BaseMessage.parseMessage(xml);
			String msgType = baseMessage.getMsgType();
			if ("text".equals(msgType)) {
				// 文本消息
				log.debug("处理text消息");
			} else if ("image".equalsIgnoreCase(msgType)) {
				// 图片消息
				log.debug("处理image消息");
			} else if ("voice".equalsIgnoreCase(msgType)) {
				// 语音消息
				log.debug("处理voice消息");
			} else if ("video".equalsIgnoreCase(msgType)) {
				// 视频消息
				log.debug("处理video消息");
			} else if ("location".equalsIgnoreCase(msgType)) {
				// 地理位置消息
				log.debug("处理location消息");
			} else if ("link".equalsIgnoreCase(msgType)) {
				// 链接消息
				log.debug("处理link消息");
			} else if ("event".equalsIgnoreCase(msgType)) {
				// 事件推送
				EventMessage eventMessage = EventMessage.parseMessage(xml);
				String event = eventMessage.getEvent();
				if ("subscribe".equalsIgnoreCase(event)) {
					// 订阅
					log.debug("处理subscribe事件");
				} else if ("unsubscribe".equalsIgnoreCase(event)) {
					// 取消订阅
					log.debug("处理unsubscribe事件");
				} else if ("SCAN".equalsIgnoreCase(event)) {
					// 用户已关注时的事件推送
					log.debug("处理SCAN事件");
				} else if ("LOCATION".equalsIgnoreCase(event)) {
					// 上报地理位置事件
					log.debug("处理LOCATION事件");
				} else if ("CLICK".equalsIgnoreCase(event)) {

					//测试点击菜单 消息回复
//					TextMessage textMessage=new TextMessage();
//					textMessage.setToUserName(eventMessage.getFromUserName());
//					textMessage.setFromUserName(eventMessage.getToUserName());
//					textMessage.setMsgType("text");
//					textMessage.setContent("测试123sd@1");
//					result=textMessage.buildXmlMessage();

					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(eventMessage.getFromUserName());
					newsMessage.setFromUserName(eventMessage.getToUserName());
					newsMessage.setMsgType("news");
					Article article1 = new Article();
					article1.setTitle("title1");
					article1.setDescription("description1");
					article1.setPicUrl("https://img.alicdn.com/tps/TB19JjaLFXXXXXxXXXXXXXXXXXX-520-280.jpg");
					article1.setUrl("www.baidu.com");

					newsMessage.getArticles().add(article1);

					Article article2 = new Article();
					article2.setTitle("title");
					article2.setDescription("description");
					article2.setPicUrl("picurl");
					article2.setUrl("url");

					newsMessage.getArticles().add(article2);
					result=newsMessage.buildXmlMessage();

					// 自定义菜单事件
					log.debug("处理CLICK事件");
				} else if ("VIEW".equalsIgnoreCase(event)) {
					// 点击菜单跳转链接时的事件推送
					log.debug("处理VIEW事件");
				} else {
					log.debug("处理未知事件");
				}
			} else {
				// 未知消息
				log.debug("处理未知消息");
			}
		} catch (Exception e) {
			log.error("processMessage error:", e);
		}
		return result;
	}

}
