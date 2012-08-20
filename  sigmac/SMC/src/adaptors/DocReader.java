package adaptors;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;

public class DocReader {
ArrayList<Title> titles = new ArrayList();
String body = null;

public void readDocFile(String path) {
File docFile = null;
WordExtractor docExtractor = null ;
CharacterRun cr = null;
Range range = null;
HWPFDocument doc=null;

try {
        docFile = new File(path);
        FileInputStream fis=new FileInputStream(docFile.getAbsolutePath());
        doc=new HWPFDocument(fis);
        docExtractor = new WordExtractor(doc);
  
    }
    catch(Exception exep) {
        System.out.println(exep.getMessage());
    }

    String all=docExtractor.getText();
    //titles.add(new Title(null));
    Range overall=doc.getOverallRange();
    DocReaderAnalizer dra=new DocReaderAnalizer();
    Object BodyFONTsize=dra.getBodyFont(overall, doc, range, cr);
    Integer bodyfontsize = new Integer(BodyFONTsize.toString());
    
 
    System.out.println(all); 
    for(int i = 0; i < overall.getEndOffset()-1; i++ ){
                    int startIndex = i;
                    int endIndex = i + 1;
                    Range ranges = new Range(startIndex, endIndex, doc);
                    CharacterRun crr = ranges.getCharacterRun(0);
                    System.out.println(crr.text());
                    
    }
    
   
    for (int i = 0; i < overall.getEndOffset()-1; i++) {
                    int startIndex = i;
                    int endIndex = i + 1;
                    Range ranges = new Range(startIndex, endIndex, doc);
                    CharacterRun crr = ranges.getCharacterRun(0);

                    if (crr.getFontSize()>bodyfontsize) {
                        while (crr.getFontSize()>bodyfontsize) {
                            i++;
                            endIndex += 1;
                            ranges = new Range(endIndex, endIndex + 1, doc);
                            crr = ranges.getCharacterRun(0);
                        }
                        ranges = new Range(startIndex, endIndex - 1, doc);
                        titles.add(new Title(ranges.text()));
                    }

                }
  
    body=all;
    for(int i=0;i<titles.size();i++){
        String kk=(String) titles.get(i).getTitleText();
        //System.out.println(kk);
             body=body.replace(kk, " ");
    }
   
   System.out.println(body);
    
   
   
    }
	public SCDocument getDocument(String filePath){
		readDocFile(filePath);
		return new SCDocument(body, titles);
		
	}
}