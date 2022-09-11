package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.TextLocation;
import name.ipsi.project.fwbp.books.werewolf.RiteLevel;
import name.ipsi.project.fwbp.books.werewolf.RiteType;

public record RiteLocation(String name, RiteType type, RiteLevel level, TextLocation... textLocations) {
}
