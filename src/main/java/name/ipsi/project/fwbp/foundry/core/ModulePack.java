package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public record ModulePack(String name, String label, String path, String system, DocumentTypes type) {

}
