package ru.samara.shop;

import ru.samara.shop.matcher.ModelMatcher;
import ru.samara.shop.model.User;
import ru.samara.shop.model.UserMeal;

import java.util.function.Function;

/**
 * @author papont
 * @date: 23.1.2014
 */
public class MealTestData {
    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(
            new Function<UserMeal, String>() {
                @Override
                public String apply(UserMeal meal) {
                    return meal.toString();
                }
            }
    );
}
