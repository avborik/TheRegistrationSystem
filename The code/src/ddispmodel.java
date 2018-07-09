import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ddispmodel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// array for store column names
	private final String[] columnNames = { "FirstName", "SecondName", "Affiliation", "Position", "Email", "Address", "Number", "Attending","ID" };
    private ArrayList<String[]> Data = new ArrayList<String[]>();  // array list for stre data in rows
    

   
    public void AddCSVData(ArrayList<String[]> DataIn) {                             // use method from class CSVFileDomestic to get data from scv file
        this.Data = DataIn;
        this.fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {                                       // get columns numbers
        return columnNames.length;// length;
    }

    @Override
    public int getRowCount() {                                        // get rows numbers
        return Data.size();
    }

    @Override
    public String getColumnName(int col) {                           // get table header
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {                    // get row values
        return Data.get(row)[col];
    }
    public boolean isCellEditable(int row, int col) {               // make editable
    	 if (col== 8) { // for set id column non-editable
             return false;
         } else {
             return true;
         }
    }
    @Override
    public void setValueAt(Object aValue, int row, int col){             // set updated data
    	Data.get(row)[col]= (String) aValue;
    	
    	fireTableCellUpdated(row,col);
    	 }   
    
}
   
 

