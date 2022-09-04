package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.werewolf.locations.AuspiceGifts;
import name.ipsi.project.fwbp.books.werewolf.locations.BreedGifts;
import name.ipsi.project.fwbp.books.werewolf.locations.TribeGifts;

public sealed interface GiftGroup permits BreedGifts, AuspiceGifts, TribeGifts {
    String name();
}
