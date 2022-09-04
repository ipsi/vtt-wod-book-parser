package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.DescriptionLocation;
import name.ipsi.project.fwbp.books.shared.locations.NameLocation;
import name.ipsi.project.fwbp.books.shared.locations.NicknamesLocation;

public record BreedLocation(
        NameLocation nameLocation,
        DescriptionLocation descriptionLocation,
        NicknamesLocation nicknamesLocation,
        InitialGnosisLocation initialGnosisLocation,
        BeginningGiftsLocation beginningGiftsLocation,
        DeformityLocations deformityLocations,
        RestrictedAbilitiesLocations restrictedAbilitiesLocations
) {
}
