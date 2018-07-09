import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Report {
     int countd, counti,countad,countnad,countai,countnai,tcounta,tcountn,crire,crre;    //  for report, counters of lines
     int sizerdn, sizerin, sizerng,sizerna,sizernn,crdre,sizerdom,sizerint,sizerre;      // for setting size of JtextField in report panel,because it dependence of result after reading and and data
     String sdcount, sicount,srng,stcounta,stcountn,srdre,srire,srre;                    
	public void numguestDom() {
		 // The number of domestic guests
	    File repdomdata = new File("Domestic.csv");
	    BufferedReader bufferedReaderrdom;                                                // make constructor of buffered reader for counting lines in files, one line = one guest
		try {
			bufferedReaderrdom = new BufferedReader(new FileReader(repdomdata));
		 countd = 0;                                                                      // line counter
	     while((bufferedReaderrdom.readLine()) != null)
	     {
	         countd++;                                                                    // line counter incremented, work if line not null
	     }
	     sdcount = Integer.toString(countd);                                       // convert counter to String, for showing in JTextField
	     sizerdn = sdcount.length();                                                      // get length of  counter, for making JTextField same size as counter
                                                                                 // showing number of domestic guests
	     System.out.println("Count dom: "+countd);                                        // for mistake checking
		} catch (FileNotFoundException e1) {
			System.out.print("report, domestic file not found");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Can not read the file");
			e1.printStackTrace();
		}
}
	// The number of international guests
		public void numguestInt(){	
	        File repintdata = new File("International.csv");
		    BufferedReader bufferedReaderint;
			try {
				bufferedReaderint = new BufferedReader(new FileReader(repintdata));
			
		     counti = 0;
		     while((bufferedReaderint.readLine()) != null)
		     {
		         counti++;
		     }
		     sicount = Integer.toString(counti);
		     sizerin = sicount.length();
	           
		     System.out.println("Count int : "+counti);
			} catch (FileNotFoundException e1) {
			   System.out.println("report, numbers, international, file not found");
				e1.printStackTrace();
			} catch (IOException e1) {
			    System.out.println("report, numbers, int, cant read file");
				e1.printStackTrace();
			}
		}
			// Total numbers
	    public void totalNum(){
			int trng = countd + counti;                                                          //  sum counters   //go to sleep, continue tmw
			srng = Integer.toString(trng);                                                //converting  to String for showing in JText field
			sizerng = srng.length();   
	}
	    public void numAttend(){
	    	// Number of attending
			/////////////////////////////////////////////////
			// get number of attending and non attending in domestic file
			File numdomdata = new File("Domestic.csv");                                          // set file for reading
		    BufferedReader bufferedReaderradom;
			try {
				bufferedReaderradom = new BufferedReader(new FileReader(numdomdata));            //initiate new buffered reader for count number of attending
			 String linead;
		     countad = 0;                                                                        // variable for count attending (yes)
		     countnad = 0;                                                                       // variable for count non-attending (no)
		     while((linead =bufferedReaderradom.readLine()) != null)                             // start reading the file
		     {
		        if(linead.contains("YES")){                                                      // count lines with YES
		        		countad++;                                                               // counter increase if line have YES
		     }
		        if (linead.contains("NO")){                                                      /// count lines with NO
		        	 countnad++;                                                                 // counter if line have NO
		        }
		     }
		      System.out.println("Count adom: "+countad + "Count nadom " + countnad);            // for mistakes checking
			} catch (FileNotFoundException e1) {
				System.out.println("Report, attending, domestic file not found");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("Report, attending, domestic can not read the file");
				e1.printStackTrace();
			}
	    
         //////////////////////////////////////////////////////////////////////////
         // get attending and non- attending numbers from international file
         File numintdata = new File("International.csv");
         BufferedReader bufferedReaderraint;
         try {
         bufferedReaderraint = new BufferedReader(new FileReader(numintdata));
         String lineai;
         countai = 0;
         countnai = 0;
         while((lineai =bufferedReaderraint.readLine()) != null) 
         {
         if(lineai.contains("YES")){
         countai++;
         }
         if (lineai.contains("NO")){
         countnai++;
         }
         }
         System.out.println("Count aint: "+countai + "Count naint " + countnai);
         } catch (FileNotFoundException e1) {
         System.out.println("Report, attending, international file not found");
         e1.printStackTrace();
         } catch (IOException e1) {
         System.out.println("Report, attending, international can not read the file");
         e1.printStackTrace();
          }
	    
        //////////////////////////////////////////////////////////////////////////////////////////
        // For total  count of attending
        tcounta = countad + countai;                                      // count total  attending form both files
        stcounta = Integer.toString(tcounta);                             // convert to string for text field                                              // setting text for field
        sizerna = stcounta.length();                                      // get same size for field
        // For total count of not-attending
        tcountn = countnad + countnai;
        stcountn = Integer.toString(tcountn);
        sizernn = stcountn.length();
/////////////////////////////////////////////////
	    }
	    public void revenue(){
	    	// Revenue for domestic
			crdre = countd * 50;                                          // multiple counter for get revenue
			srdre = Integer.toString(crdre);
			                                      // set value with symbol $
			sizerdom = srdre.length();
			/////////////////////////////////////////////////
			// Revenue for international
		    crire = counti * 150;                                         // count revenue for international 
		    srire = Integer.toString(crire);
			sizerint = srire.length();
			/////////////////////////////////////////////////
		    // Total revenue
		    crre = crdre + crire;
		    srre = Integer.toString(crre);
		    sizerre = srre.length();
	    }
}



