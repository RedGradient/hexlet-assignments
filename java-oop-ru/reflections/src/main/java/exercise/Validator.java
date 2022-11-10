package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

// BEGIN
class Validator {
    public static <T> ArrayList<String> validate(T object) {
        var list = new ArrayList<String>();

        Field[] fields = object.getClass().getDeclaredFields();
        for(var field : fields) {
            var annotations = field.getDeclaredAnnotations();
            for (var annotation : annotations) {
                if (annotation instanceof NotNull) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(object);
                        if (value == null) {
                            list.add(field.getName());
                        }
                    } catch (IllegalAccessException e) {
//                        continue;
                    }
                }

            }
        }

        return list;
    }
}
// END
