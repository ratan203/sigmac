package adaptors;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import adaptors.PDFBlock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

/**
 *
 * @author user
 */
public class TextExtractor extends PDFTextStripper {
    private ArrayList<TextPosition> chars;
    private ArrayList<PDFBlock> blocks;
    private float previous;
    private HashMap<Float,Integer> freqMap;
    private float bodyFontSize;
    private float minFontSize;
    private float maxFontSize;
    public TextExtractor() throws IOException{
        super();
        chars=new ArrayList<TextPosition>();
        blocks=new ArrayList<PDFBlock>();
        freqMap=new HashMap();
    }

    @Override
    protected void processTextPosition(TextPosition text){
        //super.processTextPosition(text);
        chars.add(text);
    }

    public void generateBlocks(){
        String temp="";
        int count=0;
        for(TextPosition text : chars){
            if(isValid(text)){
                if(previous==text.getXScale()){
                    temp+=text.getCharacter();
                    count++;
                }else{
                    PDFBlock block=new PDFBlock(previous, count, temp);
                    this.blocks.add(block);
                    temp="";
                    this.updateFreqMap(previous, count);
                    temp=text.getCharacter();
                    previous=text.getXScale();
                    count=1;
                }
            }else{
                if(!temp.equals("")){
                    PDFBlock block=new PDFBlock(previous, count, temp);
                    this.blocks.add(block);
                    temp="";
                    this.updateFreqMap(previous, count);
                    count=0;
                }
            }
        }
        updateFontInfo();
        chars.clear();
    }

    private boolean isValid(TextPosition text){
        String str=text.getCharacter();
        char[] characters=str.toCharArray();
        for(int i=0;i<characters.length;i++){
            Character c=characters[i];
            c.getNumericValue(c);
            if((c>127)){                
                return false;
            }
        }
        return true;
    }

    private void updateFreqMap(float size,int increment){
        if(this.freqMap.containsKey(size)){
            int val=this.freqMap.get(size);
            this.freqMap.put(size, val+increment);
        }else{
            this.freqMap.put(size, increment);
        }
    }

    private void updateFontInfo(){
        Set<Float> sizes=freqMap.keySet();
        Iterator itr=sizes.iterator();
        int maxFreq=0;
        int count=0;
        while(itr.hasNext()){
            Float size=(Float) itr.next();
            if(maxFontSize<size){
                maxFontSize=size;
            }
            if(minFontSize>size){
                minFontSize=size;
            }
            count=this.freqMap.get(size);
            if(count>maxFreq){
                maxFreq=count;
                bodyFontSize=size;
            }
        }
    }

    public SCDocument getPDFDocument(){
        //System.out.println(bodyFontSize);
        String body="";
        ArrayList<Title> title=new ArrayList<Title>();
        for(PDFBlock block: blocks){
            if(bodyFontSize<block.getFontSize()){
                title.add(new Title(block.getBody()));
            }else if(bodyFontSize==block.getFontSize()){
                body=body+block.getBody()+"\n";
            }else{
                
            }
        }
        SCDocument doc=new SCDocument(body, title);
        System.out.println(doc);
        return doc;
    }
}
