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
    

    /**
     * Constructor for creating a new List of Movies and a Watchlist
     */
    public MovieManager()
    {
        movies = new ArrayList<>();
        watchlist = new ArrayList<>();
        

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


    public ArrayList searchMovie(String title)
    {
        ArrayList<String> search;
        search = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().contains(title)) {

                search.add(new String(movie.getTitle()));
            }
        }
        return search;
    }

    public void searchTest()
    {ArrayList<String> test;
        test = new ArrayList<>();
        test = suggestedMovies();
        for(String title : test){ 
            System.out.println(title);
        }
    }

    public ArrayList searchCastMember(String actor)
    {
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

    public ArrayList searchGenre(String genrename)
    {
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

    public ArrayList suggestedMovies()
    {   Random rand;
        rand = new Random();
        ArrayList<String> search;
        search = new ArrayList<>();
        
        int index = rand.nextInt(watchlist.size());
        String suggA = watchlist.get(index);
        search.add(new String(suggA));

        int index2 = rand.nextInt(watchlist.size());
        if (index2 == index) { index2 ++;}
        if (index2 > watchlist.size()) {
            index2 = index2 - 2;}
        String suggB = watchlist.get(index2);
        search.add(new String(suggB));

        int index3 = rand.nextInt(watchlist.size());
        if (index3 == index) { index3 = index3 + 2;}
        if (index3 == index2) {index3++;}
        if (index3 > watchlist.size()) {index3 = index3 -3;}
        String suggC = watchlist.get(index3);
        search.add(new String(suggC));
        
        return search;
    }

    public ArrayList searchUnseenMovies()
    {
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

    public ArrayList searchWatchlistMovie(String title)
    {
        ArrayList<String> search;
        search = new ArrayList<>();
        for (String movie : watchlist) {
            if (movie.contains(title)){
                search.add(new String(movie));   
            }

        }
        return search;
    }

    public ArrayList  searchMinLenghtMovie (int lenght)
    { 
        ArrayList<String> search;
        search = new ArrayList<>();
        for (Movie movie : movies) {
            int runtime = movie.getRuntime();
            if(runtime >= lenght)
            { 
                search.add(new String(movie.getTitle()));
            }
        }
        return search;
    }

    public ArrayList  searchMovieReleasePeriod (int start, int end)
    {   ArrayList<String> search;
        search = new ArrayList<>();
        for (Movie movie : movies) {
            int release = movie.getReleaseYear();
            if(release >= start &  release <= end){
                search.add(new String(movie.getTitle()));
            }
        }
        return search;
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
