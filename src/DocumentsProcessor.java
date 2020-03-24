import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class DocumentsProcessor implements IDocumentsProcessor{

	@Override
	public Map<String, List<String>> processDocuments(String directoryPath, int n) {
		
		Map<String, List<String>> fileToSeq = new HashMap<>();
		try {
			if(directoryPath == null || directoryPath.length() == 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			if(n <= 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			
			List<String> fileList = new ArrayList<String>();		
			
			File folder = new File(directoryPath);
			File[] listOfFiles = folder.listFiles(new FilenameFilter() {
		        @Override
		        public boolean accept(File dir, String name) {
		            return !name.equals(".DS_Store");
		        }
		    });
			
			for(File file: listOfFiles) {			
				fileList.add(file.getName());
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				DocumentIterator iter = new DocumentIterator(br, n);
				List<String> list = new ArrayList<>();
				fileToSeq.put(file.getName(), list);
				while (iter.hasNext()) {
					String wordSeq = iter.next();
					if(!wordSeq.equals("")) {
						list.add(wordSeq.toLowerCase());
					}
								
				}	
				fr.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (NoSuchElementException e) {
	        System.out.println("IO error from closing reader.");
	    } catch (IOException e) {		
			e.printStackTrace();
		}
		System.out.println(fileToSeq.toString());
		return fileToSeq;
	}

	@Override
	public List<Tuple<String, Integer>> storeNWordSequences(Map<String, List<String>> docs, String nwordFilePath) {
		List<Tuple<String, Integer>> listTuples = new ArrayList<>();
		
		try {
			if(docs == null || docs.size() == 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			if(nwordFilePath == null || nwordFilePath.length() == 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			File file = new File(nwordFilePath);
			FileWriter fw = new FileWriter(file);
			for(Map.Entry<String, List<String>> kv: docs.entrySet()){
				int sum = 0;
				for(String str : kv.getValue()) {
					fw.write(str);
					fw.write(" ");
					int countByte = str.length() + 1;
					sum += countByte;
				}
				listTuples.add(new Tuple<String, Integer>(kv.getKey(), sum));
			}
			fw.close();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return listTuples;
	}

	@Override
	public TreeSet<Similarities> computeSimilarities(String nwordFilePath, List<Tuple<String, Integer>> fileindex) {
		TreeSet<Similarities> treeSet = new TreeSet<>();
		try {
			if(fileindex == null || fileindex.size() == 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			if(nwordFilePath == null || nwordFilePath.length() == 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			RandomAccessFile raf = new RandomAccessFile(nwordFilePath, "r");
			List<HashSet<String>> list = new ArrayList<>();	
			for(int i = 0; i < fileindex.size(); i++) {	
				HashSet<String> set = new HashSet<>();	
				int length = fileindex.get(i).getRight();
				byte[] file = new byte[(int) length];
				raf.read(file);
				String str = new String(file);				
				String[] array = str.split(" ");
				List<String> tmp = Arrays.asList(array);
				set.addAll(tmp);
				list.add(set);
			}						
			
			raf.close();
				
			for(int i = 0; i < list.size() - 1; i++) {
				for(int j = i + 1; j < list.size(); j++) {
					HashSet<String> exist = new HashSet<>();
					Similarities simi = new Similarities(fileindex.get(i).getLeft(), 
							fileindex.get(j).getLeft());
					treeSet.add(simi);
					for(String s: list.get(i)) {
						if(list.get(j).contains(s)) {
							if(exist.contains(s)) {
								continue;
							}
							else {
								exist.add(s);
								simi.setCount(simi.getCount() + 1);
							}						
						}
					}		
				}
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return treeSet;
	}

	@Override
	public void printSimilarities(TreeSet<Similarities> sims, int threshold) {
		
		if(sims == null || sims.size() == 0) {
			System.out.println("Your input is not valid.");
			throw new IllegalArgumentException();				
		}
		if(threshold < 0) {
			System.out.println("Your input is not valid.");
			throw new IllegalArgumentException();
		}		
		
		Comparator<Similarities> comp = 
				new Comparator<Similarities>() {
			@Override
			public int compare(Similarities s1, Similarities s2) {
				return s2.getCount() - s1.getCount();
			}
		};
		
		TreeSet<Similarities> finalSet = new TreeSet<>(comp);
		for(Similarities simi: sims) {
        	if(simi.getCount() > threshold) {
        		finalSet.add(simi);		
        	}
        }
		
        for(Similarities simi: finalSet) {
        	System.out.println(simi.getFile1() + " " + simi.getFile2() + " " + simi.getCount());
        }       
	}

	@Override
	public List<Tuple<String, Integer>> processAndStore(String directoryPath, String sequenceFile, int n) {
		List<Tuple<String, Integer>> listTuples = new ArrayList<>();
		try {
			if(directoryPath == null || directoryPath.length() == 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			if(sequenceFile == null || sequenceFile.length() == 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			if(n <= 0) {
				System.out.println("Your input is not valid.");
				throw new IllegalArgumentException();
			}
			List<String> fileList = new ArrayList<String>();					
			
			File folder = new File(directoryPath);
			File[] listOfFiles = folder.listFiles(new FilenameFilter() {
		        @Override
		        public boolean accept(File dir, String name) {
		            return !name.equals(".DS_Store");
		        }
		    });
			
			File a_file = new File(sequenceFile);
			FileWriter fw = new FileWriter(a_file);			

			for(File file: listOfFiles) {			
				fileList.add(file.getName());				
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				DocumentIterator iter = new DocumentIterator(br, n);
				int sum = 0;
				while (iter.hasNext()) {
					String wordSeq = iter.next();
					if(!wordSeq.equals("")) {
						fw.write(wordSeq.toLowerCase());
						fw.write(" ");
						int countByte = wordSeq.length() + 1;
						sum += countByte;
					}						
				}
				listTuples.add(new Tuple<String, Integer>(file.getName(), sum));
				br.close();
				fr.close();
			}
			fw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (NoSuchElementException e) {
	        System.out.println("IO error from closing reader.");
	    } catch (IOException e) {
			e.printStackTrace();
		}
		
		return listTuples;
	}	
}
