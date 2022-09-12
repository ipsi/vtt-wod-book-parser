package name.ipsi.project.fwbp.books.werewolf.locations;

import com.itextpdf.kernel.geom.Rectangle;
import name.ipsi.project.fwbp.books.shared.locations.TextArea;
import name.ipsi.project.fwbp.books.shared.locations.TextLocation;
import name.ipsi.project.fwbp.books.shared.locations.TextLocationType;
import name.ipsi.project.fwbp.books.shared.locations.ThrownWeaponLocation;

import java.util.Arrays;
import java.util.List;

public class ThrownWeaponLocations {
    public static final List<ThrownWeaponLocation> DATA = Arrays.asList(
        new ThrownWeaponLocation(
            new TextLocation(
                    TextLocationType.TABLE,
                    new TextArea(303, new Rectangle(239.221f, 225.36009f, 115.57796f, 13.0f)),
                    new TextArea(303, new Rectangle(154.62f, 212.02211f, 285.1996f, 11.929993f)),
                    new TextArea(303, new Rectangle(154.62f, 198.7321f, 257.44598f, 11.800003f)),
                    new TextArea(303, new Rectangle(154.62f, 185.28209f, 257.44598f, 11.800003f)),
                    new TextArea(303, new Rectangle(154.62f, 171.83209f, 259.62158f, 11.800003f)),
                    new TextArea(303, new Rectangle(154.62f, 158.3821f, 274.8508f, 11.800003f)),
                    new TextArea(303, new Rectangle(154.62f, 144.9321f, 259.62158f, 11.800003f)),
                    new TextArea(303, new Rectangle(154.62f, 131.4821f, 255.26059f, 11.800003f))
            )
        )
    );
}
