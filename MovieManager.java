import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 * A tool to manage different movies. Contains movie names as well as metadata.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class MovieManager
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private ArrayList<Movie> movies;
    private ArrayList<Movie> watchlist;

    /**
     * Constructor for creating a new List of Movies and a Watchlist
     */
    public MovieManager()
    {
        movies = new ArrayList<>();
        watchlist = new ArrayList<>();
    }

    public void addMovie(String title)
    {
        boolean found = false;

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                found = true;
            }
        }

        if (found) {
            System.out.println(title + " already exists.");
        } else {
            movies.add(new Movie (title));
        }
    }

    public void removeMovie(String title)
    {
        boolean searching = true;

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                movies.remove(title);
            }
        }

        if (searching) {
            System.out.println("No movie with that title found.");
        }
    }
    
    public void addDetails(String title, int runtime, String description, int releaseYear)
    {
        boolean searching = true; 
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                movie.setRuntime(runtime);
                movie.setDescription(description);
                movie.setReleaseYear(releaseYear);
            }
        }
        
        if (searching) {
            System.out.println("No movie with that title found.");
        }
    }
    
    public void addCastMember(String title, String actor, String role)
    {
        boolean searching = true;
        
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                movie.addCast(actor, role);
            }
        }
        
        if (searching) {
            System.out.println("No movie with that title found.");
        }
    }
    
    public void removeCastMember(String title, String actor)
    {
        boolean searching = true;
        
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                movie.removeCast(actor);
            }
        }
        
        if (searching) {
            System.out.println("No movie with that title found.");
        }
    }
    
    // public void searchGenre(String genrename)
    // {
        // boolean searching = false;
        // for (Movie movie : movies) {
            // if (movie.getMetaData().containsValue(genrename)) {
                // searching = false;
              
            // }
        // }
        
        // if (searching) {
            // System.out.println("No movie with that genre found.");
        // }
    // }
    
    public void addToWatchlist(String title)
    {
        boolean found = false;

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title) && movie.getAlreadySeen() == false) {
                found = true;
            }
        }

        if (found) {
            watchlist.add(new Movie(title));
        }
    }

    public void removeFromWatchlist(String title)
    {
        for (Movie movie : watchlist) {
            if (movie.getTitle().equals(title)) {
                watchlist.remove(title);
            } else {
                System.out.println("No title found!");
            }
        }
    }
    
    public void toggleAllAlreadySeen(boolean seen)
    {
         if (seen) {
             for (Movie movie : movies) {
                 movie.setAlreadySeenT();
             }
        } else {
            for (Movie movie : movies) {
                movie.setAlreadySeenF();
            }
        }
    }
}
