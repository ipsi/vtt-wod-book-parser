package name.ipsi.project.fwbp.books.shared.locations;

public record TableParagraph(
        Paragraph paragraphLocation,
        Paragraph trailer
) {
    public TableParagraph(Paragraph paragraphLocation) {
        this(paragraphLocation, null);
    }
}
