package ca.ciccc.model;

public enum Genre {
    Fiction("Fiction"), NonFiction("Non Fiction"), SciFi("Sci-Fi"), History("History"), Children("Children");

    private String genre;

    private Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

}
