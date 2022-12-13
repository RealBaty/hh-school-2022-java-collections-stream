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
  /*
  - Пропуск первой персоны реализован в stream, это позволило улучшить читаемость кода,
  а так же избавляет от необходимости проверять длину списка.
   */
  public List<String> getNames(List<Person> persons) {
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  /*
  - Не используется функция getNames, что позволяет повторно не раскладывать список в stream
  - В операторе distinct нет необходимости, в итоге в множестве все элементы уникальны
   */
  public Set<String> getDifferentNames(List<Person> persons) {
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  /*
  - Добавлено отчество, вместо второй фамилии. Более читаемая реализация через stream
   */
  public String convertPersonToString(Person person) {
    return Stream.of(person.getFirstName(), person.getMiddleName(), person.getSecondName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  /*
  - При создании HashMap не указывается её размер, размер 1 сразу приведет к расширению хэш таблицы
  - Не нужно проверять наличие ключа, перед тем как положить значение в map
  - Переименованы переменные на более читаемые
   */
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> nameByIdPersons = new HashMap<>();
    for(var person: persons){
      nameByIdPersons.put(person.getId(), this.convertPersonToString(person));
    }
    return nameByIdPersons;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  /*
  - Более быстрая реализация, работающая за O(n + m) вместо O(n * m)
  - Улучшена читаемость за счет отсутствия вложенных циклов
   */
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> personsSet1 = new HashSet<>(persons1);
    for(var person2: persons2){
      if(personsSet1.contains(person2)){
        return true;
      }
    }
    return false;
  }

  //...
  /*
  - Убрано поле класса count, т.к. оно приватное и ни где больше не использовалось
  - Подсчет реализован через count() в угоду читаемости
   */
  public long countEven(Stream<Integer> numbers) {
    return numbers
            .filter(num -> num % 2 == 0)
            .count();
  }
}
