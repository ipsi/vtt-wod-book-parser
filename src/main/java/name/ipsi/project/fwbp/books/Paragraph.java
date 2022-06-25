package name.ipsi.project.fwbp.books;

import com.itextpdf.kernel.geom.Rectangle;

public record Paragraph(TextArea... locations) implements Content {
    public Paragraph(int page, Rectangle location) {
        this(new TextArea(page, location));
    }

    @Override
    public Paragraph[] content() {
        return new Paragraph[]{this};
    }
}
