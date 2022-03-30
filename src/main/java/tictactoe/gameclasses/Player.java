package tictactoe.gameclasses;

public class Player {

    String id;
    String name;
    String symbol;

    public Player(){}

    public Player(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Player " + id + " -> "
                + name + " is winner as "
                + "'" + symbol + "'!";
    }
}

