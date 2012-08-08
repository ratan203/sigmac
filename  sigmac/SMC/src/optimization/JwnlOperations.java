/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.PointerTarget;
import net.didion.jwnl.data.PointerType;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

/**
 *
 * @author Thilina
 */
public class JwnlOperations {

    private Dictionary initWn() throws FileNotFoundException, JWNLException{
        FileInputStream is=new FileInputStream( new File("src\\optimization\\file_properties.xml"));
        if(!JWNL.isInitialized()){
            JWNL.initialize(is);
        }
        return Dictionary.getInstance();
    }

    public Set<String> getSynonyms(String lexicalForm) throws JWNLException, FileNotFoundException{
        Set<String> synonyms = new HashSet<String>();
        Dictionary dictionary=initWn();
        IndexWord indexWord = dictionary.getIndexWord(POS.NOUN, lexicalForm);
        if (indexWord == null)
        return synonyms;
        Synset[] synSets = indexWord.getSenses();
        for (Synset synset : synSets){
            Word[] words = synset.getWords();
            for (Word word : words){
                synonyms.add(word.getLemma());
            }
        }
        return synonyms;
    }

    public String getMorphologicalRoot(String lexicalForm) throws FileNotFoundException, JWNLException{
        Dictionary dictionary=initWn();
        String trimLexicalForm=lexicalForm.toLowerCase().trim();
        String morphWord=trimLexicalForm;
        IndexWord baseForms = dictionary.getMorphologicalProcessor().lookupBaseForm(POS.NOUN, trimLexicalForm);
        String[] inWords=trimLexicalForm.split(" |\\_");
        int noOfInWords=inWords.length;

        if(baseForms!=null){
            String[] outWords=baseForms.getLemma().split(" ");
            int noOfOutWords=outWords.length;
            if(noOfInWords==noOfOutWords){
                morphWord=baseForms.getLemma();
            }
        }
        return morphWord;
    }

    public boolean assertIsRel(String head,String tail) throws FileNotFoundException, JWNLException{
        Boolean isConf=false;
        String morHead=getMorphologicalRoot(head);
        String morTail=getMorphologicalRoot(tail);
        String morTailMod=morTail.replaceAll(" ", "_");
        Set<String> isSet=getHypernyms(morHead);

        if(isSet.contains(morTailMod)){
            isConf=true;
        }
        return isConf;
    }

    public boolean assertPartOfRel(String head,String tail) throws FileNotFoundException, JWNLException{
        Boolean partConf=false;
        String morHead=getMorphologicalRoot(head);
        String morTail=getMorphologicalRoot(tail);
        Set<String> partSet=getMeronym(morTail);
        String morHeadMod=morHead.replaceAll(" ", "_");

        if(partSet.contains(morHeadMod)){
            partConf=true;
        }
        return partConf;
    }

    private Set<String> getHypernyms(String lexicalForm) throws FileNotFoundException, JWNLException{
        return lookupWordsFollowingPointer(lexicalForm, PointerType.HYPERNYM);
    }

    private Set<String> getHyponyms(String lexicalForm) throws FileNotFoundException, JWNLException{
        return lookupWordsFollowingPointer(lexicalForm, PointerType.HYPONYM);
    }

    private Set<String> getHolonym(String lexicalForm) throws FileNotFoundException, JWNLException{
        return lookupWordsFollowingPointer(lexicalForm, PointerType.PART_HOLONYM);
    }

    private Set<String> getMeronym(String lexicalForm) throws FileNotFoundException, JWNLException{
        return lookupWordsFollowingPointer(lexicalForm, PointerType.PART_MERONYM);
    }

    private Set<String> lookupWordsFollowingPointer(String lexicalForm, PointerType pointerType) throws FileNotFoundException, JWNLException{
        Set<String> result = new HashSet<String>();
        Dictionary dictionary=initWn();
        IndexWord indexWord = dictionary.getIndexWord(POS.NOUN, lexicalForm);
        if (indexWord == null)
        return result;
        Synset[] synSets = indexWord.getSenses();
        for (Synset synset : synSets){
            if (synset.getPointers(pointerType).length>0){
                PointerTarget[] targets = synset.getTargets(pointerType);
                for (PointerTarget target : targets){
                    Word[] words = ((Synset) target).getWords();
                    for (Word word : words){
                        result.add(word.getLemma());
                    }
                }
            }
        }
        return result;
    }

}


