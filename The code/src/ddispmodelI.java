import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ddispmodelI  extends AbstractTableModel {
		
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private final String[] columnNamesI = {"FirstName", "SecondName", "Affiliation", "Position", "Email", "Address", "Number", "Attending","Nationality","ID"};
	    private ArrayList<String[]> Data = new ArrayList<String[]>();
	   
	    public void AddCSVData(ArrayList<String[]> DataIn) {
	    	
	        this.Data = DataIn;
	        this.fireTableDataChanged();
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNamesI.length;// length;
	    }

	    @Override
	    public int getRowCount() {
	        return Data.size();
	    }

	    @Override
	    public String getColumnName(int col) {
	        return columnNamesI[col];
	    }

	    @Override
	    public Object getValueAt(int row, int col) {
	        return Data.get(row)[col];
	    }
	    public boolean isCellEditable(int row, int col) { 
	    	 if (col== 9) { // for set id column non-editable
	             return false;
	         } else {
	             return true;
	         }
	    }
	    @Override
	    public void setValueAt(Object aValue, int row, int col){
	    	Data.get(row)[col]= (String) aValue;
	    	fireTableCellUpdated(row,col);
	    }   	
	}
