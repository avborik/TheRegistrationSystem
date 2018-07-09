import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ddispmodelS  extends AbstractTableModel {
		
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private final String[] columnNamesS = {"FirstName","SecondName", "Affiliation", "Position", "Email", "Address", "Number", "Attending","Nationality","Topic of Speech","Domestic or International","ID"};
	    private ArrayList<String[]> Data = new ArrayList<String[]>();
	   
	    public void AddCSVData(ArrayList<String[]> DataIn) {
	    	
	        this.Data = DataIn;
	        this.fireTableDataChanged();
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNamesS.length;// length;
	    }

	    @Override
	    public int getRowCount() {
	        return Data.size();
	    }

	    @Override
	    public String getColumnName(int col) {
	        return columnNamesS[col];
	    }

	    @Override
	    public Object getValueAt(int row, int col) {
	    	
	        return Data.get(row)[col];
	    }
	    
	}
