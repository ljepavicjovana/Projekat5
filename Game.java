import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files; 
import java.nio.file.Paths;
import java.io.IOException;

public class Game {

    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<String> log = new ArrayList<>();

    public Game(Player player) {
        this.player = player;
        log.add("Igra se pokrenula. Igrac: " + player.getName());
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<String> getLog() {
        return log;
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
        log.add("Dodan je neprijatelj: " + e.getType());
    }

   
    public boolean checkCollision(Player p, Enemy e) {
        boolean hit = p.intersects(e);

        if (hit) {
            log.add("Pogodak... " + e.getType() + " je pogodio igraca");
        }

        return hit;
    }

    public void resolveCollisions() {
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) {
                int hp = player.getHealth() - e.getDamage();
                if (hp < 0) hp = 0;

                player.setHealth(hp);
                log.add("Igracu je  skinuto " + e.getDamage() + " HP. Sada: " + hp);
            }
        }

        if (player.getHealth() <= 0) {
            log.add("Igrac je  porazen!");
        } else {
            log.add("Igrac prezivio!");
        }
    }
    public ArrayList<Enemy> collidingWithPlayer() {
        ArrayList<Enemy> list = new ArrayList<>();

        for (Enemy e : enemies) {
            if (player.intersects(e)) {
                list.add(e);
            }
        }

        return list;
    }


   
    public static ArrayList<Enemy> loadEnemiesFromCSV(String filePath) {
        ArrayList<Enemy> list = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (int i = 1; i < lines.size(); i++) {

                String line = lines.get(i);
                String[] parts = line.split(",");

                String type = parts[0].trim();
                int dmg = Integer.parseInt(parts[1].trim());
                int hp = Integer.parseInt(parts[2].trim());
                int x = Integer.parseInt(parts[3].trim());
                int y = Integer.parseInt(parts[4].trim());
                int w = Integer.parseInt(parts[5].trim());
                int h = Integer.parseInt(parts[6].trim());

                RectangleCollider collider = new RectangleCollider(w, h);
                collider.setPosition(x, y);

                Enemy e = new Enemy(type, dmg, hp, x, y, collider);

                list.add(e);
            }

        } catch (IOException e) {
            System.out.println("Greska pri citanju fajla!");
        }

        return list;
    }
}
