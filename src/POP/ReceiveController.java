package POP;

import Util.MailUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import SMTP.MailBody;

import java.util.ArrayList;
import java.util.List;

public class ReceiveController {
	
	
	private ReceiveService receiveService;
	
	
	
	/**
	 * 
	 * @Description: 与前端交互的接口类
	 * @param @param popmailServer
	 * @param @param mailBody2
	 * @param @return
	 * @return String
	 * @param popmailServer
	 * @param mailBody2
	 * @return
	 */

	//添加对收信的解码	
	 public PopResult receiveMail(PopMailServer popmailServer, MailBody mailBody2){
	        try {
	            /*从零开始pop3*/
	            receiveService = new ReceiveService();
	            PopResult popResult = receiveService.receiveMail(popmailServer, mailBody2);
				if(popResult.getMessage().equals("读取成功")) {
					List<String> mailStrings = popResult.getMailString();
					List<PopMail> popMails = new ArrayList<>();
					for(String s : mailStrings){
						popMails.add(MailUtil.decodePop(s));
					}
					popResult.setMails(popMails);
				}
				return popResult;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	 }
	 
	 
	 //添加删除邮件的接口
	 public boolean deleteMail(int id) {
		 try {
			 receiveService.deleteMail(id);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	 }
}
