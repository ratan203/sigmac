package XMLParser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import reconcile.Resolver;

/**
 *
 * @author thilina
 */
public class Anaphora {
  
    /**
     * @param String (Which need to resolve)
     * @return String (Anaphora resolved)
     */
    public String resolveAnaph(String Txt, String tempFileDest) {

         String resolveTxt="";

            try {
                  File file = new File(tempFileDest);
                  BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                  String text=Txt.replaceAll( "\\([^\\(]*?\\)", " ");
                  text=text.replace("(", " ").replace(")", " ").replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ");
                  out.write(text);
                  out.newLine();
                  out.close();

            } catch (Exception ex) {
                Logger.getLogger(Anaphora.class.getName()).log(Level.SEVERE, null, ex);
            }

             String[] x=new String[1];
             x[0]=tempFileDest;
             Resolver.main(x);

         try{
              // Open the file first
              FileInputStream fstream = new FileInputStream(x[0]+".coref");
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
              String[] corefArry = new String[10000];
              //Read File Line By Line
              while ((strLine = br.readLine()) != null) {                                      
                                     
                    tokens = StringUtils.substringsBetween(strLine, "<NP", "</NP>");
                    repArray=new String[10000];

                    for(int i=0;i<tokens.length;i++){
                        corefid=StringUtils.substringBetween(tokens[i], "CorefID=\"", "\">");
                        arrayIn=Integer.parseInt(corefid);
                        arrayCont=tokens[i].substring(tokens[i].indexOf(">")+1);
                        if(corefArry[arrayIn]==null){
                            corefArry[arrayIn]=arrayCont;
                        }
                      words = resolveTxt.concat(strLine).split(tokens[i]);
                      if(words[0].contains(corefArry[arrayIn])){
                          lastWord = words[0].split(corefArry[arrayIn]);
                          if(lastWord[lastWord.length-1].contains(".")){    
                            repArray[i]=corefArry[arrayIn];
                          }else{
                            repArray[i]=arrayCont; 
                          }
                      }else{
                          repArray[i]=corefArry[arrayIn];
                      }
                      System.out.println(tokens[i]+"  "+repArray[i]);
                      Pattern pat0 = Pattern.compile(tokens[i]);
                      Matcher mat0 = pat0.matcher(strLine);                          
                      strLine=mat0.replaceAll(repArray[i]);
                      mat0.reset();

                  }
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

                    File file1=new File(x[0]);
                    file1.setWritable(true);
                    file1.delete();

                    File file2=new File(x[0]+".coref");
                    file2.setWritable(true);
                    file2.delete();
                    
                    resolveTxt+=strLine;

              }
              //Close the input stream
              in.close();
          }catch (Exception e){//Catch exception if any
              System.err.println("Error: " + e.getMessage()+e.toString());
          }
          return resolveTxt;
    }
}

