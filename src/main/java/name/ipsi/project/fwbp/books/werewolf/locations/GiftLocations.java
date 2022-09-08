package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.Paragraph;

import java.util.List;

public record GiftLocations(
        GiftGroup group,
        List<Paragraph> locations,
        GiftChart... giftCharts
) {
}
