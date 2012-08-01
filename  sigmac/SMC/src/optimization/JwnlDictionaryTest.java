package optimization;

//package morphoroot;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Set;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class JwnlDictionaryTest
//{
//    private JwnlDictionary dictionary;
//
//    @Before
//    public void setUp()
//    {
//        dictionary = new JwnlDictionary(System.getenv("WORDNET_HOME"));
//    }
//
//    @Test
//    public void testLookupWord()
//    {
//        Set<String> words = dictionary.lookupMorphologicallySimilarLexicalForms("banks");
//
//        assertEquals(2, words.size());
//    }
//
//    @Test
//    public void testLookupSynonyms()
//    {
//        Set<String> words = dictionary.lookupSynonyms("pants");
//
//        assertEquals(7, words.size());
//    }
//
//    @Test
//    public void testLookupHypernyms()
//    {
//        Set<String> words = dictionary.lookupHypernyms("pant");
//
//        assertTrue("No hypernyms returned for pants", words.size() > 0);
//        assertTrue("garment not in hypernyms", words.contains("garment"));
//    }
//
//    @Test
//    public void testLookupHyponyms()
//    {
//        Set<String> words = dictionary.lookupHyponyms("pant");
//
//        assertTrue("No hyponyms returned for pants", words.size() > 0);
//        assertTrue("jeans not in hypernyms", words.contains("jean"));
//    }
//}
