public class SingleFileSuperPixelDuegon {
    private static final int MAP_WIDTH = 12;
    private static final int MAP_HEIGHT = 12;

    static class Tile {
        final int x;
        final int y;
        String type;

        Tile(int x, int y, String type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static class Player {
        String name;
        int x;
        int y;
        int hp = 20;
        int gold = 0;
        int attack = 3;

        Player(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

    static class Game {
        final Tile[][] map = new Tile[MAP_HEIGHT][MAP_WIDTH];
        final Player player;
        final java.util.List<Player> enemies = new java.util.ArrayList<>();
        final java.util.Random random = new java.util.Random();

        Game(String playerName) {
            generateMap();
            this.player = new Player(playerName, 1, 1);
            spawnEnemies();
        }

        void generateMap() {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                for (int x = 0; x < MAP_WIDTH; x++) {
                    if (x == 0 || y == 0 || x == MAP_WIDTH - 1 || y == MAP_HEIGHT - 1) {
                        map[y][x] = new Tile(x, y, "WALL");
                    } else if ((x == 5 && y == 5) || (x == 8 && y == 4) || (x == 3 && y == 8)) {
                        map[y][x] = new Tile(x, y, "CHEST");
                    } else if (x == 10 && y == 10) {
                        map[y][x] = new Tile(x, y, "EXIT");
                    } else {
                        map[y][x] = new Tile(x, y, "FLOOR");
                    }
                }
            }
        }

        void spawnEnemies() {
            enemies.add(new Player("Goblin", 8, 8));
            enemies.add(new Player("Skeleton", 9, 2));
            enemies.add(new Player("Bandit", 6, 9));
        }

        void printIntro() {
            System.out.println("\n=== Super Pixel Duegon (Single File Demo) ===");
            System.out.println("Commands:");
            System.out.println("  move north | south | east | west");
            System.out.println("  attack");
            System.out.println("  tile:x:y:TYPE");
            System.out.println("  status");
            System.out.println("  chat <message>");
            System.out.println("  help");
            System.out.println("  quit");
            System.out.println("========================================\n");
            printMap();
        }

        void printMap() {
            System.out.println("Map:");
            for (int y = 0; y < MAP_HEIGHT; y++) {
                StringBuilder line = new StringBuilder();
                for (int x = 0; x < MAP_WIDTH; x++) {
                    char ch = '.';
                    if (x == player.x && y == player.y) {
                        ch = 'P';
                    } else {
                        for (Player enemy : enemies) {
                            if (enemy.x == x && enemy.y == y) {
                                ch = 'E';
                                break;
                            }
                        }
                        if (ch == '.' && map[y][x].type.equals("WALL")) {
                            ch = '#';
                        } else if (ch == '.' && map[y][x].type.equals("CHEST")) {
                            ch = 'C';
                        } else if (ch == '.' && map[y][x].type.equals("EXIT")) {
                            ch = 'X';
                        }
                    }
                    line.append(ch).append(' ');
                }
                System.out.println(line);
            }
            System.out.println("Player: " + player.name + " | HP: " + player.hp + " | Gold: " + player.gold);
            System.out.println();
        }

        void handleCommand(String input) {
            String line = input.trim();
            if (line.isEmpty()) {
                return;
            }

            if (line.equalsIgnoreCase("help")) {
                System.out.println("Try: move north | attack | status | chat hello | tile:5:8:WALL | quit");
                return;
            }

            if (line.equalsIgnoreCase("status")) {
                System.out.println("Name: " + player.name);
                System.out.println("Position: (" + player.x + ", " + player.y + ")");
                System.out.println("HP: " + player.hp + " | Gold: " + player.gold + " | Attack: " + player.attack);
                return;
            }

            if (line.startsWith("chat ")) {
                System.out.println("[" + player.name + "] " + line.substring(5));
                return;
            }

            if (line.startsWith("tile:")) {
                handleTileCommand(line);
                return;
            }

            if (line.startsWith("move ")) {
                String dir = line.substring(5).trim().toLowerCase();
                int dx = 0;
                int dy = 0;
                switch (dir) {
                    case "north": dy = -1; break;
                    case "south": dy = 1; break;
                    case "east": dx = 1; break;
                    case "west": dx = -1; break;
                    default:
                        System.out.println("Unknown direction: " + dir);
                        return;
                }
                movePlayer(dx, dy);
                return;
            }

            if (line.equalsIgnoreCase("attack")) {
                attackNearestEnemy();
                return;
            }

            System.out.println("Unknown command. Type 'help' to see available commands.");
        }

        void handleTileCommand(String command) {
            String[] parts = command.split(":");
            if (parts.length < 4) {
                System.out.println("Invalid tile command. Example: tile:5:8:WALL");
                return;
            }
            try {
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                String type = parts[3].toUpperCase();

                if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
                    System.out.println("Tile position out of map bounds.");
                    return;
                }

                map[y][x] = new Tile(x, y, type);
                System.out.println("Tile changed to " + type + " at (" + x + ", " + y + ")");
            } catch (NumberFormatException e) {
                System.out.println("Coordinates must be numeric.");
            }
        }

        void movePlayer(int dx, int dy) {
            int nextX = player.x + dx;
            int nextY = player.y + dy;

            if (nextX < 0 || nextY < 0 || nextX >= MAP_WIDTH || nextY >= MAP_HEIGHT) {
                System.out.println("You cannot leave the map.");
                return;
            }

            Tile tile = map[nextY][nextX];
            if (tile.type.equals("WALL")) {
                System.out.println("This tile is blocked by a wall.");
                return;
            }

            player.x = nextX;
            player.y = nextY;
            System.out.println("Moved to (" + player.x + ", " + player.y + ")");

            if (tile.type.equals("CHEST")) {
                player.gold += 10 + random.nextInt(15);
                tile.type = "FLOOR";
                System.out.println("You found a chest! +" + (10 + random.nextInt(15)) + " gold");
            }

            if (tile.type.equals("EXIT")) {
                System.out.println("You reached the exit. Victory!");
            }

            for (Player enemy : enemies) {
                if (enemy.x == player.x && enemy.y == player.y) {
                    System.out.println("Enemy spotted: " + enemy.name + "! Type 'attack' to fight.");
                }
            }

            printMap();
        }

        void attackNearestEnemy() {
            Player target = null;
            int bestDistance = Integer.MAX_VALUE;

            for (Player enemy : enemies) {
                int distance = Math.abs(player.x - enemy.x) + Math.abs(player.y - enemy.y);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    target = enemy;
                }
            }

            if (target == null) {
                System.out.println("No enemies nearby.");
                return;
            }

            if (bestDistance > 1) {
                System.out.println("Target too far away. Move closer to " + target.name + ".");
                return;
            }

            int damage = 2 + random.nextInt(4);
            target.hp -= damage;
            System.out.println("You hit " + target.name + " for " + damage + " damage.");

            if (target.hp <= 0) {
                System.out.println(target.name + " is defeated!");
                player.gold += 5;
                enemies.remove(target);
            } else {
                int counter = 1 + random.nextInt(3);
                player.hp -= counter;
                System.out.println(target.name + " counterattacked for " + counter + " damage.");
            }

            if (player.hp <= 0) {
                System.out.println("You died. Game over.");
                System.exit(0);
            }

            printMap();
        }
    }

    public static void main(String[] args) throws Exception {
        String playerName = (args.length > 0) ? args[0] : "Player1";
        Game game = new Game(playerName);
        game.printIntro();

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }
            game.handleCommand(line);
        }
        scanner.close();
    }
}
