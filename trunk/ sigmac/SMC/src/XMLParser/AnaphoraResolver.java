/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Thilina
 */
public class AnaphoraResolver {
    private String textToResolve;
    private String tempPath;
    public AnaphoraResolver(String text,String path) throws IOException {
        this.textToResolve=text;
        this.tempPath=path;                 
    }
    
    /**
     * This method is for processing co-reference file and resolve only inter sentential anaphora
     * 
     * @return Anaphora resolved text
     */
    public String resolveAnaphora(){
        //Creation of co reference identified file (.coref)
        CoreferenceIdentifier an=new CoreferenceIdentifier();
        an.resolveAnaph(textToResolve, tempPath);
         String resolveTxt="";        
         try{
              // Open the file first
              FileInputStream fstream = new FileInputStream(tempPath+".coref");
              // Get the object of DataInputStream
              DataInputStream in = new DataInputStream(fstream);
              BufferedReader br = new BufferedReader(new InputStreamReader(in));
              String strLine="";
              int arrayIn;
              String corefid;
              String arrayCont;
              String[] words;
              String[] lastWord;
              String[] tokens;
              String[] repArray;
              HashMap<Integer, String> corefArry = new HashMap<Integer, String>();
              //Read File Line By Line
              while ((strLine = br.readLine()) != null) {                                      

                                     
                    tokens = StringUtils.substringsBetween(strLine, "<NP", "</NP>");
                    repArray=new String[tokens.length];

                    //Checking for resolving anaphora noun by noun
                    for(int i=0;i<tokens.length;i++){
                        corefid=StringUtils.substringBetween(tokens[i], "CorefID=\"", "\">");
                        arrayIn=Integer.parseInt(corefid);
                        arrayCont=tokens[i].substring(tokens[i].indexOf(">")+1);

                        //Check wheather the checking noun is pronoun if it is store it's proper noun 
                        //If the noun is a proper noun then replace older proper noun with same corefID with new proper noun
                        if(arrayCont.equalsIgnoreCase("he")||arrayCont.equalsIgnoreCase("she")||arrayCont.equalsIgnoreCase("it")||arrayCont.equalsIgnoreCase("his")||arrayCont.equalsIgnoreCase("her")||arrayCont.equalsIgnoreCase("its")||arrayCont.equalsIgnoreCase("they")||arrayCont.equalsIgnoreCase("them")||arrayCont.contains("The ")){
                            if(corefArry.get(arrayIn)==null){
                                corefArry.put(arrayIn, arrayCont);
                            }
                        }else{
                            corefArry.put(arrayIn, arrayCont);
                        }
                      
                        //Make sure only inter sentential anaphora are resolving
                      words = resolveTxt.concat(strLine).split(tokens[i]);
                      if(words[0].contains(corefArry.get(arrayIn))){
                          lastWord = words[0].split(corefArry.get(arrayIn));
                          if(lastWord[lastWord.length-1].contains(".")){    
                            repArray[i]=corefArry.get(arrayIn);
                          }else{
                            repArray[i]=arrayCont; 
                          }
                      }else{
                          repArray[i]=corefArry.get(arrayIn);
                      }
                      //Replace pronoun with relevent proper noun
                      System.out.println(tokens[i]+"  "+repArray[i]);
                      Pattern pat0 = Pattern.compile(tokens[i]);
                      Matcher mat0 = pat0.matcher(strLine);                          
                      strLine=mat0.replaceAll(repArray[i]);
                      mat0.reset();

                  }
                    //Remove tags from StrLine
                    Pattern pat1 = Pattern.compile("<NP");
                    Matcher mat1 = pat1.matcher(strLine);
                    strLine=mat1.replaceAll("");
                    mat1.reset();
                    
                    Pattern pat2 = Pattern.compile("</NP>");
                    Matcher mat2 = pat2.matcher(strLine);
                    strLine=mat2.replaceAll("");
                    mat2.reset();

                    if(strLine.contains(" NO=\"")){
                        String[] unused = StringUtils.substringsBetween(strLine, " NO=\"", "\">");
                        for(int k=0;k<unused.length;k++){
                            Pattern pat3 = Pattern.compile(" NO=\""+ unused[k] +"\">");
                            Matcher mat3 = pat3.matcher(strLine);
                            strLine=mat3.replaceAll("");
                            mat3.reset();
                        }
                    }

                    //Deleting temporary files
                    File file1=new File(tempPath);
                    file1.setWritable(true);
                    file1.delete();

                    File file2=new File(tempPath+".coref");
                    file2.setWritable(true);
                    file2.delete();
                    
                    //Append to resolved text
                    resolveTxt+=strLine;

              }
              //Close the input stream
              in.close();
          }catch (Exception e){//Catch exception if any
              System.err.println("Error: " + e.getMessage()+e.toString());
          }
         System.out.println(resolveTxt);
          return resolveTxt;
    }    
}
