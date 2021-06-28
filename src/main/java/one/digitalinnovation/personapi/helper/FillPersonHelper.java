package one.digitalinnovation.personapi.helper;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.entity.Phone;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
public class FillPersonHelper {

    public Person fillModel(PersonDTO personDTO) {
        LocalDate bithDate = null;
        if (personDTO.getBirthDate() != null) {
            bithDate = LocalDate.parse(personDTO.getBirthDate());
        }

        return Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .cpf(personDTO.getCpf())
                .birthDate(bithDate)
                .phones(personDTO.getPhones()
                        .stream()
                        .map(phoneDto ->  Phone.builder().type(phoneDto.getType()).number(phoneDto.getNumber()).build())
                        .collect(Collectors.toList()))
                .build();
    }

    public PersonDTO fillDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .cpf(person.getCpf())
                .birthDate(String.valueOf(person.getBirthDate()))
                .phones(person.getPhones()
                        .stream()
                        .map(phone ->  PhoneDTO.builder().type(phone.getType()).number(phone.getNumber()).build())
                        .collect(Collectors.toList()))
                .build();
    }
}
