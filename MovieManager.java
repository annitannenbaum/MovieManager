import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Random;
import java.util.Iterator;

/**
 * A tool to manage different movies. Contains movie names as well as metadata.
 * 
 * @author Anna Hoege, Bendix Falke, Paul Carpus
 * @version 06.09.2020
 */

public class MovieManager
{
    private ArrayList<Movie> movies;
    private ArrayList<Movie> watchlist;
    private ArrayList<Set> actorsToCompare;
    private String movie;

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
        Iterator<Movie> iterator = movies.iterator();
        boolean searching = true;

        if (searching == true) {
            while (iterator.hasNext()) {
                Movie movie = iterator.next();
                movies.remove(movie);
                this.removeFromWatchlist(title);
                searching = false;
            }
        } else {
            System.out.println("No movie with that title found.");
        }
    }

    public void addDetails(String title, int runtime, String description, int yearOfRelease)
    {
        boolean searching = true; 

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                movie.setRuntime(runtime);
                movie.setDescription(description);
                movie.setYearOfRelease(yearOfRelease);
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
        Iterator<Movie> it = watchlist.iterator();
        
        if (watchlist.size() > 1) {
            while (it.hasNext()) {
                Movie movie = it.next();
                watchlist.remove(movie);
            }
        } else {
            watchlist.clear();
        }
    
    }

    // Set to true if you want to set every movie to already seen, set to false if you want to set them to unseen.

    public void toggleAllAlreadySeen(boolean seen) 
    {
        if (seen) {
            for (Movie movie : movies) {
                movie.setAlreadySeenT();
                watchlist.remove(movie.getTitle());
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

    public double totalRuntime () {
        double total = 0.;

        for (Movie movie : movies) {
            total = total + movie.getRuntime();
        }
        return total;
    }

    private int numberOfMovies () {
        int total = 0;

        for (Movie movie : movies) {
            total++ ;
        }
        return total;
    }

    public double averageRuntime () {
        double total = 0.;

        for (Movie movie : movies) {
            total = total + movie.getRuntime();
        }
        total = total / this.numberOfMovies();
        return total;
    }

    public String movieRecommendations() {
        int i = 0;
        String total = "";

        if (watchlist.size()>2) { //Damit keine Endlosschleife erzeugt wird.
            while (i<3) {
                Movie movie = watchlist.get(new Random().nextInt(watchlist.size()));
                if (false==total.contains(movie.getTitle())) {
                    total = total +", "+ movie.getTitle(); 
                    i++;
                }
            }
        } else {
            System.out.println ("There are not enough movies in your watchlist");
        }

        return total;
    }

    public void seeMovie (String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                movie.setAlreadySeenT();                
            }  else {
                System.out.println("No title found!");
            }
        }
        for (Movie movie : watchlist) {
            if (movie.equals(title)) {
                watchlist.remove(title);
            }
        }
    }

    public ArrayList searchCastMember(String actor) {
        ArrayList<String> search;
        search = new ArrayList<>();

        for (Movie movie : movies) {
            HashMap cast = movie.getCast();
            if (cast.containsKey(actor)) {

                search.add(new String(movie.getTitle()));
            }
        }
        return search;
    }

    public ArrayList searchGenre(String genrename) {
        ArrayList<String> search;
        search = new ArrayList<>();

        for (Movie movie : movies) {
            HashMap metadata = movie.getMetaData();

            if (metadata.containsKey("Genre")){
                if (metadata.containsValue(genrename)) {

                    search.add(new String(movie.getTitle()));
                }
            }
        }
        return search;
    }

    public ArrayList searchMovie(String title) {
        ArrayList<String> search;
        search = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getTitle().contains(title)) {

                search.add(new String(movie.getTitle()));
            }
        }
        return search;
    }

    public ArrayList searchUnseenMovies() {
        ArrayList<String> search;
        search = new ArrayList<>();

        for (Movie movie : movies) {
            boolean seen = movie.getAlreadySeen();

            if (seen == false) {

                search.add(new String(movie.getTitle()));
            }
        }
        return search;
    }

    public ArrayList searchWatchlistMovie(String title) {
        ArrayList<String> search;
        search = new ArrayList<>();

        for (Movie movie : watchlist) {
            if (movie.getTitle().contains(title)){
                search.add(new String(movie.getTitle()));   
            }

        }
        return search;
    }

    public ArrayList  searchMinLengthMovie(int length) { 
        ArrayList<String> search;
        search = new ArrayList<>();

        for (Movie movie : movies) {
            int runtime = movie.getRuntime();

            if(runtime >= length)
            { 
                search.add(new String(movie.getTitle()));
            }
        }
        return search;
    }

    public ArrayList  searchMovieReleasePeriod (int start, int end) {
        ArrayList<String> search;
        search = new ArrayList<>();

        for (Movie movie : movies) {
            int release = movie.getYearOfRelease();

            if(release >= start &  release <= end){
                search.add(new String(movie.getTitle()));
            }
        }
        return search;
    }

    public void printTotalMoviesWithRuntime() {
        int runtimeCount = 0;

        for (Movie movie : movies) {
            runtimeCount = runtimeCount + movie.getRuntime();
        }

        System.out.println("Total number of movies: " + movies.size());
        System.out.println("Total runtime: " + runtimeCount);
    }

    public void printWatchedWithRuntime() {
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

    public void printUnwatchedWithRuntime() {
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

    public void printWatchlistWithRuntime() {
        int runtimeCount = 0;

        for (Movie movie : watchlist) {
            runtimeCount = runtimeCount + movie.getRuntime();
        }

        System.out.println("Total number of movies: " + watchlist.size());
        System.out.println("Total runtime: " + runtimeCount);
    }

    public void printMovieList() {
        System.out.println("Here's a list of all movies currently in this collection:");
        for (Movie movie : movies)
        {
            System.out.println("Title: " + movie.getTitle());
        }
    }

    public void printDetails(String title) {
        boolean searching = true;

        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Description: " + movie.getDescription());
                System.out.println("Runtime: " + movie.getRuntime());
                System.out.println("Year of Release: " + movie.getYearOfRelease());
                System.out.println("Cast: " + movie.getCast());
                System.out.println("MetaData: " + movie.getMetaData());
                searching = false;
            }
        }

        if (searching) {
            System.out.println("No movie with that title found.");
        }
    }

    public void printGridView() {

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

            fittedTitles[index] = fittedTitle + " (" + movie.getYearOfRelease() + ")"; // add the year to the array for easier printing
            index++;
        }

        // Next, create an array for each row of threes to be printed
        int rest = fittedTitles.length % 3; // determine whether there is an array with less than 3 columns
        int chunks = fittedTitles.length / 3 + (rest > 0 ? 1 : 0); //adds an extra array if there is a rest
        String[][] arrays = new String[chunks][];

        for (int i = 0; i < (rest > 0 ? chunks - 1 : chunks); i++) {
            arrays[i] = Arrays.copyOfRange(fittedTitles, i * 3, i * 6); // Exception with copyOfRange: array building doesn't work correctly. 
        }

        if (rest > 0){
            arrays[chunks-1] = Arrays.copyOfRange(fittedTitles, (chunks-1) * 3, (chunks-1) *  (3 + rest)); // makes an extra array with less than three titles
        }


        // Finally, print each row of threes accordingly

        int i = 0;

        if (rest == 0) { // in case of even rows of threes
            for (i = 0; i < arrays.length; i++) {
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||                            ||                            ||                            ||");
            System.out.println("|| " + arrays[i][0] + " || " + arrays[i][1] + " || " + arrays[i][2] + " ||");
            System.out.println("||                            ||                            ||                            ||");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            }
        } else if (rest == 2) { // in case of uneven rows with 2 left
            String[] array = arrays[i]; 
            while (i < (arrays.length-1)) {
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||                            ||                            ||                            ||");
            System.out.println("|| " + array[0] + " || " + array[1] + " || " + array[2] + " ||");
            System.out.println("||                            ||                            ||                            ||");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            i++;
            }
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||                            ||                            ||");
            System.out.println("|| " + array[0] + " || " + array[1] + " ||");
            System.out.println("||                            ||                            ||");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        } else {
            String[] array = arrays[i]; 
            while (i < (arrays.length-1)) { // in case of uneven rows with one left
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||                            ||                            ||                            ||");
            System.out.println("|| " + array[0] + " || " + array[1] + " || " + array[2] + " || ");
            System.out.println("||                            ||                            ||                            ||");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            i++;
            }
            System.out.println("||||||||||||||||||||||||||||||||");
            System.out.println("||                            ||");
            System.out.println("|| " + array[0] + " ||");
            System.out.println("||                            ||");
            System.out.println("||||||||||||||||||||||||||||||||");
        }

    }

}