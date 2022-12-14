package tasks;

import common.Person;
import common.PersonService;

import java.util.*;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
Асимптотика работы: O(n) + O1, где n - количество элементов в personIds,
O1 - асимптотика работы функции personService.findPersons(personIds)
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);
    Map<Integer, Person> personById = new HashMap<>();
    for(var person: persons){
      personById.put(person.getId(), person);
    }
    return personIds.stream()
            .map(personById::get)
            .collect(Collectors.toList());
  }
}
