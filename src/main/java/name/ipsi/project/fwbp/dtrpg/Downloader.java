package name.ipsi.project.fwbp.dtrpg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import name.ipsi.project.fwbp.JsonBodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Downloader {
    public static final Logger log = LoggerFactory.getLogger(Downloader.class);

    public static PdfDocument downloadFile(String fileId, String token) throws Exception {
        log.trace("Downloading File - creating client");
        var client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
        log.trace("Downloading File - client created, building auth request");
        var authTokenRequest = HttpRequest.newBuilder(URI.create("https://www.drivethrurpg.com/api/v1/token"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .header("Authorization", "Bearer " + token)
                .build();
        log.trace("Downloading File - auth request created");
        DtrpgTokenResponse response = client.send(authTokenRequest, new JsonBodyHandler<>(DtrpgTokenResponse.class)).body();
        log.trace("Downloading File - auth request sent, response received");
        var accessToken = response.message().accessToken();

        var body = "products_id=" + fileId + "&bundle_id=0";
        log.trace("Downloading File - creating file download request");
        var fileDownloadRequest = HttpRequest.newBuilder(URI.create("https://www.drivethrurpg.com/api/v1/file_tasks"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        log.trace("Downloading File - created file download request");

        var fileDownloadResponse = client.send(fileDownloadRequest, new JsonBodyHandler<>(DtrpgFileResponse.class)).body();

        log.trace("Downloading File - got file download response");

        var fileContentRequest = HttpRequest.newBuilder(URI.create(fileDownloadResponse.message().downloadUrl()))
                .GET()
                .header("Authorization", "Bearer " + accessToken)
                .build();
        log.trace("Downloading File - opened connection to actual file content");
        try (InputStream inputStream = client.send(fileContentRequest, HttpResponse.BodyHandlers.ofInputStream()).body()) {
            log.trace("Downloading File - returning response");
            return new PdfDocument(new PdfReader(inputStream));
        }
    }

    public record DtrpgTokenMessage(
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("customers_id") String customerId,
            String expires,
            @JsonProperty("server_time") String serverTime,
            String jwt
    ) {
        @JsonCreator
        public DtrpgTokenMessage(
                @JsonProperty("access_token") String accessToken,
                @JsonProperty("customers_id") String customerId,
                @JsonProperty("expires") String expires,
                @JsonProperty("server_time") String serverTime,
                @JsonProperty("jwt") String jwt
        ) {
            this.accessToken = accessToken;
            this.customerId = customerId;
            this.expires = expires;
            this.serverTime = serverTime;
            this.jwt = jwt;
        }
    }

    public record DtrpgTokenResponse(String status, DtrpgTokenMessage message) {
        @JsonCreator
        public DtrpgTokenResponse(
                @JsonProperty("status") String status,
                @JsonProperty("message") DtrpgTokenMessage message
        ) {
            this.status = status;
            this.message = message;
        }

    }

    public record DtrpgFileMessage(@JsonProperty("file_tasks_id") String fileTasksId, String progress,
                                   @JsonProperty("download_url") String downloadUrl) {
        @JsonCreator
        public DtrpgFileMessage(
                @JsonProperty("file_tasks_id") String fileTasksId,
                @JsonProperty("progress") String progress,
                @JsonProperty("download_url") String downloadUrl
        ) {
            this.fileTasksId = fileTasksId;
            this.progress = progress;
            this.downloadUrl = downloadUrl;
        }

    }

    public record DtrpgFileResponse(String status, DtrpgFileMessage message) {
        @JsonCreator
        public DtrpgFileResponse(
                @JsonProperty("status") String status,
                @JsonProperty("message") DtrpgFileMessage message
        ) {
            this.status = status;
            this.message = message;
        }
    }
}
