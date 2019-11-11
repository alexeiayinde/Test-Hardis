package dev.entite;

public final class FileError {
	
	private int line;
	private String message;
	private String value;
	
	public FileError(int line, String message, String value) {
		this.line = line;
		this.message = message;
		this.value = value;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileError [line=");
		builder.append(line);
		builder.append(", message=");
		builder.append(message);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
