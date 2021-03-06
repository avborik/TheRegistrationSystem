import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVFileInternational {
	private final ArrayList<String[]> RsI = new ArrayList<String[]>();
    private String[] OneRow;

    public ArrayList<String[]> ReadCSVfile(File DataFile) {
        try {
            BufferedReader brd = new BufferedReader(new FileReader(DataFile));
            while (brd.ready()) {
                String st = brd.readLine();
                OneRow = st.split(",");
                RsI.add(OneRow);
                System.out.println(Arrays.toString(OneRow));
            } // end of while
            brd.close();
        } // end of try
        catch (Exception e) {
            String errmsg = e.getMessage();
            System.out.println("File not found:");
        } // end of Catch
        return RsI;
    }// end of ReadFile method
}// end of CSVFile class