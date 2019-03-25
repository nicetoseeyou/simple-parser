package lab.nice.predicate;

import java.util.function.Predicate;

public interface PredicateBuilder<Element, Expression> {
    Predicate<Element> build(Expression expression);
}
