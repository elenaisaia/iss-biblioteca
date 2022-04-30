package Model;

public enum Stare {
    noua, buna, satisfacatoare, rea;

    public static Stare getStare(String stare){
        return switch (stare) {
            case "noua" -> noua;
            case "buna" -> buna;
            case "satisfacatoare" -> satisfacatoare;
            case "rea" -> rea;
            default -> null;
        };
    }
}
