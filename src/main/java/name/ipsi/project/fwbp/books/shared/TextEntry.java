package name.ipsi.project.fwbp.books.shared;

public sealed interface TextEntry permits StringEntry, TableEntry {

    default boolean table() {
        return false;
    }
}
