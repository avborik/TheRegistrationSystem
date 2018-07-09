import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;  //- file manipulation
import java.util.ArrayList;
import java.util.Random;

 public class RegistrationSystem {
 // global Variables
 private JFrame f;
 private CardLayout cl;
 private JPanel cp, lp, mp, rp, dp, dip, dsp,ddp,sp,repp;
 private JLabel labLogin, labUser, labPass, labMain, labReg, labDisp, labDispi, labDisps, labdeldom;
 private JLabel labfname, labsname, labdob, labgender, labemail, labaddres, labnum, labattend,labsfname,labssname,labsdob,labsgender,
 labsemail,labsaddress,labsnum,labsattend,labsnat,labstop,labsdon;
 private JButton bLogin,brsave,brcancel,bddel,bsdom,bdint,bdupdom,bdupint;
 private JTextField rnat, rtop, rdon,rtype,did,sdom,dint,sfname,ssname,sdob,sgender,semail,saddress,snum,sattend,snat,stop,sdon;
 private JTextField tfUser,rfname,rsname,rdob,rgender,remail,raddres,rnum,rattend,rng,rdn,rin,rna,rnn,rre,rdre,rire;
 private JTable ddisp, ddispi, ddisps;
 private JLabel labnat, labtop, labdon,labtype,labsdom,labdint,labrng,labrdn,labrin,labrna,labrnn,labre,labdre,labire;
 private JPasswordField pfPass;
 private JMenuBar jmb;
 private JCheckBox chspeakerdom, chspeakerint;                                                   // for choosing of safe speakers while safe guests function work
 private int id;                                                                                 // ID generation variable
 private String s;                                                                               // new guest flag saver switcher  (if s= D , program will save to domestic file, if I, it save to international and etc
 private String ss;                                                                              // same, but for Searching
                                                           
              // for setting size of JtextField in report panel,because it dependence of result after reading and and data
 Font sFont = new Font("", Font.BOLD, 15);
 Font tFont = new Font("", Font.BOLD, 20);
 Font tfFont = new Font("", 14, 14);

///////////////////////////////

 // Main Constructor
 public RegistrationSystem(){                                                                      // start, stop points and all pages is here
     gui();
     loginScreen();
     mainPanel();
     regPanel();
     searchGuests();
     deletePanel();
     dispDomPanel();
     dispIntPanel();
     dispSpkPanel();
     reportPanel();
   }
  //////////////////////////
  // Graphical user interface class
 public void gui() {
	// setting font for all option pane messages
	 UIManager.put("OptionPane.messageFont", new FontUIResource(sFont));
	// Main Frame  
  f = new JFrame ("Conference registration system");
  f.setSize(800,600);
  f.setLocationRelativeTo(null);
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //////////////////////////////////////////////////
   //Menu Bar
  jmb = new JMenuBar();
  JMenu jmfile = new JMenu("File");
  jmfile.setFont(sFont);
  JMenuItem Exit = new JMenuItem("Exit");
  Exit.setFont(sFont);
  jmfile.addSeparator();
  jmfile.add(Exit);
  jmb.add(jmfile);
  ///////////////////////////////////////
  JMenu jmguest = new JMenu("New guest");
  jmguest.setFont(sFont);
  JMenuItem jmdom = new JMenuItem("Add domestic");
  jmdom.setFont(sFont);
  JMenuItem jmint = new JMenuItem("Add international");
  jmint.setFont(sFont);
  jmguest.add(jmdom);
  jmguest.add(jmint);
  jmb.add(jmguest);
  ////////////////////////////////////////////////////////
  JMenu jmOptions = new JMenu("Search Options");
  jmOptions.setFont(sFont);
  JMenuItem sdom = new JMenuItem("Search Domestic guest ");
  sdom.setFont(sFont);
  JMenuItem sint = new JMenuItem("Search International guest");
  sint.setFont(sFont);
  JMenuItem ssp = new JMenuItem("Search Speaker");
  ssp.setFont(sFont);
  jmOptions.add(sdom);
  jmOptions.add(sint);
  jmOptions.add(ssp);
  jmb.add(jmOptions);
  /////////////////////////////////////////////////////////////
  JMenu dopt = new JMenu("Delete options");
  dopt.setFont(sFont);
  JMenuItem dgd = new JMenuItem("Delete guest");
  dgd.setFont(sFont);
  dopt.add(dgd);
  jmOptions.add(dopt);
  jmb.add(dopt);
  ///////////////////////////////////////////////////////////
  JMenu jmdisp = new JMenu("Display and update options");
  jmdisp.setFont(sFont);
  JMenuItem dlog = new JMenuItem("Display domestic guests");
  dlog.setFont(sFont);
  JMenuItem dlig = new JMenuItem("Display international guests");
  dlig.setFont(sFont);
  JMenuItem dlsg = new JMenuItem("Display speakers");
  dlsg.setFont(sFont);
  jmdisp.add(dlog);
  jmdisp.add(dlig);
  jmdisp.add(dlsg);
  jmb.add(jmdisp);
  ///////////////////////////////////////////////////////////////
  JMenu jmrep = new JMenu("Report");
  jmrep.setFont(sFont);
  JMenuItem mtr = new JMenuItem("Make the report");
  mtr.setFont(sFont);
  jmrep.add(mtr);
  jmb.add(jmrep);
  /////////////////////////////////////////////////////////////////
  JMenu jmHelp = new JMenu("Help");
  jmHelp.setFont(sFont);
  JMenuItem jmiAbout = new JMenuItem("About");
  jmiAbout.setFont(sFont);
  jmHelp.add(jmiAbout);
  jmb.add(jmHelp);
  f.setJMenuBar(jmb);
  //////////////MENU BUTTON ACTION  ///////////////////
  //////////////Exit button action/////////////////////
  Exit.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent e){
		System.exit(-5);
		   }
  });
  ////////////////////////////////////////////////
  //New Guest/add domestic button action///////////////////////
  jmdom.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
	    cl.show(cp, "regPanel");
	    rtype.setText("Domestic");                                             //set domestic type
	    rtype.setEditable(false);                                              // set type not editable
	    rnat.setVisible(false);                                                // set invisible field and labels from others types
	    labnat.setVisible(false);
	    rtop.setVisible(false);
	    labtop.setVisible(false);
	    rdon.setVisible(false);
	    labdon.setVisible(false);
	    s = "D";                                                               // set flag s to D for saving  into domestic file
	    rnat.setText("New Zealander");                                         // default set for domestic guests
	    rdon.setText("D");            
	    chspeakerdom.setSelected(false);                                       // set unselected in default
	    chspeakerdom.setVisible(true);                                         // show domestic check box
	    chspeakerint.setVisible(false);                                        // hide international check box, not needed for domestic 
	   } 
  });
  //////////////////////////////////////////////////
  //New Guest/add International button action
  jmint.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
	    cl.show(cp, "regPanel");
	    rtype.setText("International");
	    rtype.setEditable(false);
	    rtop.setVisible(false);
	    labtop.setVisible(false);
	    rdon.setVisible(false);
	    labdon.setVisible(false);
	    rnat.setVisible(true);
	    labnat.setVisible(true);
	    rnat.setText(null);                                                    // to delete text in this field, because if domestic was before, this field will be set as New Zealander
	    s = "I";   
	    rdon.setText("I");
	    chspeakerdom.setVisible(false);
	    chspeakerdom.setSelected(false);
	    chspeakerint.setVisible(true);
	    chspeakerint.setSelected(false);
	   }
 });
  ////////////////////////////////////////////////////
  //Search Domestic menu item action
  sdom.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
	    ss = "D";                                                              // another flag for search function
		cl.show(cp, "searchGuests");
	    snat.setVisible(false);                                                // hide fields, it is not needed for domestic
   	    stop.setVisible(false);
   	    sdon.setVisible(false);
   	    labsnat.setVisible(false);
   	    labstop.setVisible(false);
   	    labsdon.setVisible(false);
	   }
  });
///////////////////////////////////////////////////////
// Search International menu item function
  sint.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent e){
	    ss = "I";
		cl.show(cp, "searchGuests");
	    snat.setVisible(true);
 	    stop.setVisible(false);
 	    sdon.setVisible(false);
 	    labsnat.setVisible(true);
 	    labstop.setVisible(false);
 	    labsdon.setVisible(false);
	   }
});
 ///////////////////////////////////////////////////////
 // Search Speaker menu item function
  ssp.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent e){
		    ss = "S";
			cl.show(cp, "searchGuests");
		    snat.setVisible(false);
	 	    stop.setVisible(true);
	 	    sdon.setVisible(true);
	 	    labsnat.setVisible(false);
	 	    labstop.setVisible(true);
	 	    labsdon.setVisible(true);
		   }
	});
  ///////////////////////////////////////////////////
  // delete guest information button action
  dgd.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
	    cl.show(cp, "deletePanel");
	   }
   });
  ///////////////////////////////////////////////////
  // Display list of domestic guest button action
  dlog.addActionListener(new ActionListener (){
	  
	  public void actionPerformed(ActionEvent e){
		  CSVFileDomestic Rd = new CSVFileDomestic();                                       // new constructor of reading csv file method
			 ddispmodel ddispm = new ddispmodel();                                          // new constructor of abstract table model
		        ddisp.setModel(ddispm);                                                     // setting abstract table model for table
		        File DataFile = new File("Domestic.csv");                                   // choosing file for reading
		        ArrayList<String[]> Rs2 = Rd.ReadCSVfile(DataFile);                         // making new array list which store data from csv file, because jtable can work with files and can store data, only show from array
		        ddispm.AddCSVData(Rs2);                                                     // add data to table from array list
		        System.out.println("Rows: " + ddispm.getRowCount());
		        System.out.println("Cols: " + ddispm.getColumnCount());
		        // setting preferred width of column in table
		        ddisp.getColumnModel().getColumn(0).setPreferredWidth(100);
		        ddisp.getColumnModel().getColumn(1).setPreferredWidth(100);
		        ddisp.getColumnModel().getColumn(2).setPreferredWidth(130);
		        ddisp.getColumnModel().getColumn(3).setPreferredWidth(150);
		        ddisp.getColumnModel().getColumn(4).setPreferredWidth(180);
		        ddisp.getColumnModel().getColumn(5).setPreferredWidth(250);
		        ddisp.getColumnModel().getColumn(6).setPreferredWidth(120);
		        ddisp.getColumnModel().getColumn(7).setPreferredWidth(75);
		        ddisp.getColumnModel().getColumn(8).setPreferredWidth(45);
		        ddisp.getTableHeader().setFont(sFont);                                         //set preferred font for header of table
		        cl.show(cp, "dispDomPanel");
	  }
  });
  ///////////////////////////////////////////////////
  // Display list of international guest button action
  dlig.addActionListener(new ActionListener (){
	  
	  public void actionPerformed(ActionEvent e){
		  CSVFileInternational RdI = new CSVFileInternational();
			 ddispmodelI ddispmi = new ddispmodelI();
		        ddispi.setModel(ddispmi);
		        File DataFileI = new File("International.csv");
		        ArrayList<String[]> Rs2I = RdI.ReadCSVfile(DataFileI);
		        ddispmi.AddCSVData(Rs2I);
		        System.out.println("Rows: " + ddispmi.getRowCount());
		        System.out.println("Cols: " + ddispmi.getColumnCount());
		        ddispi.getColumnModel().getColumn(0).setPreferredWidth(100);
		        ddispi.getColumnModel().getColumn(1).setPreferredWidth(100);
		        ddispi.getColumnModel().getColumn(2).setPreferredWidth(130);
		        ddispi.getColumnModel().getColumn(3).setPreferredWidth(150);
		        ddispi.getColumnModel().getColumn(4).setPreferredWidth(180);
		        ddispi.getColumnModel().getColumn(5).setPreferredWidth(250);
		        ddispi.getColumnModel().getColumn(6).setPreferredWidth(130);
		        ddispi.getColumnModel().getColumn(7).setPreferredWidth(75);
		        ddispi.getColumnModel().getColumn(8).setPreferredWidth(100);
		        ddispi.getColumnModel().getColumn(9).setPreferredWidth(45);
		        ddispi.getTableHeader().setFont(sFont);
		        cl.show(cp, "dispIntPanel");
		 }
  });
/////////////////////////////////////////////////////////////////////////////////////////////
////Display Speaker information
  dlsg.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
		   CSVFileDomestic RdS = new CSVFileDomestic();
			 ddispmodelS ddispmS = new ddispmodelS();
		        ddisps.setModel(ddispmS);
		        File DataFile = new File("Speaker.csv");
		        ArrayList<String[]> Rs2S = RdS.ReadCSVfile(DataFile);
		        ddispmS.AddCSVData(Rs2S);
		        ddisps.removeColumn(ddisps.getColumnModel().getColumn(2));                      // hide column, which do not need be shown
		        ddisps.removeColumn(ddisps.getColumnModel().getColumn(3));
		        ddisps.removeColumn(ddisps.getColumnModel().getColumn(4));
		        ddisps.removeColumn(ddisps.getColumnModel().getColumn(7));
		        ddisps.removeColumn(ddisps.getColumnModel().getColumn(4));
		        ddisps.removeColumn(ddisps.getColumnModel().getColumn(3));
		        ddisps.removeColumn(ddisps.getColumnModel().getColumn(2));
		        ddisps.removeColumn(ddisps.getColumnModel().getColumn(4));
		        ddisps.getTableHeader().setFont(sFont);
		        System.out.println("Rows: " + ddispmS.getRowCount());
		        System.out.println("Cols: " + ddispmS.getColumnCount());
		 cl.show(cp, "dispSpkPanel");
	   }
 });
///////////////////////////////////////////////////////////////////////////////////////////////////
  //Make the report button action
  mtr.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
	    cl.show(cp, "reportPanel");
	    ///////////////////////////////////////////
	    Report r = new Report();
	    r.numguestDom();
	    rdn.setText(r.sdcount);
	    r.numguestInt();
	    rin.setText(r.sicount);
	    r.totalNum();
	    rng.setText(r.srng);
		r.numAttend();
		rna.setText(r.stcounta);
		rnn.setText(r.stcountn);
		r.revenue();
		rdre.setText(r.srdre+"$NZD");
		rire.setText(r.srire+"$NZD");
		rre.setText(r.srre+"$NZD");
		
	   }
  });
  ////////////////////////////////////////////////////////
  //About button action
  jmiAbout.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
		
		   String message = "Welcome to The Guest Registration system ver2.4\n "
			+ "For exit click File-Exit\n For add new guest, go to New guest menu and choice one of two sub-menu(Add Domestic or Add International)\n"
			+ "For search guest information, go to Searching option in menu and choice one of Tree sub-menu(Search Domestic,\nSearch International or Search speaker bar\n"
			+ "To delete guests, go to delete options in menu bar an choice between Delete Domestic and Delete International only\n"
			+ "To display list of guests, go to Display option and choice one of three sub-menu(Display Domestic, Display International \nand Display Speaker) In additon,"
			+ "\n Domestic and International tables can be updated(just click on cell and update it, and then click on update button\n"
			+ "For make report, go to Report menu\n "
			+ "User name is Admin and User password is Admin";
            JOptionPane.showMessageDialog(null, message );
  }
  });
  /////////////////////////////////////////////////////
  //Main Layout
  cl = new CardLayout();
  cp = new JPanel();
  new JLabel("God");
  //cp.add(labGod);
   cp.setLayout(cl);
   cl.show(cp, "contentPanel");
  ///////////////////////////////
  //final visual function
  f.add(cp);
  f.setVisible (true);
  jmb.setVisible(false);                                           // make this pane only for card layout
 } 
 ////////////////////////////////////
 //Part 1 Login process
 public void loginScreen() {
  lp = new JPanel(new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  lp.setBackground(Color.LIGHT_GRAY);
  
  labLogin = new JLabel("Unitec International Conference Login");
  labLogin.setFont(new Font("",Font.BOLD, 20));
  labUser = new JLabel("Username: ");
  labUser.setFont(sFont);
  labPass = new JLabel("Password: ");
  labPass.setFont(sFont);
  bLogin = new JButton("Login");
  bLogin.setFont(sFont);
  tfUser = new JTextField(10);
  tfUser.setFont(tfFont);
  pfPass = new JPasswordField(10);
 
  c.anchor = GridBagConstraints.LINE_END;
  c.gridx = 0;
  c.gridy = 1;
  lp.add(labUser,c);
  
  c.gridy ++;
  lp.add(labPass,c);
  
  c.anchor = GridBagConstraints.LINE_START;
  c.gridx =1;
  c.gridy = 1;
  lp.add(tfUser, c);
  
  c.gridy ++;
  lp.add(pfPass, c);
  
  c.gridx = 0;
  c.gridy ++;
  lp.add(bLogin,c);
  
  c.anchor = GridBagConstraints.CENTER;
  c.fill = GridBagConstraints.HORIZONTAL;
  c.gridwidth = 2;
  c.gridx = 0;
  c.gridy = 0;
  lp.add(labLogin,c);
  ////////////////////////////////////////////
  // Login Button option
  bLogin.addActionListener(new ActionListener(){
  
    public void actionPerformed(ActionEvent e){
    String usernameCheck = tfUser.getText().trim();
    char[] pass = pfPass.getPassword();
    String passwordCheck = new String(pass);
    if(usernameCheck.equals("admin") && passwordCheck.equals("admin")){
     cl.show(cp, "mainPanel");
     jmb.setVisible(true);
     JOptionPane.showMessageDialog(null, "Successfully logged in.");
     
    }else{
    	if(!usernameCheck.equals("admin")){
     JOptionPane.showMessageDialog(null, "Incorrect Username.");
    	}else if(!passwordCheck.equalsIgnoreCase("admin")){
    		JOptionPane.showMessageDialog(null, "Incorrect Password");
    	}
    }
   }
  });
  cp.add(lp, "loginPanel");
}
 //////////////////////////////////////////////////////////////////
 // Main Panel method, with card layout for all panel 
 public void mainPanel(){
  mp = new JPanel( new GridBagLayout());
  GridBagConstraints c = new GridBagConstraints();
  mp.setBackground(Color.LIGHT_GRAY);
// add picture on panel  
  try {
	BufferedImage img = ImageIO.read(new File("BK.png"));    // set picture on panel
	labMain = new JLabel(new ImageIcon(img));
} catch (IOException e) {
  System.out.println("the picture not found");
	e.printStackTrace();
}
  c.gridy = 0;
  c.gridx = 0;
  mp.add(labMain,c);
  cp.add(mp, "mainPanel");
  }
///////////////////////////////////////////////////////////
// Part 2.1 Registering guest, get guest data
 public void regPanel(){
   rp = new JPanel(new GridBagLayout());
   GridBagConstraints c = new GridBagConstraints();
   rp.setBackground(Color.LIGHT_GRAY);
   cp.add(rp, "RegPanel");
   labReg = new JLabel("Enter the guest registration information");
   labReg.setFont(tFont);
   rtype = new JTextField(15);
   rtype.setFont(sFont);
   rfname = new JTextField(15);
   rfname.setFont(tfFont);
   rsname = new JTextField(15);
   rsname.setFont(tfFont);
   rdob = new JTextField(15);
   rdob.setFont(tfFont);
   rsname.setFont(tfFont);
   rgender = new JTextField(15);
   rgender.setFont(tfFont);
   remail = new JTextField(15);
   remail.setFont(tfFont);
   raddres = new JTextField(15);
   raddres.setFont(tfFont);
   rnum = new JTextField(11);
   rnum.setFont(tfFont);
   rattend = new JTextField(3);
   rattend.setFont(tfFont);
   rnat = new JTextField(10);
   rnat.setFont(tfFont);
   rtop = new JTextField(10);
   rtop.setFont(tfFont);
   rdon = new JTextField(12);
   rdon.setFont(tfFont);
   labtype = new JLabel("Type: ");
   labtype.setFont(sFont);
   labfname = new JLabel("Enter First Name: ");
   labfname.setFont(sFont);
   labsname = new JLabel("Enter Second Name: ");
   labsname.setFont(sFont);
   labdob = new JLabel("Enter Affiliation: ");
   labdob.setFont(sFont);
   labgender = new JLabel("Enter Position: ");
   labgender.setFont(sFont);
   labemail = new JLabel("Enter the Email Address: ");
   labemail.setFont(sFont);
   labaddres = new JLabel("Enter Address: ");
   labaddres.setFont(sFont);
   labnum = new JLabel("Enter the Contact number: ");
   labnum.setFont(sFont);
   labattend = new JLabel("Enter attending information: ");
   labattend.setFont(sFont);
   labnat = new JLabel("Enter the Nationality");
   labnat.setFont(sFont);
   labtop = new JLabel("Enter the Topic of speech");
   labtop.setFont(sFont);
   labdon = new JLabel ("Domestic(D) or International(I)?");
   labdon.setFont(sFont);
   brsave = new JButton("Save");
   brsave.setFont(sFont);
   brcancel = new JButton("Cancel");
   brcancel.setFont(sFont);
   chspeakerdom = new JCheckBox("Speaker");
   chspeakerdom.setFont(sFont);
   chspeakerdom.setBackground(Color.LIGHT_GRAY);
   chspeakerint = new JCheckBox("Speaker");
   chspeakerint.setFont(sFont);
   chspeakerint.setBackground(Color.LIGHT_GRAY);
   
   c.anchor = GridBagConstraints.LAST_LINE_START;
   c.gridy = 13;
   rp.add(brcancel, c);
   
   c.anchor = GridBagConstraints.LAST_LINE_END;
   c.gridx = 1;
   c.gridy = 13;
   rp.add(brsave, c);
   
   c.anchor = GridBagConstraints.LINE_END;
   c.fill = GridBagConstraints.HORIZONTAL;
   c.gridx = 0;
   c.gridy = 1;
   rp.add(labtype, c);
   
   c.gridy ++;
   rp.add(labfname, c);
   
   c.gridy ++;
   rp.add(labsname, c);
   
   c.gridy ++;
   rp.add(labdob, c);
   
   c.gridy ++;
   rp.add(labgender, c);
   
   c.gridy ++;
   rp.add(labemail, c);
   
   c.gridy ++;
   rp.add(labaddres, c);
   
   c.gridy ++;
   rp.add(labnum, c);
   
   c.gridy ++;
   rp.add(labattend, c);
   
   c.gridy ++;
   rp.add(labnat, c);
   
   c.gridy ++;
   rp.add(labtop, c);
   
   c.gridy ++;
   rp.add(labdon , c);
 
   c.anchor = GridBagConstraints.LINE_START;
   c.fill = GridBagConstraints.HORIZONTAL;
   c.gridx = 1;
   c.gridy = 1;
   rp.add(rtype, c);
   
   c.gridy ++;
   rp.add(rfname, c);
   
   c.gridy ++;
   rp.add(rsname, c);
   
   c.gridy ++;
   rp.add(rdob, c);
   
   c.gridy ++;
   rp.add(rgender, c);
   
   c.gridy ++;
   rp.add(remail, c);
   
   c.gridy ++;
   rp.add(raddres, c);
   
   c.gridy ++;
   rp.add(rnum, c);
   
   c.gridy ++;
   rp.add(rattend, c);
   
   c.gridy ++;
   rp.add(rnat, c);
   
   c.gridy ++;
   rp.add(rtop, c);
   
   c.gridy ++;
   rp.add(rdon, c);
   
   c.anchor = GridBagConstraints.PAGE_START;
   c.fill = GridBagConstraints.HORIZONTAL;
   c.gridwidth = 2 ;
   c.gridx = 0;
   c.gridy = 0;
   c.weightx = 0.0;
   c.weighty = 0.0;
   rp.add(labReg, c);
   
   c.anchor = GridBagConstraints.EAST;
   c.gridx = 2;
   c.gridy = 1;
   c.insets = new Insets (0,10,0,0);
   rp.add(chspeakerdom, c);
   
   c.anchor = GridBagConstraints.EAST;
   c.gridx = 2;
   c.gridy = 1;
   c.insets = new Insets (0,10,0,0);
   rp.add(chspeakerint, c);
//////////////////////////////////////////////////
   // CheckBoxs function
   chspeakerdom.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent event) {
	        JCheckBox chspeakerdom = (JCheckBox) event.getSource();
	        if (chspeakerdom.isSelected()) {
	        	labtop.setVisible(true);                                                // show field for speaker
	            rtop.setVisible(true);
	            s = "SD";                                                               // set flag for show file writer to write into domestic and speaker file
	        } else {
	        	labtop.setVisible(false);                                               // hide field for speaker
	            rtop.setVisible(false); 
	        }
	    }
	});
  // check box for international speaker
   chspeakerint.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent event) {
	        JCheckBox chspeakerint = (JCheckBox) event.getSource();
	        if (chspeakerint.isSelected()) {
	        	labtop.setVisible(true);
	            rtop.setVisible(true);
	            s = "SI";
	        } else {
	        	labtop.setVisible(false);
	            rtop.setVisible(false);        
	        }
	    }
	});

/////// Registration panel button actions/////////  
// Cancel button action   
   cp.add(rp, "regPanel");
brcancel.addActionListener(new ActionListener(){
	   
	   
	   public void actionPerformed(ActionEvent e){
		   rfname.setText(null);                                                             // just set text of fields null, for convenient re-editing
		   rsname.setText(null);
		   rdob.setText(null);
		   rgender.setText(null);
		   remail.setText(null);
		   raddres.setText(null);
		   rnum.setText(null);
		   rattend.setText(null);
		   rnat.setText(null);
		   rtop.setText(null);
		   rdon.setText(null);
		    }
	   });
////////////////////////////////////////////////////
// Part 2.2 Guest registration, input validation
// Save button action (it has two part:save into scv files 
// and input validation from JTextFiles
brsave.addActionListener(new ActionListener(){
	   
	   
	   public void actionPerformed(ActionEvent e){
	// Input validation//////////////////////////
           IDGen();
		   String srfname = rfname.getText().trim();                                            // convert JTextFields to String
		   String srsname = rsname.getText().trim();
		   String srdob = rdob.getText().trim();                                                // date also will convert to string, 
		   String srgender = rgender.getText().trim().toUpperCase();
		   String sremail = remail.getText().trim();                                            // in email address I did not restrict the length, because maximum size is 254 characters
                                                                                                // and I restrict characters,input only A-Z, a-z -_ @
		   String sraddres = raddres.getText().trim();
		   String srnum = rnum.getText().trim();
		   String srattend = rattend.getText().trim().toUpperCase();                            // make capital letters for best searching
		   String srnat = rnat.getText().trim();                                                // convert to String for validation
		   String srtop = rtop.getText().trim();                                                // convert to String just for saving
		   String srdon = rdon.getText().trim().toUpperCase();                                  // make String for validation 
    /////////////////////////////////////////////////////////////
	// Firstname text field validation	   
		   if(!srfname.matches("[A-Za-z]{2,}")){    // validate input, only string, length
		   
			   rfname.setText(null);             // if wrong, set field empty
			   JOptionPane.showMessageDialog(f,     // print error message
					    "Firstname must contain only characters, and must contain 2 or more characters",   
					    srfname, JOptionPane.ERROR_MESSAGE);
		   } 
   /////////////////////////////////////////////////////////////////////////
   // Secondname text field validation
		   else if(!srsname.matches("[A-Za-z]{2,}")){    // validate input, only string, length
			   
			   rsname.setText(null);             // if wrong, set field empty
			   JOptionPane.showMessageDialog(f,     // print error message
					    "Secondname must contain only characters,and must be more than 2 characters",   
					    srsname, JOptionPane.ERROR_MESSAGE);
		   }
  //////////////////////////////////////////////////////////////////////////////
  // Date of Birth text field validation //   Instruction update Affiliation
		   /*else if (!(srdob.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)"))) {      //validate input to only digits and only forward slash /
			   rdob.setText(null);
			   JOptionPane.showMessageDialog(f,
					    "Enter Date of Birth in official format dd/mm/yyyy",   //error message
					    srdob, JOptionPane.ERROR_MESSAGE);
			   System.out.println(rdob);
		   } */
		   else if (!(srdob.matches("[a-zA-Z0-9/_ ]{3,50}"))){
			   rdob.setText(null);
			   JOptionPane.showMessageDialog(f,
					    "Enter the affiliation in apropriate manner, you can use only letters,digits, /, _ "
					    + "\n Must be between 5 and 20 characters",   //error message
					    srdob, JOptionPane.ERROR_MESSAGE);
			 }
	/////////////////////////////////////////////////////////////////////////////////
    // Gender text field validation    // Instruction updated   Position
		   else if (!(srgender.matches("[A-Za-z ]{3,20}"))){
			   rgender.setText(null);
			   JOptionPane.showMessageDialog(f,
					    "In Position text field enter only characters\n"
					    + "Must be between 3 and 20 characters",   //error message
					    srgender, JOptionPane.ERROR_MESSAGE);  
		   }
	///////////////////////////////////////////////////////////////////////////////////
    //email address text field validation
		   else if (!(sremail.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)"))){
			   remail.setText(null);
			   JOptionPane.showMessageDialog(f, "Email address must have A-Z, a-z ,-,_,0-9 characters",
					   sremail, JOptionPane.ERROR_MESSAGE);
		   }
	////////////////////////////////////////////////////////////////////////////////////
    // Address text field validation
		   else if (!(sraddres.matches("[a-zA-Z0-9/_ ]{5,}" ))){
			   raddres.setText(null);
	JOptionPane.showMessageDialog(f, "Address must be like 1/23 cook street Auckland 2210 characters", 
			   sraddres, JOptionPane.ERROR_MESSAGE);
           }
	////////////////////////////////////////////////////////////////////////////////////////
	// Contact number text field validation	   
		   else if (!(srnum.matches("(^[+]?[0-9]{5,15}$)"))){
			   rnum.setText(null);
			   JOptionPane.showMessageDialog(f, "Contact number must be more than 5 and less than 15 "
			   		+ "digits only", srnum, JOptionPane.ERROR_MESSAGE);
		   }
	//////////////////////////////////////////////////////////////////////////////////////////
	//// Attending text field validation
		   else if (!(srattend.matches("YES|NO|yes|no|Yes|No"))){
			   rattend.setText(null);
			   JOptionPane.showMessageDialog(f, "choice attending betwwen Yes-attend or No-not attend",
			   srattend, JOptionPane.ERROR_MESSAGE);
		   }
	////////////////////////////////////////////////////////////////////////////////////////////////////
		   // Nationality text field validation
		   else if (!(srnat.matches("[a-zA-Z]+\\s?+[a-zA-Z]+|[a-zA-Z]+[-][a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+\\s[a-zA-Z]+")) && (s.equals("I"))){  // flag switcher on I, because this text field must be save in international file
			   rnat.setText(null);
			   JOptionPane.showMessageDialog(f, "Nationality must be only characters or forward slash/ or hyphen-",srnat
					   ,JOptionPane.ERROR_MESSAGE);
		   }
   ////////////////////////////////////////////////////////////////////////////////////////////////////////
		   /// Topic of speech text field validation
		   else if (!(srtop.matches("[a-zA-Z0-9]{1,}|[a-zA-Z0-9]{1,}\\s[a-zA-z0-9]{1,}")) && (s.equals("S"))){ // flag switcher on S, because this text field must saved in speaker file
			   rtop.setText(null);
			   JOptionPane.showMessageDialog(f, "Topic of speech must have more than one characters or digits",
					   srtop, JOptionPane.ERROR_MESSAGE);
		   }
   ////////////////////////////////////////////////////////////////////////////////////////////////////////
		   /// Domestic or International text field validation 
		   else if (!(srdon.matches("[diDI]{1}")) && (s.equals("S"))){                               // flag switcher on S, because this text field must saved in speaker file
			   rdon.setText(null);
			   JOptionPane.showMessageDialog(f, "Speaker must be Domestic or International",
					   srdon, JOptionPane.ERROR_MESSAGE);
		   }
   ////////////////////////////////////////////////////////////////////////////////////////////////////////
		   // Part 2.3 registration guests, saving data into files
		   /// Saving part
		   // Domestic saving part
		   else if(s.equals("D")){                                                                   // flag switcher
	        	try {
	        		File Domestic = new File("Domestic.csv");                                        // initiate domestic file
	        		PrintWriter printWriterd = new PrintWriter(new FileWriter(Domestic,true));       // initiate new file writer, without overwriting
		        	try {
		        		printWriterd.println(srfname+ "," + srsname+
		        		"," + srdob + "," + srgender + "," + sremail + "," + sraddres + "," + srnum
		        		+"," + srattend + "," + id);                                                // save string with come determiner
		        	}
		        	finally {
		        		printWriterd.close();                                                       // finish writing
		        		JOptionPane.showMessageDialog(null, "Domestic guest saved successfully");
		        		rfname.setText(null);                                                       // set text fields null for convenient re-editing new guest
		        		rsname.setText(null); 
		        		rdob.setText(null);
		        		rgender.setText(null);
		        		remail.setText(null);
		        		raddres.setText(null);
		        		rnum.setText(null);
		        		rattend.setText(null);	
		        	}
	        	}
	        	catch (IOException ex) {
	        		JOptionPane.showMessageDialog(null, "Domestic guest did not saved, file open in another program "); 
	        	}
	        }
		   else if (s.equals("I")){
		   try {
       		File International = new File("International.csv");
       		PrintWriter printWriteri = new PrintWriter(new FileWriter(International,true));
	        	try {
	        		printWriteri.println(srfname + "," + srsname + "," + srdob + "," + srgender + "," + 
			        sremail + "," + sraddres + "," + srnum +"," + srattend+ "," + srnat + "," + id );
	        	}
	        	finally {
	        		printWriteri.close();
	        		JOptionPane.showMessageDialog(null, "International guest saved successfully");
	        		
	        		rfname.setText(null); 
	        		rsname.setText(null);
	        		rdob.setText(null);
	        		rgender.setText(null);
	        		remail.setText(null);
	        		raddres.setText(null);
	        		rnum.setText(null);
	        		rattend.setText(null);
	        		rnat.setText(null);
	        	}
       	}
       	catch (IOException ex) {
       		JOptionPane.showMessageDialog(null, "International guest did not saved, file open in another program");
       	}
	   }
	       else if (s.equals("SD")) {
		   try {
	       		File Speaker = new File("Speaker.csv");
	       		PrintWriter printWriters = new PrintWriter(new FileWriter(Speaker,true));
	       		
	       		File Domestics = new File("Domestic.csv");
        		PrintWriter printWritersd = new PrintWriter(new FileWriter(Domestics,true));
		        	try {
		        		printWriters.println(srfname + "," + srsname + "," + srdob + "," + srgender + "," + 
				        sremail + "," + sraddres + "," + srnum + "," + srattend + ","+ srnat + "," + srtop  + "," + 
		        		srdon + "," + id);
		        		
		        		printWritersd.println(srfname+ "," + srsname+
				        		"," + srdob + "," + srgender + "," + sremail + "," + sraddres + "," + srnum
				        		+"," + srattend + "," + id);	
		        	}
		        	finally {
		        		printWriters.close();
		        		JOptionPane.showMessageDialog(null, "Speaker saved successfully");
		        		System.out.println("Domestic saved");
		        		rfname.setText(null); 
		        		rsname.setText(null);
		        		rdob.setText(null);
		        		rgender.setText(null);
		        		remail.setText(null);
		        		raddres.setText(null);
		        		rnum.setText(null);
		        		rattend.setText(null);
		        		rtop.setText(null);
		        		rdon.setText(null);
		        		
		        		printWritersd.close();
		        		System.out.println("Speaker saved");
		        		rfname.setText(null); 
		        		rsname.setText(null);
		        		rdob.setText(null);
		        		rgender.setText(null);
		        		remail.setText(null);
		        		raddres.setText(null);
		        		rnum.setText(null);
		        		rattend.setText(null);
		        	}
	       	}
	       	catch (IOException ex) {
	       		JOptionPane.showMessageDialog(null, "Speaker or domestic guest did not saved, file open in another program");
	       	}
		   }
		   else if (s.equals("SI")) {
			   try {
		       		File Speakeri = new File("Speaker.csv");
		       		PrintWriter printWritersi = new PrintWriter(new FileWriter(Speakeri,true));
		       		
		       		File Internationals = new File("International.csv");
	        		PrintWriter printWritersii = new PrintWriter(new FileWriter(Internationals,true));
			        	try {
			        		printWritersi.println(srfname + "," + srsname + "," + srdob + "," + srgender + "," + 
					        sremail + "," + sraddres + "," + srnum + "," + srattend + ","+ srnat + "," + srtop  + "," + 
			        		srdon + "," + id);
			        		
			        		printWritersii.println(srfname+ "," + srsname+
					        		"," + srdob + "," + srgender + "," + sremail + "," + sraddres + "," + srnum
					        		+"," + srattend + "," + srnat + "," + id);	
			        	}
			        	finally {
			        		printWritersi.close();
			        		JOptionPane.showMessageDialog(null, "Speaker saved successfully");
			        		System.out.println("Speaker saved");
			        		rfname.setText(null); 
			        		rsname.setText(null);
			        		rdob.setText(null);
			        		rgender.setText(null);
			        		remail.setText(null);
			        		raddres.setText(null);
			        		rnum.setText(null);
			        		rnat.setText(null);
			        		rattend.setText(null);
			        		rtop.setText(null);
			        		rdon.setText(null);
			        		
			        		printWritersii.close();
			        		System.out.println("International saved");
			        		rfname.setText(null); 
			        		rsname.setText(null);
			        		rdob.setText(null);
			        		rgender.setText(null);
			        		remail.setText(null);
			        		raddres.setText(null);
			        		rnum.setText(null);
			        		rattend.setText(null);
			        	}
		       	}
		       	catch (IOException ex) {
		       		JOptionPane.showMessageDialog(null, "Speaker or international guest did not saved, file open in another program");
		       	}
			   }
	   }
	  });
   }
 /////////////////////////////////////////////////////////////////////////////////////////
 // ID number generation
	 public int IDGen(){
		 Random r = new Random( System.currentTimeMillis() );                              // making random number from current name
		    id = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));                          // count id, make it more unique
		    return id;
	 }
//////////////////////////////////////////////////////////////////////////////////////////
// Part 3 Guest management
// Part3.1 Guest management, Search option
// Search guest option
 public void searchGuests(){
	 sp = new JPanel(new GridBagLayout());
	 GridBagConstraints d = new GridBagConstraints();
	 cp.add(sp, "searchGuests");
	 sp.setBackground(Color.LIGHT_GRAY);
	 labsdom = new JLabel("Enter ID number or FirstName of guest");
	 labsdom.setFont(tFont);
	 bsdom = new JButton("Search");
	 bsdom.setFont(sFont);
	 sdom = new JTextField(20);
	 sdom.setFont(tfFont);
	 sfname = new JTextField(10);
	 sfname.setFont(tfFont);
	 ssname = new JTextField(10);
	 ssname.setFont(tfFont);
	 sdob = new JTextField(10);
	 sdob.setFont(tfFont);
	 sgender = new JTextField(10);
	 sgender.setFont(tfFont);
	 semail = new JTextField(10);
	 semail.setFont(tfFont);
	 saddress = new JTextField(10);
	 saddress.setFont(tfFont);
	 snum = new JTextField(10);
	 snum.setFont(tfFont);
	 sattend = new JTextField(10);
	 sattend.setFont(tfFont);
	 snat = new JTextField(10);
	 snat.setFont(tfFont);
	 stop = new JTextField(10);
	 stop.setFont(tfFont);
	 sdon = new JTextField(10);
	 sdon.setFont(tfFont);
	 labsfname = new JLabel("FirstName: ");
	 labsfname.setFont(sFont);
	 labssname = new JLabel("SecondName: ");
	 labssname.setFont(sFont);
	 labsdob = new JLabel("Date of birth: ");
	 labsdob.setFont(sFont);
	 labsgender = new JLabel("Gender: ");
	 labsgender.setFont(sFont);
	 labsemail = new JLabel("Email Address: ");
	 labsemail.setFont(sFont);
	 labsaddress = new JLabel("Address: ");
	 labsaddress.setFont(sFont);
	 labsnum = new JLabel("Phone Number: ");
	 labsnum.setFont(sFont);
	 labsattend = new JLabel("Attending: ");
	 labsattend.setFont(sFont);
	 labsnat = new JLabel("Nationality: ");
	 labsnat.setFont(sFont);
	 labstop = new JLabel("Topic of Speach: ");
	 labstop.setFont(sFont);
	 labsdon = new JLabel("Domestic or International: ");
	 labsdon.setFont(sFont);
	 sfname.setEditable(false);
	 ssname.setEditable(false);
	 sdob.setEditable(false);
	 sgender.setEditable(false);
	 semail.setEditable(false);
	 saddress.setEditable(false);
	 snum.setEditable(false);
	 sattend.setEditable(false);
	 snat.setEditable(false);
	 stop.setEditable(false);
	 sdon.setEditable(false);
	 
	 d.gridx = 0;
	 d.gridy = 3;
	 d.anchor = GridBagConstraints.WEST;
	 sp.add(labsfname,d);
     
	 d.gridy  ++;
	 sp.add(labssname,d);
	 
	 d.gridy ++;
	 sp.add(labsdob,d);
	 
	d.gridy ++;
	sp.add(labsgender,d);
	 
	d.gridy ++;
	sp.add(labsemail,d);
	 
	d.gridy ++;
	sp.add(labsaddress,d);
	 
	d.gridy ++;
	sp.add(labsnum,d);
	 
	d.gridy ++;
	sp.add(labsattend,d);
	 
	d.gridy ++;
	sp.add(labsnat,d);
	 
	d.gridy ++;
	sp.add(labstop,d);
	 
    d.gridy ++;
	sp.add(labsdon,d);
	 
	 d.gridx = 1;
	 d.gridy = 3;
	 d.gridwidth = 2;
	 d.anchor = GridBagConstraints.PAGE_END;
	 d.fill = GridBagConstraints.HORIZONTAL;
	 d.insets = new Insets (10,0,5,0);
	 sp.add(sfname, d);
	 
	 d.gridy ++;
	 sp.add(ssname, d);
	 
	  d.gridy ++;
	  sp.add(sdob, d);
	  
	  d.gridy ++;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(sgender, d);
		 
	  d.gridy ++;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(semail, d);
		 
	  d.gridy ++;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(saddress, d);
		 
	  d.gridy ++;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(snum, d);
	
	  d.gridy ++;
	  d.insets = new Insets (10,0,5,0);
      sp.add(sattend, d);
		 
	  d.gridy ++;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(snat, d);
		 
	  d.gridy ++;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(stop, d);
		 
	  d.gridy ++;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(sdon, d);
		 
	  labsdom.setFont(new Font("",Font.BOLD, 20));
	  d.gridx = 0;
	  d.gridy = 0;
	  d.anchor = GridBagConstraints.CENTER;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(labsdom, d);
		 
	  d.gridx = 0;
	  d.gridy = 1;
	  d.gridwidth = 2;
	  d.anchor = GridBagConstraints.NORTH;
      d.fill = GridBagConstraints.CENTER;
	  d.insets = new Insets (10,0,5,0);
	  sp.add(sdom, d);
		 
	  d.gridx = 0;
	  d.gridy = 2;
	  d.gridwidth = 2;
	  d.anchor = GridBagConstraints.NORTH;
	  d.fill = GridBagConstraints.CENTER;
      d.insets = new Insets (10,0,5,0);
	  sp.add(bsdom, d);
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // Search button function
 bsdom.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
        
		if (ss.equals("D")){                                                            // if flag on "D", file reader will be read domestic file
	    String searchd = sdom.getText().trim();                                         //convert text field to String, for best matching it with lines in file
	    System.out.println(searchd);                                                    // for mistake checking
	    String detd = ",";                                                              // initiate comma determiner
	    String [] datad = null;                                                         // initiate String array for store data from csv file
	    File DataFiled = new File("Domestic.csv");                                      // initiate file
	    int scld = 0;                                                                   // counter of lines
	    int sclnd = 0;                                                                  // counter of matching lines with search parameter
	    // this variables initiated for  show error message only one time, if guest would not be found
	    try {
			BufferedReader brdd = new BufferedReader(new FileReader(DataFiled));        // initiate file reader
			 while (brdd.ready()) {                                                     // start read the file
				 scld++;                                                                //  line counter increment,while lines are reading
                 String st = brdd.readLine();                                           // initiate line for matching with search parameter
                 System.out.println(st);                                                // for error catching
                 if (searchd.equals("")){                                               // Input validation, make space-entering impossible
               	  sdom.setText(null);                                                   // set fields are null, for convenient re-editing
               	  sfname.setText(null);
           	      ssname.setText(null);
           	      sdob.setText(null);
           	      sgender.setText(null);
           	      semail.setText(null);
           	      saddress.setText(null);
           	      snum.setText(null);
           	      sattend.setText(null);
               	  JOptionPane.showMessageDialog(f, "search field must have characters", null, JOptionPane.ERROR_MESSAGE, null);
               	  break;
                }
                  if (st.startsWith(searchd) | (st.endsWith(searchd))){                // matches search with start of line and end of line
                	                                                                   // In start of line is First name and in end of line is ID number.
                	 st.split(detd);              //Divide line on on string           // So allow just validate search only with FirstName and ID number.
                	 datad =(st.split(detd));                                          // add String to text field
                	 sfname.setText(datad[0]);     
                	 ssname.setText(datad[1]);
                	 sdob.setText(datad[2]);
                	 sgender.setText(datad[3]);
                	 semail.setText(datad[4]);
                	 saddress.setText(datad[5]);
                	 snum.setText(datad[6]);
                	 sattend.setText(datad[7]);
                }
                  else{
                	  sclnd++;                                                         // count not matching lines
                  }
                   }
			 System.out.println(scld + "," + sclnd);                                   // mistakes checking
	   if(scld == sclnd){
			 JOptionPane.showMessageDialog(f, "Domestic guest could not found");       // compare counters, if equals, show message
			 sdom.setText(null);                                                       // set text field are null for convenient re-editing
			 sfname.setText(null);
        	 ssname.setText(null);
        	 sdob.setText(null);
        	 sgender.setText(null);
        	 semail.setText(null);
        	 saddress.setText(null);
        	 snum.setText(null);
        	 sattend.setText(null);
	   }
			    brdd.close();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(f, "File not found", null, JOptionPane.ERROR_MESSAGE, null);
			e1.printStackTrace();	
		} catch (IOException e1) {
            e1.printStackTrace();
		}
	    }
	   else if (ss.equals("I")){
		   String searchi = sdom.getText().trim();
		    System.out.println(searchi);
		    String deti = ",";
		    String [] datai;
		    int scli = 0;
		    int sclni = 0;
		    File DataFilei = new File("International.csv");
		    try {
				BufferedReader brdi = new BufferedReader(new FileReader(DataFilei));
				 while (brdi.ready()) {
					 scli++;
	                 String st = brdi.readLine();
	                 System.out.println(st);
	                 if (searchi.equals("")){
	               	  sdom.setText(null);
	               	  sfname.setText(null);
	           	      ssname.setText(null);
	           	      sdob.setText(null);
	           	      sgender.setText(null);
	           	      semail.setText(null);
	           	      saddress.setText(null);
	           	      snum.setText(null);
	           	      sattend.setText(null);
	                	 JOptionPane.showMessageDialog(f, "search field must have characters", null, JOptionPane.ERROR_MESSAGE, null);
	                	 break;
	                 }
	                  if (st.startsWith(searchi)|(st.endsWith(searchi))){
	                	 st.split(deti);//.toString();
	                	 datai =(st.split(deti));
	                	 sfname.setText(datai[0]);
	                	 ssname.setText(datai[1]);
	                	 sdob.setText(datai[2]);
	                	 sgender.setText(datai[3]);
	                	 semail.setText(datai[4]);
	                	 saddress.setText(datai[5]);
	                	 snum.setText(datai[6]);
	                	 sattend.setText(datai[7]);
	                	 snat.setText(datai[8]);
	                }
	                  else{
	                	  sclni++;
	                  }
				 }
	                  if(scli == sclni){
	         			 JOptionPane.showMessageDialog(f, "International guest could not found");
	         			 sdom.setText(null);
	         			 sfname.setText(null);
	                 	 ssname.setText(null);
	                 	 sdob.setText(null);
	                 	 sgender.setText(null);
	                 	 semail.setText(null);
	                 	 saddress.setText(null);
	                 	 snum.setText(null);
	                 	 sattend.setText(null);
	                 	 snat.setText(null);
	         	    }
				    brdi.close();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(f, "File not found", null, JOptionPane.ERROR_MESSAGE, null);
				e1.printStackTrace();	
			} catch (IOException e1) {
	            e1.printStackTrace();
			}
		    }
		else if(ss.equals("S")) {
		   String searchs = sdom.getText().trim();
		    System.out.println(searchs);
		    String dets = ",";
		    String [] datas;
		    int scls = 0;
		    int sclns = 0;
		    File DataFile = new File("Speaker.csv");
		    try {
				BufferedReader brds= new BufferedReader(new FileReader(DataFile));
				 while (brds.ready()) {
					 scls++;
	                 String st = brds.readLine();
	                 System.out.println(st);
	                 if (searchs.equals("")){
	               	  sdom.setText(null);
	                  sfname.setText(null);
	           	      ssname.setText(null);
	           	      sdob.setText(null);
	           	      sgender.setText(null);
	               	  semail.setText(null);
	            	  saddress.setText(null);
	           	      snum.setText(null);
	           	      sattend.setText(null);
	               	  JOptionPane.showMessageDialog(f, "search field must have characters", null, JOptionPane.ERROR_MESSAGE, null);
	               	  break;
	                  }
	                  if (st.startsWith(searchs)|(st.endsWith(searchs))){
	                	 st.split(dets);//.toString();
	                	 datas =(st.split(dets));
	                	 sfname.setText(datas[0]);
	                	 ssname.setText(datas[1]);
	                	 sdob.setText(datas[2]);
	                	 sgender.setText(datas[3]);
	                	 semail.setText(datas[4]);
	                	 saddress.setText(datas[5]);
	                	 snum.setText(datas[6]);
	                	 sattend.setText(datas[7]);
	                	 stop.setText(datas[8]);
	                	 sdon.setText(datas[9]);
	                }
	                  else{
	                	 sclns++;
	                  }
	                   }
				 if(scls == sclns){
         			 JOptionPane.showMessageDialog(f, "Speaker could not found");
         			 sdom.setText(null);
         			 sfname.setText(null);
                 	 ssname.setText(null);
                 	 sdob.setText(null);
                 	 sgender.setText(null);
                 	 semail.setText(null);
                 	 saddress.setText(null);
                 	 snum.setText(null);
                 	 sattend.setText(null);
                 	 snat.setText(null);
                 	 stop.setText(null);
                 	 sdon.setText(null);
				 }
				    brds.close();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(f, "File not found", null, JOptionPane.ERROR_MESSAGE, null);
				e1.printStackTrace();	
			} catch (IOException e1) {
	            e1.printStackTrace();
			}
		    }
		   }
	      });
 }
//////////////////////////////////////////////////////////////////////////////////////////
// Part 3.2 Guests management, Delete option
// Delete guest option
 public void deletePanel(){
	 ddp = new JPanel(new GridBagLayout());
	 GridBagConstraints d = new GridBagConstraints();
	 cp.add(ddp, "deletePanel");
	 ddp.setBackground(Color.LIGHT_GRAY);
	 labdeldom = new JLabel("Enter the ID number or FirstName of domestic guest");
	 labdint = new JLabel("Enter the ID number or FirstName of international guest");
	 bddel = new JButton("Delete");
	 bddel.setFont(sFont);
	 bdint = new JButton("Delete");
	 bdint.setFont(sFont);
	 did = new JTextField(5);
	 did.setFont(tfFont);
	 dint = new JTextField(5);
	 dint.setFont(tfFont);
	 
	 labdeldom.setFont(tFont);
	 d.gridx = 0;
	 d.gridy = 0;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 ddp.add(labdeldom, d);
	 
	 d.gridx = 0;
	 d.gridy = 1;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 10;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 ddp.add(did, d);
	 
	 d.gridx = 0;
	 d.gridy = 2;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 1;
	 d.weighty = 1000;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 ddp.add(bddel, d);
	 
	 labdint.setFont(tFont);
	 d.gridx = 0;
	 d.gridy = 3;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 1;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 ddp.add(labdint, d);
	 
	 d.gridx = 0;
	 d.gridy ++;
	 d.weighty = 10;
	 ddp.add(dint, d);
	 
	 d.gridx = 0;
	 d.gridy ++;
	 d.weighty = 1000;
	 ddp.add(bdint, d);
	 
// Delete domestic guest button action
 bddel.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
	    String drid = did.getText().trim();                                                   // convert text field to String, for matching with lines in file
	    if(!(drid.matches("[A-Za-z ]{2,}|[0-9]{5}"))){
	    	 JOptionPane.showMessageDialog(f, "Please enter only ID number or FirstName",      // input validation can be only characters or 5 numbers for ID
					   drid, JOptionPane.ERROR_MESSAGE);
	    	 did.setText(null); // for re-editing
	    	 drid = "-";                                                                      //  for do not showing error message after this message
	    }
	    try { 
              File inFiled = new File("Domestic.csv");
              if (!inFiled.isFile()) {
	          System.out.println("Parameter is not an existing file");
	          JOptionPane.showMessageDialog(f, "Couldn't find domestic user", null, JOptionPane.ERROR_MESSAGE, null); 
	          return;
	        }
            //Construct the new file that will later be renamed to the original filename.
	        File tempFiled = new File(inFiled.getAbsolutePath() + ".tmp");
            BufferedReader brdd = new BufferedReader(new FileReader("Domestic.csv"));
	        PrintWriter pwdd = new PrintWriter(new FileWriter(tempFiled));
            int cld = 0;                                                                      // count lines
            int clnd = 0;                                                                     // count lines which not matches with String
	        String linedd = null;                                                             // delete value for re-editing
            //Read from the original file and write to the new
	        //unless content matches data to be removed.
	        while ((linedd = brdd.readLine()) != null) {                                      // line will be reading if not null
                   cld++;                                                                     // count lines
	          if (!linedd.trim().startsWith(drid) && (!(linedd.trim().endsWith(drid)))) {     // matches lines with string in start and end, with first name and ID number
                clnd++;                                                                       // count  not matching lines
	            pwdd.println(linedd);                                                         // mistakes checking
	            pwdd.flush();
	          }
	          else {
	        	  JOptionPane.showMessageDialog(f, "Domestic guest deleted");  
	        	     }
	        }
	        if ((cld == clnd)&&(!drid.equals("-"))){                                         // compare counters ,if equals show message or do not show if equals -
	        	  System.out.println(cld + " " + clnd);
   		   JOptionPane.showMessageDialog(f, "Domestic guest can not be found");
   		   did.setText(null);                                                                // remove value for re-writing
	        }
	        pwdd.close();
	        brdd.close();
	        //Delete the original file
	        if (!inFiled.delete()) {
	          System.out.println("Could not delete file");
	          return;
	        }
	           //Rename the new file to the filename the original file had.
	        if (!tempFiled.renameTo(inFiled)){
	          System.out.println("Could not rename file");
	        }
	       }
	        catch (FileNotFoundException ex) {
	    	    ex.printStackTrace();
	        }
	      catch (IOException ex) {
	    	   ex.printStackTrace();
	      }
	    }
});
 //////////////////////////////////////////////////////////////////////////////////////////
// Delete international button option
 bdint.addActionListener(new ActionListener(){
	  
	   
	   public void actionPerformed(ActionEvent e){
	    String drin = dint.getText().trim();
	    if(!(drin.matches("[A-Za-z ]{2,}|[0-9]{5}"))){
	    	 JOptionPane.showMessageDialog(f, "Please enter only ID number or FirstName",
					   drin, JOptionPane.ERROR_MESSAGE);
	    	 dint.setText(null);
	    	 drin = "-";
	    }
	    try {
            File inFilei = new File("International.csv");
	        if (!inFilei.isFile()) {
	          System.out.println("Parameter is not an existing file");
	          JOptionPane.showMessageDialog(f, "Couldn't find international guest", null, JOptionPane.ERROR_MESSAGE, null); 
	          return;
	        }
            //Construct the new file that will later be renamed to the original filename.
	        File tempFilei = new File(inFilei.getAbsolutePath() + ".tmp");
            BufferedReader bri = new BufferedReader(new FileReader("International.csv"));
	        PrintWriter pwi = new PrintWriter(new FileWriter(tempFilei));
            int cli = 0;
            int cnli = 0;
	        String linei ;
	        //Read from the original file and write to the new
	        //unless content matches data to be removed.
	       while ((linei = bri.readLine()) != null) {
               cli++;
              if (!linei.trim().startsWith(drin)&&(!linei.trim().endsWith(drin))) {
	        	  cnli++;
	            pwi.println(linei);
	            pwi.flush();
	            }
	           else {
	        	  JOptionPane.showMessageDialog(f, "International guest deleted");  
	        	    }
	            }
	        if ((cli == cnli)&&(!drin.equals("-"))){
	        	  System.out.println(cli + " " + cnli);
     		   JOptionPane.showMessageDialog(f, "International guest can not be found");
     		   dint.setText(null);
	        }
	        pwi.close();
	        bri.close();
	        //Delete the original file
	        if (!inFilei.delete()) {
	          System.out.println("Could not delete file");
	          return;
	        }
          //Rename the new file to the filename the original file had.
	        if (!tempFilei.renameTo(inFilei)){
	          System.out.println("Could not rename file");
	        }
	       }
	      catch (FileNotFoundException ex) {
	        ex.printStackTrace();
	      }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }
	    }
});
}
//////////////////////////////////////////////////////////////////////////////////////////
 // Part 3.3 Guest management, Displaying guests
// Displaying guest
 // Only GUI class for tables, making part in JMenu bar class and in table model class and add csv data clases
	 public void dispDomPanel(){
		 
		 dp = new JPanel(new GridBagLayout());
		 GridBagConstraints d = new GridBagConstraints();
		 cp.add(dp, "dispDomPanel");
		 dp.setBackground(Color.LIGHT_GRAY);
		 labDisp = new JLabel("List of domestic guests");
         bdupdom = new JButton("Update");
         bdupdom.setFont(new Font("",Font.BOLD, 12));
		 ddisp = new JTable();                                             //add JTable for panel
		 JScrollPane ddispsp = new JScrollPane(ddisp);
		 ddispsp.setPreferredSize(new Dimension(700,400));
		 ddisp.setFillsViewportHeight(true);
		 ddisp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 ddisp.setBackground(Color.LIGHT_GRAY);
		 
		 labDisp.setFont(new Font("",Font.BOLD, 20));
		 d.gridx = 0;
		 d.gridy = 0;
		 d.gridwidth = 1;
		 d.gridheight = 1;
		 d.weightx = 0;
		 d.weighty = 0.9;
		 d.anchor = GridBagConstraints.NORTH;
		 d.insets = new Insets (10,0,5,0);
		 dp.add(labDisp, d);
		 
		 d.gridx =0;
		 d.gridy =1;
		 d.gridwidth = 1;
		 d.gridheight =1;
		 d.weightx = 1;
		 d.weighty = 100;
		 d.anchor = GridBagConstraints.NORTH;
		 d.ipadx = 10;
		 d.ipady = 10;
		 
		 dp.add(ddispsp, d);
		 ddisp.setFont(new Font("",Font.BOLD, 14));
		 
		 d.gridx = 0;
		 d.gridy ++;
		 d.weighty = 1100;
		 d.anchor = GridBagConstraints.NORTH;
		 dp.add(bdupdom, d);
////////////////////////////////////////////////////////////////////////
// Part 3.4 Guest management, update function
///// Update button option.  This button for saving updated cells in Jtable to CSV file 
	 bdupdom.addActionListener(new ActionListener(){
		  
		   
		   public void actionPerformed(ActionEvent e){
			   File updomfile = new File("Domestic.csv");   
	            if(updomfile.exists() && !updomfile.isDirectory()){
	                updomfile.delete();
	            }
	            BufferedWriter bfwupd;
				try {
					bfwupd = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Domestic.csv")));
				
	            for(int i=0;i<ddisp.getRowCount();i++){                                                       //get column and row from table 
	                for(int j=0;j<ddisp.getColumnCount();j++){                                                // and save to the file
	                    bfwupd.append((String)ddisp.getValueAt(i,j));
	                    if(j<ddisp.getColumnCount()-1) bfwupd.append(",");                                    //delete last comma
	                    else bfwupd.newLine();
	                }
	            }
	            bfwupd.close();
	            JOptionPane.showMessageDialog(null, "Updated");
				} catch (IOException e1) {
			    JOptionPane.showMessageDialog(f, "Couldn't find a file", null, JOptionPane.ERROR_MESSAGE, null);
					e1.printStackTrace();
				}
		   }
	  });
	 }
/////////////////////////////////////////////////////////////
///Display international guests
public void dispIntPanel(){
		 
		 dip = new JPanel(new GridBagLayout());
		 GridBagConstraints d = new GridBagConstraints();
		 cp.add(dip, "dispIntPanel");
		 dip.setBackground(Color.LIGHT_GRAY);
		 labDispi = new JLabel("List of ilnternational guests");
         ddispi = new JTable();
         bdupint = new JButton("Update");
         bdupint.setFont(new Font("",Font.BOLD, 12));
		 JScrollPane ddispspi = new JScrollPane(ddispi);
		 ddispspi.setPreferredSize(new Dimension(700,400));
		 ddispi.setFillsViewportHeight(true);
		 ddispi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		 ddispi.setBackground(Color.LIGHT_GRAY);
		 labDispi.setFont(new Font("",Font.BOLD, 20));
		 d.gridx = 0;
		 d.gridy = 0;
		 d.gridwidth = 1;
		 d.gridheight = 1;
		 d.weightx = 0;
		 d.weighty = 0.9;
		 d.anchor = GridBagConstraints.NORTH;
		 d.insets = new Insets (10,0,5,0);
		 dip.add(labDispi, d);
		 
		 d.gridx =0;
		 d.gridy =1;
		 d.gridwidth = 1;
		 d.gridheight =1;
		 d.weightx = 1;
		 d.weighty = 100;
		 d.anchor = GridBagConstraints.NORTH;
		 d.ipadx = 10;
		 d.ipady = 10;
		 
		 dip.add(ddispspi, d);
		 ddispi.setFont(new Font("",Font.BOLD, 14));
		 
		 d.gridx = 0;
		 d.gridy ++;
		 d.weighty = 1100;
		 d.anchor = GridBagConstraints.NORTH;
		 dip.add(bdupint, d);
//////////////////////////////////////////////////////////////////////////////////////
// Update button option
		 bdupint.addActionListener(new ActionListener(){
			  
			   
			   public void actionPerformed(ActionEvent e){
				
				   File upintfile = new File("International.csv");
		            if(upintfile.exists() && !upintfile.isDirectory()){
		                upintfile.delete();
		            }
		            BufferedWriter bfwupi;
					try {
						bfwupi = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("International.csv")));
					
		            for(int i=0;i<ddispi.getRowCount();i++){                                     // get data from table and save in file
		                for(int j=0;j<ddispi.getColumnCount();j++){
		                    bfwupi.append((String)ddispi.getValueAt(i,j));
		                    if(j<ddispi.getColumnCount()-1) bfwupi.append(",");                  //delete last comma
		                    else bfwupi.newLine();
		                }
		            }
		            bfwupi.close();
		            JOptionPane.showMessageDialog(null, "Updated");
					} catch (IOException e1) {
				    JOptionPane.showMessageDialog(f, "Couldn't find a file", null, JOptionPane.ERROR_MESSAGE, null);
						e1.printStackTrace();
					}
			   }	
		  });
		 }
 ///////////////////////////////////////////////////////////////////////////////////////////////////
//// Displaying speakers
 public void dispSpkPanel() {
	 dsp = new JPanel(new GridBagLayout());
	 GridBagConstraints d = new GridBagConstraints();
	 cp.add(dsp, "dispSpkPanel");
	 dsp.setBackground(Color.LIGHT_GRAY);
	 labDisps = new JLabel("List of  Speakers");
     ddisps = new JTable();
	 JScrollPane ddispsps = new JScrollPane(ddisps);
	 ddispsps.setPreferredSize(new Dimension(700,400));
	 ddisps.setFillsViewportHeight(true);
	 ddisps.setBackground(Color.LIGHT_GRAY);
	 
	 labDisps.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 0;
	 d.gridy = 0;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 dsp.add(labDisps, d);
	 
	 d.gridx =0;
	 d.gridy =1;
	 d.gridwidth = 1;
	 d.gridheight =1;
	 d.weightx = 1;
	 d.weighty = 100;
	 d.anchor = GridBagConstraints.NORTH;
	 d.ipadx = 10;
	 d.ipady = 10;
	 
	 dsp.add(ddispsps, d);
	 ddisps.setFont(new Font("",Font.BOLD, 14));	
	 }
/////////////////////////////////////////////////////////////////
 // Part 3.4 Guest management, make report
 // Making Report part
 // Only GUI in this class, all action in JMenuBar class
 public void reportPanel(){
	 repp = new JPanel(new GridBagLayout());
	 GridBagConstraints d = new GridBagConstraints();
	 cp.add(repp, "reportPanel");
	 repp.setBackground(Color.LIGHT_GRAY);	
	 labrng = new JLabel("Total number of guests: ");
	 labrdn = new JLabel("Number of domestic guests: ");
	 labrin = new JLabel("Number of international guests: ");
	 labrna = new JLabel("Number of attendings: " );
	 labrnn = new JLabel("Number of non-attendings: ");
	 labre = new JLabel("Total revenue: ");
	 labdre = new JLabel("Domestic revenue: ");
	 labire = new JLabel("International revenue: ");
	 Report rs = new Report();
	 rng = new JTextField(rs.sizerng);
	 rdn = new JTextField(rs.sizerdn);
	 rin = new JTextField(rs.sizerin);
	 rna = new JTextField(rs.sizerna);
	 rnn = new JTextField(rs.sizernn);
	 rre = new JTextField(rs.sizerre);
	 rdre = new JTextField(rs.sizerdom);
	 rire = new JTextField(rs.sizerint);
	 rng.setEditable(false);
	 rdn.setEditable(false);
	 rin.setEditable(false);
	 rna.setEditable(false);
	 rnn.setEditable(false);
	 rre.setEditable(false);
	 rdre.setEditable(false);
	 rire.setEditable(false);
	 rng.setBackground(Color.LIGHT_GRAY);
	 rdn.setBackground(Color.LIGHT_GRAY);
	 rin.setBackground(Color.LIGHT_GRAY);
	 rna.setBackground(Color.LIGHT_GRAY);
	 rnn.setBackground(Color.LIGHT_GRAY);
	 rre.setBackground(Color.LIGHT_GRAY);
	 rdre.setBackground(Color.LIGHT_GRAY);
	 rire.setBackground(Color.LIGHT_GRAY);
	 
	 labrng.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 0;
	 d.gridy = 0;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(labrng, d);
	 
	 labrdn.setFont(new Font("",20, 20));
	 d.gridx = 0;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(labrdn, d);
	 
	 labrin.setFont(new Font("",20, 20));
	 d.gridx = 0;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(labrin, d);
	 
	 labrna.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 0;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(labrna, d);
	 
	 labrnn.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 0;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(labrnn, d);
	 
	 labre.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 0;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(labre, d);
	 
	 labdre.setFont(new Font("",20, 20));
	 d.gridx = 0;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(labdre, d);
	 
	 labire.setFont(new Font("",20, 20));
	 d.gridx = 0;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(labire, d);
	 
	 rng.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 1;
	 d.gridy = 0;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(rng, d);
	 
	 rdn.setFont(new Font("",20, 20));
	 d.gridx = 1;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(rdn, d);
	 
	 rin.setFont(new Font("",20, 20));
	 d.gridx = 1;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(rin, d);
	 
	 rna.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 1;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(rna, d);
	 
	 rnn.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 1;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(rnn, d);
	 
	 rre.setFont(new Font("",Font.BOLD, 20));
	 d.gridx = 1;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(rre, d);
	 
	 rdre.setFont(new Font("",20, 20));
	 d.gridx = 1;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(rdre, d);
	 
	 rire.setFont(new Font("",20, 20));
	 d.gridx = 1;
	 d.gridy ++;
	 d.gridwidth = 1;
	 d.gridheight = 1;
	 d.weightx = 0;
	 d.weighty = 0.9;
	 d.anchor = GridBagConstraints.NORTH;
	 d.insets = new Insets (10,0,5,0);
	 repp.add(rire, d);
 }
 ////////////////////////////////////////////////////////////
 // Main method, program start here
 public static void main(String[] args) {
  new RegistrationSystem();       // new constructor
 }
}