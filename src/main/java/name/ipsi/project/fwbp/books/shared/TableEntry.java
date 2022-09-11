package name.ipsi.project.fwbp.books.shared;

public record TableEntry(Table data) implements TextEntry {

    @Override
    public boolean table() {
        return true;
    }
}
