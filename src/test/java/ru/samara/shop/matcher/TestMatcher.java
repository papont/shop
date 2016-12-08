package ru.samara.shop.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import ru.samara.shop.web.json.JsonUtil;

abstract public class TestMatcher<T> extends BaseMatcher<String> {
    protected T expected;

    public TestMatcher(T expected) {
        this.expected = expected;
    }

    abstract protected boolean compare(T expected, String actual);

    @Override
    public boolean matches(Object actual) {
        return compare(expected, (String) actual);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(JsonUtil.writeValue(expected));
    }
}
