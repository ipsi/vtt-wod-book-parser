package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.shared.locations.Content;
import name.ipsi.project.fwbp.books.shared.locations.Paragraph;

public record InitialWillpower(Paragraph... content) implements Content {
}
