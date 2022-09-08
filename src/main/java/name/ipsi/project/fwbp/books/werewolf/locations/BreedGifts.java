package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.werewolf.Breeds;

public record BreedGifts(Breeds breed) implements GiftGroup {
    @Override
    public String name() {
        return breed.displayName();
    }

    @Override
    public String journalIdName() {
        return "breeds";
    }

    @Override
    public String pageIdGroup() {
        return "breed";
    }
}
