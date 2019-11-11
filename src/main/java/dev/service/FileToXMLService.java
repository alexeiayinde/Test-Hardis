package dev.service;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import dev.entite.FileError;
import dev.entite.FileReference;
import dev.entite.FileTXT;

public final class FileToXMLService {
	
	public void convertFile(FileTXT fileTXT, String savePath) {
		
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			document.setXmlStandalone(true);
			
			// report element
			Element report = document.createElement("report");
			document.appendChild(report);
			
			// inputFile element
			Element inputFile = document.createElement("inputFile");
			inputFile.appendChild(document.createTextNode(fileTXT.getInputFile()));
			report.appendChild(inputFile);
			
			// references element
			Element references = document.createElement("references");
			report.appendChild(references);
			
			// reference elements
			for (FileReference fileReference : fileTXT.getReferences()) {
				Element reference = document.createElement("reference");
				reference.setAttribute("numReference", String.valueOf(fileReference.getNumReference()));
				reference.setAttribute("color", fileReference.getColor());
				reference.setAttribute("price", String.valueOf(fileReference.getPrice()));
				reference.setAttribute("size", String.valueOf(fileReference.getSize()));
				references.appendChild(reference);
			}
			
			// errors element
			Element errors = document.createElement("errors");
			report.appendChild(errors);
			
			// error elements
			for (FileError fileError : fileTXT.getErrors()) {
				Element error = document.createElement("error");
				error.setAttribute("line", String.valueOf(fileError.getLine()));
				error.setAttribute("message", fileError.getMessage());
				error.appendChild(document.createTextNode(fileError.getValue()));
				errors.appendChild(error);
			}
			
			// Création du fichier XML
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(savePath));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
