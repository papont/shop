package ru.samara.shop.matcher;

import org.junit.Assert;
import ru.samara.shop.model.User;
import ru.samara.shop.model.UserMeal;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.01.2015.
 *
 * @param <T> : entity
 * @param <R> : testEntity, converter result for compare
 */
public class ModelMatcher<T, R> {
    protected Function<T, R> entityConverter;

    public ModelMatcher(Function<T, R> entityConverter, Class<T> entityClass) {
        this.entityConverter = entityConverter;
    }

    public ModelMatcher(Function<UserMeal, String> function) {

    }


    public void assertEquals(T expected, T actual) {
        Assert.assertEquals(entityConverter.apply(expected), entityConverter.apply(actual));
    }

    public void assertListEquals(List<T> expected, List<T> actual) {
        Assert.assertEquals(map(expected, entityConverter), map(actual, entityConverter));
    }

    public static <S, T> List<T> map(List<S> list, Function<S, T> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }

}
