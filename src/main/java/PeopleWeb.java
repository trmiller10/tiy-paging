import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class PeopleWeb {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Person> people = CSVParser.parse("people.csv");

        Spark.get(
                "/",
                (request, response) -> {
                    HashMap<String, Object> m = new HashMap<String, Object>();

                    int offset;
                    try {
                        offset = Integer.valueOf(request.queryParams("offset"));
                    } catch(NumberFormatException e){
                        offset = 0;
                    }

                    m.put("offset", offset);
                    m.put("backOffset", offset != 0 ? offset-20 : null);
                    m.put("nextOffset", offset < people.size()-20 ? offset+20 : null);

                    m.put("people", people.subList(offset, offset+20));

                    return new ModelAndView(m, "people.mustache");
                },
                new MustacheTemplateEngine()
        );

        Spark.get(
                "/person",
                (request, response) -> {
                    HashMap<String, Object> m = new HashMap<>();

                    for(Person person : people){
                        if(person.id.equals(request.queryParams("id"))){
                            m.put("person", person);
                            break;
                        }
                    }

                    return new ModelAndView(m, "person.mustache");
                },
                new MustacheTemplateEngine()
        );
    }
}
