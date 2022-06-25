package name.ipsi.project.fwbp.foundry;

public record GiftData(
        boolean created,
        boolean iscreated,
        String version,
        String worldAnvil,
        String description,
        PowerTypes type,
        int level,
        String dice1,
        String dice2,
        int bonus,
        String difficulty,
        boolean rollable,
        boolean isRollable,
        boolean active,
        boolean isactive,
        String groupName,
        String system
) implements ItemData {

}