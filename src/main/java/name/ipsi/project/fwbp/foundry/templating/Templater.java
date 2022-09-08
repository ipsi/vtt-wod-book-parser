package name.ipsi.project.fwbp.foundry.templating;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Templater {
    private static final TemplateLoader templateLoader = new ClassPathTemplateLoader("/handlebars/");
    private static final Handlebars handlebars = new Handlebars(templateLoader);

    static {
        handlebars.setCharset(StandardCharsets.UTF_8);
        handlebars.setPrettyPrint(true);
        handlebars.registerHelper("split", (context, options) -> ((String)context).split("\n"));
        //noinspection unchecked,rawtypes
        handlebars.registerHelper("sorted-entry-set", (context, options) -> ((Map)context).entrySet().stream().sorted().collect(Collectors.toList()));
        //noinspection unchecked,rawtypes
        handlebars.registerHelper("sort", (context, options) -> ((List)context).stream().sorted().collect(Collectors.toList()));
        handlebars.registerHelper("generate-journal-id", (context, options) -> FoundryUtils.generateId("journal", (String) context));
        handlebars.registerHelper("generate-tribe-id", (context, options) -> FoundryUtils.generateId("tribe", (String) context));
        handlebars.registerHelper("generate-id", (context, options) -> FoundryUtils.generateId(options.hash("group"), (String) context));
    }

    public static Handlebars instance() {
        return handlebars;
    }
}
