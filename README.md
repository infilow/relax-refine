# relax-refine

Simple variable and bean verify toolkit, without any annotation.

## Usage

```
<dependency>
    <groupId>com.infilos</groupId>
    <artifactId>relax-refine</artifactId>
    <version>LATEST</version>
</dependency>
```

## Sample

**Refine variables:**

```java
RefineResult result1 = Refine.check("Julia", Strings.NotBlank);

RefineResult result2 = Refine.check(
    Refine.of("case-1").expect("Julia").match(Strings.NotBlank),
    Refine.of("case-2").expect("").match(Strings.IsBlank)
);

Integer val = 12;
RefineResult result3 = Refine.of("case-3").when(() -> val >10).expect(val).match(Numbers.Modulo(5,2)).check();

RefineResult result4 = Refine.of("case-4").expect("value").matchAll(Objects.NotNull(),Strings.NotBlank).check();

RefineResult result5 = Refine.of("case-5").expect("abcde").matchAny(Strings.Startswith("a"),Strings.Startswith("b")).check();

RefineResult result6 = Refine.of("case-6").expect("abcde").matchOne(Strings.Startswith("a"),Strings.Startswith("b")).check();

RefineResult result7 = Refine.of("case-6").expect("abcde").matchNone(Strings.Startswith("f"),Strings.Startswith("g")).check();
```

**Refine bean without external parameters:**

```java
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
```

**Refine bean with external parameters, here is an enum:**

```java
public enum CriteriaType {
    Keyword, Content
}

public class Criteria implements Refinable<CriteriaType, Criteria> {
    String keyword;
    String content;

    @Override
    public RefineResult refine(Criteria bean, CriteriaType type) {
        CriteriaType checkType = Optional.ofNullable(type).orElse(CriteriaType.Keyword);

        switch (checkType) {
            case Keyword:
                return Refine.of("Criteria.keyword").expect(keyword).match(Strings.NotBlank).check();
            case Content:
                return Refine.of("Criteria.content").expect(content).match(Strings.NotBlank).check();
        }

        throw new UnsupportedOperationException();
    }
}
```

## Contributions

### Release

- Snapshot: `mvn clean deploy`
- Release: `mvn clean package source:jar gpg:sign install:install deploy:deploy`
