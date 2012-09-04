/*
 * Copyright 2012 Jayaprabath Fernando
 *
 * This file is part of SigmaC.
 *
 * SigmaC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SigmaC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SigmaC.  If not, see <http://www.gnu.org/licenses/>.
 */
package smc;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.objectbank.TokenizerFactory;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
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
            if(sentence.size()<3 || sentence.size()>=MAX_SENTENCE_LENTH){
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
        doc.setLastModifiedDate(xe.getLastModifiedDate());
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
        TokenizerFactory<CoreLabel> tokenizerFactory =
        PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
        List<CoreLabel> rawWords2 =
        tokenizerFactory.getTokenizer(new StringReader(title)).tokenize();
        if(rawWords2.isEmpty() || rawWords2.size()> MAX_SENTENCE_LENTH){
            return new HashSet<String>();
        }
        Tree parse = lp.apply(title);
        return analyzer.analyzeTitle(parse);
        
    }
}
