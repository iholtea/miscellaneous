package ionuth.hackrank.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestIO {
	
	public static void readFileBufferReader() {
		
		try {
			InputStream inputStream = TestIO.class.getClassLoader()
					.getResourceAsStream("result09-tree-01.in");
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String fileLine = "";
			List<String> lines = new ArrayList<String>();
			while( (fileLine=br.readLine())!=null ) {
				lines.add(fileLine);
			}
			lines.forEach(System.out::println);
		}
		catch(IOException ex) {
			System.err.println(ex);
		}
		
	}
	
	public static void readFileUrl() {
		try {
			URL inputUrl = TestIO.class.getClassLoader().getResource("result09-tree-01.in");
			Path inputPath = Paths.get(inputUrl.toURI());
			List<String> lines = Files.lines(inputPath).collect(Collectors.toList());
			lines.forEach(System.out::println);
		} catch(URISyntaxException | IOException ex) {
			System.err.println(ex);
		}
	}
	
	public static void readLineConsoleBuff() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		int n = 1;
		try {
			while( !(line=br.readLine()).equals("") ) {
				System.out.println("Read line " + n + " : " + line );
				n++;
			}
		} catch(IOException ex) {
			System.err.println(ex);
		}
	}
	
	public static void readConsoleScanner() {
		 Scanner in = new Scanner(System.in);
		 int n = in.nextInt();
         int m = in.nextInt();
         System.out.println("n and m values: " + n + " " + m);
         String str = in.next();
         System.out.println("read string: " + str);
         int[] arr = new int[n];
         for(int i=0; i<n; i++) {
        	 arr[i] = in.nextInt();
         }
         Stream.of(arr).forEach(nr -> System.out.print(nr + " "));
         in.close();
          
	}
	
	public static void main(String[] args) {
		
		//readFileBufferReader();
		//readFileUrl();
		//readLineConsoleBuff();
		readConsoleScanner();
		
	}
	
}
