
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameApp {
	//glavni prozor aplikacije sam dodala + polje gdje ce da se unese ime igraca+ polje gdje se unosi health igraca..pozicije igraca x i y kao i za unos putanje
    private JFrame frame;
    private JTextField nameField;
    private JTextField healthField;
    private JTextField xField;
    private JTextField yField;
    //izbor tj tip collidera
    private JComboBox<String> colliderCombo;
    //ovo je polje za unos putanje gdje se ucitavaju neprijatelji -enemies iz CSV 
    private JTextField csvField;
    //prikaz log igre 
    private JTextArea outputArea;

    
    private static final int PRAVOUGAONIK_SIRINA = 32;
    private static final int PRAVOUGAONIK_VISINA = 32;
    private static final int KRUG_POLUPRECNIK = 16;


    public void initUI() {

        frame = new JFrame("Igra pocinje");
        
        	
        }
        	
        }