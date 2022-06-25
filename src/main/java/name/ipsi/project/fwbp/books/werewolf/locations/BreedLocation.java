package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.Records;

public record BreedLocation(
        Records.NameLocation nameLocation,
        Records.DescriptionLocation descriptionLocation,
        Records.NicknamesLocation nicknamesLocation,
        InitialGnosisLocation initialGnosisLocation,
        BeginningGiftsLocation beginningGiftsLocation,
        DeformityLocations deformityLocations,
        RestrictedAbilitiesLocations restrictedAbilitiesLocations
) {
}
