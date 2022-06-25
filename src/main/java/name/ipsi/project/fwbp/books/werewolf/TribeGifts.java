package name.ipsi.project.fwbp.books.werewolf;

public record TribeGifts(Tribes tribe) implements GiftGroup {
    @Override
    public String name() {
        return tribe.displayName();
    }
}
