package name.ipsi.project.fwbp.dtrpg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import name.ipsi.project.fwbp.JsonBodyHandler;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class Downloader {
    public static PdfDocument downloadFile(String fileId, String token) throws Exception {
        var client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
        var authTokenRequest = HttpRequest.newBuilder(URI.create("https://www.drivethrurpg.com/api/v1/token"))
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .header("Authorization", "Bearer " + token)
                .build();
        DtrpgTokenResponse response = client.send(authTokenRequest, new JsonBodyHandler<>(DtrpgTokenResponse.class)).body();
        var accessToken = response.getMessage().getAccessToken();

        var body = "products_id=" + fileId + "&bundle_id=0";
        var fileDownloadRequest = HttpRequest.newBuilder(URI.create("https://www.drivethrurpg.com/api/v1/file_tasks"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        var fileDownloadResponse = client.send(fileDownloadRequest, new JsonBodyHandler<>(DtrpgFileResponse.class)).body();

        var fileContentRequest = HttpRequest.newBuilder(URI.create(fileDownloadResponse.getMessage().getDownloadUrl()))
                .GET()
                .header("Authorization", "Bearer " + accessToken)
                .build();
        try (InputStream inputStream = client.send(fileContentRequest, HttpResponse.BodyHandlers.ofInputStream()).body()) {
            return new PdfDocument(new PdfReader(inputStream));
        }
    }

    public static class DtrpgTokenMessage {
        private final @JsonProperty("access_token") String accessToken;
        private final @JsonProperty("customers_id") String customerId;
        private final String expires;
        private final @JsonProperty("server_time") String serverTime;
        private final String jwt;

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

        public String getAccessToken() {
            return accessToken;
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getExpires() {
            return expires;
        }

        public String getServerTime() {
            return serverTime;
        }

        public String getJwt() {
            return jwt;
        }
    }

    public static class DtrpgTokenResponse {
        private final String status;
        private final DtrpgTokenMessage message;

        @JsonCreator
        public DtrpgTokenResponse(
                @JsonProperty("status") String status,
                @JsonProperty("message") DtrpgTokenMessage message
        ) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public DtrpgTokenMessage getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (DtrpgTokenResponse) obj;
            return Objects.equals(this.status, that.status) &&
                    Objects.equals(this.message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(status, message);
        }

        @Override
        public String toString() {
            return "DtrpgTokenResponse[" +
                    "status=" + status + ", " +
                    "message=" + message + ']';
        }

    }

    public static class DtrpgFileMessage {
        @JsonProperty("file_tasks_id")
        private final String fileTasksId;
        private final String progress;
        @JsonProperty("download_url")
        private final String downloadUrl;

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

        @JsonProperty("file_tasks_id")
        public String getFileTasksId() {
            return fileTasksId;
        }

        public String getProgress() {
            return progress;
        }

        @JsonProperty("download_url")
        public String getDownloadUrl() {
            return downloadUrl;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (DtrpgFileMessage) obj;
            return Objects.equals(this.fileTasksId, that.fileTasksId) &&
                    Objects.equals(this.progress, that.progress) &&
                    Objects.equals(this.downloadUrl, that.downloadUrl);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fileTasksId, progress, downloadUrl);
        }

        @Override
        public String toString() {
            return "DtrpgFileMessage[" +
                    "fileTasksId=" + fileTasksId + ", " +
                    "progress=" + progress + ", " +
                    "downloadUrl=" + downloadUrl + ']';
        }

    }

    public static class DtrpgFileResponse {
        private final String status;
        private final DtrpgFileMessage message;

        @JsonCreator
        public DtrpgFileResponse(
                @JsonProperty("status") String status,
                @JsonProperty("message") DtrpgFileMessage message
        ) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public DtrpgFileMessage getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (DtrpgFileResponse) obj;
            return Objects.equals(this.status, that.status) &&
                    Objects.equals(this.message, that.message);
        }

        @Override
        public int hashCode() {
            return Objects.hash(status, message);
        }

        @Override
        public String toString() {
            return "DtrpgFileResponse[" +
                    "status=" + status + ", " +
                    "message=" + message + ']';
        }

    }
}
