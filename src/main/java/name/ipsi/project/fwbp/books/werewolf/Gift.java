package name.ipsi.project.fwbp.books.werewolf;

import name.ipsi.project.fwbp.books.shared.BookEntry;
import name.ipsi.project.fwbp.foundry.core.FoundryUtils;

import java.util.List;

public record Gift(
        String id,
        String name,
        String description,
        String system,
        GiftRoll giftRoll,
        List<GiftAvailability> availableTo
) implements BookEntry, Comparable<Gift> {
    public Gift(String name, String description, String system, GiftRoll giftRoll, List<GiftAvailability> availableTo) {
        this(FoundryUtils.generateId("gift", name), name, description, system, giftRoll, availableTo);
    }

    public boolean availableFor(String groupName) {
        for (var gl : availableTo) {
            if (gl.group().name().equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Gift o) {
        return name.compareTo(o.name);
    }
}
