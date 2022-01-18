package com.g3.feedbackApp.Models.Converters;

import com.g3.feedbackApp.Models.DTOS.PostDTO;
import com.g3.feedbackApp.Models.DTOS.VersionDTO;
import com.g3.feedbackApp.Models.PostModel;
import com.g3.feedbackApp.Models.VersionModel;

import java.util.List;

public class VersionConverter {

    public VersionModel convertVersionDTOWithoutIdToVersionModel(VersionDTO versionDTO){
        return new VersionModel(versionDTO.getVersionCounter(), versionDTO.getPostId(), versionDTO.getFilePath());
    }

    public VersionDTO convertVersionModelToVersionDTO(VersionModel versionModel){
        VersionDTO dtoToReturn =  new VersionDTO(versionModel.getVersionId(), versionModel.getVersionCounter(), versionModel.getPostId(), versionModel.getFilePath());
        return dtoToReturn;
    }

}