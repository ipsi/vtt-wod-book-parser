package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.TextLocation;
import name.ipsi.project.fwbp.books.werewolf.FetishLevel;
import name.ipsi.project.fwbp.books.werewolf.FetishType;

public record FetishLocation(
        String name,
        FetishType type,
        FetishLevel level,
        int gnosis,
        TextLocation... textLocations
) {

}
