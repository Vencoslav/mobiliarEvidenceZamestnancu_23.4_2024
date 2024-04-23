import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainForm extends JFrame {
    private JPanel panelMain;
    private JTextField txtJmeno;
    private JTextField txtPrijmeni;
    private JCheckBox checkBox1;
    private JTextField txtDatum;
    private JButton btDalsi;
    private JButton btPredchozi;
    private JTable table1;
    private int index = 0;
    private File selectedFile;
    private List<Zamestnanec> seznam = new ArrayList<>();

    public MainForm(){
        setContentPane(panelMain);
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Seznam zaměstnanců");

        initMenu();

        btDalsi.addActionListener(e->{tlacitkoDalsi();});
        btPredchozi.addActionListener(e->{tlacitkoPredchozi();});



    }

    public void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu soubor = new JMenu("Soubor");
        menuBar.add(soubor);

        JMenuItem nacit = new JMenuItem("Načti");
        soubor.add(nacit);
        nacit.addActionListener(e ->{vyberSoubor();});
    }

    public void vyberSoubor(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("textové soubory", "txt"));
        int resut = fc.showOpenDialog(this);
        if (resut == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            System.out.println("Vybrán soubor: " + selectedFile.getAbsolutePath());
            nactiSoubor(selectedFile);
        }
    }

    public void nactiSoubor(File selectedFile){
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))){
            while (sc.hasNextLine()){
                String radek = sc.nextLine();
                String[] rozdelovac = radek.split(":");
                if (rozdelovac.length != 4){
                    throw new RuntimeException("Špatný počet prvků na řádku.");
                }
                String jmeno = rozdelovac[0];
                String prijmeni = rozdelovac[1];
                Boolean penzijniPojisteni = rozdelovac[2].equals("ano");
                LocalDate datumNarozeni = LocalDate.parse(rozdelovac[3]);
                Zamestnanec zamestnanec = new Zamestnanec(jmeno, prijmeni, penzijniPojisteni, datumNarozeni);
                seznam.add(zamestnanec);
                dislplay();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Soubor " + selectedFile + " nebyl nalezen " + e.getLocalizedMessage());
        }
    }


    public void tlacitkoDalsi(){
        if(index < seznam.size()-1){
            index++;
            dislplay();
        }
    }

    public void tlacitkoPredchozi(){
        if(index > 0){
            index--;
            dislplay();
        }
    }

    public void dislplay(){
        Zamestnanec aktualniZamestnanec = seznam.get(index);
        txtJmeno.setText(aktualniZamestnanec.getJmeno());
        txtPrijmeni.setText(aktualniZamestnanec.getPrijmeni());
        checkBox1.setSelected(aktualniZamestnanec.getPenzijniPojisteni());
        txtDatum.setText(String.valueOf(aktualniZamestnanec.getDatum()));

        TableModel tableModel = new TableModel(seznam);
        table1.setModel(tableModel);
    }
}
