import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by doug on 5/8/16.
 */
public class CSVParser {

    public static ArrayList<Person> parse(String filename) throws FileNotFoundException {

        ArrayList<Person> people = new ArrayList<>();

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        scanner.nextLine();

        while(scanner.hasNext()){
            String data = scanner.nextLine();

            String[] splitData = data.split("\"?,\"?");

            Person person = new Person();
            person.id = splitData[0];
            person.firstName = splitData[1];
            person.lastName = splitData[2];
            person.email = splitData[3];
            person.country = splitData[4];
            person.ipAddress = splitData[5];

            people.add(person);
        }

        System.out.println(people);

        return people;

    }
}
