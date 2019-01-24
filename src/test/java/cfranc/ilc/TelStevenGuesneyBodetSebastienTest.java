/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cfranc.ilc;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 *
 * @author st376873
 */
public class TelStevenGuesneyBodetSebastienTest {
    


	String[] when2SimpleWords = new String[] {"parapluie", "parachute"};
        String[] totalDifferentWords = new String[] {"mur", "habitation"};
	
        private MarkovWord m = new MarkovWord();
        
	@Test
	public void getSimilarity_2SimpleWords_26() {
		double expected = 0.33;
		double actual = m.getSimilarity(when2SimpleWords[0],when2SimpleWords[1], 2);
		assertEquals(expected, actual,0.01);		
	}
        
        
        

	@Test
	public void getSimilarity_SameWord_100() {
		
		double expected = 1.0;
		double actual = m.getSimilarity(when2SimpleWords[0],when2SimpleWords[0], 2);
		assertEquals(expected, actual,0.000000001);		
	}	
        
        
        @Test
	public void getSimilarity_TotalDifferentWord_0() {
		
		double expected = 0;
		double actual = m.getSimilarity(totalDifferentWords[0],totalDifferentWords[1], 2);
		assertEquals(expected, actual,0.000000001);		
	}	
        
        
        @Test
	public void common_OneWordCommon_1() {
		List<MarkovData> one = new ArrayList<MarkovData>();
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("world", 1));
		List<MarkovData> two = new ArrayList<MarkovData>();
                two.add(new MarkovData("hello", 1));
                two.add(new MarkovData("people", 1));
		double expected = 1.0;
		double actual = m.common(one, two);
		assertEquals(expected, actual,0.000000001);		
	}	
        
        
         @Test
	public void common_Same5Words_5() {
		List<MarkovData> one = new ArrayList<MarkovData>();
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("world", 1));
                one.add(new MarkovData("how", 1));
                one.add(new MarkovData("are", 1));
                one.add(new MarkovData("you", 1));
		List<MarkovData> two = one;
		double expected = 5.0;
		double actual = m.common(one, two);
		assertEquals(expected, actual,0.000000001);		
	}	
        
        @Test
	public void common_AlwaysSame5Words_25() {
		List<MarkovData> one = new ArrayList<MarkovData>();
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("hello", 1));
		List<MarkovData> two = one;
		double expected = 25.0;
		double actual = m.common(one, two);
		assertEquals(expected, actual,0.000000001);		
	}
        
        @Test
	public void common_AllDifferents3Words_0() {
		List<MarkovData> one = new ArrayList<MarkovData>();
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("wolrd", 1));
                one.add(new MarkovData("all", 1));
		List<MarkovData> two = new ArrayList<MarkovData>();
                two.add(new MarkovData("Bonjour", 1));
                two.add(new MarkovData("le", 1));
                two.add(new MarkovData("monde", 1));
		double expected = 0.0;
		double actual = m.common(one, two);
		assertEquals(expected, actual,0.000000001);		
	}
        
        @Test
	public void union_AllDifferents3Words_6() {
	        List<MarkovData> one = new ArrayList<MarkovData>();
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("wolrd", 1));
                one.add(new MarkovData("all", 1));
		List<MarkovData> two = new ArrayList<MarkovData>();
                two.add(new MarkovData("Bonjour", 1));
                two.add(new MarkovData("le", 1));
                two.add(new MarkovData("monde", 1));
		double expected = 6;
		double actual = m.union(one, two);
		assertEquals(expected, actual,0.000000001);
	}
        
        @Test
	public void union_sentences3WordsWithOneSameWord_5() {
	        List<MarkovData> one = new ArrayList<MarkovData>();
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("wolrd", 1));
                one.add(new MarkovData("all", 1));
		List<MarkovData> two = new ArrayList<MarkovData>();
                two.add(new MarkovData("hello", 1));
                two.add(new MarkovData("le", 1));
                two.add(new MarkovData("monde", 1));
		double expected = 5;
		double actual = m.union(one, two);
		assertEquals(expected, actual,0.000000001);
	}
        
         @Test
	public void union_sentences3WordsWithAllSameWords_3() {
	        List<MarkovData> one = new ArrayList<MarkovData>();
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("wolrd", 1));
                one.add(new MarkovData("all", 1));
		List<MarkovData> two = one;
		double expected = 3;
		double actual = m.union(one, two);
		assertEquals(expected, actual,0.000000001);
	}
        
        
        @Test
	public void union_sentences3SameWordsWithAllSameWords_3() {
	        List<MarkovData> one = new ArrayList<MarkovData>();
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("hello", 1));
                one.add(new MarkovData("hello", 1));
		List<MarkovData> two = one;
		double expected = 3;
		double actual = m.union(one, two);
		assertEquals(expected, actual,0.000000001);
	}
        
        
        
        
        
        @Test
	public void processString_emptyString() {
	        String sentence = "";
                List<MarkovData> expected =  new ArrayList<MarkovData>();
                expected.add(new MarkovData("%%", 1));
                m.displayResult(expected);
		List<MarkovData> actual = m.processString(sentence, 2);
                m.displayResult(actual);
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
        
        
        @Test
	public void processString_oneWord() {
	        String sentence = "hello";
                List<MarkovData> expected =  new ArrayList<MarkovData>();
                expected.add(new MarkovData("%h", 1));
                expected.add(new MarkovData("he", 1));
                expected.add(new MarkovData("el", 1));
                expected.add(new MarkovData("ll", 1));
                expected.add(new MarkovData("lo", 1));
                expected.add(new MarkovData("o%", 1));
                m.displayResult(expected);
		List<MarkovData> actual = m.processString(sentence, 2);
                m.displayResult(actual);
		assertArrayEquals(expected.toArray(), actual.toArray());
	}
        
        
        @Test
	public void contains_emptyString_0() {
	     MarkovData data = new MarkovData("hello", 2);
             String sentence = "";
             double expected = 0;
             double actual = m.contains(sentence);
             assertEquals(expected, actual,0.000000001);
	}
        
        
        
        @Test
	public void contains_differentsWords_0() {
	     MarkovData data = new MarkovData("world", 1);
             String sentence = "mur";
             double expected = 0;
             double actual = m.contains(sentence);
             assertEquals(expected, actual,0.000000001);
	}
        
        @Test
	public void contains_wordContainedAtIndex3_3() {

             MarkovWord markovWord = new MarkovWord("Helloworld", 2);
             String sentence = "ll";
             double expected = 3.0;
             double actual = markovWord.contains(sentence);
             assertEquals(expected, actual,0.000000001);
	}
        
        
         @Test
	public void contains_sameWords_0() {

             MarkovWord markovWord = new MarkovWord("world", 2);
             String sentence = "world";
             double expected = 0.0;
             double actual = markovWord.contains(sentence);
             assertEquals(expected, actual,0.000000001);
	}
        
        
      
    
}
