package name.ipsi.project.fwbp.books;

import name.ipsi.project.fwbp.books.werewolf.GiftLevel;
import name.ipsi.project.fwbp.books.werewolf.GiftRoll;
import name.ipsi.project.fwbp.foundry.FoundryUtils;

import java.util.List;

public record Gift(
        String id,
        String name,
        String description,
        String system,
        GiftRoll giftRoll,
        List<GiftLevel> availableTo
) implements BookEntry {
    public Gift(String name, String description, String system, GiftRoll giftRoll, List<GiftLevel> availableTo) {
        this(FoundryUtils.generateId(), name, description, system, giftRoll, availableTo);
    }

    public boolean availableFor(String groupName) {
        for (var gl : availableTo) {
            if (gl.group().name().equals(groupName)) {
                return true;
            }
        }
        return false;
    }
}
