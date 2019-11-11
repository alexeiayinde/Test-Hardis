package dev.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dev.exception.ColorFormatException;
import dev.exception.PriceFormatException;
import dev.exception.ReferenceFormatException;
import dev.exception.SizeFormatException;

public class FileServiceTest {
	
	private FileService fileService = new FileService();

	@Test
	public void testExtractReference() {
		// Test numReference != 10 chiffres
		assertThrows(ReferenceFormatException.class, () -> fileService.extractReference("146010010;R;45.12;27"));
		
		// Test numReference avec lettres
		assertThrows(ReferenceFormatException.class, () -> fileService.extractReference("146010010a;R;45.12;27"));
		
	}
	
	@Test
	public void testExtractColor() {
		// Test color ni 'R', ni 'G', ni 'B'
		assertThrows(ColorFormatException.class, () -> fileService.extractColor("1460100110;P;45.12;27"));
		
	}
	
	@Test
	public void testExtractPrice() {
		// test price au mauvais format (avec lettre)
		assertThrows(PriceFormatException.class, () -> fileService.extractPrice("1460100101;R;45.a2;27"));
		
	}
	
	@Test
	public void testExtractSize() {
		// Test size au mauvais format (double au lieu de int)
		assertThrows(SizeFormatException.class, () -> fileService.extractSize("1460100110;R;45.12;27.1"));
		
	}

}
