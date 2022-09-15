package name.ipsi.project.fwbp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class DataClassGenerator {

    private final StringBuilder sb = new StringBuilder();
    private final Map<String, String> defaults = new HashMap<>();
    private final Map<String, Map<String, Object>> records = new HashMap<>();
    private final Set<String> additionalImports = new HashSet<>();

    private List<Map.Entry<String, Object>> fields;

    public static void main(String[] args) throws IOException {
        new DataClassGenerator().generate();
    }

    public DataClassGenerator() {
        defaults.put("created", "true");
        defaults.put("version", "\"2.0.3\"");
    }

    public void generate() throws IOException {
        record system(String id, Path path) {}
        for (var system : Arrays.asList(
                new system("wod", Path.of("..", "Foundry_WoD20")),
                new system("wod5e", Path.of("..", "foundry-WOD5"))
        )) {
            var dirPath = Path.of("src", "main", "java", "name", "ipsi", "project", "fwbp", "foundry", system.id(), "data", "item");
            var template = new ObjectMapper().readValue(system.path().resolve("template.json").toFile(), Template.class);

            var itemImages = system.path().resolve("assets").resolve("img").resolve("items");
            if (Files.isDirectory(itemImages)) {
                try(var stream = Files.list(itemImages)) {
                    var fileNames = stream
                            .map(s -> {
                                String fileName = s.getFileName().toString();
                                if (fileName.startsWith("main")) {
                                    fileName = "main_" + fileName.substring(4);
                                }
                                return String.format("public static final String %s = \"%s\";", fileName.toUpperCase().substring(0, fileName.lastIndexOf(".")), system.path().relativize(s));
                            })
                            .collect(Collectors.joining("\n"));

                    Files.writeString(dirPath.resolve("ItemImages.java"), String.format("""
                            package name.ipsi.project.fwbp.foundry.%s.data.item;
                            
                            public class ItemImages {
                                
                            %s
                                
                                private ItemImages() {
                                    // Not instantiable
                                }
                            }
                            """, system.id(), fileNames.indent(4)));
                }
            }

            for (var name : (List<String>)template.items.get("types")) {
                var typeData = (Map<String, ?>)template.items().get(name);
                var itemTemplates = (Map<String, Object>) template.items().get("templates");

                var templates = itemTemplates.entrySet().stream()
                        .filter(e -> ((Map<String, List<String>>)typeData).get("templates").contains(e.getKey()))
                        .flatMap(e -> ((Map<String, Object>) e.getValue()).entrySet().stream())
                        .toList();

                fields = Stream.of(templates, ((Map<String, Object>) typeData).entrySet())
                        .flatMap(Collection::stream)
                        .filter(e -> !e.getKey().equals("templates"))
                        .toList();

                generateClass(name.replaceAll(" ", "").transform(this::capitalise));

                sb.insert(0, String.format("""
                package name.ipsi.project.fwbp.foundry.%s.data.item;
                               
                import com.fasterxml.jackson.annotation.JsonAnyGetter;
                import com.fasterxml.jackson.annotation.JsonAnySetter;
                import com.fasterxml.jackson.annotation.JsonProperty;
                import com.fasterxml.jackson.annotation.JsonPropertyOrder;
                import name.ipsi.project.fwbp.foundry.core.ItemData;%s
                               
                import java.util.Map;
                import java.util.HashMap;

                 """, system.id(), additionalImports.stream()
                        .map(s -> "import " + s + ";")
                        .collect(Collectors.joining("\n"))
                        .transform(s -> s.length() > 0 ? "\n" + s + "\n" : s)));

                if (!Files.isDirectory(dirPath)) {
                    Files.createDirectories(dirPath);
                }

                Files.writeString(dirPath.resolve(name.replaceAll(" ", "").transform(this::capitalise) + "Data.java"), sb.toString());
                sb.setLength(0);
                records.clear();
                additionalImports.clear();
            }
        }
    }

    private void generateClass(String classPrefix) {
        sb.append(String.format("""
                @JsonPropertyOrder(alphabetic = true)
                public record %sData(
                """, classPrefix));

        for (var attr : fields) {
            sb.append(with(classPrefix, attr, (jsonName, name, propertyClassName, defaultValue) ->
                    String.format("@JsonProperty(\"%s\") %s %s,\n", jsonName, propertyClassName, name).indent(4)));
        }

        sb.append("""
                    @JsonAnyGetter
                    @JsonAnySetter
                    Map<String, Object> additionalData
                ) implements ItemData {
                """.indent(0));

        sb.append(generateBuilder(classPrefix).indent(4));

        for (var record : records.entrySet()) {
            sb.append(generateRecord(record.getKey(), record.getValue()).indent(4));
        }

        sb.append("}\n");
    }

    private String generateRecord(String name, Map<String, Object> properties) {
        var sb = new StringBuilder();

        String outerClassName = name.transform(this::capitalise);
        sb.append(String.format("""
                public record %s(
                """, outerClassName));

        for (var property : properties.entrySet()) {
            sb.append(with(outerClassName, property, (jsonName, name1, propertyClassName, defaultValue) ->
                    String.format("%s %s,\n", propertyClassName, name1)).indent(4));
        }

        sb.deleteCharAt(sb.length() - 2);

        sb.append("""
                ) {}
                
                """);

        return sb.toString();
    }

    private String generateBuilder(String classPrefix) {
        var sb = new StringBuilder();
        String builderClassName = classPrefix + "DataBuilder";
        sb.append(String.format("""
            public static class %s {
            """, builderClassName));

        for (var attr : fields) {
            sb.append(with(classPrefix, attr, (jsonName1, name1, propertyClassName, defaultValue1) -> {
                if (defaults.containsKey(name1)) {
                    defaultValue1 = defaults.get(name1);
                }
                return String.format("private %s %s = %s;\n", propertyClassName, name1, defaultValue1).indent(4);
            }));
        }

        sb.append(String.format("""
            
            private final Map<String, Object> additionalProperties = new HashMap<>();

            public %s() {
                // Do nothing
            }
            
            """.indent(4), builderClassName));

        for (var attr : fields) {
            sb.append(with(classPrefix, attr, (jsonName, name, propertyClassName, defaultValue) ->
                    String.format("""
                            public %s with%s(%s %s){
                                this.%s = %s;
                                return this;
                            }

                            """, builderClassName, name.transform(this::capitalise), propertyClassName, name, name, name).indent(4)));
        }

        sb.append(String.format("""
                public %s withAdditionalProperty(String name, Object value) {
                    this.additionalProperties.put(name, value);
                    return this;
                }
                
                """.indent(4), builderClassName));

        sb.append(String.format("""
                public %sData build() {
                    return new %sData(
                """.indent(4), classPrefix, classPrefix));

        for (var attr : fields) {
            sb.append(with(classPrefix, attr, (jsonName, name, propertyClassName, defaultValue) -> String.format("%s,\n", name).indent(12)));
        }

        sb.append("additionalProperties\n".indent(12));

        sb.append(");\n".indent(8));
        sb.append("}\n".indent(4));

        sb.append("}\n");

        return sb.toString();
    }

    private String processPropertyName(String name) {
        if (name.startsWith("is"))
            name = name.substring(2);

        if (name.startsWith("spending")) {
            var ca = name.toCharArray();
            ca[8] = Character.toUpperCase(ca[8]);
            name = new String(ca);
        } else if (name.startsWith("spend")) {
            var ca = name.toCharArray();
            ca[5] = Character.toUpperCase(ca[5]);
            name = new String(ca);
        }

        return name;
    }

    private String with(String outerClassName, Map.Entry<String, Object> entry, With with) {
        Function<Map.Entry<String, Object>, Object> handleDefault = (e) -> {
            var propertyName = e.getKey();
            var defaultPropertyValue = e.getValue();
            if ((Arrays.asList("Feature", "Fetish", "Damage", "Power", "MeleeWeapon", "RangedWeapon").contains(outerClassName) && "type".equals(propertyName)) || "conceal".equals(propertyName)) {
                return "null";
            }
            if (defaultPropertyValue == null || (defaultPropertyValue.getClass().equals(String.class) && !((String) defaultPropertyValue).startsWith("\""))) {
                defaultPropertyValue = '"' + ((String)defaultPropertyValue) + '"';
            }
            return defaultPropertyValue;
        };
        String propertyName = entry.getKey();
        Object defaultValue = entry.getValue();
        String propertyClassName;
        if (defaultValue.getClass().equals(Boolean.class)) {
            propertyClassName = "boolean";
        } else if (propertyName.equals("type")) {
            propertyClassName = switch (outerClassName) {
                case "Feature" -> {
                    additionalImports.add("name.ipsi.project.fwbp.foundry.wod.FeatureTypes");
                    yield "FeatureTypes";
                }
                case "Fetish" -> {
                    additionalImports.add("name.ipsi.project.fwbp.foundry.wod.werewolf.FetishType");
                    yield "FetishType";
                }
                case "Damage" -> {
                    additionalImports.add("name.ipsi.project.fwbp.foundry.wod.DamageTypes");
                    yield "DamageTypes";
                }
                case "Power" -> {
                    additionalImports.add("name.ipsi.project.fwbp.foundry.wod.PowerTypes");
                    yield "PowerTypes";
                }
                default -> throw new RuntimeException("Unknown type " + outerClassName);
            };
        } else if (propertyName.equals("conceal")) {
            additionalImports.add("name.ipsi.project.fwbp.foundry.wod.WeaponConcealment");
            propertyClassName = "WeaponConcealment";
        } else {
            propertyClassName = defaultValue.getClass().getSimpleName();
        }

        if (entry.getValue() instanceof Map<?,?>) {
            var recordProps = (Map<String, Object>) entry.getValue();
            if (!records.containsKey(propertyName)) {
                records.put(propertyName, recordProps);
            }

            propertyClassName = propertyName.transform(this::capitalise);

            defaultValue = "new " + propertyClassName + "(" + recordProps.entrySet().stream().map(handleDefault).map(Object::toString).collect(Collectors.joining(",")) + ")";
        } else {
            defaultValue = handleDefault.apply(Map.entry(propertyName, defaultValue));
        }

        return with.with(propertyName, processPropertyName(propertyName),
                propertyClassName,
                defaultValue);
    }

    public interface With {
        String with(String jsonName, String name, String propertyClassName, Object defaultValue);
    }

    public record Template(
            @JsonProperty("Actor") Map<String, Object> actors,
            @JsonProperty("Item") Map<String, Object> items
    ) {}

    private String capitalise(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
