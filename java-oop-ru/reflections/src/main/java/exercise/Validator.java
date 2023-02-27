package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;

// BEGIN
class Validator {
    public static <T> ArrayList<String> validate(T object) {
        var fieldNames = new ArrayList<String>();

        Field[] fields = object.getClass().getDeclaredFields();
        for (var field : fields) {
            var annotations = field.getDeclaredAnnotations();
            for (var annotation : annotations) {
                if (annotation instanceof NotNull) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(object);
                        if (value == null) {
                            fieldNames.add(field.getName());
                        }
                    } catch (IllegalAccessException e) {
//                        continue;
                    }
                }
            }
        }

        return fieldNames;
    }
}
// END
