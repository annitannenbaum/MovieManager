import java.util.HashMap;
/**
 * A basic class that creates Objects of the type Movie.
 * 
 * @author Anni
 * @version 18.08.2020
 */
public class Movie
{
    private String title;
    private String description;
    private int runtime;
    private int releaseYear;
    private HashMap cast;
    private boolean alreadySeen;
    private HashMap metadata;

    /**
     * Constructor for Objects of the class Movie.
     */
    public Movie(String title)
    {
        this.title = title;
        runtime = 0;
        description = "";
        HashMap<String, String> cast = new HashMap<String, String>();
        alreadySeen = false;
        releaseYear = 0;
        HashMap<String, String> metadata = new HashMap<String, String>();
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setRuntime(int runtime)
    {
        this.runtime = runtime;
    }

    public void setAlreadySeenT()
    {
        if (alreadySeen == false) {
           alreadySeen = true;
        }
    }
    
    public void setAlreadySeenF()
    {
        if (alreadySeen) {
            alreadySeen = false;
        }
    }
    
    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = releaseYear;
    }
    

    // Adds an actor/role pair to the cast 
    public void addCast(String actor, String role)
    {
        cast.put(actor, role);
    }
    
    public void addMetaData(String key, String value)
    {
        metadata.put(key, value);
    }
    
    public HashMap getMetaData()
    {
        return metadata;
    }
    
    public void removeCast(String actor)
    {
        cast.remove(actor);
    }

    public String getTitle()
    {
        return title;
    }

    public int getRuntime()
    {
        return runtime;
    }

    public HashMap getCast()
    {
        return cast;
    }

    public boolean getAlreadySeen()
    {
        return alreadySeen;
    }
}
