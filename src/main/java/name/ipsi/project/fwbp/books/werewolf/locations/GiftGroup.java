package name.ipsi.project.fwbp.books.werewolf.locations;

public sealed interface GiftGroup permits BreedGifts, AuspiceGifts, TribeGifts {
    String name();
    String journalIdName();
    String pageIdGroup();
}
