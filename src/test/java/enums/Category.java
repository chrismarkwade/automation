package enums;

/**
 * @author Chris Wade
 */

public enum Category {
    
    APPSTORE                ("Appstore for Android"),
    BOOKS                   ("Books & Audible"),
    MUSIC                   ("Music"),
    PRIME_PHOTOS            ("Prime Photos & Drive"),
    MOVIES_TV               ("Movies & TV Shows"),
    KINDLE                  ("Kindle"),
    ELECTRONICS             ("Electronics"),
    SOFTWARE                ("Software"),
    VIDEO_GAMES             ("Video Games"),
    HOME_KITCHEN_PETS       ("Home, Kitchen, & Pets"),
    TOOLS_PATIO_GARDEN      ("Tools, Patio & Garden"),
    HEALTH_BEAUTY_GROCERY   ("Health, Beauty, & Grocery"),
    TOYS_BABY               ("Toys & Baby"),
    CLOTHING_SHOES_JEWELRY  ("Clothing, Shoes & Jewelry"),
    SPORTS_OUTDOORS         ("Sports & Outdoors"),
    AUTOMOTIVE_INDUSTRIAL   ("Automotive & Industrial"),
    BOUTIQUES_FRANCOPHONES  ("Boutiques Francophones");
    
    private String category;
    
    private Category(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }
    
}
