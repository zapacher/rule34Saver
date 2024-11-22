package ee.rule34;


import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Properties {
    private String saveFolder;
    private int threads;
    private List<String> searchList;
    private String baseUrl;
    private String searchUrl;

    public String getSaveFolder() {
        return saveFolder;
    }

    public int getThreads() {
        return threads;
    }

    public List<String> getSearchList() {
        return searchList;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public Properties() {
        Yaml yaml = new Yaml();
        try (InputStream input = Properties.class.getResourceAsStream("/application.yml")) {
            Map<String, Object> config = yaml.load(input);

            Map<String, Object> application = (Map<String, Object>) config.get("application");

            Map<String, Object> defaultConfig = (Map<String, Object>) application.get("default");
            Map<String, Object> settings = (Map<String, Object>) application.get("settings");

            // Assign values from the nested YAML structure
            this.baseUrl = (String) defaultConfig.get("baseUrl");
            this.searchUrl = (String) defaultConfig.get("searchUrl");
            this.threads = (Integer) settings.get("threads");
            this.saveFolder = (String) settings.get("saveFolder");
            this.searchList = (List<String>) settings.get("searchList");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
