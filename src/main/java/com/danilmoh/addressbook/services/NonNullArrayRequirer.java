package com.danilmoh.addressbook.services;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NonNullArrayRequirer {
    @SafeVarargs
    public static <T> List<T> requireNonNull(T... objects) {
        if (objects ==null) throw new NullPointerException();
        Arrays.stream(objects).forEach(Objects::requireNonNull);
        return Arrays.asList(objects);
    }
}
