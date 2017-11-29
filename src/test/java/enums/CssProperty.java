package enums;

/**
 * @author Chris Wade
 */

public enum CssProperty {
    
    COLOR           ("color"),
    DISPLAY         ("display"),
    FONT_FAMILY     ("font-family"),
    FONT_SIZE       ("font-size"),
    POSITION        ("position"),
    TEXT_ALIGN      ("text-align");
    
    private String property;
    
    private CssProperty(String property) {
        this.property = property;
    }
    
    public String getProperty() {
        return property;
    }
    
}
