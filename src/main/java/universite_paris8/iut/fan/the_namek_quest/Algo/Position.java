package universite_paris8.iut.fan.the_namek_quest.Algo;

/**
 * Classe Position
 * ---------------
 * Représente une position (x, y) sur la grille du jeu.
 * Sert à identifier les cases pour les algorithmes de cheminement (BFS, Grille, etc.).
 * Implémente hashCode() et equals() pour permettre l’utilisation dans des collections (HashMap, HashSet).
 */
public class Position {
    private int x;
    private int y;
    /**
     * Constructeur
     * @param x Coordonnée X
     * @param y Coordonnée Y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Calcule un code de hachage unique pour la position.
     * Permet l’utilisation dans des structures de données comme HashMap/HashSet.
     */
    @Override
    public int hashCode() {
        return x * 31 + y;
    }

    /**
     * Vérifie l’égalité entre deux positions (mêmes coordonnées x et y).
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Position other = (Position) obj;
            if (this.x != other.x) {
                return false;
            } else {
                return this.y == other.y;
            }
        }
    }



    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Affiche la position sous forme de chaîne de caractères.
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}