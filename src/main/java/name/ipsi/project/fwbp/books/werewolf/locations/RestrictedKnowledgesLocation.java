package name.ipsi.project.fwbp.books.werewolf.locations;

import name.ipsi.project.fwbp.books.Content;
import name.ipsi.project.fwbp.books.Paragraph;

public record RestrictedKnowledgesLocation(Paragraph... content) implements Content {
}
