package exercise;

import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
class SingleTag extends Tag {

    SingleTag(String tagName, Map<String, String> arguments) {
        this.tagName = tagName;
        this.attributes = new LinkedHashMap<>(arguments);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("<");
        builder.append(tagName);
        for (var pair : attributes.entrySet()) {
            var attrib = String.format(" %s=\"%s\"", pair.getKey(), pair.getValue());
            builder.append(attrib);
        }
        builder.append(">");
        return builder.toString();
    }
}
// END
