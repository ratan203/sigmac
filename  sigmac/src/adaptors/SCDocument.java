package adaptors;

import java.util.ArrayList;

public class SCDocument {
	String body;
	ArrayList<Title>  titles;
	
	
	public SCDocument(String body, ArrayList<Title> titles) {
		// TODO Auto-generated constructor stub
		this.body = body;
		this.titles = titles;
	}
	
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public ArrayList<Title> getTitles() {
		return titles;
	}
	public void setTitles(ArrayList<Title> titles) {
		this.titles = titles;
	}
	
	
}
