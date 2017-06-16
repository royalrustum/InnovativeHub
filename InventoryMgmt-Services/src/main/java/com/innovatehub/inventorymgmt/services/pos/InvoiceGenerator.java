package com.innovatehub.inventorymgmt.services.pos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoiceGenerator {

	private BaseFont bfBold;
	private BaseFont bf;
	private int pageNumber = 0;

	private int COL0_OFFSET = 20;
	private int COL1_OFFSET = 250;
	private int COL2_OFFSET = 300;
	private int COL3_OFFSET = 420;
	private int COL4_OFFSET = 570;
	private int ITEM_OFFSET = 2;
	private int ITEM_VERTICAL_OFFSET = 15;
	private int PAGE_FOOTER_OFFSET = 50;
	private int PAGE_FULL_HEIGHT = 615;
	
	public static void main(String[] args) {
		String pdfFilename = "one";
		InvoiceGenerator generateInvoice = new InvoiceGenerator();
		/*
		 * if (args.length < 1) { System.err.println("Usage: java " +
		 * generateInvoice.getClass().getName() + " d:/exportpdf.pdf");
		 * System.exit(1); }
		 */

		pdfFilename = "Akshara1.pdf";
		generateInvoice.createPDF(pdfFilename);

	}

	private void createPDF(String pdfFilename) {

		Document doc = new Document();
		PdfWriter docWriter = null;
		initializeFonts();

		try {
			String path = pdfFilename;
			docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.addAuthor("Akshara Tech.");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("Akshara Tech.");
			doc.addTitle("Receipt");
			doc.setPageSize(PageSize.LETTER);

			doc.open();
			PdfContentByte cb = docWriter.getDirectContent();

			boolean beginPage = true;
			int y = 0;

			for (int i = 0; i < 100; i++) {
				if (beginPage) {
					beginPage = false;
					generateLayout(doc, cb);
					generateHeader(doc, cb);
					y = PAGE_FULL_HEIGHT;
				}
				generateDetail(doc, cb, i, y);
				y = y - ITEM_VERTICAL_OFFSET;
				if (y < PAGE_FOOTER_OFFSET) {
					printPageNumber(cb);
					doc.newPage();
					beginPage = true;
				}
			}
			printPageNumber(cb);

			if (y < PAGE_FOOTER_OFFSET) {
				y = PAGE_FULL_HEIGHT;
			}

			cb.moveTo(COL2_OFFSET, y);
			cb.lineTo(COL3_OFFSET, y);
			cb.moveTo(COL3_OFFSET, y);
			cb.lineTo(COL4_OFFSET, y);
						
			y -= ITEM_VERTICAL_OFFSET;
			
			createContent(cb, COL2_OFFSET + ITEM_OFFSET, y, "Total GST: 12", PdfContentByte.ALIGN_LEFT);
			createContent(cb, COL3_OFFSET + ITEM_OFFSET, y, "Sub Total: 12", PdfContentByte.ALIGN_LEFT);
			y -= ITEM_VERTICAL_OFFSET;
			
			createHeadings(cb, COL3_OFFSET + ITEM_OFFSET, y, "Total: 12", PdfContentByte.ALIGN_LEFT);
			
			y -= ITEM_VERTICAL_OFFSET;
			
			cb.moveTo(COL3_OFFSET, y);
			cb.lineTo(COL4_OFFSET, y);
			cb.stroke();

		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}
	}

	private void generateLayout(Document doc, PdfContentByte cb) {

		try {

			cb.setLineWidth(1f);

			// Invoice Header box layout
			cb.rectangle(420, 700, 150, 60);
			cb.moveTo(420, 720);
			cb.lineTo(570, 720);
			cb.moveTo(420, 740);
			cb.lineTo(570, 740);
			cb.moveTo(480, 700);
			cb.lineTo(480, 760);
			cb.stroke();

			// Invoice Header box Text Headings
			createHeadings(cb, 422, 743, "Account No.", Element.ALIGN_LEFT);
			createHeadings(cb, 422, 723, "Invoice No.", Element.ALIGN_LEFT);
			createHeadings(cb, 422, 703, "Invoice Date", Element.ALIGN_LEFT);

			// Invoice Detail box layout
			cb.rectangle(20, 50, 550, 600);
			cb.moveTo(20, 630);
			cb.lineTo(570, 630);
			/*
			 * cb.moveTo(50, 50); cb.lineTo(50, 650);
			 */
			/*
			 * cb.moveTo(150, 50); cb.lineTo(150, 650);
			 */
			cb.moveTo(COL1_OFFSET, 50);
			cb.lineTo(COL1_OFFSET, 650);
			cb.moveTo(COL2_OFFSET, 50);
			cb.lineTo(COL2_OFFSET, 650);
			cb.moveTo(COL3_OFFSET, 50); 
			cb.lineTo(COL3_OFFSET, 650);
			 
			cb.stroke();

			// Invoice Detail box Text Headings
			createHeadings(cb, COL0_OFFSET + ITEM_OFFSET, 633, "Item Description", Element.ALIGN_LEFT);
			createHeadings(cb, COL1_OFFSET + ITEM_OFFSET, 633, "Qty", Element.ALIGN_LEFT);
			createHeadings(cb, COL2_OFFSET + ITEM_OFFSET, 633, "GST Tax", Element.ALIGN_LEFT);
			createHeadings(cb, COL3_OFFSET + ITEM_OFFSET, 633, "Price", Element.ALIGN_LEFT);

			// add the images
			Image companyLogo = Image.getInstance("/home/kiran/venkateswara.jpg");
			companyLogo.setAbsolutePosition(25, 700);
			companyLogo.scalePercent(25);
			doc.add(companyLogo);

		}

		catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void generateHeader(Document doc, PdfContentByte cb) {

		try {

			createHeadings(cb, 200, 750, "Company Name", Element.ALIGN_LEFT);
			createHeadings(cb, 200, 735, "Address Line 1", Element.ALIGN_LEFT);
			createHeadings(cb, 200, 720, "Address Line 2", Element.ALIGN_LEFT);
			createHeadings(cb, 200, 705, "City, State - ZipCode", Element.ALIGN_LEFT);
			createHeadings(cb, 200, 690, "Country", Element.ALIGN_LEFT);

			createHeadings(cb, 482, 743, "ABC0001", Element.ALIGN_LEFT);
			createHeadings(cb, 482, 723, "123456", Element.ALIGN_LEFT);
			createHeadings(cb, 482, 703, "09/26/2012", Element.ALIGN_LEFT);

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void generateDetail(Document doc, PdfContentByte cb, int index, int y) {
		DecimalFormat df = new DecimalFormat("0.00");

		try {

			// createContent(cb, 48, y, String.valueOf(index + 1),
			// PdfContentByte.ALIGN_RIGHT);
			// createContent(cb, 52, y, "ITEM" + String.valueOf(index + 1),
			// PdfContentByte.ALIGN_LEFT);
			createContent(cb, COL0_OFFSET + ITEM_OFFSET, y, "Product Description - SIZE " + String.valueOf(index + 1),
					PdfContentByte.ALIGN_LEFT);

			createContent(cb, COL1_OFFSET + ITEM_OFFSET, y, String.valueOf(index + 1),
					 PdfContentByte.ALIGN_LEFT);
					
			double price = Double.valueOf(df.format(Math.random() * 10));
			double extPrice = price * (index + 1);
			createContent(cb, COL2_OFFSET + ITEM_OFFSET, y, df.format(price), PdfContentByte.ALIGN_LEFT);
			createContent(cb, COL3_OFFSET + ITEM_OFFSET, y, df.format(price), PdfContentByte.ALIGN_LEFT);
			// createContent(cb, 568, y, df.format(extPrice),
			// PdfContentByte.ALIGN_RIGHT);

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void createHeadings(PdfContentByte cb, float x, float y, String text, int align) {

		cb.beginText();
		cb.setFontAndSize(bfBold, 8);
		cb.showTextAligned(align, text.trim(), x, y, 0);
		cb.endText();

	}

	private void printPageNumber(PdfContentByte cb) {

		cb.beginText();
		cb.setFontAndSize(bfBold, 8);
		cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber + 1), 570, 25, 0);
		cb.endText();

		pageNumber++;

	}

	private void createContent(PdfContentByte cb, float x, float y, String text, int align) {

		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.showTextAligned(align, text.trim(), x, y, 0);
		cb.endText();

	}

	private void initializeFonts() {

		try {
			bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
