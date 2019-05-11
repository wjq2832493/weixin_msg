package com.wjq.weixin.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Controller（控制器），其实就相当于是Servlet，但是Spring MVC把所有的Servlet相关API都屏蔽掉了！
//屏蔽的好处：不需要依赖Tomcat就可以实现单元测试。
@RestController// 基于RESTful风格的WEB服务的控制器
@RequestMapping("/wjq/weixin/receiver")
public class MessageReceiverController {
     private static final Logger  LOG=org.slf4j.LoggerFactory.getLogger(MessageReceiverController.class);
	// 必须要有Handler方法才不会出现404
	// Handler方法就是用来处理各种请求的操作、入口
	@GetMapping
	public String echo(//
			@RequestParam("signature") String signature, //
			@RequestParam("timestamp") String timestamp, //
			@RequestParam("nonce") String nonce, //
			@RequestParam("echostr") String echostr//
	) {
		// 正常的接入方法要求对数据的有效性进行验证，验证通过以后返回echostr
		// 把timestamp和nonce放入一个数组，并且对两个字符串进行排序
		// 排序以后，把数组里面的元素拼接成一个String，使用SHA1算法进行加密
		// 加密的时候，需要使用一个【秘钥】，这个秘钥在公众号平台中生成的
		// 加密的以后的内容，如果跟signature相同，表示验证通过

		return echostr;
	}
	@PostMapping
	public String receive(
			@RequestParam(value="signature",required=false) String signature, //
			@RequestParam(value="timestamp",required=false) String timestamp, //
			@RequestParam(value="nonce",required=false) String nonce,//
			@RequestBody String xml
			){
		//把收到的请求消息和信息打印出来
		//使用日志记录器可以非常方便输出日期时间并且还有位置并且可以根据位置级别可以临时过滤需要的信息
		LOG.trace("\n收到的请求参数\n "//大括号是一个占位符，需要后面传入参数
				+ "signature:{}\n"
				+ "timestamp:{}\n"
				+ "nonce:{}\n"
				+ "收到的请求内容\n{}\n"
				,signature,timestamp,nonce,xml);
				return "success";
		
	}
}

