package dev.entite;

import java.util.ArrayList;
import java.util.List;

public final class FileTXT {
	
	private String inputFile;
	private List<FileReference> references;
	private List<FileError> errors;
	
	public FileTXT() {
		this.references = new ArrayList<FileReference>();
		this.errors = new ArrayList<FileError>();
	}
	
	public void addReference(FileReference reference) {
		this.references.add(reference);
	}
	
	public void addError(FileError error) {
		this.errors.add(error);
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String fileName) {
		this.inputFile = fileName;
	}

	public List<FileReference> getReferences() {
		return references;
	}

	public void setReferences(List<FileReference> references) {
		this.references = references;
	}

	public List<FileError> getErrors() {
		return errors;
	}

	public void setErrors(List<FileError> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileTXT [fileName=");
		builder.append(inputFile);
		builder.append(", references=");
		builder.append(references);
		builder.append(", errors=");
		builder.append(errors);
		builder.append("]");
		return builder.toString();
	}
	
}
