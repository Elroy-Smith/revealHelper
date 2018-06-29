package pl.coderslab;

public class Slide {
	private String fileName = "";
	private int globalIndex = 0;
	private int localIndex = 0;
	private String text = "";
	
	public Slide() {}
	
	
	public Slide(String fileName, int globalIndex, int localIndex, String text) {
		super();
		this.fileName = fileName;
		this.globalIndex = globalIndex;
		this.localIndex = localIndex;
		this.text = text;
	}


	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getGlobalIndex() {
		return globalIndex;
	}
	public void setGlobalIndex(int globalIndex) {
		this.globalIndex = globalIndex;
	}
	public int getLocalIndex() {
		return localIndex;
	}
	public void setLocalIndex(int localIndex) {
		this.localIndex = localIndex;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

}
