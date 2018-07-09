import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVFileDomestic {
	        private final ArrayList<String[]> Rs = new ArrayList<String[]>();                  // for store data from csv
	        private String[] OneRow;

	        public ArrayList<String[]> ReadCSVfile(File DataFile) {                            // main metod for get data from csv file
	            try {
	                BufferedReader brd = new BufferedReader(new FileReader(DataFile)); 
	                while (brd.ready()) {
	                    String st = brd.readLine();
	                    OneRow = st.split(",");                                                 // determiner
	                    Rs.add(OneRow);
	                    System.out.println(Arrays.toString(OneRow));
	                } // end of while
	                brd.close();
	            } // end of try
	            catch (Exception e) {
	                String errmsg = e.getMessage();
	                System.out.println("File not found:");
	            } // end of Catch
	            return Rs;
	        }// end of ReadFile method
	      
	        
	    }// end of CSVFile class
    