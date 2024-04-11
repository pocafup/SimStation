package simstation;
import mvc.*;

public enum Heading {
    NORTH, EAST, SOUTH, WEST;

    public static Heading parse(String heading) {
        if (heading.equalsIgnoreCase("north")) return NORTH;
        if (heading.equalsIgnoreCase("south")) return SOUTH;
        if (heading.equalsIgnoreCase("west")) return WEST;
        if (heading.equalsIgnoreCase("east")) return EAST;
        Utilities.error("Invalid Heading: ");
        return null;
    }

    public static Heading random() {
        int luck = Utilities.rng.nextInt(4);
        return switch (luck) {
            case 0 -> NORTH;
            case 1 -> SOUTH;
            case 2 -> EAST;
            default -> WEST;
        };
    }

}

