package adaptors;

import java.io.File;
import java.util.ArrayList;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.SlideShow;

import org.apache.poi.hwpf.usermodel.CharacterRun;

public class PPTReader {

public SCDocument getDocument(String path) {
File docFile = null;

CharacterRun cr = null;

ArrayList titles = new ArrayList();
String body = null;
try {
    HSLFSlideShow slideshow=new HSLFSlideShow(path);
    SlideShow ppss = new SlideShow(slideshow);
    PPTExtractor pptex=new PPTExtractor(); //get object of pptextractor class
    
    SCDocument sd=pptex.textextractor(slideshow,ppss);        //call textextractor method
    return sd;
    
    }
    catch(Exception exep)
    {
        System.out.println(exep.getMessage());
        return null;
    }  

}

//public SCDocument getDocument(String filePath){
//		readPPTFile(filePath);
//		return new SCDocument(body, titles);
//		
//	}


}