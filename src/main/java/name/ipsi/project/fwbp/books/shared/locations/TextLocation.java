package name.ipsi.project.fwbp.books.shared.locations;

public record TextLocation(TextLocationType type, TextArea... locations) implements Content {
    @Override
    public Paragraph[] content() {
        return new Paragraph[] {
                new Paragraph(locations)
        };
    }
}
