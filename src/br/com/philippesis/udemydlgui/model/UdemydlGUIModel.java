package br.com.philippesis.udemydlgui.model;

public class UdemydlGUIModel {

    private String mUrl;

    private String mUser;

    private String mPassword;

    private String mDownloadsPath;

    private UdemydlGUIModel(String url, String user, String password, String downloadsPath) {
        this.mUrl = url;
        this.mUser = user;
        this.mPassword = password;
        this.mDownloadsPath = downloadsPath;
    }

    public static class Builder {

        private String url;

        private String user;

        private String password;

        private String downloadsPath;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setDownloadsPath(String downloadsParh) {
            this.downloadsPath = downloadsParh;
            return this;
        }

        public UdemydlGUIModel build() {
            return new UdemydlGUIModel(url, user, password, downloadsPath);
        }

    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmUser() {
        return mUser;
    }

    public String getmPassword() {
        return mPassword;
    }

    public String getmDownloadsPath() {
        return mDownloadsPath;
    }
}
