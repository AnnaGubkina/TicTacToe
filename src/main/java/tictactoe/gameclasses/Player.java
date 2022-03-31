package tictactoe.gameclasses;

public class Player {

    String id;
    String name;
    String symbol;
    int rating;

    public Player(){}

    public Player(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rating = 0;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Player " + id + " -> "
                + name + " is winner as "
                + "'" + symbol + "'!";
    }


}

