package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.*;
import name.ipsi.project.fwbp.books.werewolf.Tribes;

public record TribeLocation(
        Tribes tribe,
        DescriptionLocation description,
        AppearanceLocation appearance,
        KinfolkTerritoryLocation kinfolkTerritory,
        TribalTotemLocation tribalTotem,
        CharacterCreationLocation characterCreation,
        InitialWillpower initialWillpower,
        BackgroundRestrictionsLocation backgroundRestrictions,
        DerangementLocation derangementLocation,
        BeginningGiftsLocation beginningGifts,
        QuoteLocation quote,
        StereotypesLocation stereotypes
) {

    public TribeLocation(
            Tribes tribe,
            DescriptionLocation description,
            AppearanceLocation appearance,
            KinfolkTerritoryLocation kinfolkTerritory,
            TribalTotemLocation tribalTotem,
            CharacterCreationLocation characterCreation,
            InitialWillpower initialWillpower,
            BeginningGiftsLocation beginningGifts,
            QuoteLocation quote,
            StereotypesLocation stereotypes
    ) {
        this(
                tribe,
                description,
                appearance,
                kinfolkTerritory,
                tribalTotem,
                characterCreation,
                initialWillpower,
                null,
                null,
                beginningGifts,
                quote,
                stereotypes
        );
    }

    public TribeLocation(
            Tribes tribe,
            DescriptionLocation description,
            AppearanceLocation appearance,
            KinfolkTerritoryLocation kinfolkTerritory,
            TribalTotemLocation tribalTotem,
            CharacterCreationLocation characterCreation,
            InitialWillpower initialWillpower,
            BackgroundRestrictionsLocation backgroundRestrictions,
            BeginningGiftsLocation beginningGifts,
            QuoteLocation quote
    ) {
        this(
                tribe,
                description,
                appearance,
                kinfolkTerritory,
                tribalTotem,
                characterCreation,
                initialWillpower,
                backgroundRestrictions,
                null,
                beginningGifts,
                quote,
                null
        );
    }

    public TribeLocation(
            Tribes tribe,
            DescriptionLocation description,
            AppearanceLocation appearance,
            KinfolkTerritoryLocation kinfolkTerritory,
            TribalTotemLocation tribalTotem,
            CharacterCreationLocation characterCreation,
            InitialWillpower initialWillpower,
            BackgroundRestrictionsLocation backgroundRestrictions,
            BeginningGiftsLocation beginningGifts,
            QuoteLocation quote,
            StereotypesLocation stereotypes
    ) {
        this(
                tribe,
                description,
                appearance,
                kinfolkTerritory,
                tribalTotem,
                characterCreation,
                initialWillpower,
                backgroundRestrictions,
                null,
                beginningGifts,
                quote,
                stereotypes
        );
    }

    public TribeLocation(
            Tribes tribe,
            DescriptionLocation description,
            AppearanceLocation appearance,
            KinfolkTerritoryLocation kinfolkTerritory,
            TribalTotemLocation tribalTotem,
            InitialWillpower initialWillpower,
            BackgroundRestrictionsLocation backgroundRestrictions,
            DerangementLocation derangementLocation,
            BeginningGiftsLocation beginningGifts,
            QuoteLocation quote
    ) {
        this(
                tribe,
                description,
                appearance,
                kinfolkTerritory,
                tribalTotem,
                null,
                initialWillpower,
                backgroundRestrictions,
                derangementLocation,
                beginningGifts,
                quote,
                null
        );
    }
}
