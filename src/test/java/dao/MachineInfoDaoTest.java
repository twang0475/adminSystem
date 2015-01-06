package dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.landis.throughout.dao.MachineInfoDao;
import com.landis.throughout.model.MachineInfo;

@ContextConfiguration("classpath:applicationContext.xml")
public class MachineInfoDaoTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private MachineInfoDao dao;
	@Test
	public void insert() {
		MachineInfo machine = new MachineInfo();
		machine.setClientId(17);
		machine.setModel("LT1 Duocentric");
		machine.setPlant("Changchun");
		machine.setSerialNo("8620074");
		machine.setShippedDate("15-三月-2015");
		
		Date now = new Date();
		machine.setCreateTime(now);
//		client.setFullName("Test");
		machine.setUpdateTime(now);
		int rtn = 0;
//		
		try {
			rtn = dao.save(machine);
			System.out.println(machine.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
