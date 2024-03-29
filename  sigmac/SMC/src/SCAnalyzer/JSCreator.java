package SCAnalyzer;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import smc.Concept;
import smc.Document;


public class JSCreator {
	

	public JSCreator() {
		// TODO Auto-generated constructor stub
	}
	public void CreateJS(Concept[] con,String actualpath) throws IOException{
		ArrayList<Concept> concepts = new ArrayList<Concept>();
		for(int i=0;i<con.length;i++){
			concepts.add(con[i]);
		}
		Document document= new Document();
		document.addConcepts(concepts);
		CreateJS(document, actualpath);
	}
	
	public void CreateJS(Document doc,String actualpath) throws IOException{
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder() ;
		
		String json = createJSON(doc);
		
		
		try {
			
			br = new BufferedReader(new FileReader(new File("visual\\example2.js")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line;
		
		try {
			while ((line = br.readLine()) != null) {

				if(line.trim().equals("var json = replace;")){
                                    
					sb.append("var json = "+json+";");
				}else if(line.trim().equals("var actualpath=replace2;")){
                    String newstring="\""+actualpath+"\"";
                    newstring=newstring.replace("\\", "/");
                    sb.append("var actualpath = "+newstring+";");
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
		

		
//		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("D:\\Project_clone\\SMC\\example2.js")));
//                bw.append(js);
                FileWriter writer = new FileWriter("visual\\replaced.js");
                writer.write(js);
                writer.close();
			
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
			
			String newkey = key.replaceAll("[\\-\\+\\.\\^:,]","");
			jss.append("\t{\n");
			jss.append("\t\t\"id\" : \""+newkey+"\",\n");
			jss.append("\t\t\"name\" : \""+newkey+"\",\n");			
			jss.append("\t\t\"data\" : {\n");
			jss.append("\t\t\"$color\" : \"#83548B\",\n");
			jss.append("\t\t\"$type\" :  \"circle\" \n\t\t},\n");
			
			
			HashMap rel = cm.get(key).getRelationships();
			Set<String> relatedconceptSet = rel.keySet();
			
			int l =relatedconceptSet.size();
			int j= 0;
			
			jss.append("\t\t\"adjacencies\" : [\n");
			for(String rkey: relatedconceptSet){
				
				String newrkey = rkey.replaceAll("[\\-\\+\\.\\^:,]","");
                j++;
				if(currentRel.get(rkey)!=null){
					if(!(currentRel.get(rkey).equals(key))){
						currentRel.put(key, rkey);
						jss.append("\t\t{\n");
						jss.append("\t\t\"nodeTo\" : \""+ newrkey+"\",\n");
						jss.append("\t\t\"nodeFrom\" : \""+ newrkey+"\",\n");
						jss.append("\t\t\"data\" : {}\n");
						jss.append("\t\t}");
																	
                        jss.append(",\n");                      
						
					}	
				}else if(currentRel.get(key)!=null){
					if(!(currentRel.get(key).equals(rkey))){
						currentRel.put(key, rkey);
						jss.append("\t\t{\n");
						jss.append("\t\t\"nodeTo\" : \""+ newrkey+"\",\n");
						jss.append("\t\t\"nodeFrom\" : \""+ newrkey+"\",\n");
						jss.append("\t\t\"data\" : {}\n");
						jss.append("\t\t}");						
												
	                    jss.append(",\n");	                    
						
					}	
				}else{
					currentRel.put(key, rkey);
					jss.append("\t\t{\n");
					jss.append("\t\t\"nodeTo\" : \""+ newrkey+"\",\n");
					jss.append("\t\t\"nodeFrom\" : \""+ newrkey+"\",\n");
					jss.append("\t\t\"data\" : {}\n");
					jss.append("\t\t}");
										
					jss.append(",\n");
          
				}											
			}
			if((relatedconceptSet.size()>0) && (jss.lastIndexOf("[")<jss.lastIndexOf(","))){
				jss.deleteCharAt(jss.lastIndexOf(","));
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
