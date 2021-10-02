package in.nareshit.raghu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.raghu.entity.Specilization;

public class SpecializationExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//1. Define your Excel file Name
		response.addHeader("Content-Disposition", "attachment;filename=SPECS.xlsx");
		
		//2. read data given by controller
		@SuppressWarnings("unchecked")
		List<Specilization> specilizations = (List<Specilization>) model.get("specilizations");
		
		//3. Create one sheet
		Sheet sheet = workbook.createSheet("SPECILIZATION");
		
		//4. Create row#0 as header
		setHead(sheet);
		
		//5. Create row#1 onwards from List<T>
		setBody(sheet,specilizations);
		
		
	}

	private void setBody(Sheet sheet, List<Specilization> specilizations) {
		int rowNum = 1;
		for(Specilization spec:specilizations) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getId());
			row.createCell(1).setCellValue(spec.getSpecCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNote());

			
		}
	}

	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
	}

}
