package scrapper;

public class Scrapper {
    private String urlToScrapFrom;

    public Scrapper(String urlToScrapFrom) {
        this.urlToScrapFrom = urlToScrapFrom;
    }

    public void scrap() {
        System.out.println(String.format("Scrapping from => %s", urlToScrapFrom));
    }
}
