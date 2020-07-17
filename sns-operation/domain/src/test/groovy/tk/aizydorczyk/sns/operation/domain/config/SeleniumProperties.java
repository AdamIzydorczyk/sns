package tk.aizydorczyk.sns.operation.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "selenium", ignoreUnknownFields = false)
public class SeleniumProperties {
    private String browser;
    private String applicationUrl;
    private String defaultUserName;
    private String defaultPassword;
    private int defaultWaitingTime;

    public String getDefaultUserName() {
        return defaultUserName;
    }

    public void setDefaultUserName(String defaultUserName) {
        this.defaultUserName = defaultUserName;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public int getDefaultWaitingTime() {
        return defaultWaitingTime;
    }

    public void setDefaultWaitingTime(int defaultWaitingTime) {
        this.defaultWaitingTime = defaultWaitingTime;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
