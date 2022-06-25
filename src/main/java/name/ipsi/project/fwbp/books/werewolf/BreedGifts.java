package name.ipsi.project.fwbp.books.werewolf;

public record BreedGifts(Breeds breed) implements GiftGroup {
    @Override
    public String name() {
        return breed.displayName();
    }
}
