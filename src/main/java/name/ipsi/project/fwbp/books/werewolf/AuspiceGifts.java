package name.ipsi.project.fwbp.books.werewolf;

public record AuspiceGifts(Auspices auspice) implements GiftGroup {
    @Override
    public String name() {
        return auspice.displayName();
    }
}
