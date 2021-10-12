package com.g3.feedbackApp.Services;

import com.g3.feedbackApp.DataSources.Interfaces.IDataSourcePost;
import com.g3.feedbackApp.Services.Interfaces.IPostService;
import org.springframework.stereotype.Component;

@Component
public class PostService implements IPostService {

    private IDataSourcePost datasource;

    public PostService(IDataSourcePost datasource){
        this.datasource = datasource;
    }
}
