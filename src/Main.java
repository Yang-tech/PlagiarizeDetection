import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		DocumentsProcessor dp = new DocumentsProcessor();
		Map<String, List<String>> m1 = dp.processDocuments("/Users/yanya/Desktop/594/HW/hw1/test_doc_set", 5);
//		List<Tuple<String, Integer>> l1 = dp.processAndStore("/Users/yanya/Desktop/594/HW/hw1/sm_doc_set2", "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt", 6);
		List<Tuple<String, Integer>> l1 = dp.storeNWordSequences(m1, "/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt");
		TreeSet<Similarities> t1 = dp.computeSimilarities("/Users/yanya/Desktop/594/HW/hw1/nWordFile.txt", l1);
		dp.printSimilarities(t1, 100);
	}
}
