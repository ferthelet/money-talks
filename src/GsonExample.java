import com.google.gson.Gson;

public class GsonExample {

    public static void main(String[] args) {
        // Create a Gson object
        Gson gson = new Gson();

        // Create a simple Java object
        Person person = new Person("John", 30, "john@example.com");

        // Serialize the object to JSON
        String json = gson.toJson(person);
        System.out.println("JSON representation: " + json);

        // Deserialize the JSON back to a Java object
        Person personFromJson = gson.fromJson(json, Person.class);
        System.out.println("Deserialized object: " + personFromJson);
    }
}

class Person {
    String name;
    int age;
    String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}