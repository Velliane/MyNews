
package com.menard.mynews.model.top_stories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ArticleTopStories {

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;


    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<Result> getResults() {
        return results;
    }

    public  void setResults(List<Result> results){
        this.results = results;
    }

}
