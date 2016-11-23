package ru.samara.shop.util.exception;


import ru.samara.shop.LoggerWrapper;

public class ExceptionUtil {
    private static final LoggerWrapper LOG = LoggerWrapper.get(ExceptionUtil.class);

    public static void check(boolean found, int id) {
        check(found, "id=" + id );
    }

    public static void check(boolean found, String msg) {
        if (!found) throw LOG.getNotFoundException("Not found entity with " + msg);
    }

    public static <T> T check(T object, int id) {
        return check(object, "id=" + id);
    }

    public static <T> T check(T object, String msg) {
        check(object != null, msg);
        return object;
    }

}
