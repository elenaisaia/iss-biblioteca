package Model;

public enum Status {
    nerestituit, restituit;

    public static Status getStatus(String status){
        return switch (status) {
            case "restituit" -> restituit;
            case "nerestituit" -> nerestituit;
            default -> null;
        };
    }
}
