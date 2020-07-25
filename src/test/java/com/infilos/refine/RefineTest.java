package com.infilos.refine;

import com.infilos.refine.std.Numbers;
import com.infilos.refine.std.Objects;
import com.infilos.refine.std.Strings;
import org.junit.Test;
import sample.Person;

/**
 * @author zhiguang.zhang on 2020-06-13.
 */

public class RefineTest {

    @Test
    public void test() {
        Refine.check("Julia", Strings.NotBlank).showDetails();
        Refine.check("Name", "Julia", Strings.NotBlank).showDetails();

        Person person = new Person("Julia", "Male");
        person.refine().showDetails();

        Refine.check(
            Refine.of("case-1").expect("Julia").match(Strings.NotBlank),
            Refine.of("case-2").expect("").match(Strings.IsBlank)
        ).showDetails();

        Integer val1 = 12;
        Refine.of("case-3").when(() -> val1 >10).expect(val1).match(Numbers.Modulo(5,2)).check().showDetails();

        Refine.of("case-4").expect("value").matchAll(Objects.NotNull(),Strings.NotBlank).check().showDetails();
        Refine.of("case-5").expect("abcde").matchAny(Strings.Startswith("a"),Strings.Startswith("b")).check().showDetails();
        Refine.of("case-6").expect("abcde").matchOne(Strings.Startswith("a"),Strings.Startswith("b")).check().showDetails();
        Refine.of("case-6").expect("abcde").matchNone(Strings.Startswith("f"),Strings.Startswith("g")).check().showDetails();
    }
}