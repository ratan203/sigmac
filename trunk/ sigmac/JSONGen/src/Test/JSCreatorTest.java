package Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import smc.Concept;
import smc.Document;
import smc.JSCreator;
import smc.RelatedConcept;



public class JSCreatorTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
//		RelatedConcept rec1 = new RelatedConcept("is a ", "Java", false, 4);
//		RelatedConcept rec2 = new RelatedConcept("is a ", "Programmaing Language", true, 4);
//		RelatedConcept rec3 = new RelatedConcept("is a ", "Php", false, 4);
//		RelatedConcept rec4 = new RelatedConcept("is a ", "Web", true, 4);
//		
//		Concept cn1 = new Concept("Java");
//		Concept cn2 = new Concept("Programmaing Language");
//		Concept cn3 = new Concept("Php");
//		Concept cn4 = new Concept("Web");
//		
//		cn1.addRelatedConcept(rec2);
//		cn2.addRelatedConcept(rec1);
//		cn3.addRelatedConcept(rec4);
//		cn4.addRelatedConcept(rec3);
//		
//		ArrayList<Concept> con = new ArrayList<Concept>();
//		con.add(cn1);
//		con.add(cn2);
//		con.add(cn3);
//		con.add(cn4);
		Document doc = null;
		FileInputStream fileIn =
            new FileInputStream("too");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			doc = (Document) in.readObject();
			in.close();
			fileIn.close();
		
		

		
		JSCreator jsc = new JSCreator();
		
		//System.out.println(jsc.createJSON(doc));
		System.out.println(jsc.CreateJS(doc));
		
	}

}
