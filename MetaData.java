
/**
 * Beschreiben Sie hier die Klasse MetaData.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class MetaData
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int ageRating;
    private String genre;
    private String director;
    private int budget;

    /**
     * Konstruktor für Objekte der Klasse MetaData
     */
    public MetaData()
    {
        ageRating = 0;
        genre = "";
        director = "";
        budget = 0;
    }

    public void setageRating(int ageRating)
    {
        this.ageRating = ageRating;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    
    public void setDirector(String director)
    {
        this.director = director;
    }
    
    public void setBudget(int budget)
    {
        this.budget = budget;
    }
    
    public int getAgeRating()
    {
        return ageRating;
    }
    
    public String getGenre()
    {
        return genre;
    }
    
    public String getDirector()
    {
        return director;
    }
    
    public int getBudget()
    {
        return budget;
    }
}
