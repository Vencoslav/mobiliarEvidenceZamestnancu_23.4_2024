import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModel extends AbstractTableModel {
    private List<Zamestnanec> seznam = new ArrayList<>();

    public TableModel(List<Zamestnanec> seznam) {
        this.seznam = seznam;
    }
    @Override
    public int getRowCount() {
        return seznam.size();
    }

    public int getColumnCount() {
        return 4;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        Zamestnanec zamestnanec = seznam.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return zamestnanec.getJmeno();
            case 1:
                return zamestnanec.getPrijmeni();
            case 2:
                return zamestnanec.getPenzijniPojisteni();
            case 3:
                return zamestnanec.getDatum();
            default:
                return null;
        }
    }
}
