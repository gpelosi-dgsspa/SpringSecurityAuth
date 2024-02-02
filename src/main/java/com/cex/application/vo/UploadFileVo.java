package com.cex.application.vo;

public class UploadFileVo 
{
	private String fileName;
	private String base64Content;
	
	public UploadFileVo() {
		super();
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBase64Content() {
		return base64Content;
	}
	public void setBase64Content(String base64Content) {
		this.base64Content = base64Content;
	}

	@Override
	public String toString() {
		return "UploadFileVo [fileName=" + fileName + ", base64Content=" + base64Content + "]";
	}
}
