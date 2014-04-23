package com.blueone.common.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blueone.common.domain.MailInfo;
import com.blueone.common.util.Configuration;
import com.blueone.common.util.Utility;
import com.blueone.customer.domain.CustomerInfo;

//@Service
public class MailService implements IMailService {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	private final String webmasterName = Configuration.getInstance().getProperty("mail.webmaster.name");
	private final String webmasterEmail = Configuration.getInstance().getProperty("mail.webmaster.email");
	
	@Override
	public String getCheckEmail(MailInfo mailModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		StringBuffer eMailInfo = new StringBuffer();
		List<CustomerInfo> memberList = sqlSession.selectList("mail.getCheckEmail", mailModel);
		
		if (memberList != null && memberList.size() > 0) {
			for (int i = 0; i < memberList.size(); i++) {
				if (i>0) eMailInfo.append(";");
				eMailInfo.append(memberList.get(i).getCustEmail());
			}
		}
		
		return eMailInfo.toString();
	}
	
	@Override
	public boolean sentMail(MailInfo mailModel) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// 硫붿씪異붽�(�몃젋�앹뀡 泥섎━)
//			sqlMapClient.startTransaction();
			int rst = sqlSession.insert("mail.insertTBL090810", mailModel);
			if (0 == rst) return false;
			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			// 硫붿씪諛쒖넚
//			MailSender mailSender = new MailSender();
//			mailSender.sent(mailModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}

		return true;
	}
	
	@Override
	public boolean sentMailUserId(CustomerInfo member) {
		
		// �댁슜
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("title", "�뚯썝�뺣낫");
		hm.put("content", "�꾩씠��: " + member.getCustId());
		String cont = applyTemplet("sample/mail/email.htm", hm);
		
		MailInfo mailModel = new MailInfo();
		mailModel.setFromNm(webmasterName);
		mailModel.setFromAddr(webmasterEmail);
		mailModel.setToAddr(member.getCustEmail());
		mailModel.setSubject("[踰뺣Ц�붽탳�≪꽱�� �뚯썝�뺣낫 - �꾩씠��");
		mailModel.setCont(cont);
		mailModel.setInsUser("admin");
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// 硫붿씪異붽�(�몃젋�앹뀡 泥섎━)
//			sqlMapClient.startTransaction();
			
			int rst = sqlSession.insert("mail.insertTBL090810", mailModel);
			if (0 == rst) return false;
			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			// 硫붿씪諛쒖넚
//			MailSender mailSender = new MailSender();
//			mailSender.sent(mailModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}

		return true;
	}
	
	@Override
	public boolean sentMailPasswd(CustomerInfo member) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// 硫붿씪異붽�(�몃젋�앹뀡 泥섎━)
//			sqlMapClient.startTransaction();
			
			// 鍮꾨�踰덊샇 �낅뜲�댄듃
			String newPasswd = Long.toString(Math.round(Math.random() * 1000000));
			member.setPasswd(newPasswd);
			member.setUpdUser("admin");
			sqlSession.insert("member.updateTBL020210Passwd", member);
			
			// �댁슜
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("title", "�뚯썝�뺣낫");
			hm.put("content", "鍮꾨�踰덊샇 : " + member.getPasswd());
			String cont = applyTemplet("sample/mail/email.htm", hm);
			
			MailInfo mailModel = new MailInfo();
			mailModel.setFromNm(webmasterName);
			mailModel.setFromAddr(webmasterEmail);
			mailModel.setToAddr(member.getCustEmail());
			mailModel.setSubject("[踰뺣Ц�붽탳�≪꽱�� �뚯썝�뺣낫 - 鍮꾨�踰덊샇");
			mailModel.setCont(cont);
			mailModel.setInsUser("admin");
			
			int rst = sqlSession.insert("mail.insertTBL090810", mailModel);
			if (0 == rst) return false;
			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			// 硫붿씪諛쒖넚
//			MailSender mailSender = new MailSender();
//			mailSender.sent(mailModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}

		return true;
	}
	
	@Override
	public boolean sentMailUserInfo(CustomerInfo member) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// 硫붿씪異붽�(�몃젋�앹뀡 泥섎━)
//			sqlMapClient.startTransaction();
			
			// 鍮꾨�踰덊샇 �낅뜲�댄듃
			String newPasswd = Long.toString(Math.round(Math.random() * 1000000));
			member.setPasswd(newPasswd);
			member.setUpdUser("admin");
			sqlSession.insert("member.updateTBL020210Passwd", member);
			
			
			// �댁슜
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("title", "�뚯썝�뺣낫");
			hm.put("content", "�꾩씠��: " + member.getCustId() + ", 鍮꾨�踰덊샇 : " + member.getPasswd());
			String cont = applyTemplet("sample/mail/email.htm", hm);
			
			MailInfo mailModel = new MailInfo();
			mailModel.setFromNm(webmasterName);
			mailModel.setFromAddr(webmasterEmail);
			mailModel.setToAddr(member.getCustEmail());
			mailModel.setSubject("[踰뺣Ц�붽탳�≪꽱�� �뚯썝�뺣낫");
			mailModel.setCont(cont);
			mailModel.setInsUser("admin");
			
			
			int rst = sqlSession.insert("mail.insertTBL090810", mailModel);
			if (0 == rst) return false;
			
//			sqlMapClient.commitTransaction();
//			sqlMapClient.getCurrentConnection().commit();
			
			// 硫붿씪諛쒖넚
//			MailSender mailSender = new MailSender();
//			mailSender.sent(mailModel);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			sqlSession.close();
		}

		return true;
	}
	
	@Override
	public List<MailInfo> getList(MailInfo mailModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectList("mail.getList", mailModel);
	}
	
	@Override
	public int getTotalCount(MailInfo mailModel) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return (Integer) sqlSession.selectOne("mail.getTotalCount", mailModel);
	}
	
	@Override
	public MailInfo selectTBL090810(long mailSeq) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return (MailInfo) sqlSession.selectOne("mail.selectTBL090810", mailSeq);
	}
	
	/**
     * 硫붿씪 �쒗뵆由우쓣 �쎌뼱�⑤떎.
     * @param templetName
     * @return
     */
    public static String getTemplet( String templetName) {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            Configuration conf = Configuration.getInstance();
            String templetDir = conf.getProperty("webroot.dir");
            //reader = new BufferedReader( new FileReader( templetDir + "/" + templetName));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(templetDir + "/" + templetName),"UTF8"));

            String line = null;
            while((line = reader.readLine())!=null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while getting templet : " + templetName + "-" + e.getClass().getName() + ":" + e.getMessage());
        } finally {
            if( reader!=null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                }
            }
        }
        return sb.toString();
    }
    

    private static String applyTemplet( String templet, HashMap<String, String> mapData) {
        String content = getTemplet( templet);
        Iterator<String> it = mapData.keySet().iterator();
        String key, value;
        Object  obj;
        while( it.hasNext()) {
            key = it.next().toString();
            obj = mapData.get( key);
            if( obj == null) {
                value="";
            } else if ( obj instanceof Number ) {
                value= Utility.format( ((Number)obj).doubleValue());
            } else if ( obj instanceof java.sql.Date ) {
                value= Utility.format( (java.sql.Date)obj);
            } else if ( obj instanceof Date ) {
                value= Utility.format( (Date)obj);
            } else if ( obj instanceof Timestamp ) {
                value= Utility.format( (Timestamp)obj);
            } else {
                value = obj.toString();
            }
             content = Utility.replace( content, "{" + key + "}" , value);
        }
        return content;
    }
	
}//End class
