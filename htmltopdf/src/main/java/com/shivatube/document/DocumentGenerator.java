package com.shivatube.document;

import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class DocumentGenerator {

	public String htmlToPdf(String processedHtml) {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);
			
			DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);
			
			ConverterProperties converterProperties = new ConverterProperties();
			
			converterProperties.setFontProvider(defaultFont);
			
			HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);
			
			FileOutputStream fout = new FileOutputStream("/Users/shivajichandra/Desktop/Document/employee.pdf");
			
			byteArrayOutputStream.writeTo(fout);
			byteArrayOutputStream.close();
			
			byteArrayOutputStream.flush();
			fout.close();
			
			return null;
			
		} catch(Exception ex) {
			
			//exception occured
		}
		
		return null;
	}
}
