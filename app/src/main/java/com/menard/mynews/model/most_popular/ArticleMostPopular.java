
package com.menard.mynews.model.most_popular;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleMostPopular {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

   public void setResults(List<Result> results) {
        this.results = results;
    }

}
