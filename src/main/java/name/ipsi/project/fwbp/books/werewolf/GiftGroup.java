package name.ipsi.project.fwbp.books.werewolf;

public sealed interface GiftGroup permits BreedGifts, AuspiceGifts, TribeGifts {
    String name();
}
