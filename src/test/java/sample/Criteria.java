package sample;

import com.infilos.refine.Refine;
import com.infilos.refine.api.Refinable;
import com.infilos.refine.api.RefineResult;
import com.infilos.refine.std.Strings;

import java.util.Optional;

/**
 * @author zhiguang.zhang on 2020-06-13.
 */

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