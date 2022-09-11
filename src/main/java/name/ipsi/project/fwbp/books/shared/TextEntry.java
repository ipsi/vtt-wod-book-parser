package name.ipsi.project.fwbp.books.shared;

public sealed interface TextEntry permits StringEntry, TableEntry, ListEntry {

    default boolean table() {
        return false;
    }

    default boolean list() {
        return false;
    }
}
