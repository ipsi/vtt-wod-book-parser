package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.Records;
import name.ipsi.project.fwbp.books.werewolf.InitialRage;

public record AuspiceLocation(
        Records.NameLocation nameLocation,
        Records.TitleLocation titleLocation,
        Records.DescriptionLocation descriptionLocation,
        InitialRage initialRage,
        BeginningGiftsLocation beginningGiftsLocation,
        Records.StereotypeLocation stereotype,
        Records.QuoteLocation quote
) {
}
