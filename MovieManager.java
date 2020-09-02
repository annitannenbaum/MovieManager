import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Random;
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
    private ArrayList<String> watchlist;
    private ArrayList<Set> actorsToCompare;
    private Random rand;

    /**
     * Constructor for creating a new List of Movies and a Watchlist
     */
    public MovieManager()
    {
        movies = new ArrayList<>();
        watchlist = new ArrayList<>();
        rand = new Random();

    }

    public void addWatchlistTest() {
        watchlist.add(new String("Snowpiercer"));
        watchlist.add(new String("LotR"));
        watchlist.add(new String("Spiderman"));
        watchlist.add(new String("Spiderman 2"));
        watchlist.add(new String("BlueJ Test Movie"));
        watchlist.add(new String("LotR - 2"));
        watchlist.add(new String("Batman v Superman"));
        watchlist.add(new String("Movie"));
    }

    public void addMovieTest() {
        movies.add(new Movie("Snowpiercer"));
        movies.add(new Movie("LotR"));
        movies.add(new Movie("Spiderman"));
        movies.add(new Movie("Spiderman 2"));
        movies.add(new Movie("BlueJ Test Movie"));
        movies.add(new Movie("LotR - 2"));
        movies.add(new Movie("Batman v Superman"));
        movies.add(new Movie("Movie"));
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

    public void addMetadata (String title, String key, String value) {
        boolean searching = true; 
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                searching = false;
                movie.addMetaData(key, value);

            }
        }

        if (searching) {
            System.out.println("No movie with that title found.");
        }
    }

    public void showMetadata(String title) {
        for(Movie movie : movies) {
            if (movie.getTitle().equals(title)) {

                System.out.println(movie.getMetaData());

            }   
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
public void searchMovie(String title)
    {

        for (Movie movie : movies) {
            String t = movie.getTitle();
            if (t.contains(title)) {

                System.out.println(movie.getTitle());
            }
        }

    }
    public void searchCastMember(String actor)
    {

        System.out.println(actor + ":");
        for (Movie movie : movies) {
            HashMap cast = movie.getCast();
            if (cast.containsKey(actor)) {

                System.out.println(movie.getTitle());
            }
        }

    }

    public void searchGenre(String genrename)
    {
        boolean searching = true;
        for (Movie movie : movies) {
            HashMap metadata = movie.getMetaData();
            if (metadata.containsKey("Genre")){
                if (metadata.containsValue(genrename)) {
                    searching = false;
                    System.out.println(movie.getTitle());
                }
            }
        }

        if (searching) {
            System.out.println("No movie with that genre found.");
        }
    }

    public void empfohleneFilme()
    {
        int index = rand.nextInt(watchlist.size());
        String suggA = watchlist.get(index);
        System.out.println("Dir wird der Film " + suggA + " empfohlen!");

        int index2 = rand.nextInt(watchlist.size());
        if (index2 == index) { index2 ++;}
        if (index2 > watchlist.size()) {
            index2 = index2 - 2;}
        String suggB = watchlist.get(index2);
        System.out.println("Dir wird der Film " + suggB + " empfohlen!");

        int index3 = rand.nextInt(watchlist.size());
        if (index3 == index) { index3 = index3 + 2;}
        if (index3 == index2) {index3++;}
        if (index3 > watchlist.size()) {index3 = index3 -3;}
        String suggC = watchlist.get(index3);
        System.out.println("Dir wird der Film " + suggC + " empfohlen!");
    }

    public void searchUnseenMovies()
    {

        for (Movie movie : movies) {
            boolean seen = movie.getAlreadySeen();
            if (seen == false) {

                System.out.println(movie.getTitle());
            }
        }

    }

    public void searchWatchlistMovie(String title)
    {

        for (String movie : watchlist) {
            if (movie.contains(title)){
                System.out.println(movie);   
            }

        }

    }

    public void  searchMinLenghtMovie (int lenght)
    { 

        for (Movie movie : movies) {
            int runtime = movie.getRuntime();
            if(runtime >= lenght)
            { 
                System.out.println(movie.getTitle());
            }
        }

    }

    public void  searchMovieReleasePeriod (int start, int end)
    { 
        for (Movie movie : movies) {
            int release = movie.getReleaseYear();
            if(release >= start &  release <= end){
                System.out.println(movie.getTitle());
            }
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
            watchlist.add(new String(title));
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
