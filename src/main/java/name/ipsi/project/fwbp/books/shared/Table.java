package name.ipsi.project.fwbp.books.shared;

public record Table(HeaderRow header, String trailer, Row... rows) {

    public record HeaderRow(Column... columns) { }

    public record Row(Column... columns) { }

    public record Column(String data) { }
}
