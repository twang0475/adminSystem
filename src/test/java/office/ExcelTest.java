package office;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.landis.throughout.dao.ClientInfoDao;
import com.landis.throughout.dao.MachineInfoDao;
import com.landis.throughout.model.ClientInfo;
import com.landis.throughout.model.MachineInfo;

@ContextConfiguration("classpath:applicationContext.xml")
public class ExcelTest extends AbstractJUnit4SpringContextTests {
	@Resource
	private ClientInfoDao clientDao;
	@Resource
	private MachineInfoDao machineDao;
	@Test
	public void insertExcel() {
		String filePath = "D:\\test\\China_Userlist_20140228.xlsx";
		try {
			// 构造 XSSFWorkbook 对象，strPath 传入文件路径  
			XSSFWorkbook xwb = new XSSFWorkbook(filePath);
			// 读取第一章表格内容  
			XSSFSheet sheet = xwb.getSheetAt(0);  
			// 定义 row、cell  
			XSSFRow row;  
			String cell;  
			Date now = null;
			System.out.println(sheet.getPhysicalNumberOfRows());
			// 循环输出表格中的内容  
//			for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {  
//				now = new Date();
//				row = sheet.getRow(i);  
//				String shortName = row.getCell(3).toString().trim();
//				ClientInfo client = clientDao.loadClientInfoByShortName(shortName);
//				if (client == null) {
//					client = new ClientInfo();
//					client.setCreateTime(now);
//					client.setUpdateTime(now);
//					client.setShortName(shortName);
//					client.setFullName(row.getCell(2).toString().trim());
//					int rtn = 0;
//					try {
//						rtn = clientDao.save(client);
//						
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					
//				}
//				MachineInfo machine = new MachineInfo();
//				machine.setClientId(client.getId());
//				machine.setModel(row.getCell(5).toString().trim());
//				machine.setPlant(row.getCell(4).toString().trim());
//				machine.setSerialNo(getStringCellValue(row.getCell(1)).trim());
//				machine.setShippedDate(row.getCell(6).toString().trim());
//				machine.setCreateTime(now);
//				machine.setUpdateTime(now);
//				try {
//					machineDao.save(machine);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getStringCellValue(XSSFCell xssfCell) {
        String strCell = "";
        switch (xssfCell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = xssfCell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(xssfCell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(xssfCell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (xssfCell == null) {
            return "";
        }
        return strCell;
    }
}
