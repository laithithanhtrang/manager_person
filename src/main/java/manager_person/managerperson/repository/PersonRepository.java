package manager_person.managerperson.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import manager_person.managerperson.model.Person;

@Repository
public class PersonRepository {
    private List<Person> people = new ArrayList<>();
    public PersonRepository(){
        people.add(new Person(1, "Lại Thị Thanh TRang", "Chú bé Remy lang thang theo gánh xiếc của bác Vitaly", false, "29/10/1996", null));
        people.add(new Person(2, "Phạm Hoàng Nhật Linh", "Chú bé Remy lang thang theo gánh xiếc của bác Vitaly", false, "14/10/1996", null));
      }

    public List<Person> getAll() {
        return this.people;
    }

    public Person create(Person person) {
        int id;
        if (people.isEmpty()) {
            id = 1;
        } else {
            Person lastPerson = people.get(people.size() - 1);
            id = lastPerson.getId() + 1;
        }
        person.setId(id);
        people.add(person);
        return person;
    }

    public Person edit(Person person) {
        get(person.getId()).ifPresent(existPerson -> {
            existPerson.setName(person.getName());
            existPerson.setJob(person.getJob());
            existPerson.setGender(person.isGender());
            existPerson.setBirthDay(person.getBirthDay());
            if (person.getPhoto().getOriginalFilename().isEmpty())
                existPerson.setPhoto(person.getPhoto());
        });
        return person;
    }

    public Optional<Person> get(int id) {
        return people.stream().filter(p -> p.getId() == id).findFirst();
    }
}
