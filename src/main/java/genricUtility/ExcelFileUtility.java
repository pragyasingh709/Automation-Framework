package genricUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This Class consist of method to read Data from Excell File
 * 
 * @author abhis
 */
public class ExcelFileUtility {
	/**
	 * This method is used to read data from Excel file Provided sheetname, row and cell
	 * 
	 * @param Sheetname
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public String toReadDataFromExcelFile(String Sheetname,int row, int cell) throws EncryptedDocumentException, IOException {
	FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
	  Workbook wb = WorkbookFactory.create(efis);
	String value = wb.getSheet(Sheetname).getRow(row).getCell(cell).toString();
	return value;

}
}