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
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        Anaphora ana=new Anaphora();
//        String txt= "Java is a programming language originally developed by James Gosling at Sun Microsystems released in 1995 as a core component of Sun Microsystems' Java platform. The language derives much of its syntax from C and C++ but has a simpler object model and fewer low-level facilities than either C or C++. Java application is typically compiled to bytecode. It can run on any Java Virtual Machine(JVM) regardless of computer architecture. Java is a general-purpose, concurrent, class-based, object-oriented language that is specifically designed to have as few implementation dependencies as possible. It is intended to let application developers 'write once, run anywhere' (WORA), meaning that code that runs on one platform does not need to be recompiled to run on another. Java is as of 2012 one of the most popular programming languages in use, particularly for client-server web applications, with a reported 10 million users.";
//        String tmpFile="C:\\Users\\Thilina\\Documents\\NetBeansProjects\\AnaphoraRes\\temp file\\test.txt";
//        String anaRes=ana.resolveAnaph(txt,tmpFile);
//        System.out.println(anaRes);
//    }

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
              String arrayCont1;
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
                        arrayCont1=arrayCont;
//                        if(arrayCont1.length()>=3&&arrayCont1.trim().substring(0, 2).equalsIgnoreCase("a ")){
//                            arrayCont="The "+arrayCont.substring(3);
//                        }else if(arrayCont1.length()>=4&&arrayCont1.trim().substring(0, 3).equalsIgnoreCase("an ")){
//                            arrayCont="The "+arrayCont.substring(4);
//                        }
                        if(arrayCont.equalsIgnoreCase("he")||arrayCont.equalsIgnoreCase("she")||arrayCont.equalsIgnoreCase("it")||arrayCont.equalsIgnoreCase("his")||arrayCont.equalsIgnoreCase("her")||arrayCont.equalsIgnoreCase("its")||arrayCont.equalsIgnoreCase("they")||arrayCont.equalsIgnoreCase("them")||arrayCont.contains("The ")){
                            if(corefArry[arrayIn]==null){
                                corefArry[arrayIn]=arrayCont;
                            }
                        }else{
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

