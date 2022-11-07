package exercise;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {

    private String body;
    private LinkedList<Tag> children;

    PairedTag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        this.tagName = tagName;
        this.attributes = new LinkedHashMap<>(attributes);
        this.body = body;
        this.children = new LinkedList<>(children);
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

        builder.append(body);
        children.forEach(tag -> builder.append(tag.toString()));

        var closingTag = String.format("</%s>", tagName);
        builder.append(closingTag);

        return builder.toString();
    }
}
// END
