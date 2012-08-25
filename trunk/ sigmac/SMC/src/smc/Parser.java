/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author user
 */
public class Parser {

    LexicalizedParser lp;
    TreebankLanguagePack tlp;
    GrammaticalStructureFactory gsf;
    SentenceAnalyzer analyzer;
    StrengthCalculator calc;
    private static final int MAX_SENTENCE_LENTH=30;

    public Parser(String modelFile){
        lp=new LexicalizedParser(modelFile);
        tlp = new PennTreebankLanguagePack();
        gsf = tlp.grammaticalStructureFactory();
        analyzer=new SentenceAnalyzer();
        calc=new StrengthCalculator();
    }

    public Document parse(String fileName,DocumentPreprocessor.DocType doctype,
            String delimeter){
        DocumentPreprocessor p=new DocumentPreprocessor(fileName,doctype);
        p.setElementDelimiter(delimeter);
        Document doc=new Document(fileName);
        int sentenceCount=0;
       
        
        for (List<HasWord> sentence : p){
            if(sentence.isEmpty() || sentence.size()>=MAX_SENTENCE_LENTH){
                continue;
            }
            sentenceCount++;
            
            try{
            Tree parse = lp.apply(sentence);
            ArrayList<Concept> cons=analyzer.analyze(parse);
//            for(Concept con : cons){
//                con.printConcept();
//            }
//            System.out.println("ddssssssssssssssss");
            doc.addConcepts(cons);
            } catch(Exception e){
                System.out.println("file not executed");
            }
            
        }

        XMLFormattingExtractor xe=new XMLFormattingExtractor();
        doc.setTitleInfo(xe.getXMLFormatting(doc.getUri()));
        String URI=xe.getDocPath();
        doc.setUri(URI);
        if(URI.contains("\\")){
            doc.setName(URI.substring(URI.lastIndexOf("\\")));
        }
//        System.out.println("done");
//        calc.calculateImportance(doc);
//        doc.printDoc();
        doc.setSize(sentenceCount);
        return doc;
//        try {
//            FileOutputStream fos = new FileOutputStream("doccc.ser");
//            try {
//                ObjectOutputStream oout = new ObjectOutputStream(fos);
//                oout.writeObject(doc);
//                oout.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public Set<String> parseTitle(String title){
       // ArrayList<String> words=new ArrayList<String>();
        Tree parse = lp.apply(title);
        return analyzer.analyzeTitle(parse);
    }
}
