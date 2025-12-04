
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 550);
        frame.setLayout(new BorderLayout());

       
        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nameField = new JTextField("Harry Poter");
        healthField = new JTextField("90");
        xField = new JTextField("12");
        yField = new JTextField("9");
        colliderCombo = new JComboBox<>(new String[]{"Rectangle", "Circle"});
        csvField = new JTextField("enemies.csv");

        form.add(new JLabel("Ime igrača:"));
        form.add(nameField);

        form.add(new JLabel("Health:"));
        form.add(healthField);

        form.add(new JLabel("X pozicija:"));
        form.add(xField);

        form.add(new JLabel("Y pozicija:"));
        form.add(yField);

        form.add(new JLabel("Kolajder tip:"));
        form.add(colliderCombo);

        form.add(new JLabel("CSV fajl:"));
        form.add(csvField);

        JButton startBtn = new JButton("Pokreni igru");
        startBtn.addActionListener(e -> runGame());

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(startBtn, BorderLayout.SOUTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(outputArea);

        frame.add(top, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);

        frame.setVisible(true);
    }

   
    private void runGame() {
        try {
            String name = nameField.getText();
            int health = Integer.parseInt(healthField.getText().trim());
            int px = Integer.parseInt(xField.getText().trim());
            int py = Integer.parseInt(yField.getText().trim());
            String csv = csvField.getText().trim();

          
            Collidable playerCollider;

            if (colliderCombo.getSelectedItem().equals("Rectangle")) {
                RectangleCollider rc = new RectangleCollider(PRAVOUGAONIK_SIRINA, PRAVOUGAONIK_VISINA);
                rc.setPosition(px, py);
                playerCollider = rc;
            } else {
                CircleCollider cc = new CircleCollider(KRUG_POLUPRECNIK);
                cc.setPosition(px, py);
                playerCollider = cc;
            }

            
            Player player = new Player(name, health, px, py, playerCollider);

            
            Game game = new Game(player);

           
            List<Enemy> enemies = Game.loadEnemiesFromCSV(csv);
            for (Enemy e : enemies) {
                game.addEnemy(e);
            }

           
            game.resolveCollisions();

          
            StringBuilder sb = new StringBuilder();

            sb.append("STATUS IGRACA:\n");
            //da bi bilo stto citljivije i sto slicnije datom primjeru
            sb.append(player).append("\n\n");

            sb.append("NEPRIJATELJI U IGRI:\n");
            for (Enemy e : enemies) sb.append(e).append("\n");
            sb.append("\n");

            sb.append("U KOLIZIJI SA IGRACEM:\n");
            for (Enemy e : game.collidingWithPlayer()) sb.append(e.getDisplayName()).append("\n");
            sb.append("\n");

            sb.append("DOGAĐAJI :\n");
            for (String line : game.getLog()) sb.append(line).append("\n");

            outputArea.setText(sb.toString());

            
            if (player.getHealth() <= 0) {
                JOptionPane.showMessageDialog(frame,
                        "Igrac je porazen...",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Igrac je prezivio",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                    "Greška: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameApp().initUI());
    }
}
