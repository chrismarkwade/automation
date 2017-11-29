package enums;

/**
 * @author Chris Wade
 */

public enum Department {
    
    AUTOMOTIVE              ("Automotive", "All Automotive"),
    BOOKS                   ("Books", "All Books"),
    BABY                    ("Baby", "All Baby"),
    COMPUTERS               ("Computers", "All Computers"),
    ELECTRONICS             ("Electronics", "All Electronics"),
    JEWELRY                 ("Jewelry", "Jewelry"),
    KINDLE                  ("Kindle Store", "Kindle"),
    LUGGAGE_BAGS            ("Luggage & Bags", "Luggage & Bags"),
    MOVIES_TV               ("Movies & TV", "All Movies & TV Shows"),
    MUSIC                   ("Music", "All Music"),
    SOFTWARE                ("Software", "All Software"),
    TOOLS_HOME_IMPROVEMENT  ("Tools & Home Improvement", "All Tools & Home Improvement"),
    TOYS_GAMES              ("Toys & Games", "All Toys & Games"),
    WATCHES                 ("Watches", "Watches");
    
    private String name;
    private String linkText;
    
    private Department(String name, String linkText) {
        this.name = name;
        this.linkText = linkText;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLinkText() {
        return linkText;
    }
    
}
