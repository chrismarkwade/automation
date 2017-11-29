package enums;

/**
 * @author Chris Wade
 */

public enum Product {
    
    A_MAN_ON_THE_MOON   ("A Man on the Moon: The Voyages of the Apollo Astronauts"),
    ASTROPHYSICS        ("Astrophysics for People in a Hurry"),
    CALCULUS            ("Calculus: Early Transcendentals"),
    COSMOS              ("Cosmos"),
    DEATH_BY_BLACK_HOLE ("Death By Black Hole: And Other Cosmic Quandries"),
    GRAVITY             ("Gravity: An Introduction to Einstein's General Relativity"),
    INTERSTELLAR        ("Interstellar [Blu-ray]"),
    PALE_BLUE_DOT       ("Pale Blue Dot: A Vision of the Human Future in Space");
    
    private String title;
    
    private Product(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
}
