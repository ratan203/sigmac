package adaptors;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author user
 */
public class PDFAdapter {
    private File[] files;
    private SCDocument sc;
    public PDFAdapter(File file){
        files=new File[1];
        files[0]=file;
    }
    public PDFAdapter(){
        files=new File[1];
    }
    public PDFAdapter(File[] files){
        this.files=files;
    }
    public SCDocument getDocument() throws IOException{
        for(int i=0;i<files.length;i++){
            PDDocument document=PDDocument.load(files[i]);
            TextExtractor stripper = new TextExtractor();
            stripper.setStartPage( 1 );
            String s=stripper.getText(document);
            stripper.generateBlocks();
            sc=stripper.getPDFDocument();
            //System.out.println(sc);
        }
        return sc;
    }
    
    public SCDocument getDocument(String path) throws IOException{
        files[0]=new File(path);
        for(int i=0;i<files.length;i++){
            PDDocument document=PDDocument.load(files[i]);
            TextExtractor stripper = new TextExtractor();
            stripper.setStartPage( 1 );
            String s=stripper.getText(document);
            stripper.generateBlocks();
            sc=stripper.getPDFDocument();
            //System.out.println(sc);
        }
        return sc;
    }
}
