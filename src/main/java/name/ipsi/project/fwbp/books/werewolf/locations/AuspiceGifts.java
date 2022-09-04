package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.werewolf.Auspices;

public record AuspiceGifts(Auspices auspice) implements GiftGroup {
    @Override
    public String name() {
        return auspice.displayName();
    }
}
