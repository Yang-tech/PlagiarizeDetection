import static org.junit.Assert.*;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Test;

public class DocumentsProcessorTest {

	/**
	 * create the new DocumentsProcessor object and test whether the method "processDocuments"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testProcessDocumentsPathException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.processDocuments(null, 2);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "processDocuments"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testProcessDocumentsPathException2() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.processDocuments("", 2);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "processDocuments"
	 * can throw the correct FileNotFoundException when input is invalid 
	 */
	@Test (expected = FileNotFoundException.class)
	public void testProcessDocumentsPathException3() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.processDocuments("not_exist_file", 2);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "processDocuments"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testProcessDocumentsNException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.processDocuments("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", -1);
	}
	
	/**
	 * create the new DocumentsProcessor object and test the method "processDocument"
	 * can return the correct value
	 */
	@Test
	public void testProcessDocuments() {		
		DocumentsProcessor dp = new DocumentsProcessor();
		Map<String, List<String>> fileToSeq = dp.processDocuments("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", 5);
		Map<String, List<String>> map = new HashMap<>();
		List<String> fileList1 = new ArrayList<String>();
		fileList1.add("abcbcdefjhijklm");
		fileList1.add("bcdefjhijklmopq");
		fileList1.add("efjhijklmopqrst");
		fileList1.add("hijklmopqrstuvw");
		fileList1.add("klmopqrstuvwxyz");
		map.put("test1.txt", fileList1);
		List<String> fileList2 = new ArrayList<String>();
		fileList2.add("mklcbdabcbcdefj");
		fileList2.add("cbdabcbcdefjhij");
		fileList2.add("abcbcdefjhijklm");
		fileList2.add("bcdefjhijklmrmk");
		map.put("test2.txt", fileList2);
		List<String> fileList3 = new ArrayList<String>();
		fileList3.add("efjhijklmopqrst");
		fileList3.add("hijklmopqrstmkl");
		fileList3.add("klmopqrstmklcbd");
		fileList3.add("opqrstmklcbdabc");
		fileList3.add("rstmklcbdabcbcd");
		fileList3.add("mklcbdabcbcdefj");
		map.put("test3.txt", fileList3);		
		assertEquals(fileToSeq.size(), map.size());
		assertTrue(fileToSeq.equals(map));
	}

	/**
	 * create the new DocumentsProcessor object and test whether the method "storeNWordSequences"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testStoreNWordSequencesPathException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		Map<String, List<String>> map = new HashMap<>();
		List<String> fileList1 = new ArrayList<String>();
		fileList1.add("abcbcdefjhijklm");
		fileList1.add("bcdefjhijklmopq");
		fileList1.add("efjhijklmopqrst");
		fileList1.add("hijklmopqrstuvw");
		fileList1.add("klmopqrstuvwxyz");
		map.put("test1.txt", fileList1);
		List<String> fileList2 = new ArrayList<String>();
		fileList2.add("mklcbdabcbcdefj");
		fileList2.add("cbdabcbcdefjhij");
		fileList2.add("abcbcdefjhijklm");
		fileList2.add("bcdefjhijklmrmk");
		map.put("test2.txt", fileList2);
		List<String> fileList3 = new ArrayList<String>();
		fileList3.add("efjhijklmopqrst");
		fileList3.add("hijklmopqrstmkl");
		fileList3.add("klmopqrstmklcbd");
		fileList3.add("opqrstmklcbdabc");
		fileList3.add("rstmklcbdabcbcd");
		fileList3.add("mklcbdabcbcdefj");
		map.put("test3.txt", fileList3);	
		dp.storeNWordSequences(map, null);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "storeNWordSequences"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testStoreNWordSequencesMapException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.storeNWordSequences(null, "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt");
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "storeNWordSequences"
	 * can throw the correct FileNotFoundException when input is invalid 
	 */
	@Test (expected = FileNotFoundException.class)
	public void testStoreNWordSequencesException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		Map<String, List<String>> map = new HashMap<>();
		List<String> fileList1 = new ArrayList<String>();
		fileList1.add("abcbcdefjhijklm");
		fileList1.add("bcdefjhijklmopq");
		fileList1.add("efjhijklmopqrst");
		fileList1.add("hijklmopqrstuvw");
		fileList1.add("klmopqrstuvwxyz");
		map.put("test1.txt", fileList1);
		List<String> fileList2 = new ArrayList<String>();
		fileList2.add("mklcbdabcbcdefj");
		fileList2.add("cbdabcbcdefjhij");
		fileList2.add("abcbcdefjhijklm");
		fileList2.add("bcdefjhijklmrmk");
		map.put("test2.txt", fileList2);
		List<String> fileList3 = new ArrayList<String>();
		fileList3.add("efjhijklmopqrst");
		fileList3.add("hijklmopqrstmkl");
		fileList3.add("klmopqrstmklcbd");
		fileList3.add("opqrstmklcbdabc");
		fileList3.add("rstmklcbdabcbcd");
		fileList3.add("mklcbdabcbcdefj");
		map.put("test3.txt", fileList3);
		dp.storeNWordSequences(map, "not_exist_file");
	}
	
	/**
	 * create the new DocumentsProcessor object and test the method "storeNWordSequences"
	 * can store the word sequence and return correctly
	 */
	@Test
	public void testStoreNWordSequences() {		
		DocumentsProcessor dp = new DocumentsProcessor();
		Map<String, List<String>> fileToSeq = dp.processDocuments("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", 5);	
		List<Tuple<String, Integer>> results = dp.storeNWordSequences(fileToSeq, "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt");
		List<Tuple<String, Integer>> listTuples = new ArrayList<>();
		Tuple<String, Integer> tuple1 = new Tuple<String, Integer>("test1.txt", 80);
		Tuple<String, Integer> tuple2 = new Tuple<String, Integer>("test2.txt", 64);
		Tuple<String, Integer> tuple3 = new Tuple<String, Integer>("test3.txt", 96);
		listTuples.add(tuple1);
		listTuples.add(tuple2);
		listTuples.add(tuple3);
		assertEquals(listTuples.size(), results.size());
		assertTrue(results.get(0).getRight().equals(tuple1.getRight()));
		assertTrue(results.get(0).getLeft().equals(tuple1.getLeft()));
		assertTrue(results.get(1).getRight().equals(tuple2.getRight()));
		assertTrue(results.get(1).getLeft().equals(tuple2.getLeft()));
		assertTrue(results.get(2).getRight().equals(tuple3.getRight()));
		assertTrue(results.get(2).getLeft().equals(tuple3.getLeft()));
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "computeSimilarities"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testComputeSimilaritiesListException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.computeSimilarities("/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt", null);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "computeSimilarities"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testComputeSimilaritiesPathException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		List<Tuple<String, Integer>> listTuples = new ArrayList<>();
		Tuple<String, Integer> tuple1 = new Tuple<String, Integer>("test1.txt", 80);
		Tuple<String, Integer> tuple2 = new Tuple<String, Integer>("test2.txt", 64);
		Tuple<String, Integer> tuple3 = new Tuple<String, Integer>("test3.txt", 96);
		listTuples.add(tuple1);
		listTuples.add(tuple2);
		listTuples.add(tuple3);
		dp.computeSimilarities(null, listTuples);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "computeSimilarities"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = FileNotFoundException.class)
	public void testComputeSimilaritiesFileException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		List<Tuple<String, Integer>> listTuples = new ArrayList<>();
		Tuple<String, Integer> tuple1 = new Tuple<String, Integer>("test1.txt", 80);
		Tuple<String, Integer> tuple2 = new Tuple<String, Integer>("test2.txt", 64);
		Tuple<String, Integer> tuple3 = new Tuple<String, Integer>("test3.txt", 96);
		listTuples.add(tuple1);
		listTuples.add(tuple2);
		listTuples.add(tuple3);
		dp.computeSimilarities("not_exist_file", listTuples);
	}
	
	/**
	 * create the new DocumentsProcessor object and test the method "computeSimilarities"
	 * can compute the file similarities and return correctly
	 */
	@Test
	public void testComputeSimilarities() {
		DocumentsProcessor dp = new DocumentsProcessor();
		Map<String, List<String>> fileToSeq = dp.processDocuments("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", 5);	
		List<Tuple<String, Integer>> list = dp.storeNWordSequences(fileToSeq, "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt");
		TreeSet<Similarities> treeSet = dp.computeSimilarities("/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt", list);
		TreeSet<Similarities> results = new TreeSet<>();
		Similarities simi1 = new Similarities("test1.txt", "test2.txt");
		simi1.setCount(1);
		Similarities simi2 = new Similarities("test1.txt", "test3.txt");
		simi2.setCount(3);
		Similarities simi3 = new Similarities("test2.txt", "test3.txt");
		simi3.setCount(1);
		results.add(simi1);
		results.add(simi2);
		results.add(simi3);
		assertEquals(treeSet.size(), results.size());
		assertTrue(treeSet.equals(results));
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "printSimilarities"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testPrintSimilaritiesPathException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.printSimilarities(null, 1);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "printSimilarities"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testPrintSimilaritiesException() {
		DocumentsProcessor dp = new DocumentsProcessor();
		TreeSet<Similarities> results = new TreeSet<>();
		Similarities simi1 = new Similarities("test1.txt", "test2.txt");
		simi1.setCount(1);
		Similarities simi2 = new Similarities("test1.txt", "test3.txt");
		simi2.setCount(3);
		Similarities simi3 = new Similarities("test2.txt", "test3.txt");
		simi3.setCount(1);
		results.add(simi1);
		results.add(simi2);
		results.add(simi3);
		dp.printSimilarities(results, -1);
	}
	
	/**
	 * create the new DocumentsProcessor object and test the method "printSimilarities"
	 * can run correctly
	 */
	@Test
	public void testPrintSimilarities() {
		DocumentsProcessor dp = new DocumentsProcessor();
		Map<String, List<String>> fileToSeq = dp.processDocuments("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", 5);	
		List<Tuple<String, Integer>> list = dp.storeNWordSequences(fileToSeq, "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt");
		TreeSet<Similarities> treeSet = dp.computeSimilarities("/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt", list);
		dp.printSimilarities(treeSet, 0);
		dp.printSimilarities(treeSet, 1);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "processAndStore"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testProcessAndStoreException1() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.processAndStore(null, "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt", 2);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "processAndStore"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testProcessAndStoreException2() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.processAndStore("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", null, 2);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "processAndStore"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testProcessAndStoreException3() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.processAndStore("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt",  -1);
	}
	
	/**
	 * create the new DocumentsProcessor object and test whether the method "processAndStore"
	 * can throw the correct IllegalArgumentException when input is invalid 
	 */
	@Test (expected = FileNotFoundException.class)
	public void testProcessAndStoreException4() {
		DocumentsProcessor dp = new DocumentsProcessor();
		dp.processAndStore("not_exist_file", "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt",  1);
	}
	
	/**
	 * create the new DocumentsProcessor object and test the method "processAndStore"
	 * can process and store the results correctly and return the correct value
	 */
	@Test
	public void testProcessAndStore() {
		DocumentsProcessor dp = new DocumentsProcessor();
		List<Tuple<String, Integer>> results = dp.processAndStore("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt", 5);
		List<Tuple<String, Integer>> listTuples = new ArrayList<>();
		Tuple<String, Integer> tuple1 = new Tuple<String, Integer>("test1.txt", 80);
		Tuple<String, Integer> tuple2 = new Tuple<String, Integer>("test2.txt", 64);
		Tuple<String, Integer> tuple3 = new Tuple<String, Integer>("test3.txt", 96);
		listTuples.add(tuple1);
		listTuples.add(tuple2);
		listTuples.add(tuple3);
		assertEquals(listTuples.size(), results.size());
		assertTrue(results.get(0).getRight().equals(tuple1.getRight()));
		assertTrue(results.get(0).getLeft().equals(tuple1.getLeft()));
		assertTrue(results.get(1).getRight().equals(tuple2.getRight()));
		assertTrue(results.get(1).getLeft().equals(tuple2.getLeft()));
		assertTrue(results.get(2).getRight().equals(tuple3.getRight()));
		assertTrue(results.get(2).getLeft().equals(tuple3.getLeft()));
	}
}
