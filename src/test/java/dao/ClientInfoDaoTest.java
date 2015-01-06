package dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.landis.throughout.dao.ClientInfoDao;
import com.landis.throughout.model.ClientInfo;

@ContextConfiguration("classpath:applicationContext.xml")
public class ClientInfoDaoTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private ClientInfoDao dao;

//	@Test
//	public void insert() {
//		ClientInfo client = new ClientInfo();
//		Date now = new Date();
//		client.setCreateTime(now);
//		client.setShortName("Test");
//		client.setFullName("Test");
//		client.setUpdateTime(now);
//		int rtn = 0;
//		
//		try {
//			rtn = dao.save(client);
//			System.out.println("123");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	@Test
	public void loadClientInfo() {
		try {
			ClientInfo client = dao.loadClientInfo(11);
			System.out.println(client.getFullName());
			System.out.println(client.getMachines().get(0).getSerialNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
