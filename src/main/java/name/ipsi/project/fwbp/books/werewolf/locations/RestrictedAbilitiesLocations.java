package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.Records;

public record RestrictedAbilitiesLocations(Records.DescriptionLocation descriptionLocation, RestrictedSkillsLocation restrictedSkillsLocation,
                                           RestrictedKnowledgesLocation restrictedKnowledgesLocation) {
}
