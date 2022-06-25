package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.Paragraph;
import name.ipsi.project.fwbp.books.werewolf.GiftGroup;

import java.util.List;

public record GiftLocations(GiftGroup group, List<Paragraph> locations) {
}
