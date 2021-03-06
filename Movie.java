import java.util.*;
/**
 * A basic class that creates Objects of the type Movie.
 * 
 * @author Anni
 * @version 31.08.2020
 */
public class Movie
{
    private String title;
    private String description;
    private int runtime;
    private int yearOfRelease;
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
        cast = new HashMap();
        alreadySeen = false;
        yearOfRelease = 0;
        metadata = new HashMap();
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
    
    public void setYearOfRelease(int yearOfRelease)
    {
        this.yearOfRelease = yearOfRelease;
    }
    

    // Adds an actor/role pair to the cast 
    public void addCast(String actor, String role)
    { 
        cast.put(actor, role);
    }
    
    public void setMetaData(String key, String value)
    {
        metadata.put(key, value);
    }
    
    public HashMap getMetaData()
    {
        return metadata;
    }
    
    public void removeMetaData(String key)
    {
        metadata.remove(key);
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
    
    public String getDescription()
    {
        return description;
    }

    public HashMap getCast()
    {
        return cast;
    }

    public boolean getAlreadySeen()
    {
        return alreadySeen;
    }
    
    public int getYearOfRelease()
    {
        return yearOfRelease;
    }
    
    public int getTitleLength(String title)
    {
        StringBuilder string = new StringBuilder(title);
        int titleLength = string.length();
        
        return titleLength;
    }
    
    public String trimTitle(String title)
    {
        StringBuilder string = new StringBuilder(title);
        string.setLength(21);
        string.replace(18, 22, "...");
        
        title = string.toString();
        return title;
    }
    
    // Dynamically appends padding to a Movie title
    public String padTitle(String title)
    {
        StringBuilder string = new StringBuilder(title);
        int diff = 21 - string.length();
        String toAppend = " ";
        int i = 0;
        String result = "";
        
        while (i < diff) {
            result = result + toAppend;
            i++;
        }
        
        
        title = string.toString() + result;
        return title;
    }
}
