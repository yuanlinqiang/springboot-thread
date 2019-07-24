package com.example.contoller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.rabbit.topic.TopicSender;

/**
 * @deprecated:
 * @author作者：mp 2019年5月31日
 */
@Controller
public class ThradContoller {

	@Autowired
	private TopicSender TopicSender;
	@Autowired
	private com.example.rabbit.topic.TopicReceiver TopicReceiver;
	@Autowired
	private com.example.rabbit.topic.TopicReceiver2 TopicReceiver2;

    @Autowired
    private com.example.rabbit.topic.TopicReceiver3 TopicReceiver3;

	SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-DD HH:mm:ss");

	@GetMapping("/thread")
	@ResponseBody
	public long Thread() {
		Date date = new Date();
		long start = date.getTime();
		System.out.println(sdf.format(date));
		for (int i = 0; i < 20000; i++) {
			TopicSender.send(i);
			//TopicSender.send1(i);
			//TopicSender.send2(i);
			//TopicSender.send3(i);

		}
		Date enddate = new Date();
		long end = enddate.getTime();
		System.out.println(sdf.format(enddate));
		System.out.println("所消耗时间为：" + (end - start) + "毫秒");
		return end - start;
	}
	
}
