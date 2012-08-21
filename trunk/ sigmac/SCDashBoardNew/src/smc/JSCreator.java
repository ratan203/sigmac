package smc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JSCreator {
	

	public JSCreator() {
		// TODO Auto-generated constructor stub
	}
	
	public String CreateJS(Document doc) throws IOException{
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder() ;
		
		String json = createJSON(doc);
		
		
		try {
			
			br = new BufferedReader(new FileReader(new File("C:\\Users\\Eranda\\workspace\\SCDashBoardNew\\WebContent\\example2.js")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		
		try {
			while ((line = br.readLine()) != null) {

				if(line.equals("var json = replace;")){
					sb.append("var json = "+json+";");
				}else{
				sb.append(line+"\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {

			BufferedWriter bw = new BufferedWriter(new FileWriter("ex2.js"));
			bw.append(sb.toString());
			bw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String js = sb.toString();
		
		
		
		/*Pattern pat = Pattern.compile("replace");   
		Matcher mat = pat.matcher(js);  
		System.out.println(mat.replaceAll(json));   
		mat.reset(); */ 
		

		
		return js;
			
	}
	
	public String createJSON(Document doc){
		
//retreiv the doc 
		
		HashMap<String,Concept> cm = doc.getDoc();		
		Set<String> conceptSet = cm.keySet();
		
		StringBuffer jss = new StringBuffer();
		jss.append("[\n");
		
		HashMap<String, String> currentRel = new HashMap<String, String>();
		
		int k =conceptSet.size();
		int i= 0;
		//for each concept
		for(String key : conceptSet){
			
		
			jss.append("\t{\n");
			jss.append("\t\t\"id\" : \""+key+"\",\n");
			jss.append("\t\t\"name\" : \""+key+"\",\n");			
			jss.append("\t\t\"data\" : {\n");
			jss.append("\t\t\"$color\" : \"#83548B\",\n");
			jss.append("\t\t\"$type\" :  \"circle\" \n\t\t},\n");
			
			
			HashMap rel = cm.get(key).getRelationships();
			Set<String> relatedconceptSet = rel.keySet();
			
			int l =relatedconceptSet.size();
			int j= 0;
			
			jss.append("\t\t\"adjacencies\" : [\n");
			for(String rkey: relatedconceptSet){				
				if(currentRel.get(rkey)!=null){
					if(!(currentRel.get(rkey).equals(key))){
						currentRel.put(key, rkey);
						jss.append("\t\t{\n");
						jss.append("\t\t\"nodeTo\" : \""+ rkey+"\",\n");
						jss.append("\t\t\"nodeFrom\" : \""+ key+"\",\n");
						jss.append("\t\t\"data\" : {}\n");
						jss.append("\t\t}");
						j++;
						if(!(i==l)){
						jss.append(",\n");
						}
					}	
				}else if(currentRel.get(key)!=null){
					if(!(currentRel.get(key).equals(rkey))){
						currentRel.put(key, rkey);
						jss.append("\t\t{\n");
						jss.append("\t\t\"nodeTo\" : \""+ rkey+"\",\n");
						jss.append("\t\t\"nodeFrom\" : \""+ key+"\",\n");
						jss.append("\t\t\"data\" : {}\n");
						jss.append("\t\t}");
						j++;
						if(!(j==l)){
						jss.append(",\n");
						}
					}	
				}else{
					currentRel.put(key, rkey);
					jss.append("\t\t{\n");
					jss.append("\t\t\"nodeTo\" : \""+ rkey+"\",\n");
					jss.append("\t\t\"nodeFrom\" : \""+ key+"\",\n");
					jss.append("\t\t\"data\" : {}\n");
					jss.append("\t\t}");
					j++;
					if(!(j==l)){
					jss.append(",\n");
					}
				}
			}
			jss.append("\t\t]\n");
			jss.append("\t}");
			i++;
			if(!(i==k)){
			jss.append(",\n");
			}
		}
		jss.append("]\n");
		return jss.toString();
		
	
		
	}

}
