import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John", "Oliver", "Charlie", "Thomas", "Alfie", "Riley", "William", "James");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown", "Aldridge", "Adamson", "Forman", "Simon", "WifKinson", "Ogden");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long minorPplCount = persons.stream().filter(person -> person.getAge() < 18).count();//количество несовершеннолетних
        System.out.println(minorPplCount);
        List<String> conscripts =  persons.stream()
                .filter(person -> (person.getAge() > 18 && person.getAge() < 27))
                .map(Person::getFamily)
                .distinct() //for fun =)
                //.forEach(System.out::println);
                .collect(Collectors.toList());
        System.out.println(conscripts);//список уникальных фамилий призывников
        System.out.println();
        List<Person> workablePerson = persons.stream()
                .filter(person -> (person.getEducation() == Education.HIGHER))
                .filter(person -> (person.getAge() > 18 && person.getAge() < 65))
                .filter(person -> ((person.getAge() < 60 && person.getSex() == Sex.WOMAN) || (person.getSex() == Sex.MAN)))
                .sorted(Comparator.comparing(Person::getFamily))
                //.limit(100)
                .collect(Collectors.toList()); //люди с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин
         System.out.println(workablePerson);

    }
}
