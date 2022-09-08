package name.ipsi.project.fwbp.books.shared;

import java.util.Comparator;
import java.util.List;

public record PowerCollection<G, P extends Comparable<P>>(
        G group,
        List<P> powers
) {
    public PowerCollection {
        powers.sort(Comparator.naturalOrder());
    }
}
