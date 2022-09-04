package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.*;

public record AuspiceLocation(
        NameLocation nameLocation,
        TitleLocation titleLocation,
        DescriptionLocation descriptionLocation,
        InitialRage initialRage,
        BeginningGiftsLocation beginningGiftsLocation,
        StereotypeLocation stereotype,
        QuoteLocation quote
) {
}
