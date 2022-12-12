package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  //Не хотим выдавать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    String result = person.getFirstName();
    if (person.getMiddleName() != null) {
      result += " " + person.getMiddleName();
    }
    if (person.getSecondName() != null) {
      result += " " + person.getSecondName();
    }
    return result;
  }

  // словарь id персоны -> ее имя (возможно полное имя) (нужно ли скипать первую персону?)
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    Person::getFirstName,
                    (a, b) -> a));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> personsSet1= new HashSet<>(persons1);
    for(var person2: persons2){
      if(personsSet1.contains(person2)){
        return true;
      }
    }
    return false;
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    return numbers
            .filter(num -> num % 2 == 0)
            .count();
  }
}
