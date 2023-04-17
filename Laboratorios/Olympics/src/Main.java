import com.google.gson.Gson;
import java.io.*;
import java.util.*;

public class Main {
    static String folder = "data";
    static String path = "data/data.txt";

    static ArrayList<Country> countries;
    static Scanner scanner;

    public static void main(String[] args) throws IOException  {

        init();
        load();
        boolean onLoop = true;

        while(onLoop){
            menu();
            switch (scanner.nextLine()){
                case "1":
                    System.out.println("Por favor ingresa el país de la forma pais::medalla::cantidad");
                    String countryToadd = scanner.nextLine();
                    String [] countrySection  = countryToadd.split("::");
                    boolean found = false;
                    for (Country country : countries) {
                        if (country.getName().equals(countrySection[0])){
                            found = true;
                            if (countrySection[1].equals("oro")){
                                country.setGold(country.getGold() + Integer.valueOf(countrySection[2]));
                            } else if (countrySection[1].equals("plata")) {
                                country.setSilver(country.getSilver() + Integer.valueOf(countrySection[2]));
                            }else country.setBronze(country.getBronze() + Integer.valueOf(countrySection[2]));
                        }
                    }
                    if (!found){
                        Country country = new Country(countrySection[0]);
                        if (countrySection[1].equals("oro")){
                            country.setGold(country.getGold() + Integer.valueOf(countrySection[2]));
                        } else if (countrySection[1].equals("plata")) {
                            country.setSilver(country.getSilver() + Integer.valueOf(countrySection[2]));
                        }else if (countrySection[1].equals("bronce")) country.setBronze(country.getBronze() + Integer.valueOf(countrySection[2]));

                        countries.add(country);
                    }
                    break;

                case "2":
                    System.out.println("Ordenamiento Interfaz Comparable - Mostrar Medallería \n");
                    Collections.sort(countries);
                    countries.forEach(country -> System.out.println(country.getName() + "| Oro:"
                                                                    + country.getGold() +  " Plata:" +  country.getSilver() + " Bronce: " +  country.getBronze()));
                    break;

                case "3":
                    System.out.println("\nOrdenamiento por cantidad de medallas obtenidas");
                    Collections.sort(countries, (a,b) ->{
                        return  ( b.getGold() + b.getSilver() + b.getBronze()) - ( a.getGold() + a.getSilver() + a.getBronze()) ;
                    });
                    countries.forEach(country -> System.out.println(country.getName()+ ": " + (country.getGold() + country.getSilver() + country.getBronze())));
                    break;

                case "4":
                    insertionSort();
                    System.out.println("Paises");
                    countries.forEach(country -> System.out.print(country.getName()+ ", "));
                    break;

                case "5":
                    System.out.println("Chao");
                    onLoop = false;
            }
        }
        save();
    }
    public static void menu(){
        System.out.println("\n1. Ingresar un país.\n" +
                           "2. Mostrar medallería.\n" +
                           "3. Mostrar total de medallas.\n" +
                           "4. Mostrar países.\n" +
                           "5. Salir.\n");
    }
    public static void insertionSort(){
        for (int i = 1; i < countries.size() ; i++) {
            Country key = countries.get(i);
            int j = i -1;
            while (j>-1 && countries.get(j).getName().compareTo(key.getName()) > 0 ){
                countries.set(j+1, countries.get(j));
                j--;
            }
            countries.set(j+1,key);
        }
    }
    public static void init(){

        scanner = new Scanner(System.in);
        countries = new ArrayList<>();

    }


    public static void load() throws IOException {
        File file = new File(path);

        if(file.exists()){
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String content = "";
            String line = "";
            while((line = reader.readLine()) != null){
                content += line + "\n";
            }

            Gson gson = new Gson();
            Country[] array= gson.fromJson(content, Country[].class); // No funciona con arraylist, solo array[]. Convierte a objetos las cadenas de texto.
            countries.addAll(List.of(array));

        }else{
            File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
    }
    public static void save( ) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            File f = new File(folder);
            if(!f.exists()){
                f.mkdirs();
            }
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);

        Gson gson = new Gson();
        String data = gson.toJson(countries);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        writer.write(data);
        writer.flush();
        fos.close();
    }
}