package name.ipsi.project.fwbp.foundry.core;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder(alphabetic = true)
public class Compatibility {
    private final String minimum;
    private final String verified;
    private final String maximum;

    public Compatibility(String minimum, String verified, String maximum) {
        this.minimum = minimum;
        this.verified = verified;
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public String getVerified() {
        return verified;
    }

    public String getMaximum() {
        return maximum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compatibility that = (Compatibility) o;
        return Objects.equals(minimum, that.minimum) && Objects.equals(verified, that.verified) && Objects.equals(maximum, that.maximum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimum, verified, maximum);
    }

    @Override
    public String toString() {
        return "Compatibility{" +
                "minimum='" + minimum + '\'' +
                ", verified='" + verified + '\'' +
                ", maximum='" + maximum + '\'' +
                '}';
    }
}
