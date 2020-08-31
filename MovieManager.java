import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
/**
 * A tool to manage different movies. Contains movie names as well as metadata.
 * 
 * @author Anni 
 * @version 31.08.2020
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

    public void addMetaData(String title, String key, String value)
    {
        boolean searching = true;

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                movie.setMetaData(key, value);
            }
        }

        if (searching) {
            System.out.println("No movie with that title found.");
        }
    }

    public void removeMetaData(String title, String key)
    {
        boolean searching = true;

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                movie.removeMetaData(key);
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
        } else {
            System.out.println("Movie was either not found or already watched.");
        }
    }

    public void removeFromWatchlist(String title)
    {
        boolean searching = true;
        for (Movie movie : watchlist) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                watchlist.remove(title);
            }
        }

        if (searching) {
            System.out.println("No title found!");
        }
    }
    
    // Set to true if you want to set every movie to already seen, set to false if you want to set them to unseen.
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
            last = actor;
            if (actor.equals(last)) {
                lastCount++;
            } else if (lastCount > mostCount) {
                mostCount = lastCount;
                mostCommon = last;
            }
        }
        System.out.println("The actor starring in most movies is: " + mostCommon + ".");
    }

    public void printTotalMoviesWithRuntime()
    {
        int runtimeCount = 0;

        for (Movie movie : movies) {
            runtimeCount = runtimeCount + movie.getRuntime();
        }

        System.out.println("Total number of movies: " + movies.size());
        System.out.println("Total runtime: " + runtimeCount);
    }

    public void printWatchedWithRuntime()
    {
        int runtimeCount = 0;
        int movieCount = 0;

        for (Movie movie : movies) {
            if (movie.getAlreadySeen()) {
                runtimeCount = runtimeCount + movie.getRuntime();
                movieCount++;
            }
        }

        System.out.println("Total number of unwatched movies: " + movieCount);
        System.out.println("Total runtime of unwatched movies: " + runtimeCount);
    }

    public void printUnwatchedWithRuntime()
    {
        int runtimeCount = 0;
        int movieCount = 0;

        for (Movie movie : movies) {
            if (movie.getAlreadySeen() == false) {
                runtimeCount = runtimeCount + movie.getRuntime();
                movieCount++;
            }
        }

        System.out.println("Total number of unwatched movies: " + movieCount);
        System.out.println("Total runtime of unwatched movies: " + runtimeCount);
    }
    
    public void printWatchlistWithRuntime()
    {
        int runtimeCount = 0;

        for (Movie movie : watchlist) {
            runtimeCount = runtimeCount + movie.getRuntime();
        }

        System.out.println("Total number of movies: " + watchlist.size());
        System.out.println("Total runtime: " + runtimeCount);
    }

    public void printMovieList()
    {
        System.out.println("Here's a list of all movies currently in this collection:");
        for (Movie movie : movies)
        {
            System.out.println("Title: " + movie.getTitle());
        }
    }

    public void printDetails(String title)
    {
        boolean searching = true;

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Description: " + movie.getDescription());
                System.out.println("Runtime: " + movie.getRuntime());
                System.out.println("Cast: " + movie.getCast());
                System.out.println("MetaData: " + movie.getMetaData());
                searching = false;
            }
        }
        
        if (searching) {
            System.out.println("No movie with that title found.");
        }
    }

    public void printGridView()
    {
        // First, get all currently stored titles and trim them to size

        String[] fittedTitles = new String[movies.size()];
        String fittedTitle = "";
        int index = 0;
        
        for (Movie movie : movies) {
            int len = movie.getTitleLength(movie.getTitle());

            if (len > 21){
                fittedTitle = movie.trimTitle(movie.getTitle());
            } else if (len < 21) {
                fittedTitle = movie.padTitle(movie.getTitle());
            }

            fittedTitles[index] = fittedTitle + " (" + movie.getReleaseYear() + ")"; // add the year to the array for easier printing
            index++;
        }

        // Next, create an array for each row of threes to be printed

        int rest = fittedTitles.length % 3; // determine whether there is an array with less than 3 columns
        int chunks = fittedTitles.length / 3 + (rest > 0 ? 1 : 0); //adds an extra array if there is a rest
        String[][] arrays = new String[chunks][];

        for (int i = 0; i < (rest > 0 ? 2 : chunks); i++) {
            arrays[i] = Arrays.copyOfRange(fittedTitles, i * 3, i * 6); // Exception with copyOfRange: array building doesn't work correctly. 
        }

        if (rest > 0){
            arrays[chunks - 1] = Arrays.copyOfRange(fittedTitles, (chunks - 1) * 3, (chunks - 1) *  3 + rest); // makes an extra array with less than three titles
        }
        
        // quick test if array was built correctly
        for (int i = 0; i < arrays.length; ++i) {
            for(int j = 0; j < arrays[i].length; ++j) {
                System.out.println(arrays[i][j]);
            }
        }
        
        // Finally, print each row of threes accordingly

        // int i = 0;

        // if (rest == 0) { // in case of even rows of threes
            // for (i = 0; i < arrays.length; i++) {
                // System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                // System.out.println("||                            ||                            ||                            ||");
                // System.out.println("|| " + arrays[i][0] + " || " + arrays[i][1] + " || " + arrays[i][2] + " ||");
                // System.out.println("||                            ||                            ||                            ||");
                // System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            // }
        // } else if (rest == 2) { // in case of uneven rows with 2 left
            // String[] array = arrays[i]; 
            // while (i < (arrays.length-1)) {
                // System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                // System.out.println("||                            ||                            ||                            ||");
                // System.out.println("|| " + array[0] + " || " + array[1] + " || " + array[2] + " ||");
                // System.out.println("||                            ||                            ||                            ||");
                // System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                // i++;
            // }
            // System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            // System.out.println("||                            ||                            ||");
            // System.out.println("|| " + array[0] + " || " + array[1] + " ||");
            // System.out.println("||                            ||                            ||");
            // System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        // } else {
            // String[] array = arrays[i]; 
            // while (i < (arrays.length-1)) { // in case of uneven rows with one left
                // System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                // System.out.println("||                            ||                            ||                            ||");
                // System.out.println("|| " + array[0] + " || " + array[1] + " || " + array[2] + " || ");
                // System.out.println("||                            ||                            ||                            ||");
                // System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                // i++;
            // }
            // System.out.println("||||||||||||||||||||||||||||||||");
            // System.out.println("||                            ||");
            // System.out.println("|| " + array[0] + " ||");
            // System.out.println("||                            ||");
            // System.out.println("||||||||||||||||||||||||||||||||");
        // }

    }

}
