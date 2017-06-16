package com.innovatehub.inventorymgmt.services.pos;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class PrintServiceImpl implements PrintService {
	private int PRINT_RECEIPT_TOTALS_TOP_OFFSET = 220;
	private int PRINT_RECEIPT_TOTALS_ITEM_OFFSET = 20;

	public void printSaleReceipt() throws IOException, FileNotFoundException, DocumentException {
		test1();
		// ByteArrayOutputStream byteArrayOutputStream = new
		// ByteArrayOutputStream();
		/*
		 * FileOutputStream byteArrayOutputStream = new
		 * FileOutputStream("test.pdf");
		 * 
		 * System.out.println(pdfTemplate.getAcroFields().getFields().size());
		 * PdfStamper stamper = new PdfStamper(pdfTemplate,
		 * byteArrayOutputStream); stamper.getWriter().setPageSize(PageSize.A4);
		 * 
		 * stamper.getAcroFields().setField("title", "Akshara");
		 * stamper.getAcroFields().setField("saleID", "123456");
		 * stamper.getAcroFields().setField("saleDate", "5/1/2016");
		 * stamper.getAcroFields().setField("customerName",
		 * "Kiran Kumar Raju V");
		 * 
		 * float tableHeight = this.constructSaleItemsTable(stamper,
		 * pdfTemplate);
		 * 
		 * PdfContentByte contentBytePDF = stamper.getOverContent(1);
		 * 
		 * constructReceiptFooter(contentBytePDF,
		 * pdfTemplate.getPageSize(1).getHeight() -
		 * PRINT_RECEIPT_TOTALS_TOP_OFFSET - tableHeight);
		 * 
		 * stamper.getAcroFields().setGenerateAppearances(true);
		 * stamper.setFormFlattening(true); stamper.close();
		 * pdfTemplate.close();
		 */

		// this.layoutItems(stamper.get);
	}

	private void test() throws IOException, DocumentException {
		PdfReader pdfTemplate = new PdfReader("/home/kiran/Desktop/Sale-Receipt-Template.pdf");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfTemplate, baos);
		AcroFields form = stamper.getAcroFields();

		form.setField("title", "Akshara");
		form.setField("saleID", "123456");
		form.setField("saleDate", "5/1/2016");
		form.setField("customerName", "Kiran Kumar Raju V");

		stamper.getAcroFields().setGenerateAppearances(true);
		stamper.setFormFlattening(true);
		stamper.close();

		PdfReader reader = new PdfReader(baos.toByteArray());
		Document document = new Document(reader.getPageSize(1));
		PdfCopy pdfCopy = new PdfCopy(document, new FileOutputStream("HelloWorldStampCopy.pdf"));
		document.open();

		pdfCopy.addPage(pdfCopy.getImportedPage(reader, 1));

		pdfCopy.open();
		/*
		 * float tableHt = constructSaleItemsTable(document);
		 * this.constructReceiptFooter(pdfCopy.getDirectContent(), tableHt);
		 */

		pdfCopy.close();
		document.close();
	}

	private void test1() {
		try {
			PdfReader pdfReader;
			PdfStamper pdfStamper;
			ByteArrayOutputStream baos;

			Document document = new Document();
			PdfSmartCopy pdfSmartCopy = new PdfSmartCopy(document, new FileOutputStream("Akshara.pdf"));

			document.open();

			pdfReader = new PdfReader("/home/kiran/Desktop/Sale-Receipt-Template.pdf");
			baos = new ByteArrayOutputStream();
			pdfStamper = new PdfStamper(pdfReader, baos);

			AcroFields form = pdfStamper.getAcroFields();

			// key statement 1
			form.setGenerateAppearances(true);

			// acroFields.setExtraMargin(5, 5);
			form.setField("title", "Akshara");
			form.setField("saleID", "123456");
			form.setField("saleDate", "5/1/2016");
			form.setField("customerName", "Kiran Kumar Raju V");

			// key statement 2
			pdfStamper.setFormFlattening(true);

			pdfStamper.close();
			pdfReader.close();

			pdfReader = new PdfReader(baos.toByteArray());
			pdfSmartCopy.addPage(pdfSmartCopy.getImportedPage(pdfReader, 1));

			// float tableHt = this.constructSaleItemsTable(pdfSmartCopy);

			// Create a temporary PDF to calculate the height
			ByteArrayOutputStream ms = new ByteArrayOutputStream();

			Document doc = new Document(PageSize.A4);

			PdfWriter w = PdfWriter.getInstance(doc, ms);

			doc.open();

			PdfPTable t = new PdfPTable(4);
			// In order to use WriteSelectedRows you need to set the width of the
			// table
			t.setTotalWidth(500);
			t.setWidths(new int[] { 1, 1, 1, 1 });
			// t.setSplitRows(true);

			for (int i = 0; i < 100; i++) {
				t.addCell("1");
				t.addCell("1");
				t.addCell("1");
				t.addCell("1");

				t.completeRow();

			}
			
			t.writeSelectedRows(0, t.getRows().size(), 0, 0, w.getDirectContent());

			doc.close();

			PdfReader pdfReader1 = new PdfReader(ms.toByteArray());
			pdfSmartCopy.addPage(pdfSmartCopy.getImportedPage(pdfReader1, 1));
			
			this.constructReceiptFooter(pdfSmartCopy.getDirectContent(), 0);

			pdfSmartCopy.freeReader(pdfReader);
			pdfSmartCopy.freeReader(pdfReader1);
			
			pdfReader1.close();
			pdfReader.close();

			document.close();
		} catch (DocumentException dex) {
			dex.printStackTrace();
			System.exit(1);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	private void constructReceiptFooter(PdfContentByte contentBytePDF, float positioningHeight)
			throws IOException, DocumentException {
		float relativeHeight = positioningHeight;
		String[] totalsFields = { "Sub Total:", "Sale Tax:", "Total: " };

		for (String totalField : totalsFields) {
			BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.EMBEDDED);
			contentBytePDF.setFontAndSize(bf, 10.5f);
			contentBytePDF.showTextAligned(Element.ALIGN_RIGHT, totalField, 350, relativeHeight, 0);
			relativeHeight -= PRINT_RECEIPT_TOTALS_ITEM_OFFSET;
		}
	}

	private float constructSaleItemsTable(PdfSmartCopy document) throws DocumentException {
		PdfPTable t = new PdfPTable(4);
		// In order to use WriteSelectedRows you need to set the width of the
		// table
		t.setTotalWidth(500);
		t.setWidths(new int[] { 1, 1, 1, 1 });
		// t.setSplitRows(true);

		for (int i = 0; i < 100; i++) {
			t.addCell("1");
			t.addCell("1");
			t.addCell("1");
			t.addCell("1");

			t.completeRow();

		}

		// document.add(t);

		t.writeSelectedRows(0, t.getRows().size(), 0, 600, document.getDirectContentUnder());

		/*
		 * ColumnText columnText = new ColumnText(stamper.getOverContent(1));
		 * columnText.addElement(t);
		 * 
		 * float tableHeight = this.calculateTableHeight(t);
		 * 
		 * columnText.setSimpleColumn(0, 600, 500, 300); columnText.go();
		 */

		float tableHeight = this.calculateTableHeight(t);

		return tableHeight;
	}

	private float calculateTableHeight(PdfPTable table) throws DocumentException {

		// Create a temporary PDF to calculate the height
		ByteArrayOutputStream ms = new ByteArrayOutputStream();

		Document doc = new Document(PageSize.A4);

		PdfWriter w = PdfWriter.getInstance(doc, ms);

		doc.open();

		table.writeSelectedRows(0, table.getRows().size(), 0, 0, w.getDirectContent());

		doc.close();
		return table.getTotalHeight();

	}

	public void triggerNewPage(PdfStamper stamper, Rectangle pagesize, PdfImportedPage page, ColumnText column,
			Rectangle rect, int pagecount) throws DocumentException {
		stamper.insertPage(pagecount, pagesize);
		PdfContentByte canvas = stamper.getOverContent(pagecount);
		canvas.addTemplate(page, 0, 0);
		column.setCanvas(canvas);
		column.setSimpleColumn(rect);
		column.go();
	}
}
