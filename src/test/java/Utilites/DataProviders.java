package Utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String path ="./TestData/OpenCart_LoginTestData.xlsx";
		
		ExcelUtility Uexel= new ExcelUtility(path);
		
		int totalrow= Uexel.getRowCount("Sheet1");
		int totalcount= Uexel.getCellCount("Sheet1", 1);
		
		String logindata[][] =new String[totalrow][totalcount];
		
		for(int i=1; i<=totalrow; i++) 
		{
			for(int j=0; j<totalcount; j++)
			{
				logindata[i-1][j]= Uexel.getCellData("Sheet1", i, j);
			}
		}
			
		return logindata;
	}
}
