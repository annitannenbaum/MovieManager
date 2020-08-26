import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
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
    private ArrayList<Set> actorsToCompare;

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
    
    public void printCast(String title)
    {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                System.out.println(movie.getCast());
            }
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
    
    public void searchGenre(String genrename)
    {
        boolean searching = true;
        for (Movie movie : movies) {
            HashMap metadata = movie.getMetaData();
            if (metadata.containsValue(genrename)) {
                searching = false;
                System.out.println(movie.getTitle());
            }
        }
        
        if (searching) {
            System.out.println("No movie with that genre found.");
        }
    }
    
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
    
    public void printMostCommonActor()
    {
        actorsToCompare = new ArrayList<>();
        
        for (Movie movie : movies) { // retrieve the actors from the cast-HashMap for each movie and add the Sets to a List
            HashMap actors = movie.getCast();
            Set actorList = actors.keySet();
            actorsToCompare.add(actorList);
        }
        // Turn the ArrayList into an Array for easy sorting
        String[] actorArr = actorsToCompare.toArray(new String[actorsToCompare.size()]);
        Arrays.sort(actorArr);
        int mostCount = 0;
        int lastCount = 0;
        String mostCommon = "";
        String last = "";
        
        for (String actor : actorArr) {
            if (actor.equals(last)) {
                lastCount++;
            } else if (lastCount > mostCount) {
                mostCount = lastCount;
                mostCommon = last;
            }
            last = actor;
            System.out.println("The actor starring in most movies is: " + last + ".");
        }
    }
}
