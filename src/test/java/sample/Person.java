package sample;

import com.infilos.refine.Refine;
import com.infilos.refine.api.Refinable;
import com.infilos.refine.api.RefineResult;
import com.infilos.refine.std.Strings;

/**
 * @author infilos on 2020-06-13.
 */

public class Person implements Refinable<Void, Person> {
    String name;
    String gender;

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public RefineResult refine(Person bean, Void params) {
        return Refine.check(
            Refine.of("Person.name").expect(name).match(Strings.NotBlank),
            Refine.of("Person.gender").expect(gender).match(Strings.NotBlank)
        );
    }
}
