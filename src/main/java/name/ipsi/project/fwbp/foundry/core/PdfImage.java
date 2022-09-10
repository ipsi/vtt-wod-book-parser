package name.ipsi.project.fwbp.foundry.core;

public record PdfImage(String name, ImageType type, byte[] data) {
    public static PdfImage createCoverImage(byte[] data) {
        return new PdfImage("adventure-cover.jpeg", ImageType.COVER, data);
    }
}
