package dev.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import dev.entite.FileError;
import dev.entite.FileReference;
import dev.entite.FileTXT;
import dev.exception.ColorFormatException;
import dev.exception.PriceFormatException;
import dev.exception.ReferenceFormatException;
import dev.exception.SizeFormatException;

public class FileService {
	
	// Méthode pour extraire toutes les références et erreurs d'un fichier TXT
	public FileTXT extractData(String filePath) {
		FileTXT fileTXT = new FileTXT();
		File file;
		BufferedReader br ;
		String referenceLine;	
		int lineCount = 0;
		
		int ref;
		String color;
		double price;
		int size;
		
		file = new File(filePath);		
		try {
			br = new BufferedReader(new FileReader(file));
					
			while ((referenceLine = br.readLine()) != null) {
				lineCount++;
				
				try {
					ref = extractReference(referenceLine);
					color = extractColor(referenceLine);
					price = extractPrice(referenceLine);
					size = extractSize(referenceLine);
					
					// Les données du fichier sont affectées à un objet FileTXT
					fileTXT.setInputFile(file.getName());
					fileTXT.addReference(new FileReference(ref, color, price, size));
				} catch (ReferenceFormatException e) {
					// Une erreur est ajoutée dans la liste d'erreurs de l'objet FileTXT
					fileTXT.addError(new FileError(lineCount, "Incorrect value for reference", referenceLine));
				} catch (ColorFormatException e) {
					// Une erreur est ajoutée dans la liste d'erreurs de l'objet FileTXT
					fileTXT.addError(new FileError(lineCount, "Incorrect value for color", referenceLine));
				} catch (PriceFormatException e) {
					// Une erreur est ajoutée dans la liste d'erreurs de l'objet FileTXT
					fileTXT.addError(new FileError(lineCount, "Incorrect value for price", referenceLine));
				} catch (SizeFormatException e) {
					// Une erreur est ajoutée dans la liste d'erreurs de l'objet FileTXT
					fileTXT.addError(new FileError(lineCount, "Incorrect value for size", referenceLine));
				}				
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileTXT;
	}
	
	// Méthode pour extraire le numéro de référence pour une ligne du fichier TXT
	public int extractReference(String referenceLine) throws ReferenceFormatException{
		int ref; 
		try {
			// Le numéro de référence doit être une suite de chiffres
			ref = Integer.parseInt(referenceLine.substring(0, referenceLine.indexOf(";")));
			
			// La taille du numéro de référence doit être égale à 10
			if (referenceLine.substring(0, referenceLine.indexOf(";")).length() != 10) {
				throw new ReferenceFormatException();
			}
		} catch (NumberFormatException e) {
			throw new ReferenceFormatException();
		}
		
		return ref;
	}
	
	// Méthode pour extraire la couleur pour une ligne du fichier TXT
	public String extractColor(String referenceLine) throws ColorFormatException {
		String color;
		color = referenceLine.substring(referenceLine.indexOf(";")+1, referenceLine.indexOf(";", referenceLine.indexOf(";")+1));
		
		// La couleur doit être une des valeurs possibles (R, G ou B)
		if (!color.equals("R") && !color.equals("G") && !color.equals("B")) {
			throw new ColorFormatException();
		}
		
		return color;
	}

	// Méthode pour extraire le prix pour une ligne du fichier TXT
	public double extractPrice(String referenceLine) throws PriceFormatException {
		double price;
		try {
			// Le prix doit être une valeur de type double
			price = Double.parseDouble(referenceLine.substring(referenceLine.indexOf(";", referenceLine.indexOf(";")+1)+1, referenceLine.indexOf(";", referenceLine.indexOf(";", referenceLine.indexOf(";")+1)+1)));
		} catch (NumberFormatException e) {
			throw new PriceFormatException();
		}
		
		return price;
	}
	
	// Méthode pour extraire la taille pour une ligne du fichier TXT
	public int extractSize(String referenceLine) throws SizeFormatException {
		int size;
		try {
			// La taille doit être une valeur de type entier
			size = Integer.parseInt(referenceLine.substring(referenceLine.lastIndexOf(";")+1, referenceLine.length()));
		} catch (NumberFormatException e) {
			throw new SizeFormatException();
		}
		return size;
	}
}
