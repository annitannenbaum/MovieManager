import java.util.Arrays;
/**
 * Beschreiben Sie hier die Klasse Materials.
 * 
 * @author Anni 
 * @version 16.08.2020
 */
public class Materials
{
    // Instanzvariablen
    private String name;
    private int[] volume;
    private double weight;
    private String description;

    /**
     * Konstruktor für Objekte der Klasse Materials
     */
    public Materials(String name)
    {
        // Instanzvariable initialisieren
        this.name = name;
        volume = new int[3];
        weight = 0. ;
        description = "";
    }

    public void setVolume(int width, int height, int depth)
    {
        volume[0] = width;
        volume[1] = height;
        volume[2] = depth;
    }
    
    public void setWeight (double weight)
    {
        this.weight = weight;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getName()
    {
        return name;
    }

    public int[] getVolume()
    {
        return volume;
    }
    
    public String getVolumeS()
    {
        return Arrays.toString(volume);
    }

    public double getWeight()
    {
        return weight;
    }

    public String getDescription()
    {
        return description;
    }

    public int calcVolume()
    {
        int cbVol = 0;
        cbVol = volume[0]*volume[1]*volume[2];
        return cbVol;
    }
}
