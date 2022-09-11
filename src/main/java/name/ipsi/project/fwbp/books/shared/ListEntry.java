package name.ipsi.project.fwbp.books.shared;

import java.util.List;

public record ListEntry(List<String> data) implements TextEntry {
    @Override
    public boolean list() {
        return true;
    }
}
