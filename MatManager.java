import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
/**
 * A class for organizing collections of materials. Is dependant on Materials 
 * class.
 * @author Anni 
 * @version 16.08.2020
 */
public class MatManager
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private ArrayList<Materials> materials;

    /**
     * Constructor for objects of the MatManager class. Each collection of
     * things should be saved as a new list.
     */
    public MatManager()
    {
        materials = new ArrayList<>();
    }

    public void addMaterial(String name) //add new Object to collection by name
    {
        // check if material name is already in collection
        boolean overlap = false;
        for (Materials materials : materials) {
            materials.getName();
            if (materials.getName().equals(name)  == true) {
                overlap = true;
            }
        }

        if (overlap == true) {
            System.out.println("Material " + name + " already exists.");
        } else {
            materials.add(new Materials(name));
        }
    }

    public void addSpecs(String name, int width, int height, int depth, double weight, String description)
    {
        for (Materials materials : materials) {
            materials.getName();
            if (materials.getName().equals(name) == true) {
                materials.setVolume(width, height, depth);
                materials.setWeight(weight);
                materials.setDescription(description);
                System.out.println("Data successfully added to: " + materials.getName());
            }  else {
                System.out.println("This material doesn't exist: " + name);
            }
        }
    }

    public void printNames()
    {
        for (Materials materials : materials) {
            System.out.println(materials.getName());
        }
    }

    public void printNameWeightVolume()
    {
        int i = 0;
        while (i < materials.size()) {
            Materials material = materials.get(i);
            System.out.println(material.getName() + " " + material.getWeight() + " " + material.getVolumeS());
            i++;
        }
    }

    public void printNameDescription()
    {
        Iterator<Materials> itr = materials.iterator();
        while (itr.hasNext()) {
            Materials material = itr.next();
            System.out.println(material.getName() + ": " + material.getDescription());
        }
    }

    public void searchPrefix(String prefix)
    {
        boolean found = false;
        for (Materials materials : materials) {
            materials.getName();
            if (materials.getName().startsWith(prefix)) {
                System.out.println(materials.getName());
                found = true;
            } if (found == false) {
                System.out.println("No materials with this prefix found.");
                break;
            }
        }
    }

    public void printTotalWeight()
    {
        double weight = 0.0;
        double total = 0.0;
        for (Materials materials : materials) {
            weight = materials.getWeight();
            total += weight;
        }
        System.out.println("The total weight is: " + total + ".");
    }

    public void printMaxVolume()
    {
        int maxVol = 0;
        String matName = "";
        for (Materials materials : materials) {
            if (materials.calcVolume() > maxVol) {
                maxVol = materials.calcVolume();
                matName = materials.getName();
            }
        }
        System.out.println(matName + " has the highest volume at " + maxVol + ".");
    }

    public void printSpecificWeightVol(String name)
    {
        for (Materials materials : materials) {
            materials.getName();
            double specWeight = 0;
            String specVolume = "";
            if (materials.getName().equals(name) == true) {
                specWeight = materials.getWeight();
                specVolume = materials.getVolumeS();
                System.out.println(name + " has the weight " + specWeight);
                System.out.println("and the volume " + " " + specVolume);
                break;
            } else {
                System.out.println("Oops, that name doesn't exist.");
            }
        }
    }

    public void printMatOfTheDay()
    {
        Random randomizer = new Random();
        int index = randomizer.nextInt(materials.size());
        Materials matOTD = materials.get(index);
        String matName = matOTD.getName();
        System.out.println("The Material of the Day is: " + matName);
    }

    public void checkEmpty()
    {
        for (Materials materials : materials) {
            String name = materials.getName();
            double weight = materials.getWeight();
            int[] volume = materials.getVolume();
            String description = materials.getDescription();

            if (name == null) {
                System.out.println("Missing value detected: Name cannot be empty");
            } else if (weight <= 0.0) {
                System.out.println("Missing value detected: Weight cannot be negative or empty.");
            } else if (volume == null) {
                System.out.println("Missing value detected: Volume cannot be empty.");
            } else if (description == null) {
                System.out.println("Missing value detected: Description cannot be empty.");
            }

        }
    }
}
