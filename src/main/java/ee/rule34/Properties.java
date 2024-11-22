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
            this.threads = (Integer) application.get("threads");
            this.saveFolder = (String) application.get("saveFolder");
            this.searchList = (List<String>) application.get("searchList");
            this.baseUrl = (String) application.get("baseUrl");
            this.searchUrl = (String) application.get("searchUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
