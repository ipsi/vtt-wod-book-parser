package name.ipsi.project.fwbp.books;

public class Records {

    public record NameLocation(Paragraph... content) implements Content { }

    public record DescriptionLocation(Paragraph... content) implements Content { }

    public record TitleLocation(Paragraph... content) implements Content { }

    public record StereotypeLocation(Paragraph... content) implements Content { }

    public record QuoteLocation(Paragraph... content) implements Content { }

    public record NicknamesLocation(Paragraph... content) implements Content { }

}
