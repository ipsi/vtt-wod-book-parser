package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.werewolf.Tribes;

public record TribeGifts(Tribes tribe) implements GiftGroup {
    @Override
    public String name() {
        return tribe.displayName();
    }

    @Override
    public String journalIdName() {
        return "tribes";
    }

    @Override
    public String pageIdGroup() {
        return "tribe";
    }
}
