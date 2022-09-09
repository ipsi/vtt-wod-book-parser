package name.ipsi.project.fwbp.foundry.templating;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Templater {
    private static final Logger log = LoggerFactory.getLogger(Templater.class);

    private static final TemplateLoader templateLoader = new ClassPathTemplateLoader("/handlebars/");
    private static final Handlebars handlebars = new Handlebars(templateLoader);

    static {
        handlebars.setCharset(StandardCharsets.UTF_8);
        handlebars.setPrettyPrint(true);
        handlebars.registerHelper("split", (context, options) -> ((String)context).split("\n"));
        handlebars.registerHelper("generate-id", (context, options) -> FoundryUtils.generateId(options.hash("group"), (String) context));
    }

    public static Handlebars instance() {
        return handlebars;
    }
}
