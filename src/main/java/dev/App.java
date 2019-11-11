package dev;

import dev.entite.FileTXT;
import dev.service.FileService;
import dev.service.FileToJsonService;
import dev.service.FileToXMLService;

public class App {
	
	private static FileService fileService = new FileService();
	private static FileTXT file;

	public static void main(String[] args) {
		
		initApp("JavaTest.txt", "JSON", "JavaTestJSON.txt");

	}

	private static void initApp(String filePath, String fileFormat, String savePath) {
		// Extraction des donn�es du fichier TXT
		file = fileService.extractData(filePath);
		
		// Conversion du fichier TXT en fichier JSON
		if (fileFormat.equals("JSON")) {
			FileToJsonService fileToJsonService = new FileToJsonService();
			fileToJsonService.convertFile(file, savePath);
		}
		
		// Conversion du fichier TXT en fichier XML
		if (fileFormat.equals("XML")) {
			FileToXMLService fileToXMLService = new FileToXMLService();
			fileToXMLService.convertFile(file, savePath);
		}
	}

}
