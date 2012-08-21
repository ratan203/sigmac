package test;

import java.io.IOException;

import smc.DocObjectCreator;
import smc.JSCreator;

public class DocObjectCreatorTest {

	public static void main (String[] args){
		
		DocObjectCreator dococ = new DocObjectCreator();
		
		JSCreator jsc = new JSCreator();
		try {
			jsc.CreateJS(dococ.getDoc("testDocx.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
